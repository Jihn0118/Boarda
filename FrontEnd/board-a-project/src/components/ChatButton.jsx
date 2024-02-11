import React from "react";
import { Chat } from "@mui/icons-material";
import styled from "styled-components";
import { useRecoilState } from "recoil";
import { chatOpenState } from "../recoil/atoms/chattingAtom";

const StyledChattingButton = styled(Chat)`
  && {
    cursor: pointer;
    position: fixed;
    right: 1rem;
    bottom: 1rem;
    width: 2rem;
    height: 2rem;
    z-index: 2;
  }
`;

export default function ChatButton() {
  const [isChatOpen, setIsChatOpen] = useRecoilState(chatOpenState); // 채팅방 열렸는지 여부 상태

  // 누르면 true false 상태값 바뀌게
  // ** useRecoilState()의 두 번째 요소는 콜백함수나 값을 인자로 받는데, 값을 넣으면 그냥 그 값을 set함
  // 콜백함수를 받으면 콜백함수를 수행함
  const handleChatToggle = () => {
    setIsChatOpen((oldVal) => !oldVal);
  };

  return (
    <StyledChattingButton onClick={handleChatToggle}></StyledChattingButton>
  );
}
