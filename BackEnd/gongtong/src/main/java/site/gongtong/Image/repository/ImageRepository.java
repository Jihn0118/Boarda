package site.gongtong.Image.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.gongtong.Image.model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {

}
