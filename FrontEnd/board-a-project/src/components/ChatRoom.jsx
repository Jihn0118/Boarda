import React from "react";
import { Box, TextField, Button } from "@mui/material";
import styled from "styled-components";
import { useRecoilValue } from "recoil";
import { prevChatMessageState, webSocket } from "../recoil/atoms/chattingAtom";
import { sendMessage } from "../api/chattingAPI";
import socketService from "../utils/socketService";

const ChatContainer = styled(Box)`
  && {
    background-color: white;
    box-shadow: 0.2rem 0.2rem 0.2rem 0.2rem #d98f8f;
    border-radius: 5%;
    border-color: #d98f8f;
    border-style: solid;
    border-width: 0.3rem;
    height: 50vh;
    width: 20vw;
    margin: auto;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    position: fixed;
    right: 5rem;
    bottom: 2rem;
    z-index: 2;
  }
`;

const MessageList = styled(Box)`
  && {
    flex-grow: 1;
    overflow: auto;
    padding: 1rem;
    z-index: 2;
  }
`;

const MessageInput = styled.input`
  border-radius: 5%;
  border: 0.05rem solid;
  width: 100%;
  height: 2rem;
  z-index: 2;
`;

// const MessageInput = styled(TextField)`
//   && {
//     margin: 1rem;
//   }
// `;

const SendButton = styled(Button)`
  && {
    margin: 1rem;
    width: 5%;
    height: 30%;
    background-color: #d98f8f;
    z-index: 2;
  }
`;

export default function ChatRoom() {
  // 이전까지 있었던 채팅 기록을 가져옴 - 배열
  // 채팅창에 반복문으로 쭉 뿌려주기
  const prevMessages = useRecoilValue(prevChatMessageState);
  const socket = socketService.socket;
  let nowMessage = null;

  return (
    <ChatContainer>
      <MessageList>
        {prevMessages.map((e) => {
          return <div>{e}</div>;
        })}
      </MessageList>
      <Box display="flex" alignItems="center">
        <MessageInput
          placeholder="메시지를 입력하세요."
          onChange={(e) => {
            nowMessage = e.target.value;
            console.log(nowMessage);
          }}
        />
        <SendButton
          onClick={() => {
            sendMessage(socket, nowMessage, 1, "ssafy");
          }}
          variant="contained"
        >
          전송
        </SendButton>
      </Box>
    </ChatContainer>
  );
}
