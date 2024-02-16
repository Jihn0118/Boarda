import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { ssoLogin, userAPI } from "../../api/userAPI";
import { useRecoilState } from "recoil";
import { loginUserState } from "../../recoil/atoms/userState";
import { Button, TextField, Grid, Typography } from "@mui/material";
import { Box } from "@mui/system";

export default function Login() {
  // 기본 로그인
  const navigate = useNavigate();
  const [id, setId] = useState("");
  const [password, setPassword] = useState("");

  const [loginUser, setLoginUser] = useRecoilState(loginUserState);

  async function doLogin() {
    let loginData = {
      id: id,
      password: password,
    };
    try {
      const res = await userAPI.login(loginData);
      console.log(res)
      // 스프링 HttpStatus.OK
      if (res.status === 200) {
        let newLoginUser = {
          id: res.data.memberId,
          nickname: res.data.memberNickname,
          profile: res.data.memberImage,
        };
        setLoginUser(newLoginUser);
        localStorage.setItem("loginUser", JSON.stringify(newLoginUser));
        alert("로그인 완료!");
        navigate("/home");
      } else {
        alert("로그인 실패!");
        navigate("/login");
      }
    } catch (error) {
      console.log(error);
      alert("로그인 실패!");
      console.log("로그인 에러 발생");
      navigate("/login");
    }
  }

  return (
    <Box className="mt-4 flex flex-col justify-center items-center mx-auto mb-4">
      <Typography variant="h4">로그인</Typography>
      <Box component="form" sx={{ "& > :not(style)": { m: 1 }, maxWidth: 300 }}>
        <TextField
          label="아이디(이메일)"
          type="email"
          value={id}
          onChange={(e) => setId(e.target.value)}
          fullWidth
        />
        <TextField
          label="비밀번호"
          type="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          fullWidth
        />
        <Button variant="contained" onClick={doLogin}>
          로그인
        </Button>
      </Box>
      <Typography variant="body1">또는</Typography>
      {/* <Grid container spacing={2} justifyContent="center">
        <Grid item>
          <Button variant="outlined" onClick={() => ssoLogin("kakao")}>
            카카오 로그인
          </Button>
        </Grid>
        <Grid item>
          <Button variant="outlined" onClick={() => ssoLogin("google")}>
            구글 로그인
          </Button>
        </Grid>
        <Grid item>
          <Button variant="outlined" onClick={() => ssoLogin("naver")}>
            네이버 로그인
          </Button>
        </Grid>
      </Grid> */}
      <Grid container spacing={2} justifyContent="center">
        <Grid item>
          <Link to="/find_pw">
            <Button variant="text">비밀번호 찾기</Button>
          </Link>
        </Grid>
        <Grid item>
          <Link to="/signup">
            <Button variant="text">회원가입</Button>
          </Link>
        </Grid>
      </Grid>
    </Box>
  );
}

// return (
//   <div>
//     <div className="mt-4">
//       <div className="flex flex-col justify-start items-center mx-auto mb-4">
//         <div>
//           <label>아이디(이메일)</label>
//           <input
//             type="email"
//             value={id}
//             onChange={(e) => setId(e.target.value)}
//           />
//         </div>
//         <div>
//           <label>비밀번호</label>
//           <input
//             type="password"
//             value={password}
//             onChange={(e) => setPassword(e.target.value)}
//           />
//         </div>
//         <br />
//         <div>
//           <button
//             className="border-4 border-indigo-500/100"
//             onClick={doLogin}
//           >
//             로그인
//           </button>
//         </div>

//         <div>또는</div>
//         <div>
//           <>
//             <button onClick={() => ssoLogin("kakao")}>카카오 로그인</button>
//             <button onClick={() => ssoLogin("google")}>구글 로그인</button>
//             <button onClick={() => ssoLogin("naver")}>네이버 로그인</button>
//           </>
//         </div>

//         <div>
//           <Link to="/find_pw">
//             <button>비밀번호 찾기</button>
//           </Link>
//           <Link to="/signup">
//             <button>회원가입</button>
//           </Link>
//         </div>
//       </div>
//     </div>
//   </div>
// );
