package site.gongtong.map.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CafeMap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String brand;       // 레드버튼, 히어로, 홈즈앤루팡

    private String branch;      // 강남점, **점, ...

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
                .latitude(mapDto.getY())
                .longitude(mapDto.getX())
                .image(mapDto.getPlace_url())
                .build();
    }
}

