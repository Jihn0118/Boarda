import React from "react";
import Header from "./../../components/Header";
import { Outlet } from "react-router-dom";
import ChatRoom from "../../components/ChatRoom";
import ChatButton from "../../components/ChatButton";
import { chatOpenState } from "../../recoil/atoms/chattingAtom";
import { useRecoilValue } from "recoil";

// 기본 레이아웃에 헤더, 채팅방이 무조건 있게 만들었습니다.
// 채팅방은 채팅방 atom 중 chatOpenState를 통해 열려있는지 닫혀있는지 다르게 랜더링 되도록 했습니다.
// 여기선 값 변경이 필요 없어서 useRecoilValue만 가져왔어용

export default function RootLayout() {
  const isOpen = useRecoilValue(chatOpenState);

  return (
    <>
      <Header></Header>
      <Outlet></Outlet>
      {isOpen && <ChatRoom></ChatRoom>}
      <ChatButton></ChatButton>
    </>
  );
}
