package site.gongtong.map.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import site.gongtong.cafe.model.Cafe;
import site.gongtong.map.repository.MapRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MapController {

    private final MapRepository mapRepository;

    @GetMapping
    public List<Cafe> getCafeByLocationAndBrand(@RequestParam("location") String location, @RequestParam("brand") String brand) {
        if (brand.equals("상관없음")) {
            return mapRepository.findByLocationContaining(location);
        }
        return mapRepository.findByLocationContainingAndBrand(location, brand);
    }
}
