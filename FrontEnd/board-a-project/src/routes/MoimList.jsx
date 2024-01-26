import React, { useEffect, useState } from 'react';
import axios from 'axios';
// import { Link, navigate } from '@reach/router';
import { Link, useNavigate } from 'react-router-dom';
import Modal from "react-modal";

Modal.setAppElement("#root");

const MoimList = () => {
  const [moimList, setMoimList] = useState([]);
  const [location, setLocation] = useState('서울시 강남구'); // 초기값 설정
  const [modalIsOpen, setModalIsOpen] = useState(false);
  const navigate = useNavigate();

  const getMoimList = async () => {
    try {
      console.log(location);
      console.log()
      const resp = await axios.get(`//localhost:8081/moim/choice?location=${location}`);
      console.log(resp.data);
      setMoimList(resp.data);
      
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
    axios.get('//localhost:8081/moim/checkroom', {
      params: {
        num: 11
      }
    })
    .then(function (response) {
      console.log("ㅇㅇ" + response.data);
      if (response.data == 0) {
        navigate('/write');
      } else if (response.data == 1) {
        setModalIsOpen(true);
      }
    })
    .catch(function (error) {
      console.log(error);
    });
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
          <option value="서울시 강남구">강남구</option>
          <option value="서울시 마포구">마포구</option>
        </select>
      </label>
      
      <ul>
        {moimList.map((moim) => (
          <li key={moim.id}>
          <Link to={`/moim/${moim.id}`}>
            {moim.id} {moim.title} {moim.datetime} temp/{moim.number}
          </Link>
        </li>
        ))}
      </ul>
      <div>
        <Modal
          isOpen={modalIsOpen}
          onRequestClose={() => setModalIsOpen(false)}
          style={{
            overlay: {
              backgroundColor: 'rgba(0, 0, 0, 0.5)'
            },
            content: {
              color: 'lightsteelblue'
            }
          }}
        >
          <h2>알림</h2>
          <p>이미 참여 중인 모임이 있습니다!</p>
          <button onClick={() => setModalIsOpen(false)}>확인</button>
        </Modal>
        <button onClick={moveToWrite}>글쓰기</button>
      </div>
    </div>
  );
};

export default MoimList;