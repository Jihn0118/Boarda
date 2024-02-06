import React, { useEffect } from "react";
import { Outlet } from "react-router-dom";
const { kakao } = window;

const Home = () => {
  const rankData = [
    "알케미스트",
    "다빈치코드",
    "블루마블",
    "블리츠",
    "크랭크",
  ];
  const endSoon = [
    {
      title: "레드버튼 강남점",
      latlng: new kakao.maps.LatLng(37.501931286572834, 127.0264523435014),
    },
    {
      title: "레드버튼 강남2호점",
      latlng: new kakao.maps.LatLng(37.4998154950733, 127.027458092239),
    },
  ];

  useEffect(() => {
    // rankData, endSoon axios 요청 해야하지만 일단은 더미데이터
  }, []);

  const renderMap = () => {
    useEffect(() => {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(37.566826, 126.9786567),
        level: 9,
        disableDoubleClickZoom: true,
        draggable: false,
      };
      const map = new kakao.maps.Map(container, options);
      map.setZoomable(false);

    }, []);
    return <div id="map" style={{ width: "500px", height: "400px" }}></div>;
  };

  return (
    <>
      <div>홈 화면 입니다.</div>
      <Outlet></Outlet>
      <div>캐러셀</div>

      <div>
        {/* 랭킹 */}
        <h2>종합 랭킹</h2>
        <div>
          <ol>
            {rankData &&
              rankData.map((data, idx) => (
                <li key={idx}>
                  <span>{ data }</span>
                </li>
              ))}
          </ol>
        </div>
      </div>

      <div>
        {/* 지도 */}
        {renderMap()}
      </div>
    </>
  );
};

export default Home;
