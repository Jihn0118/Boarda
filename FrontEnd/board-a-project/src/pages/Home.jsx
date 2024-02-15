import React, { useEffect, useState } from "react";
import { Outlet } from "react-router-dom";
import { Carousel } from "@material-tailwind/react";

//import API
import rankingAPI from "../api/rankingAPI";
import { urgentMoim } from "../api/moimAPI";

// import icon, image
import Carousel01 from "../assets/images/Carousel01.png";
import Carousel02 from "../assets/images/Carousel02.png";
import banner from "../assets/images/banner.png";
import banner2 from "../assets/images/banner_2.png";
import logo_1 from "../assets/images/logo_1.png";
import logo_2 from "../assets/images/logo_2.png";
import logo_3 from "../assets/images/logo_3.png";
import TextsmsIcon from "@mui/icons-material/Textsms";

const { kakao } = window;

const Home = () => {
  const [rankGameData, setRankGame] = useState([]);
  const [rankCafeData, setRankCafe] = useState([]);
  const [deadlineMoim, setDeadlineMoim] = useState([]);
  let temp_dict = {
    강남구: 0,
    강동구: 0,
    강북구: 0,
    강서구: 0,
    관악구: 0,
    광진구: 0,
    구로구: 0,
    금천구: 0,
    노원구: 0,
    도봉구: 0,
    동대문구: 0,
    동작구: 0,
    마포구: 0,
    서대문구: 0,
    서초구: 0,
    성동구: 0,
    성북구: 0,
    송파구: 0,
    양천구: 0,
    영등포구: 0,
    용산구: 0,
    은평구: 0,
    종로구: 0,
    중구: 0,
    중랑구: 0,
  };
  const cord_info = {
    강남구: [127.0495556, 37.514575],
    강동구: [127.1258639, 37.52736667],
    강북구: [127.0277194, 37.63695556],
    강서구: [126.851675, 37.54815556],
    관악구: [126.9538444, 37.47538611],
    광진구: [127.0845333, 37.53573889],
    구로구: [126.8895972, 37.49265],
    금천구: [126.9041972, 37.44910833],
    노원구: [127.0583889, 37.65146111],
    도봉구: [127.0495222, 37.66583333],
    동대문구: [127.0421417, 37.571625],
    동작구: [126.941575, 37.50965556],
    마포구: [126.9105306, 37.56070556],
    서대문구: [126.9388972, 37.57636667],
    서초구: [127.0348111, 37.48078611],
    성동구: [127.039, 37.56061111],
    성북구: [127.0203333, 37.58638333],
    송파구: [127.1079306, 37.51175556],
    양천구: [126.8687083, 37.51423056],
    영등포구: [126.8983417, 37.52361111],
    용산구: [126.9675222, 37.53609444],
    은평구: [126.9312417, 37.59996944],
    종로구: [126.9816417, 37.57037778],
    중구: [126.9996417, 37.56100278],
    중랑구: [127.0947778, 37.60380556],
  };

  useEffect(() => {
    // rankGameData, rankCafeData, endSoon axios 요청
    const fetchGameData = async () => {
      try {
        const res = await rankingAPI.getRankingGame();
        console.log(res.data);
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

    const fetchMoimData = async () => {
      try {
        const res = await urgentMoim();
        res.data.map((data, idx) => {
          const gu = data.location.split(" ")[1];
          temp_dict[gu] += 1;
        });
        setDeadlineMoim(temp_dict);
      } catch (error) {
        console.log(error);
      }
    };

    fetchGameData();
    fetchCafeData();
    fetchMoimData();
  }, []);

  const Rendermap = () => {
    useEffect(() => {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(37.536826, 126.9786567),
        level: 9,
        disableDoubleClickZoom: true,
        draggable: false,
      };
      const map = new kakao.maps.Map(container, options);
      map.setZoomable(false);

      Object.entries(cord_info).map(([key, data], idx) => {
        const position = new kakao.maps.LatLng(data[1], data[0]);

        // circle.setMap(map);
        if (deadlineMoim[key] != 0) {
          const content = `<div style="width: ${
            10 * deadlineMoim[key]
          }px; height: ${
            10 * deadlineMoim[key]
          }px; border-radius: 50%; background-color: #ff0000; display: flex; justify-content: center; align-items: center;"><span>${
            deadlineMoim[key]
          }</span></div>
          </div>`;
          const my_overlay = new kakao.maps.CustomOverlay({
            position: position,
            content: content,
          });
          my_overlay.setMap(map);
        }
      });
    }, []);

    return <div id="map" style={{ width: "400px", height: "344px" }}></div>;
  };

  return (
    <>
      <Outlet></Outlet>

      {/* 캐러셀 */}
      <div>
        <Carousel>
          <img
            src={banner}
            alt="image 1"
            className="h-full w-full object-cover"
          />
          <img
            src={banner2}
            alt="image 2"
            className="h-full w-full object-cover"
          />
        </Carousel>
      </div>

      {/* 인기매장 */}
      <div className="container mx-auto flex justify-between py-10">
        <div className="w-2/3 px-4">
          <h1 className="text-2xl font-bold pl-10 ...">인기 매장</h1>
          <div className="container flex justify-center py-5 px-10">
            <div className="container flex justify-start space-x-10 py-5 h-96">
              {rankCafeData &&
                rankCafeData.map(
                  (data, idx) =>
                    idx <= 2 && ( // 인기매장 top 3만 표시
                      <div
                        key={idx}
                        className="flex flex-col w-72 px-12 py-5 items-center border-2 border-rose-600 ..."
                      >
                        <div className="flex flex-col items-center">
                          <img
                            src={
                              rankCafeData[idx].cafe.brand === "레드버튼"
                                ? logo_1
                                : rankCafeData[idx].cafe.brand === "홈즈앤루팡"
                                ? logo_2
                                : logo_3
                            }
                            alt="brand logo"
                            style={{
                              width: "150px",
                              height: "150px",
                              marginBottom: "20px",
                            }}
                          />

                          <h2 className="text-xl text-center font-bold ...">
                            {rankCafeData[idx].cafe.brand}{" "}
                            {rankCafeData[idx].cafe.branch}
                          </h2>
                        </div>
                        <div className="py-10">
                          <span>{idx + 1} 위</span>
                        </div>
                      </div>
                    )
                )}
            </div>
          </div>
        </div>

        {/* 마감임박 */}
        <div className="w-1/3 px-4">
          <h1 className="text-2xl font-bold ...">마감 임박</h1>
          <div className="flex justify-center py-10">
            <div>
              <Rendermap />
            </div>
          </div>
        </div>
      </div>

      {/* 인기게임 */}
      <div className="px-20">
        <h1 className="text-2xl font-bold ...">인기 게임 TOP10</h1>
        <div className="conatiner mx-auto flex-col justify-center my-5 py-2">
          {/* 범례 */}
          <div className="flex justify-start space-x-4">
            <div className="w-20 text-center">순위</div>
            <div className="w-96" style={{ padding: "0px 12px" }}>
              게임
            </div>
            <div className="w-40 text-center">플레이 인원</div>
            <div className="w-40 text-center">난이도</div>
            <div className="w-40 text-center">사용 연령</div>
            <div className="w-40 text-center">플레이 시간</div>
          </div>
          {/* 게임목록 */}
          <div className="flex justify-start py-5">
            <div>
              {rankGameData &&
                rankGameData.map((data, idx) => (
                  <div className="flex justify-start items-center space-x-4 border-b-2 border-rose-600 ...">
                    <div className="w-20 text-center">{idx + 1}</div>
                    <div className="flex w-96 space-x-4 items-center">
                      <div
                        style={{
                          width: "60px",
                          height: "60px",
                          margin: "10px 0px",
                        }}
                      >
                        <img
                          src={data.game.image}
                          alt="game_img"
                          style={{ width: "100%", height: "100%" }}
                        />
                      </div>
                      <span>{data.game.title}</span>
                    </div>
                    <div className="w-40 text-center">
                      {data.game.minNum == data.game.maxNum
                        ? data.game.minNum
                        : data.game.minNum + " - " + data.game.maxNum}{" "}
                      인
                    </div>
                    <div className="w-40 text-center">
                      {data.game.difficulty} / 5
                    </div>
                    <div className="w-40 text-center">
                      {data.game.age}세 이상
                    </div>
                    <div className="w-40 text-center">
                      {data.game.playTime}분
                    </div>
                  </div>
                ))}
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default Home;
