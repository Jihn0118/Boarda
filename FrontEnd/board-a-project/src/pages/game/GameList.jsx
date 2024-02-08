import React, { useEffect, useState } from "react";
import { getGameList } from "../../api/gameAPI";
import Pagination from "@material-ui/lab/Pagination";
import { useNavigate } from "react-router-dom";
import { useRecoilState } from "recoil";
import { gameListState } from "../../recoil/atoms/gameState";

import { CardGalaxy } from "../../mui-treasury/card-galaxy/CardGalaxy";
import AccessAlarmIcon from '@mui/icons-material/AccessAlarm';
import AccessibilityIcon from '@mui/icons-material/Accessibility';
import SearchIcon from '@mui/icons-material/Search';
import Grid from "@material-ui/core/Grid";

const GameList = () => {
  const [gameList, setGameList] = useRecoilState(gameListState);
  const [num, setNum] = useState(0);
  const [time, setTime] = useState(0);
  const [keyword, setKeyword] = useState("");
  const navigate = useNavigate();

  const [totalItemsCount, setTotalItemsCount] = useState(0);
  const [activePage, setActivePage] = useState(1);
  const itemsCountPerPage = 8;

  const currentGameList = gameList.slice(
    (activePage - 1) * itemsCountPerPage,
    activePage * itemsCountPerPage
  );

  const handlePageChange = async (event, pageNumber) => {
    setActivePage(pageNumber);
  };

  const handleNumChange = (selectedNum) => {
    setNum(selectedNum);
  };

  const handleTimeChange = (selectedTime) => {
    setTime(selectedTime);
  };

  const handleKeywordChange = (newKeyword) => {
    setKeyword(newKeyword);
  };

  useEffect(() => {
    setTotalItemsCount(gameList.length);
  }, [gameList]);

  useEffect(() => {
    const fetchData = async () => {
      const data = await getGameList(time, num, keyword);
      setGameList(data);
    };

    fetchData(); // 함수를 호출하여 데이터를 가져옵니다.
  }, [time, num, keyword]); // 빈 배열을 넣어 컴포넌트가 마운트 될 때만 실행하게 합니다.

  return (
    <div>
      <div className="mt-4">
        <div className="flex justify-between mx-auto mb-4" style={{ maxWidth: '1100px' }}>
          <label class="relative block flex-grow mr-2 text-center" style={{ flex: '0.3' }}>
            <span class="sr-only">Time:</span>
            <span class="absolute inset-y-0 left-0 flex items-center pl-2">
              <AccessAlarmIcon style={{ color: '#718096' }} />
            </span>
            <select
              value={time}
              onChange={(e) => handleTimeChange(e.target.value)}
              class="placeholder:italic placeholder:text-slate-400 block w-full bg-white border border-slate-300 rounded-md py-2 pl-10 pr-3 shadow-sm focus:outline-none focus:border-sky-500 focus:ring-sky-500 focus:ring-1 sm:text-sm"
            >
              <option value="0">전체</option>
              <option value="15">0 ~ 15 분</option>
              <option value="30">16 ~ 30 분</option>
              <option value="45">31 ~ 45 분</option>
              <option value="60">46 ~ 60 분</option>
              <option value="75">61 ~ 75 분</option>
              <option value="90">76 ~ 90 분</option>
              <option value="105">91 분 이상</option>
            </select>
          </label>

          <label class="relative block flex-grow mr-2 text-center" style={{ flex: '0.3' }}>
            <span class="sr-only">Num:</span>
            <span class="absolute inset-y-0 left-0 flex items-center pl-2">
              <AccessibilityIcon style={{ color: '#718096' }} />
            </span>
            <select
              value={num}
              onChange={(e) => handleNumChange(e.target.value)}
              class="placeholder:italic placeholder:text-slate-400 block w-full bg-white border border-slate-300 rounded-md py-2 pl-10 pr-3 shadow-sm focus:outline-none focus:border-sky-500 focus:ring-sky-500 focus:ring-1 sm:text-sm"
            >
              <option value="0">전체</option>
              <option value="1">1</option>
              <option value="2">2</option>
              <option value="3">3</option>
              <option value="4">4</option>
              <option value="5">5</option>
              <option value="6">6</option>
              <option value="7">7</option>
              <option value="8">8</option>
              <option value="9">9 이상</option>
            </select>
          </label>

          <label class="relative block flex-grow" style={{ flex: '1.4' }}>
            <span class="sr-only">Keyword:</span>
            <span class="absolute inset-y-0 left-0 flex items-center pl-2">
              <SearchIcon style={{ color: '#718096' }} />
            </span>
            <input 
              value={keyword}
              onChange={(e) => handleKeywordChange(e.target.value)}
              class="placeholder:italic placeholder:text-slate-400 block w-full bg-white border border-slate-300 rounded-md py-2 pl-10 pr-3 shadow-sm focus:outline-none focus:border-sky-500 focus:ring-sky-500 focus:ring-1 sm:text-sm" 
              placeholder="Search for anything..." 
              type="text" 
              name="search"
            />
          </label>
        </div>

      </div>

      <div style={{ display: "flex", justifyContent: "center" }}>
        <Grid container spacing={3} style={{ maxWidth: "80%" }}>
          {currentGameList.map((game) => (
            <Grid item xs={12} sm={6} md={3} key={game.id}>
              <CardGalaxy
                onClick={() => navigate(`/game/${game.id}`)}
                title={game.title}
                subtitle={`플레이 시간: ${game.playTime}분`}
                imageUrl={game.image}
              />
            </Grid>
          ))}
        </Grid>
      </div>

      <div className="flex justify-center mt-4">
        <Pagination
          count={Math.ceil(totalItemsCount / itemsCountPerPage)}
          page={activePage}
          onChange={handlePageChange}
        />
      </div>
    </div>
  );
};

export default GameList;