import React, { useState, useEffect } from "react";
import { Link, Route, useParams } from "react-router-dom";
import axios from "axios";

const { kakao } = window;

const Rest_api_key_kakao = import.meta.env.VITE_KAKAO_API_KEY

// 백에서 가져온 데이터로 변경해야함!!!
const positions = [
  {
      title: '레드버튼 강남점', 
      latlng: new kakao.maps.LatLng(37.501931286572834, 127.0264523435014)
  },
  {
      title: '레드버튼 강남2호점', 
      latlng: new kakao.maps.LatLng(37.4998154950733, 127.027458092239)
  },
];
// 마커 이미지 주소(추후 디자인 변경 필요)
var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";

const Arcade = () => {
  const [Location, setLocation] = useState("");
  const [Brand, setBrand] = useState("");

  const onLocationHandler = (event) => {
    setLocation(event.currentTarget.value);
  };
  const onBrandHandler = (event) => {
    setBrand(event.currentTarget.value);
  };

  const onSubmitHandler = (event) => {
    event.preventDefault();
    console.log("Location: ", Location);
    console.log("Brand: ", Brand);

    let body = {
      brand: Brand,
      location: Location,
    };

    // const request_MapInfo = () => {
    //   axios({
    //     method: "get",
    //     url: "https://dapi.kakao.com/v2/local/search/keyword.json",
    //     headers: {
    //       "Authorization": `KakaoAK ${Rest_api_key_kakao}`,
    //     },
    //     params: {
    //       "query" : "강남역 레드버튼",
    //       "category_group_code" : "CE7", // category_group_code는 카페
    //       "page": 1, // page는 최대 3, 응답의 response.Meta['is_end']로 마지막 페이지인지 요청하고 다음 페이지 요청 필요 or response.Meta['pagable_count']로...
    //     }
    //   })
    //   .then((response) => {
    //     console.log(response.data);
    //     // category_name == "가정,생활 > 여가시설 > 보드카페 > {브랜드이름}" 으로 보드게임카페인지 그냥 카페인지 확인 가능한데 굳이?
    //     // address_name or road_address(주소), phone(번호), place_name(지점명), place_url(이미지 받을 수 있는 주소)
    //     // x(LNG), y(LAT) 
    //   })
    //   .catch((error) => console.log(error));
    // }
    // return request_MapInfo();

    
  };

  const renderContent = () => {
    useEffect(() => {
      const container = document.getElementById('map');
      const options = {
        center: new kakao.maps.LatLng(37.49982193285956, 127.03690105516682),
        level: 3,
      };
      const map = new kakao.maps.Map(container, options);
    }, []);
    return (
      <div id="map" style={{ width: "500px", height: "400px" }}>    
      </div>
    );
  };

  return (
    <div>
      <div>
        <form onSubmit={onSubmitHandler}>
          <label>지역</label>
          <input type="text" value={Location} onChange={onLocationHandler} />
          <label>브랜드</label>
          <input type="text" value={Brand} onChange={onBrandHandler} />
          <button>검색</button>
        </form>
      </div>
      <div>{renderContent()}</div>
    </div>
  );
};

export default Arcade;
