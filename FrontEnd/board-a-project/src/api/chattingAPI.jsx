import { useRecoilState, useRecoilValue } from "recoil";
import { prevChatMessageState, webSocket } from "../recoil/atoms/chattingAtom";
import socketService from "../utils/socketService";

export function connectSocket() {
  let socket = socketService.socket;
  const [prevMessage, setPrevMessage] = useRecoilState(prevChatMessageState);

  if (!socket) {
    // 스프링 서버로 웹소켓 연결
    // 로그인했으면 소켓 바로 연결 시도
    socket = new WebSocket(import.meta.env.VITE_HANDSHAKE_URI);
  }
  socket.onopen = (e) => {
    console.log("웹소켓연결됨");
    // 소켓 열릴 때 이전 채팅 목록이 넘어올 것
    // 그걸 파싱해서 prevMessage로 저장
    setPrevMessage(e.data);
  };
  socket.onmessage = (e) => {
    // 메세지가 도착할 때 마다 이전 prevmessage에 추가로 넣어야지
    // 이전거는 스프레드하고 새로 온거를 배열에 추가
    setPrevMessage((oldVal) => [...oldVal, e.data]);
  };
  socket.onclose = (e) => {
    console.log("웹소켓 닫힘");
  };

  socket.onerror = (e) => {
    console.log("웹소켓 오류 발생");
    console.log(e);
  };

  return socket;
}

export function sendMessage(message, chattingRoomId, userId) {
  // 채팅창 전송 버튼 누르면 스프링 서버로 데이터 전송
  // 필요정보? 메시지, 어떤유저가 쳤는지, 채팅방 번호
  const data = {
    chattingRoomId,
    userId,
    message,
  };
  socketService.socket.send(JSON.stringify(data))
}
