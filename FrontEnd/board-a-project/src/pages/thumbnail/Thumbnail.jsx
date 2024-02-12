import React from "react";
import { Link } from "react-router-dom";
import styled from "styled-components";
import thumbnailImage from "../../assets/images/thumbnailImage.png";

const StartButton = styled(Link)`
  && {
    color: #bf1111;
    font-size: 1.5rem;
    font-weight: bold;
    text-decoration: none;
    background-color: #f2f2f2;
    border-radius: 7%;
    width: 10rem;
    height: 4rem;
    position: absolute;
    right: 8rem;
    bottom: 5rem;
    display: flex;
    justify-content: center;
    align-items: center;
    opacity: 80%;
  }
`;
const StartDiv = styled.div`
  background-image: url(${thumbnailImage});
  background-size: cover;
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
