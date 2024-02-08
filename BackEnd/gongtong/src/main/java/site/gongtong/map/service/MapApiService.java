package site.gongtong.map.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import site.gongtong.map.model.MapDto;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//@Service
//public class MapApiService {
//
//    private final RestTemplate restTemplate;
//    private final String API_KEY = "KAKAO_API_KEY"; // 카카오 API 키 입력해주기~
//
//    public MapApiService(RestTemplateBuilder restTemplateBuilder) {
//        this.restTemplate = restTemplateBuilder.build();
//    }
//
//    public List<MapDto> getCafeData(String brand, int page) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Authorization", "KakaoAK " + API_KEY);
//        HttpEntity<String> entity = new HttpEntity<>(headers);
//        String url = "https://dapi.kakao.com/v2/local/search/keyword.json?query=서울+" + brand + "&category_group_code=CE7&page=" + page;
//
//        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);
//        List<MapDto> cafes = (List<MapDto>) response.getBody().get("documents");
//
//        return cafes;
//    }
//}


@Service
public class MapApiService {

    private final RestTemplate restTemplate;
    private final String API_KEY = "b4ec8b14bab859d5b3e6789c5103b72b"; // 카카오 API 키 입력해주기~

    public MapApiService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public List<MapDto> getCafeData(String brand, int page) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + API_KEY);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        String url = "https://dapi.kakao.com/v2/local/search/keyword.json?query=서울+" + brand + "&category_group_code=CE7&page=" + page;

        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);
        List<Map<String, String>> documents = (List<Map<String, String>>) response.getBody().get("documents");

        if (documents == null) {
            throw new RuntimeException("API 호출 결과가 예상과 다릅니다.");
        }

        return documents.stream()
                .map(this::convertToMapDto)
                .collect(Collectors.toList());
    }

    private MapDto convertToMapDto(Map<String, String> document) {
        MapDto mapDto = new MapDto();
        mapDto.setAddress_name(document.get("address_name"));
        mapDto.setPhone(document.get("phone"));
        mapDto.setPlace_name(document.get("place_name"));
        mapDto.setPlace_url(document.get("place_url"));
        mapDto.setX(document.get("x"));
        mapDto.setY(document.get("y"));
        System.out.println(mapDto.getAddress_name());
        System.out.println(mapDto.getPhone());
        System.out.println(mapDto.getPlace_name());
        System.out.println(mapDto.getPlace_url());
        System.out.println(mapDto.getX());
        System.out.println(mapDto.getY());
        return mapDto;
    }


}



