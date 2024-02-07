package site.gongtong.map.service;

import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import site.gongtong.map.model.CafeMap;
import site.gongtong.map.model.MapDto;
import site.gongtong.map.repository.MapRepository;

import java.util.List;

//@Service
//public class MapService {
//    private final MapRepository mapRepository;
//    private final MapApiService apiService;
//    private final String[] brands = {"레드버튼", "홈즈앤루팡", "히어로 보드게임"};
//
//    public MapService(MapRepository mapRepository, MapApiService apiService) {
//        this.mapRepository = mapRepository;
//        this.apiService = apiService;
//    }
//
//    public void getCafeDataAndSave() {
//        for (String brand : brands) {
//            for (int page = 1; page <= 15; page++) {
//                List<MapDto> cafes = apiService.getCafeData(brand, page);
//                for (MapDto mapDto : cafes) {
//                    CafeMap cafeMap = CafeMap.fromMapDto(mapDto);
//                    mapRepository.save(cafeMap);
//                }
//            }
//        }
//    }
//}

@Service
public class MapService {
    private final MapRepository mapRepository;
    private final MapApiService apiService;
    private final String[] brands = {"레드버튼", "홈즈앤루팡", "히어로 보드게임"};

    public MapService(MapRepository mapRepository, MapApiService apiService) {
        this.mapRepository = mapRepository;
        this.apiService = apiService;
    }

    public void getCafeDataAndSave() {
        for (String brand : brands) {
            Pair<List<MapDto>, Integer> response = apiService.getCafeData(brand, 1);
            int total_count = response.getSecond();
            int total_page = (total_count + 14) / 15; // 카카오 API는 한 페이지에 최대 15개의 결과를 반환합니다.

            for (int page = 1; page <= total_page; page++) {
                List<MapDto> cafes = apiService.getCafeData(brand, page).getFirst();
                for (MapDto mapDto : cafes) {
                    CafeMap cafeMap = CafeMap.fromMapDto(mapDto);
                    mapRepository.save(cafeMap);

                    // DB에 잘 저장되었는지 확인
                    CafeMap savedCafeMap = mapRepository.findByBrandAndBranch(cafeMap.getBrand(), cafeMap.getBranch());
                    if (savedCafeMap == null) {
                        System.out.println("Failed to save: " + cafeMap.getBrand() + " " + cafeMap.getBranch());
                    }
                }
            }
        }
    }
}

