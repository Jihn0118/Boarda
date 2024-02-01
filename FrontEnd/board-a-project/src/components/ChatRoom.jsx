import React from "react";
import { Box, TextField, Button } from "@mui/material";
import styled from "styled-components";
import { useRecoilValue } from "recoil";
import { prevChatMessageState, webSocket } from "../recoil/atoms/chattingAtom";
import { sendMessage } from "../api/chattingAPI";
import socketService from "../utils/socketService";

const ChatContainer = styled(Box)`
  && {
    border-radius: 5%;
    border-color: #d98f8f;
    border-style: solid;
    border-width: 0.1rem;
    height: 50vh;
    width: 30vw;
    margin: auto;
    //border: 1px solid #000;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    position: fixed;
    right: 5rem;
    bottom: 2rem;
  }
`;

const MessageList = styled(Box)`
  && {
    flex-grow: 1;
    overflow: auto;
    padding: 1rem;
  }
`;

const MessageInput = styled(TextField)`
  && {
    margin: 1rem;
  }
`;

const SendButton = styled(Button)`
  && {
    margin: 1rem;
    width: 5%;
    height: 30%;
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
      <Box display="flex">
        <MessageInput
          variant="outlined"
          fullWidth
          sx={{ width: '21100px', height: '50px' }}
          
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
