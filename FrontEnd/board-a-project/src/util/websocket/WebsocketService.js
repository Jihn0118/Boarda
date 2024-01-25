// 소켓 관련 유틸 모음
const WebSocketService = {
  socket: null, // 실제 소켓을 여기에 저장

  //소켓 연결 시도
  connect() {
    console.log(this)
    this.socket = new WebSocket(import.meta.env.VITE_WS_HANDSHAKE_URI);
    this.socket.onopen = () => {
      console.log("웹소켓 정상 연결");
    };
    this.socket.onmessage = (e) => {
      const data = JSON.parse(e.data);
      console.log(data);
    };
    this.socket.onerror = (e) => {
      console.log(e);
    };
    this.socket.onclose = () => {
      console.log("웹소켓 닫음");
    };
  },

  // 메시지 전송하기 (전송 클릭)
  handleSend(message) {
    if (!!this.socket && this.socket.readyState === WebSocket.OPEN) {
      this.socket.send(JSON.stringify(message));
    } else {
      console.log("전송실패");
    }
  },
};
export default WebSocketService;
