package site.gongtong.alarm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.gongtong.alarm.model.Alarm;

@Repository
public interface AlarmRepository extends JpaRepository<Alarm, Integer> {
}
