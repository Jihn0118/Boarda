// 웹소켓 객체 생성
const socket = new WebSocket("ws://localhost:8081/ws");

// 소켓 연결되면 open message error close (on)사용 가능
// open - 커넥션이 제대로 만들어 졌을 때
// message - 데이터 수신 시
// error - 에러
// close - 커넥션 종료시

// * send - 데이터 전송

socket.onopen = (e) => {
  console.log("웹소켓 연결됨");
  socket.send("싸피");
};

socket.onmessage = (e) => {
  console.log(e.data);
};
