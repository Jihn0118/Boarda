package site.gongtong.map.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import site.gongtong.cafe.model.Cafe;
import site.gongtong.map.repository.MapRepository;

import java.util.List;

@RestController
public class MapController {

    private final MapRepository mapRepository;

    @Autowired
    public MapController(MapRepository mapRepository) {
        this.mapRepository = mapRepository;
    }

    @GetMapping
    public List<Cafe> getCafeByLocationAndBrand(@RequestParam("location") String location, @RequestParam("brand") String brand) {
        if(brand.equals("상관없음")) {
            return mapRepository.findByLocationContaining(location);
        }
        return mapRepository.findByLocationContainingAndBrand(location, brand);
    }
}
