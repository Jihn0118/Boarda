package site.gongtong.moim.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.gongtong.moim.model.Moim;
import site.gongtong.moim.model.MoimConditionDto;
import site.gongtong.moim.model.MoimJoin;
import site.gongtong.moim.service.MoimService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/moim")
@Slf4j
public class MoimController {
    private final MoimService moimService;

    // 지역명 받고 해당 지역이 있는 모임 방 리스트를 리턴
    @GetMapping("/choice")
    public ResponseEntity<List<Moim>> getMoimList(@RequestParam(name="location") String location) {
        log.info("초이스 들어옴!!");
        return new ResponseEntity<List<Moim>>(moimService.getMoimList(location), HttpStatus.OK);
    }

    @GetMapping("/deadline")
    public ResponseEntity<List<Moim>> getDeadlineList(){
        log.info("마감임박 들어옴!!");
        return new ResponseEntity<List<Moim>>(moimService.getDeadlineList(), HttpStatus.OK);
    }

    // 조건
    // 1. 참여한 진행 중인 모임이 있으면 못 만들게 한다.
    @GetMapping("/checkroom")
    public ResponseEntity<Integer> checkRoom(@RequestParam(name="num") int userNum){
        log.info("진행중인 방 체크");

        return new ResponseEntity<Integer>(moimService.checkRoom(userNum), HttpStatus.OK);
    }

    @PostMapping("/room")
    public ResponseEntity<Void> createRoom(@RequestBody MoimConditionDto moimConditionDto){
        log.info("방 만들기");
        log.info(moimConditionDto.toString());
        int userNum = moimConditionDto.getUserId();

        Moim moim = Moim.builder()
                .title(moimConditionDto.getTitle())
                .content(moimConditionDto.getContent())
                .number(moimConditionDto.getNumber())
                .location(moimConditionDto.getLocation())
                .datetime(moimConditionDto.getDatetime())
                .status('P')
                .build();

        moimService.createRoom(moim, userNum);
        // 모임 컨디션 객체를 열어서 user_num 이랑 모임 정보를 가지고 모임방 만들고, 모임멤버 만들어서 연결
        return ResponseEntity.ok().build();
    }

    @PostMapping("/join")
    public ResponseEntity<Void> joinMoim(@RequestBody MoimJoin moimJoin){
        log.info("방에 참여하기");
        // 멤버 아이디랑 모임 아이디 받아서 
        // 모임 멤버 객체 만들기
        return ResponseEntity.ok().build();
    }

    @GetMapping("/friend")
    public ResponseEntity<Void> inviteFriend(@RequestParam(name="my_id") int myId, @RequestParam(name="f_id") int friendId, @RequestParam(name="moim_id") int moimId){
        log.info("친구 초대하기");
        // 내 아이디랑 친구 아이디 받아서 내 모임에 친구 추가하기
        return ResponseEntity.ok().build();
    }
}
