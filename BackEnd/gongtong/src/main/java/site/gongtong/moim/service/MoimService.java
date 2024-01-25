package site.gongtong.moim.service;

import site.gongtong.moim.model.Moim;

import java.util.List;

public interface MoimService {
    List<Moim> getMoimList(String location);

    List<Moim> getDeadlineList();

    Integer checkRoom(int userNum);

    void createRoom(Moim moim, int userNum);
}
