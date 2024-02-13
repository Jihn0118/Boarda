import styled from "@emotion/styled";
import React from "react";
import { Link, useNavigate } from "react-router-dom";
import { useRecoilState, useRecoilValue } from "recoil";
import { loginUserState } from "../recoil/atoms/userState";
import boardaLogo from "../assets/images/boardaLogo.png";
import bellImg from "../assets/images/bellImg.png";
import Alarm from "./Alarm";

// 헤더 - mui paper로 다시 작업중입니다.

// StyledHeader 컴포넌트 생성
const StyledHeader = styled.header`
  background-color: #ffffff; /* 배경색 설정 */
  padding: 0px 18px; /* 내부 여백 설정 */
  display: flex;
  justify-content: space-between;
  align-items: stretch;

  a {
    color: #000000; /* 링크 텍스트 색상 */
    text-decoration: none; /* 밑줄 제거 */
    font-weight: bold;

    padding: 20px 24px; /* 링크 내부 여백 */
    transition: background-color 0.3s ease; /* 배경색 변경 트랜지션 */
  }
  a:hover {
    background-color: #8976fd; /* 호버 시 배경색 변경 */
    color: white;
  }

  /* login 스타일링 */
  .login_btn {
    color: #000000; /* 링크 텍스트 색상 */
    text-decoration: none; /* 밑줄 제거 */
    margin: 5px 10px; /* 각 링크 사이 간격 */
    padding: 0px 12px; /* 링크 내부 여백 */
    border-radius: 5px; /* 링크 테두리 둥글게 */
    transition: background-color 0.3s ease; /* 배경색 변경 트랜지션 */
  }
  /* 호버 효과 */
  .login_btn:hover {
    background-color: #8976fd; /* 호버 시 배경색 변경 */
    color: white;
  }
`;

const ItemContainer = styled.div`
  display: flex;
  align-items: center;
`;

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

// 로그인 상태일 때 보이는 부분 - 마이페이지로, 로그아웃 버튼
// 헤더에서만 쓰이는 컴포넌트
function LoginUserDiv() {
  const [loginUser, setLoginUser] = useRecoilState(loginUserState);

  const logoutUser = {
    id: "",
    password: "",
    nickname: "",
    birth: "",
    gender: "",
    profileImage: "",
    jwt: "",
    num: "",
    userName: "",
  };

  return (
    <>
      <p>{loginUser.nickname}님 환영합니다</p>
      <Alarm></Alarm>
      <Link to={`/myPage/${loginUser.id}`}>마이페이지</Link>
      <button
        onClick={() => {
          // 로그아웃 수행시
          setLoginUser(logoutUser); // 로그인유저 정보 비우기
          localStorage.clear();
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
  const navigate = useNavigate();
  console.log(loginUser);

  return (
    <StyledHeader>
      <div>
        <HeaderLogo
          onClick={() => {
            navigate("/home");
          }}
        ></HeaderLogo>
      </div>

      <ItemContainer>
        <Link to="/moim/">모임</Link>
        <Link to="/game">게임</Link>
        <Link to="/cafe">매장</Link>

        <Link to="/board">게시판</Link>
      </ItemContainer>

      <ItemContainer>
        {!loginUser.id && (
          <Link to="/login" className="login_btn">
            로그인
          </Link>
        )}
        {loginUser.id && <LoginUserDiv></LoginUserDiv>}

        <Link to="/my-page/2">마이페이지</Link>
      </ItemContainer>
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
