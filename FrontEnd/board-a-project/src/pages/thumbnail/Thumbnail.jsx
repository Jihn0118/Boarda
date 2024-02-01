import React from "react";
import { Link } from "react-router-dom";
import styled from "styled-components";
const StartButton = styled(Link)`
  color: red;
  text-decoration: none;
  background-color: skyblue;
  border-radius: 30%;
  width: 5rem;
  height: 3rem;
  position: fixed;
  right: 8rem;
  bottom: 10rem;
  display: flex;
  justify-content: center;
  align-items: center;
  
`;
const StartDiv = styled.div`
  background-image: url("src/assets/images/ThumbnailImage.jpg");
  width: 100vw;
  height: 100vh;
`;
export default function Thumbnail() {
  return (
    <StartDiv>
      <StartButton to="/home">시작하기</StartButton>
    </StartDiv>
  );
}
