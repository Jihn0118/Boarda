import React, { useState } from "react";
import { isAuthed } from "../../utils/authUtil";
import { useNavigate } from "react-router";
import Tabs from "@mui/material/Tabs";
import Tab from "@mui/material/Tab";
import styled from "styled-components";

const StyledTabs = styled(Tabs)`
  && {
    width: 20%;
    margin-top: 10%;
    border: black solid;
    border-radius: 10%;
  }
`;
const StyledTab = styled(Tab)`
  && {
    &.Mui-selected {
      background-color: lightblue; /* 선택된 Tab의 배경색을 lightblue로 지정 */
      color: white;
    }
    font-size: large;
    font-weight: bold;

  }
`;

export default function MyPage() {
  // 네비게이터로 이동시키기 
  const navigate = useNavigate();

  // // 로그인 여부 확인
  // if(!isAuthed()) {
  //   navigate("/home");
  //   alert("로그인한 유저만 접근할 수 있습니다.")
  // }
  const [val, setVal] = useState(1);

  return (
    <StyledTabs
      value={val}
      onChange={(e, newVal) => {
        setVal(newVal);
      }}
      orientation="vertical"
    >
      <StyledTab label="김싸피의피드" value={1}></StyledTab>
      <StyledTab label="김싸피의작성글" value={2}></StyledTab>
      <StyledTab label="회원정보수정" value={3}></StyledTab>
      <StyledTab label="내그룹이력" value={4}></StyledTab>
      <StyledTab label="팔로우/차단" value={5}></StyledTab>
      <StyledTab label="참여중인 그룹" value={6}></StyledTab>
    </StyledTabs>
  );
}
