package site.gongtong.cafe.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.gongtong.cafe.model.Cafe;
import site.gongtong.cafe.service.CafeService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cafe")
@Slf4j
public class CafeController {
    private final CafeService cafeService;

    @GetMapping("/list")
    public ResponseEntity<List<Cafe>> getCafeList(@RequestParam(name="location", defaultValue = "") String location,
                                                  @RequestParam(name="brand", defaultValue = "") String brand){
        List<Cafe> cafeList = cafeService.getCafeList(location, brand);

        return new ResponseEntity<>(cafeList, HttpStatus.OK);
    }

    @GetMapping("/detail")
    public ResponseEntity<Cafe> getCafeInfo(@RequestParam(name="cafe_id", defaultValue = "0") Integer cafeId) {
        log.info("보드게임 상세정보 들어옴!");

        Cafe cafe = cafeService.getCafeInfo(cafeId);

        if(cafe != null){
            return new ResponseEntity<>(cafe, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

}
