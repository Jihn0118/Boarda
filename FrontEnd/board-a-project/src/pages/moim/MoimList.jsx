import React, { useEffect, useState } from "react";
import { useRecoilState } from "recoil";
import { getMoimList, checkRoom } from "../../api/moimAPI";
import { moimListState, locationState } from "../../recoil/atoms/moimState";
import { useLocation } from "react-router-dom";
import styled from "styled-components";
import Modal from "react-modal";
import MoimDetailModal from "./MoimDetailModal";
import MoimMakeModal from "./MoimMakeModal";
import Pagination from "@mui/material/Pagination";

Modal.setAppElement("#root");
const StyledButton = styled.button`
  padding: 10px 20px;
  border-radius: 5px;
  border: none;
  color: white;
  background-color: #3498db;

  &:hover {
    background-color: #2980b9;
  }
`;

const MoimList = () => {
  const [moimList, setMoimList] = useRecoilState(moimListState);
  const [location, setLocation] = useRecoilState(locationState);
  const [sort, setSort] = useState("1");
  const location2 = useLocation();

  const [detailModalIsOpen, setDetailModalIsOpen] = useState(false);
  const [selectedMoimId, setSelectedMoimId] = useState(null);

  const [makeModalIsOpen, setMakeModalIsOpen] = useState(false);

  const [totalItemsCount, setTotalItemsCount] = useState(0);
  const [activePage, setActivePage] = useState(1);
  const itemsCountPerPage = 5;

  const getMoimListData = async (selectedLocation) => {
    const locationToUse = selectedLocation ? selectedLocation : location;
    const data = await getMoimList(locationToUse, sort);
    setMoimList(data);
  };

  const handleLocationChange = (selectedLocation) => {
    setLocation(selectedLocation); // 햄버거 메뉴에서 선택한 값으로 location 업데이트
  };

  const handleSortChange = (selectedSort) => {
    setSort(selectedSort); // 정렬 방식 선택 시 sort 상태 업데이트
  };

  const openDetailModal = (moimId) => {
    if (!detailModalIsOpen) {
      setSelectedMoimId(moimId);
      setDetailModalIsOpen(true);
    } else {
      console.log("Modal is already open");
    }
  };

  const closeDetailModal = () => {
    setDetailModalIsOpen(false);
  };

  const MakeRoom = async () => {
    const data = await checkRoom();
    console.log("ㅇㅇ" + data);
    if (data === 0) {
      setMakeModalIsOpen(true);
    } else {
      alert("이미 참여 중인 모임이 있습니다!");
    }
  };

  const closeMakeModal = () => {
    setMakeModalIsOpen(false);
  };

  const handlePageChange = async (event, pageNumber) => {
    setActivePage(pageNumber);
  };

  const currentMoimList = moimList.slice(
    (activePage - 1) * itemsCountPerPage,
    activePage * itemsCountPerPage
  );

  useEffect(() => {
    getMoimListData();
    if (location2.state?.updated) {
      getMoimListData();
    }
  }, [location2, location, sort]); // location 값이 변경될 때마다 API 요청

  useEffect(() => {
    setTotalItemsCount(moimList.length);
  }, [moimList]);

  useEffect(() => {
    window.scroll({ top: document.body.scrollHeight, behavior: "smooth" });
  }, [location2]);

  return (
    <div className="flex flex-col items-center justify-center min-h-screen bg-gray-100">
      <div className="w-3/4 p-4 bg-white rounded shadow">
        <div className="flex justify-between mb-4">
          <label>
            Location:
            <select
              value={location}
              onChange={(e) => handleLocationChange(e.target.value)}
              className="ml-2 p-1 border rounded"
            >
              <option value="">선택없음</option>
              <option value="서울시 강남구">강남구</option>
              <option value="서울시 마포구">마포구</option>
              <option value="서울시 성동구">성동구</option>
              <option value="서울시 강서구">강서구</option>
            </select>
          </label>
          <label>
            정렬:
            <select
              value={sort}
              onChange={(e) => handleSortChange(e.target.value)}
              className="ml-2 p-1 border rounded"
            >
              <option value="1">최신순</option>
              <option value="2">마감임박순</option>
              <option value="3">모집일시</option>
            </select>
          </label>
        </div>
        <table className="table-auto w-full text-left border-collapse border border-gray-300">
          <thead>
            <tr>
              <th className="border border-gray-300 px-4 py-2">번호</th>
              <th className="border border-gray-300 px-4 py-2">제목</th>
              <th className="border border-gray-300 px-4 py-2">날짜</th>
              <th className="border border-gray-300 px-4 py-2">인원</th>
            </tr>
          </thead>
          <tbody>
            {currentMoimList && currentMoimList.map((moim) => (
              <tr
                key={moim.id}
                onClick={() => openDetailModal(moim.id)}
                className="text-blue-500 hover:underline cursor-pointer"
              >
                <td className="border border-gray-300 px-4 py-2">{moim.id}</td>
                <td className="border border-gray-300 px-4 py-2">
                  {moim.title}
                </td>
                <td className="border border-gray-300 px-4 py-2">
                  {moim.datetime.split("T")[0]}
                </td>
                <td className="border border-gray-300 px-4 py-2">
                  {moim.currentNumber}/{moim.number}
                </td>
              </tr>
            ))}
          </tbody>
        </table>
        {selectedMoimId && (
          <MoimDetailModal
            moimId={selectedMoimId}
            isOpen={detailModalIsOpen}
            onRequestClose={closeDetailModal}
          />
        )}

        <div className="flex justify-center">
          <Pagination
            count={Math.ceil(totalItemsCount / itemsCountPerPage)}
            page={activePage}
            onChange={handlePageChange}
          />
        </div>

        <div>
          <div className="flex justify-end">
            <StyledButton onClick={() => MakeRoom()}>글쓰기</StyledButton>
          </div>
          <MoimMakeModal
            isOpen={makeModalIsOpen}
            onRequestClose={closeMakeModal}
          />
        </div>
      </div>
    </div>
  );
};

export default MoimList;
