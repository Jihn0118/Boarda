package WebCrawling;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class WebCrawlerApplication {

    //서버 DB
    //서버 DB
    @Value("${spring.datasource.driver-class-name}")
    static String JDBC_DRIVER;
    @Value("${spring.datasource.url}")
    static String DB_URL;
    @Value("${spring.datasource.username}")
    static String USERNAME;
    @Value("${spring.datasource.password}")
    static String PASSWORD;

    public static void main(String[] args) {
        SpringApplication.run(WebCrawlerApplication.class, args);

        // ChromeDriver 경로 설정
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\SSAFY\\Desktop\\back-demo\\demo\\src\\main\\resources\\chromedriver.exe");

        // ChromeDriver 인스턴스 생성
        WebDriver driver = new ChromeDriver();

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // JDBC 드라이버 로드
            Class.forName(JDBC_DRIVER);

            // 데이터베이스 연결
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            // SQL 문장을 준비
            String sql = "INSERT INTO boardgame (title, min_num, max_num, time, year, age, difficulty, image) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);

            for (int page = 65; page <= 178; page++) {
                driver.get("https://boardlife.co.kr/rank.php?pg=" + page);

                // 웹페이지 로딩 기다리는 시간 설정
                driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

                // 게임 리스트에서 각 게임의 상세 페이지로 이동하는 코드
                List<WebElement> games = driver.findElements(By.cssSelector(".game-rank-div >  a"));
                List<String> gameUrls = new ArrayList<>();

                for (WebElement game : games) {
                    gameUrls.add(game.getAttribute("href"));
                }

                for (String gameUrl : gameUrls) {
                    driver.get(gameUrl);

                    WebElement gameTitle = driver.findElement(By.cssSelector("#boardgame-title")); //제목

                    // 데이터베이스에 동일한 title이 이미 있는지 검사
                    String checkSql = "SELECT COUNT(*) FROM boardgame WHERE title = ?";
                    PreparedStatement checkPstmt = conn.prepareStatement(checkSql);
                    checkPstmt.setString(1, gameTitle.getText());
                    ResultSet rs = checkPstmt.executeQuery();

                    if (rs.next()) {
                        int count = rs.getInt(1);

                        // 'title'이 이미 존재하면 데이터를 수집하지 않고 다음 반복으로 넘어감
                        if (count > 0) {
                            continue;
                        }
                    }

                    WebElement players = driver.findElement(By.cssSelector(".data-value.data-member")); //인원
                    String playerRange = players.getText();
                    playerRange = playerRange.replace("명", ""); // '명'을 제거
                    int min_num, max_num; // 최소 인원, 최대 인원

                    // "1-4"와 같은 형태의 경우
                    if (playerRange.contains("-")) {
                        String[] splitRange = playerRange.split("-"); // '-'를 기준으로 문자열을 분리
                        try {
                            min_num = Integer.parseInt(splitRange[0].trim()); // 최소인원
                            max_num = Integer.parseInt(splitRange[1].trim()); // 최대인원
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid player format: " + playerRange);
                            continue; // skip this record
                        }
                    } else {
                        try {
                            min_num = max_num = Integer.parseInt(playerRange);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid player format: " + playerRange);
                            continue; // skip this record
                        }
                    }

                    WebElement playTimeElement = driver.findElement(By.cssSelector(".data-value.data-time")); //플레이시간
                    String playTimeRange = playTimeElement.getText().replace("분", ""); // '분'을 제거

                    int time; // 플레이 시간

                    // "40-50"와 같은 형태의 경우
                    if (playTimeRange.contains("-")) {
                        String[] splitPlayTime = playTimeRange.split("-"); // '-'를 기준으로 문자열을 분리
                        try {
                            int minPlayTime = Integer.parseInt(splitPlayTime[0].trim()); // 최소 플레이 시간
                            int maxPlayTime = Integer.parseInt(splitPlayTime[1].trim()); // 최대 플레이 시간
                            time = (minPlayTime + maxPlayTime) / 2;
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid play time format: " + playTimeRange);
                            continue; // skip this record
                        }
                    } else {
                        try {
                            time = Integer.parseInt(playTimeRange.trim());
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid play time format: " + playTimeRange);
                            continue; // skip this record
                        }
                    }

//                    WebElement releaseYearElement = driver.findElement(By.cssSelector("#game-rate-year")); //출판년도
//                    int year = Integer.parseInt(releaseYearElement.getText().replace("(", "").replace(")", "").trim()); // 괄호와 공백을 제거
//                    java.sql.Date year = java.sql.Date.valueOf(releaseYearElement.getText().replace("(", "").replace(")", "").trim() + "-01-01"); // 괄호와 공백을 제거
//                    pstmt.setDate(5, year);
//                    int year = Integer.parseInt(releaseYearElement.getText().replace("(", "").replace(")", "").trim()); // 괄호와 공백을 제거
                    WebElement releaseYearElement = driver.findElement(By.cssSelector("#game-rate-year")); //출판년도
                    String yearStr = releaseYearElement.getText().replace("(", "").replace(")", "").trim();
                    int year;

                    if (yearStr.equals("─")) {
                        year = 0; // no year information, set to 0
                    } else {
                        try {
                            year = Integer.parseInt(yearStr); // 괄호와 공백을 제거
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid year format: " + yearStr);
                            continue; // skip this record
                        }
                    }


                    WebElement ageUseElement = driver.findElement(By.cssSelector(".data-value.data-old")); //사용연령

                    int age;
                    try {
                        age = Integer.parseInt(ageUseElement.getText().replace("세 이상", "").trim());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid age format: " + ageUseElement.getText().replace("세 이상", "").trim());
                        continue; // skip this record
                    }


                    WebElement difficultyElement = driver.findElement(By.cssSelector("#game-rate")); //난이도
                    float difficulty = Float.parseFloat(difficultyElement.getText().trim()); // 문자열을 float 형태로 변환

                    WebElement gameImage = driver.findElement(By.xpath("//*[@id=\"div-wrapper\"]/div[2]/div[1]/div[3]/div[2]/div[2]/div/div[1]/a/img")); //이미지
                    String image = gameImage.getAttribute("src");

                    // 데이터 출력
                    System.out.println("게임 제목: " + gameTitle.getText());
                    System.out.println("플레이 시간: " + time + "분");
                    System.out.println("사용 연령: " + age + "세 이상");
                    System.out.println("난이도: " + difficulty);
                    System.out.println("출판년도: " + year);
                    System.out.println("최소 인원: " + min_num);
                    System.out.println("최대 인원: " + max_num);
                    System.out.println("게임 이미지: " + image);

                    // 데이터베이스에 데이터 저장
                    pstmt.setString(1, gameTitle.getText());
                    pstmt.setInt(2, min_num);
                    pstmt.setInt(3, max_num);
                    pstmt.setInt(4, time);
                    pstmt.setInt(5, year);
                    pstmt.setInt(6, age);
                    pstmt.setFloat(7, difficulty);
                    pstmt.setString(8, image);
                    pstmt.executeUpdate();

                    // 뒤로 가기
                    driver.navigate().back();
                    // 페이지 로딩 기다리기
                    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                }
            }

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        // WebDriver 종료
        driver.quit();
    }
}
