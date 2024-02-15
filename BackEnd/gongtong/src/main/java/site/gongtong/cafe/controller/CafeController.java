package site.gongtong.cafe.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import site.gongtong.cafe.model.Cafe;
import site.gongtong.cafe.model.CafeDetailDto;
import site.gongtong.cafe.service.CafeService;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/cafe")
@Slf4j
public class CafeController {
    private final CafeService cafeService;

    @GetMapping("/list")
    public ResponseEntity<List<Cafe>> getCafeList(@RequestParam(name = "location", defaultValue = "") String location,
                                                  @RequestParam(name = "brand", defaultValue = "") String brand) {
        List<Cafe> cafeList = cafeService.getCafeList(location, brand);

        return new ResponseEntity<>(cafeList, HttpStatus.OK);
    }

    @GetMapping("/detail")
    public ResponseEntity<CafeDetailDto> getCafeInfo(@RequestParam(name = "cafe_id", defaultValue = "0") Integer cafeId) {
        log.info("보드게임 상세정보 들어옴!");

        CafeDetailDto cafeDetailDto = cafeService.getCafeInfo(cafeId);

        if (cafeDetailDto != null) {
            return new ResponseEntity<>(cafeDetailDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

}
