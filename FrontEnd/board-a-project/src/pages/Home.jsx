import React from "react";
import { Outlet } from "react-router-dom";

const Home = () => {
  return (
    <>
      <div>홈 화면 입니다.</div>
      <button
        onClick={() => {
          console.log(import.meta.env.VITE_HANDSHAKE_URI);
          const socket = new WebSocket(import.meta.env.VITE_HANDSHAKE_URI);
          console.log(socket);
        }}
      >
        웹소켓연결
      </button>
      <Outlet></Outlet>
    </>
  );
};

export default Home;
