package site.gongtong.moim.service;

import site.gongtong.moim.model.Moim;

import java.util.List;

public interface MoimService {

    List<Moim> getSortedMoimList(String location, int sorting);

    List<Moim> getDeadlineList();

    Integer checkRoom(int userNum);

    Integer createRoom(Moim moim, int userNum);

    Integer joinRoom(int moimId, String memberId);

    List<Moim> getMyMoimList(int userNum);

    Moim getMyMoim(int userNum);
}
