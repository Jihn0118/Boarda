import React from "react";
import { Outlet } from "react-router-dom";

const Home = () => {
  return (
    <>
      <div>홈 화면 입니다.</div>
      <Outlet></Outlet>

    </>
  );
};

export default Home;
