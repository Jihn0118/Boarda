import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link, navigate } from '@reach/router';

const MoimList = () => {
  const [moimList, setMoimList] = useState([]);
  const [location, setLocation] = useState('강남구'); // 초기값 설정

  const getMoimList = async () => {
    try {
      const resp = await axios.get(`//localhost:8080/moim/choice?location=${location}`);
      setMoimList(resp.data.data);
      
      const pngn = resp.data.pagination;
      console.log(pngn);
    } catch (error) {
      console.error('데이터를 가져오는 중 에러 발생:', error);
    }
  };

  const handleLocationChange = (selectedLocation) => {
    setLocation(selectedLocation); // 햄버거 메뉴에서 선택한 값으로 location 업데이트
  };

  const moveToWrite = () => {
    navigate('/write');
  };

  useEffect(() => {
    getMoimList(); // 초기 렌더링 시 API 요청
  }, [location]); // location 값이 변경될 때마다 API 요청

  return (
    <div>
      {/* 햄버거 메뉴로 선택지 제공 */}
      <label>
        Location:
        <select value={location} onChange={(e) => handleLocationChange(e.target.value)}>
          <option value="강남구">강남구</option>
          <option value="마포구">마포구</option>
        </select>
      </label>
      
      <ul>
        {moimList.map((moim) => (
          <li key={moim.idx}>
            <Link to={`/moim/${moim.idx}`}>{moim.title}</Link>
          </li>
        ))}
      </ul>
      <div>
        <button onClick={moveToWrite}>글쓰기</button>
      </div>
    </div>
  );
};

export default MoimList;