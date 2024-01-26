package site.gongtong.moim.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import site.gongtong.moim.model.Moim;

import java.util.List;

@Repository
public interface MoimRepository extends JpaRepository<Moim, Integer> {
    List<Moim> getMoimsByLocation(String location);
    //@Query("SELECT m FROM Moim m WHERE (SELECT COUNT(mm) FROM MoimMember mm WHERE mm.moim = m) = m.number - 1")
    @Query(value = "SELECT * FROM moim m WHERE (SELECT COUNT(*) FROM moim_member mm WHERE mm.moim_id = m.id) = m.number - 1", nativeQuery = true)
    List<Moim> getMoimsDeadLine();

}
