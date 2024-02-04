package site.gongtong.map.service;

import org.springframework.stereotype.Service;
import site.gongtong.map.model.CafeMap;
import site.gongtong.map.model.MapDto;
import site.gongtong.map.repository.MapRepository;

import java.util.List;

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
            for (int page = 1; page <= 3; page++) {
                List<MapDto> cafes = apiService.getCafeData(brand, page);
                for (MapDto mapDto : cafes) {
                    CafeMap cafeMap = CafeMap.fromMapDto(mapDto);
                    mapRepository.save(cafeMap);
                }
            }
        }
    }
}


