import React, { useEffect, useState } from "react";
import { Outlet } from "react-router-dom";
import { Carousel } from "@material-tailwind/react";
import Carousel01 from "../assets/images/Carousel01.png";
import Carousel02 from "../assets/images/Carousel02.png";

import rankingAPI from "../api/rankingAPI";

const { kakao } = window;

const Home = () => {
  const [rankGameData, setRankGame] = useState([]);
  const [rankCafeData, setRankCafe] = useState([]);
  const rankData = ["a", "b", "c", "d", "e"];
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
    // rankGameData, rankCafeData, endSoon axios 요청
    const fetchGameData = async () => {
      try {
        const res = await rankingAPI.getRankingGame();
        // console.log(res);
        setRankGame(res.data);
      } catch (error) {
        console.log(error);
      }
    };

    const fetchCafeData = async () => {
      try {
        const res = await rankingAPI.getRankingCafe();
        // console.log(res);
        setRankCafe(res.data);
      } catch (error) {
        console.log(error);
      }
    };

    fetchGameData();
    fetchCafeData();
    console.log(rankGameData)

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

      {/* 캐러셀 */}
      <div>
        <Carousel>
          <img
            src={Carousel01}
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

      {/* 인기매장 */}
      <div className="container mx-auto flex py-10">
        <div className="w-2/3 px-4">
          <h1 className="text-2xl font-bold ...">인기 매장</h1>
          <div className="container flex justify-center py-5">
            <div className="container flex justify-between py-5">
                {rankCafeData &&
                  rankCafeData.map((data, idx) => (
                      idx <= 2 && ( // 인기매장 top 3만 표시
                      <div key = {idx}>
                        <span>{rankCafeData[idx].cafe.image}</span>
                        <h2 className="text-xl text-center...">
                          {rankCafeData[idx].cafe.brand}{" "}
                          {rankCafeData[idx].cafe.branch}
                        </h2>
                        <div className="py-10">
                          <span>{rankCafeData[idx].cafe.rate} / 5</span>
                        </div>
                      </div>
                      )
                  ))}
            </div>
          </div>
        </div>
        
        {/* 마감임박 */}
        <div className="w-1/3 px-4">
          <h1 className="text-2xl font-bold ...">마감 임박</h1>
          <div className="container flex justify-center py-5">
            <div>{renderMap()}</div>
          </div>
        </div>
      </div>

      {/* 인기게임 */}
      <div>
        <h1 className="text-2xl font-bold ...">인기 게임</h1>
        <div className="container flex justify-center py-5">
          <div className="container flex justify-between py-5">
            <div>
                <ol>
                  {rankGameData &&
                    rankGameData.map((data, idx) => (
                      <li key={idx}>
                        <span>
                          {idx + 1}. {data.game.title}
                        </span>
                      </li>
                    ))}
                </ol>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default Home;
