package site.gongtong.map.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import site.gongtong.cafe.model.Cafe;

@Entity(name = "cafe")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CafeMap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String brand;       // 브랜드 이름
    private String branch;      // 지점 이름
    private String location;    // 카페 주소
    private String contact;     // 전화번호
    private Float rate;         // 평점
    private String latitude;    // 위도
    private String longitude;   // 경도
    private String image;       // 카페 이미지

    // MapDto로부터 CafeMap 객체를 생성하는 메소드
    public static CafeMap fromMapDto(MapDto mapDto) {
        return CafeMap.builder()
                .brand(mapDto.getBrand())
                .branch(mapDto.getBranch())
                .location(mapDto.getAddress_name())
                .contact(mapDto.getPhone())
                .latitude(mapDto.getX())
                .longitude(mapDto.getY())
                .image(mapDto.getPlace_url())
                .build();
    }

}

