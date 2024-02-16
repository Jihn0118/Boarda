package site.gongtong.moim.service;

import site.gongtong.moim.model.Moim;
import site.gongtong.moim.model.MoimGroup;

import java.util.List;

public interface MoimService {

    List<Moim> getSortedMoimList(String location, int sorting);

    List<Moim> getDeadlineList();

    Integer checkRoom(String memberId);

    Integer createRoom(Moim moim, String memberId);

    Integer joinRoom(int moimId, String memberId);

    List<Moim> getMyMoimList(String memberId);

    Moim getMyMoim(String memberId);

    int inviteFriend(String memberId, String friendId, int moimId);

    long exitRoom(String memberId, int moimId);

    List<MoimGroup> getMyMoimGroupList(String memberId);
}
