package site.gongtong.moim.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.gongtong.moim.model.Moim;
import site.gongtong.moim.service.MoimService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/moim")
public class MoimController {
    private final MoimService moimService;

    // 지역명 받고 해당 지역이 있는 모임 방 리스트를 리턴
    @GetMapping("/choice")
    public ResponseEntity<List<Moim>> getMoimList(@RequestParam String location) {
        System.out.println("초이스 들어옴!!");
        return new ResponseEntity<List<Moim>>(moimService.getMoimList(location), HttpStatus.OK);
    }

    @GetMapping("/deadline")
    public ResponseEntity<List<Moim>> getDeadlineList(){
        System.out.println("마감임박 들어옴!!");
        return new ResponseEntity<List<Moim>>(moimService.getDeadlineList(), HttpStatus.OK);
    }

//    @PostMapping("/room")
//    public ResponseEntity<> createRoom(@RequestBody Moim moim){
//
//    }



}
