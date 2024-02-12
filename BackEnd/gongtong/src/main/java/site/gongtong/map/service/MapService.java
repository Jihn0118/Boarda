package site.gongtong.map.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import site.gongtong.cafe.model.Cafe;
import site.gongtong.map.model.MapDto;
import site.gongtong.map.repository.MapRepository;

import java.util.List;

@Service
public class MapService {
    private final MapRepository mapRepository;
    private final MapApiService apiService;
    private final String[] brands = {"레드버튼", "홈즈앤루팡", "히어로보드"};

    private static final Logger logger = LoggerFactory.getLogger(MapService.class);

    public MapService(MapRepository mapRepository, MapApiService apiService) {
        this.mapRepository = mapRepository;
        this.apiService = apiService;
    }

    public void getCafeDataAndSave() {
        for (String brand : brands) {
            for (int page = 1; page <= 45; page++) {
                try {
                    List<MapDto> cafes = apiService.getCafeData(brand, page);
                    for (MapDto mapDto : cafes) {
                        // DB에 이미 동일한 branch를 가진 데이터가 있는지 확인
                        if (mapRepository.existsByBranch(mapDto.getBranch())) {
                            logger.info("Branch " + mapDto.getBranch() + " already exists in the database.");
                            continue;
                        }

                        Cafe cafeMap = Cafe.fromMapDto(mapDto);
                        mapRepository.save(cafeMap);
                        logger.info("Saved data: " + cafeMap);
                    }
                } catch (Exception e) {
                    logger.error("API 호출에서 오류가 발생했습니다: " + e.getMessage());
                }
            }
        }
    }


}


