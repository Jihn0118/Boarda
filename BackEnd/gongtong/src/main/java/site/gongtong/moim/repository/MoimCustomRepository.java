package site.gongtong.moim.repository;

import site.gongtong.moim.model.Moim;

import java.util.List;

public interface MoimCustomRepository {

    Moim findById(int moimId);
    
    //getMoimsDeadLine() 메서드임
    List<Moim> findAllDeadLine();

    List<Moim> findByLocationAndStatusOrderByIdDesc(String location, Character status);

    List<Moim> findByLocationAndStatusOrderByDatetime(String location, Character status);

    List<Moim> getMoimWithMemberCountOrder();
}
