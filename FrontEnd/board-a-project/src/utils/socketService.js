// 소켓 연결되면 open message error close (on)사용 가능
// open - 커넥션이 제대로 만들어 졌을 때
// message - 데이터 수신 시
// error - 에러
// close - 커넥션 종료시

// * send - 데이터 전송

// 소켓 관련 유틸 모음
const socketService = {
    socket: null, // 실제 소켓을 여기에 저장
  
    // 소켓 연결 시도
    connect() {
      //console.log(this)
      //console.log(import.meta.env.VITE_WS_HANDSHAKE_URI)
  
      // 스프링 서버로 핸드셰이크 요청 (JS 웹소켓 객체 생성)
      this.socket = new WebSocket(import.meta.env.VITE_HANDSHAKE_URI);
  
      // 연결되었을 때 동작 정의
      this.socket.onopen = () => {
        console.log("웹소켓 정상 연결");
      };
  
      // 웹소켓 통해서 데이터 날아왔을 때 동작
      this.socket.onmessage = (e) => {
        const data = JSON.parse(e.data);
        console.log(data);
      };
  
      // 에러났을 때 동작
      this.socket.onerror = (e) => {
        console.log(e);
      };
  
      // 웹소켓 연결 끊어졌을 때 동작
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
  export default socketService;
  