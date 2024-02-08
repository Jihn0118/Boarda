package site.gongtong.map.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MapDto {
    private String address_name;
    private String phone;
    private String place_name;
    private String place_url;
    private String x;
    private String y;

    // place_name에서 브랜드와 지점명을 분리하는 메소드
    public String getBrand() {
        // 브랜드와 지점명이 공백으로 구분된다고 가정하고 구현.
        return place_name.split(" ")[0];
    }

    public String getBranch() {
        return place_name;
    }
}


