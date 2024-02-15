import styled from "@emotion/styled";
import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { useRecoilState, useRecoilValue } from "recoil";
import { loginUserState } from "../recoil/atoms/userState";
import boardaLogo from "../assets/images/boardaLogo.png";
import bellImg from "../assets/images/bellImg.png";
import Alarm from "./Alarm";

// Material-UI
import { Drawer, Hidden } from "@mui/material";

// StyledHeader 컴포넌트 생성
const StyledHeader = styled.header`
  background-color: #ffffff; /* 배경색 설정 */
  padding: 0px 20px; /* 내부 여백 설정 */
  display: flex;
  justify-content: space-between;
  align-items: stretch;

  a {
    color: #000000; /* 링크 텍스트 색상 */
    text-decoration: none; /* 밑줄 제거 */
    font-weight: bold;

    padding: 18px 24px; /* 링크 내부 여백 */
    transition: background-color 0.3s ease; /* 배경색 변경 트랜지션 */
  }
  a:hover {
    background-color: #bad8fc; /* 호버 시 배경색 변경 */
    color: white;
  }

  /* login 스타일링 */
  .login_btn {
    color: #000000; /* 링크 텍스트 색상 */
    text-decoration: none; /* 밑줄 제거 */
    padding: 12px 20px; /* 링크 내부 여백 */
    border-radius: 10px; /* 링크 테두리 둥글게 */
    transition: background-color 0.3s ease; /* 배경색 변경 트랜지션 */
  }
  /* 호버 효과 */
  .login_btn:hover {
    background-color: #8976fd; /* 호버 시 배경색 변경 */
    color: white;
  }
`;

// 로고 스타일
const HeaderLogo = styled.div`
  // flex: 1 0 auto;
  background-image: url(${boardaLogo});
  background-size: cover;
  width: 160px;
  height: 40px;
  margin: 10px 10px;
  &:hover {
    cursor: pointer;
  }
`;

// 메뉴 담는 컨테이너 스타일
const ItemContainer = styled.div`
  display: flex;
  align-items: center;

  @media (max-width: 900px) {
    display: none; // 900px 이하에서는 숨김
  }
`;

// Drawer 메뉴 스타일
const HamburgerContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;

  a {
    color: #000000; /* 링크 텍스트 색상 */
    text-decoration: none; /* 밑줄 제거 */
    font-weight: bold;

    padding: 18px 24px; /* 링크 내부 여백 */
    transition: background-color 0.3s ease; /* 배경색 변경 트랜지션 */
    //setDrawerOpen(false);
  }
  a:hover {
    background-color: #8976fd; /* 호버 시 배경색 변경 */
    color: white;
  }
`;

const ProfileImage = styled.img`
  width: 2rem;
  height: 2rem;
  border-radius: 70%;
  object-fit: cover;
  border: black 0.1rem solid;
  margin-right: 1rem;
  margin-left: 0.5rem;
`;

const StyledAlarm = styled(Alarm)`
  && {
    margin-left: 5rem;
    padding-left: 3rem;
  }
`;

// 로그인 상태일 때 보이는 부분 - 마이페이지로, 로그아웃 버튼
// 헤더에서만 쓰이는 컴포넌트
function LoginUserDiv() {
  const [loginUser, setLoginUser] = useRecoilState(loginUserState);

  const logoutUser = {
    id: "",
    nickname: "",
    profile: "",
  };

  return (
    <>
      <p>{loginUser.nickname}님 환영합니다</p>
      <StyledAlarm></StyledAlarm>
      <Link to={`/my-page/${loginUser.id}`}>마이페이지</Link>

      <button
        onClick={() => {
          // 로그아웃 수행시
          localStorage.clear();
          alert("로그아웃 되었습니다.");
          window.location.href = "/home";
        }}
      >
        로그아웃
      </button>
      <ProfileImage
        src={`${import.meta.env.VITE_S3_BASE}${loginUser.profile}`}
        alt=""
      />
    </>
  );
}

// 유저 로그인 여부 확인 로직 바꿔야합니다 일단 지금은 세션 확인해보는거로
export default function Header() {
  const loginUser = useRecoilValue(loginUserState);
  const navigate = useNavigate();

  const [isDrawerOpen, setDrawerOpen] = useState(false);

  const toggleDrawer = (open) => (event) => {
    if (
      event.type === "keydown" &&
      (event.key === "Tab" || event.key === "Shift")
    ) {
      return;
    }

    setDrawerOpen(open);
  };

  return (
    <StyledHeader className="sticky top-0 z-10">
      <div>
        {/* 로고 */}
        <HeaderLogo
          onClick={() => {
            navigate("");
          }}
        ></HeaderLogo>
      </div>

      {/* 중간메뉴 */}
      <ItemContainer>
        <Link to="/home">홈</Link>
        <Link to="/moim/">모임</Link>
        <Link to="/game">게임</Link>
        <Link to="/cafe">매장</Link>
      </ItemContainer>

      {/* 우측메뉴 */}
      <ItemContainer>
        {!loginUser.id && (
          <Link to="/login" className="login_btn">
            로그인
          </Link>
        )}
        {loginUser.id && <LoginUserDiv></LoginUserDiv>}
      </ItemContainer>

      {/* Drawer */}
      <Hidden mdUp>
        <button onClick={toggleDrawer(true)}>
          &#9776; {/* 햄버거 아이콘 */}
        </button>
        <Drawer
          anchor="right"
          open={isDrawerOpen}
          onClose={toggleDrawer(false)}
        >
          <HamburgerContainer onClick={toggleDrawer(false)}>
            <Link to="/moim/">모임</Link>
            <Link to="/game">게임</Link>
            <Link to="/cafe">매장</Link>
            <Link to="/board">게시판</Link>
          </HamburgerContainer>
        </Drawer>
      </Hidden>
    </StyledHeader>
  );
}
