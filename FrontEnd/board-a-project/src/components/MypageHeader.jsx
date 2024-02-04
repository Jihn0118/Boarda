import styled from "@emotion/styled";
import React from "react";

const MypageDiv = styled.div`
  width: 80vw;
  height: 10vh;
  background-color: violet;
  opacity: 30%;
  margin: auto;
  margin-top: 3rem;
  border-radius: 5%;
  font-size: 3rem;
  text-align: center;

`;

export default function MypageHeader() {
  return <MypageDiv>마이페이지헤더</MypageDiv>;
}
