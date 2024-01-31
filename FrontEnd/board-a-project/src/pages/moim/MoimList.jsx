import React, { useEffect, useState } from 'react';
import { useRecoilState } from 'recoil';
import { getMoimList, checkRoom } from '../../api/moimAPI';
import { moimListState, locationState } from '../../recoil/atoms/moimState';
import { Link, useNavigate } from 'react-router-dom';
import Modal from "react-modal";
import Pagination from "react-js-pagination";
import { Outlet } from 'react-router-dom';



Modal.setAppElement("#root");

const MoimList = () => {
  const [moimList, setMoimList] = useRecoilState(moimListState);
  const [location, setLocation] = useRecoilState(locationState);
  const [sort, setSort] = useState('1');
  const [modalIsOpen, setModalIsOpen] = useState(false);
  const navigate = useNavigate();

  const [totalItemsCount, setTotalItemsCount] = useState(0);
  const [activePage, setActivePage] = useState(1);
  const itemsCountPerPage = 5;
  
  

  // const getMoimList = async () => {
  //   try {
  //     console.log(location);
  //     console.log(sort);

  //     const resp = await axios.get(`//www.boarda.site:8080/moim/list?location=${location}&sort=${sort}`);
  //     setMoimList(resp.data);
  //     console.log(resp.data);
      
  //     // 만약 페이지네이션 정보를 상태로 관리해야 한다면, recoil 상태에 추가하세요.
  //   } catch (error) {
  //     console.error('데이터를 가져오는 중 에러 발생:', error);
  //   }
  // };

  const getMoimListData = async () => {
    const data = await getMoimList(location, sort);
    setMoimList(data);
  };

  const handleLocationChange = (selectedLocation) => {
    setLocation(selectedLocation); // 햄버거 메뉴에서 선택한 값으로 location 업데이트
  };

  const handleSortChange = (selectedSort) => {
    setSort(selectedSort); // 정렬 방식 선택 시 sort 상태 업데이트
  };

  const handlePageChange = (pageNumber) => {
    setActivePage(pageNumber);
  };

  const currentMoimList = moimList.slice((activePage - 1) * itemsCountPerPage, activePage * itemsCountPerPage);

  // const moveToWrite = () => {
  //   axios.get('//www.boarda.site:8080/moim/checkroom', {
  //     params: {
  //       num: 11
  //     }
  //   })
  //   .then(function (response) {
  //     console.log("ㅇㅇ" + response.data);
  //     if (response.data == 0) {
  //       navigate('/write');
  //     } else if (response.data == 1) {
  //       setModalIsOpen(true);
  //     }
  //   })
  //   .catch(function (error) {
  //     console.log(error);
  //   });
  // };

  const moveToMake = async () => {
    const data = await checkRoom(11);
    console.log("ㅇㅇ" + data);
    if (data === 0) {
      navigate('/moim/list/make');
    } else if (data === 1) {
      setModalIsOpen(true);
    }
  };

  useEffect(() => {
    getMoimListData(); // 초기 렌더링 시 API 요청
  }, [location, sort]); // location 값이 변경될 때마다 API 요청

  useEffect(() => {
    setTotalItemsCount(moimList.length);
  }, [moimList]);

  return (
    <div>
      <label>
        Location:
        <select value={location} onChange={(e) => handleLocationChange(e.target.value)}>
          <option value="서울시 강남구">강남구</option>
          <option value="서울시 마포구">마포구</option>
        </select>
      </label>
      < br/>
      <label>
        정렬:
        <select value={sort} onChange={(e) => handleSortChange(e.target.value)}>
          <option value="1">최신순</option>
          <option value="2">마감임박순</option>
          <option value="3">모집일시</option>
        </select>
      </label>
      
      <ul>
        {currentMoimList.map((moim) => (
          <li key={moim.id}>
          <Link to={`/moim/list/${moim.id}`}>
            {moim.id} {moim.title} {moim.datetime} temp/{moim.number}
          </Link>
        </li>
        ))}
      </ul>

      <Pagination
        activePage={activePage}
        itemsCountPerPage={itemsCountPerPage}
        totalItemsCount={totalItemsCount} // 전체 아이템의 수, API 응답에서 가져올 수 있음
        pageRangeDisplayed={5} // 한 번에 보여줄 페이지 번호의 수
        onChange={handlePageChange}
      />


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
        <button onClick={moveToMake}>글쓰기</button>
      </div>
      <Outlet></Outlet>
    </div>
  );
};

export default MoimList;