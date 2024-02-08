package site.gongtong.map.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import site.gongtong.map.model.MapDto;

import java.util.List;
import java.util.Map;

@Service
public class MapApiService {

    private final RestTemplate restTemplate;
    private final String API_KEY = "KAKAO_API_KEY"; // 카카오 API 키 입력해주기~

    public MapApiService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public List<MapDto> getCafeData(String brand, int page) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + API_KEY);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        String url = "https://dapi.kakao.com/v2/local/search/keyword.json?query=서울+" + brand + "&category_group_code=CE7&page=" + page;

        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);
        List<MapDto> cafes = (List<MapDto>) response.getBody().get("documents");

        return cafes;
    }
}


