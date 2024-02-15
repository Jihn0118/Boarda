package site.gongtong.moim.repository;

import site.gongtong.moim.model.Moim;

import java.util.List;

public interface MoimCustomRepository {

    Moim findById(int moimId);

    //getMoimsDeadLine() 메서드임
    List<Moim> findAllDeadLine();

    List<Moim> findByLocationAndStatusOrderByIdDesc(String location);

    List<Moim> findByLocationAndStatusOrderByDatetime(String location);

    List<Moim> findByLocationAndStatusOrderByCount(String location);

    List<Moim> findMoimListByMemberId(String memberId);

    Moim findMoimByMemberId(String memberId);

    long minusCurrentNumber(String memberId, int moimId, int num);

    long deleteMoim(int moimId);
}
