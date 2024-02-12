import React, { useEffect } from "react";
import { Outlet } from "react-router-dom";
import { Carousel } from "@material-tailwind/react";
import Carousel01 from "../assets/images/Carousel01.png";
import Carousel02 from "../assets/images/Carousel02.png";

const { kakao } = window;

const Home = () => {
  const rankData = ["알케미스트", "다빈치코드", "블루마블", "블리츠", "크랭크"];
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
        level: 10,
        disableDoubleClickZoom: true,
        draggable: false,
      };
      const map = new kakao.maps.Map(container, options);
      map.setZoomable(false);
    }, []);
    return <div id="map" style={{ width: "400px", height: "300px" }}></div>;
  };

  return (
    <>
      <Outlet></Outlet>
      <div>
        <Carousel>
          <img
            src={Carousel01} // src import로 바꾸기
            alt="image 1"
            className="h-full w-full object-cover"
          />
          <img
            src={Carousel02}
            alt="image 2"
            className="h-full w-full object-cover"
          />
        </Carousel>
      </div>
      <div class="container mx-auto flex py-10">
        <div className="w-2/3 px-4">
          <h1 class="text-2xl font-bold ...">인기 순위</h1>

          <div class="container flex justify-between py-5">
            <div>
              <h2 class="text-xl text-center ...">전체</h2>
              <div class="py-10">
                <ol>
                  {rankData &&
                    rankData.map((data, idx) => (
                      <li key={idx}>
                        <span>
                          {idx + 1}. {data}
                        </span>
                      </li>
                    ))}
                </ol>
              </div>
            </div>

            <div>
              <h2 class="text-xl text-center...">남성 | 여성</h2>
              <div class="py-10">
                <ol>
                  {rankData &&
                    rankData.map((data, idx) => (
                      <li key={idx}>
                        <span>
                          {idx + 1}. {data}
                        </span>
                      </li>
                    ))}
                </ol>
              </div>
            </div>

            <div>
              <h2 class="text-xl ...">10대 | 20대 | 30대↑</h2>
              <div class="py-10">
                <ol>
                  {rankData &&
                    rankData.map((data, idx) => (
                      <li key={idx}>
                        <span>
                          {idx + 1}. {data}
                        </span>
                      </li>
                    ))}
                </ol>
              </div>
            </div>
          </div>
        </div>

        <div className="w-1/3 px-4">
          <h1 class="text-2xl font-bold ...">마감 임박</h1>
          <div class="container flex justify-center py-5">
            <div>{renderMap()}</div>
          </div>
        </div>
      </div>
    </>
  );
};

export default Home;
