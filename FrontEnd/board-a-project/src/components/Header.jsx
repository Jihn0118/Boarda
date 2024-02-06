import styled from "@emotion/styled";
import React from "react";
import { Link } from "react-router-dom";
import { useRecoilState, useRecoilValue } from "recoil";
import { loginUserState } from "../recoil/atoms/UserState";

// 헤더 - mui paper로 다시 작업중입니다.

// StyledHeader 컴포넌트 생성
const StyledHeader = styled.header`
  background-color: #d98f8f; /* 배경색 설정 */
  padding: 10px 20px; /* 내부 여백 설정 */
  display: flex;
  justify-content: space-between;
  align-items: center;

  /* Link 스타일링 */
  a {
    color: #fff; /* 링크 텍스트 색상 */
    text-decoration: none; /* 밑줄 제거 */
    margin-right: 10px; /* 각 링크 사이 간격 */
    padding: 8px 12px; /* 링크 내부 여백 */
    border-radius: 5px; /* 링크 테두리 둥글게 */
    transition: background-color 0.3s ease; /* 배경색 변경 트랜지션 */
  }

  /* 링크 호버 효과 */
  a:hover {
    background-color: #555; /* 호버 시 배경색 변경 */
  }
`;
const HeaderLogo = styled.div`
  // flex: 1 0 auto;
  background-image: url("src/assets/images/boardaLogo.png");
  background-size: cover;
  width: 11vw;
  height: 5vh;
`;

// 로그인 상태일 때 보이는 부분 - 마이페이지로, 로그아웃 버튼
// 헤더에서만 쓰이는 컴포넌트
function LoginUserDiv() {
  const [loginUser, setLoginUser] = useRecoilState(loginUserState);

  return (
    <>
      <p>{loginUser.nickname}님 환영합니다</p>
      <Link to="/myPage">마이페이지</Link>
      <button
        onClick={() => {
          // 로그아웃 수행시
          sessionStorage.clear; // 세션스토리지 비우기
          setLoginUser({}); // 로그인 유저 빈객체로 바꾸기
          localStorage.removeItem("jwt");
          alert("로그아웃 되었습니다.");
          window.location.href = "/home";
        }}
      >
        로그아웃
      </button>
    </>
  );
}

// 유저 로그인 여부 확인 로직 바꿔야합니다 일단 지금은 세션 확인해보는거로
export default function Header() {
  const loginUser = useRecoilValue(loginUserState);

  return (
    <StyledHeader>
      <HeaderLogo></HeaderLogo>
      <div>
        <Link to="/home">홈</Link>
        <Link to="/moim/">모임</Link>
        <Link to="/game">게임</Link>
        <Link to="/cafe">매장</Link>
        <Link to="/board">게시판</Link>
      </div>
      <div>
        {!loginUser.id && <Link to="/login">로그인</Link>}
        {loginUser.id && LoginUserDiv}
      </div>
          <Link to="/my-page/2">마이페이지개발중</Link>
    </StyledHeader>
  );
}

// const Header = () => {
//   return (
//     <header>
//       <Link to="/home">홈</Link>
//       &nbsp;&nbsp;|&nbsp;&nbsp;
//       <Link to="/moim/list">모임</Link>
//       &nbsp;&nbsp;|&nbsp;&nbsp;
//       <Link to="/game">게임</Link>
//       <Link to="/login">로그인</Link>
//       <hr/>
//     </header>
//   );
// };

// export default Header;

// import React from 'react';
// import { Link as RouterLink } from 'react-router-dom';
// import { AppBar, Toolbar, IconButton, Typography, Link, Box } from '@mui/material';
// import HomeIcon from '@mui/icons-material/Home';
// import GroupIcon from '@mui/icons-material/Group';
// import GameIcon from '@mui/icons-material/Gamepad';

// const Header = () => {
//   return (
//     <AppBar position="static" style={{ background: 'linear-gradient(45deg, #FE6B8B 30%, #FF8E53 90%)' }}>
//       <Toolbar>
//         <Link color="inherit" component={RouterLink} to="/home">
//           <Box display="flex" alignItems="center" marginRight={2}>
//             <IconButton edge="start" color="inherit" aria-label="home" marginRight={1}>
//               <HomeIcon />
//             </IconButton>
//             <Typography variant="h6" component="div">
//               홈
//             </Typography>
//           </Box>
//         </Link>

//         <Link color="inherit" component={RouterLink} to="/moim/list">
//           <Box display="flex" alignItems="center" marginRight={2}>
//             <IconButton edge="start" color="inherit" aria-label="moim" marginRight={1}>
//               <GroupIcon />
//             </IconButton>
//             <Typography variant="h6" component="div">
//               모임
//             </Typography>
//           </Box>
//         </Link>

//         <Link color="inherit" component={RouterLink} to="/game">
//           <Box display="flex" alignItems="center">
//             <IconButton edge="start" color="inherit" aria-label="game" marginRight={1}>
//               <GameIcon />
//             </IconButton>
//             <Typography variant="h6" component="div">
//               게임
//             </Typography>
//           </Box>
//         </Link>
//       </Toolbar>
//     </AppBar>
//   );
// };

// export default Header;
