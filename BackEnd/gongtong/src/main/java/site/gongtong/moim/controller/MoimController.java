package site.gongtong.moim.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.gongtong.moim.model.JoinCondition;
import site.gongtong.moim.model.Moim;
import site.gongtong.moim.model.MoimCondition;
import site.gongtong.moim.model.MoimGroup;
import site.gongtong.moim.service.MoimService;
import site.gongtong.security.jwt.TokenUtils;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/moim")
@Slf4j
public class MoimController {
    private final MoimService moimService;

    @GetMapping("/list")
    public ResponseEntity<List<Moim>> getSortedList(@RequestParam(name = "location") String location, @RequestParam(name = "sort") int sorting) {
        log.info("리스트 정렬 들어옴!!!");

        List<Moim> sortedMoimList = moimService.getSortedMoimList(location, sorting);

        return new ResponseEntity<>(sortedMoimList, HttpStatus.OK);
    }

    @GetMapping("/deadline")
    public ResponseEntity<List<Moim>> getDeadlineList() {
        log.info("마감임박 들어옴!!");
        return new ResponseEntity<>(moimService.getDeadlineList(), HttpStatus.OK);
    }

    // 조건
    // 1. 참여한 진행 중인 모임이 있으면 못 만들게 한다.
    @GetMapping("/checkroom")
    public ResponseEntity<Integer> checkRoom(HttpServletRequest request) {
        log.info("진행중인 방 체크");
        String memberId = TokenUtils.getUserIdFromToken(TokenUtils.fetchToken(request));

        return new ResponseEntity<>(moimService.checkRoom(memberId), HttpStatus.OK);
    }

    @PostMapping("/room")
    public ResponseEntity<Integer> createRoom(@RequestBody MoimCondition moimCondition, HttpServletRequest request) {
        log.info("방 만들기");
        log.info(moimCondition.toString());
        String memberId = TokenUtils.getUserIdFromToken(TokenUtils.fetchToken(request));

        Moim moim = Moim.builder()
                .title(moimCondition.getTitle())
                .content(moimCondition.getContent())
                .number(moimCondition.getNumber())
                .location(moimCondition.getLocation())
                .datetime(moimCondition.getDatetime())
                .status('P')
                .currentNumber(1)
                .build();

        int result = moimService.createRoom(moim, memberId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/join")
    public ResponseEntity<Integer> joinMoim(@RequestBody JoinCondition joinCondition, HttpServletRequest request) {
        log.info("방에 참여하기");
        String memberId = TokenUtils.getUserIdFromToken(TokenUtils.fetchToken(request));

        Integer result = moimService.joinRoom(joinCondition.getMoimId(), memberId);

        // 이미 꽉 찬 방이면 result가 false, 아니면 result true
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/friend")
    public ResponseEntity<Integer> inviteFriend(HttpServletRequest request, @RequestParam(name = "f_id") String friendId, @RequestParam(name = "moim_id") int moimId) {
        log.info("친구 초대하기");
        String memberId = TokenUtils.getUserIdFromToken(TokenUtils.fetchToken(request));
        // 내 아이디랑 친구 아이디 받아서 내 모임에 친구 추가하기

        int result = moimService.inviteFriend(memberId, friendId, moimId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

//    @GetMapping("/mymoimlist")
//    public ResponseEntity<List<Moim>> getMyMoimList(HttpServletRequest request) {
//        log.info("내 모임 이력 리스트 출력!!");
//        String memberId = TokenUtils.getUserIdFromToken(TokenUtils.fetchToken(request));
//
//        List<Moim> myMoimList = moimService.getMyMoimList(memberId);
//
//        return new ResponseEntity<>(myMoimList, HttpStatus.OK);
//    }

    @GetMapping("/mymoimlist")
    public ResponseEntity<List<MoimGroup>> getMyMoimList(HttpServletRequest request) {
        log.info("내 모임 이력 리스트 출력!!");
        String memberId = TokenUtils.getUserIdFromToken(TokenUtils.fetchToken(request));

        List<MoimGroup> myMoimList = moimService.getMyMoimGroupList(memberId);

        return new ResponseEntity<>(myMoimList, HttpStatus.OK);
    }

    @GetMapping("/mymoim")
    public ResponseEntity<Moim> getMyMoim(HttpServletRequest request) {
        log.info("현재 내가 참여중인 모임 들어옴!!!");
        String memberId = TokenUtils.getUserIdFromToken(TokenUtils.fetchToken(request));

        Moim myMoim = moimService.getMyMoim(memberId);

        return new ResponseEntity<>(myMoim, HttpStatus.OK);
    }

    @DeleteMapping("/exit")
    public ResponseEntity<Long> exitMoim(HttpServletRequest request, @RequestParam("moim_id") int moimId) {
        log.info("모임 나가기 들어옴!!!");

        String memberId = TokenUtils.getUserIdFromToken(TokenUtils.fetchToken(request));

        long result = moimService.exitRoom(memberId, moimId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
