import React, { useState } from "react";
import {Link} from "react-router-dom";
import axios from "axios";

const Login = () => {
  // 기본 로그인
  const [Email, setEmail] = useState("");
  const [Password, setPassword] = useState("");

  const onEmailHandler = (event) => {
    setEmail(event.currentTarget.value);
  };
  const onPasswordHandler = (event) => {
    setPassword(event.currentTarget.value);
  };

  const onSubmitHandler = (event) => {
    event.preventDefault();
    console.log("Email: ", Email);
    console.log("Password: ", Password);

    let body = {
      email: Email,
      password: Password,
    };
    // 백으로 보낼 axios 코드 필요
    const request = axios.post("http://localhost:5000/api/login", body)
      .then(response => response.data)
      .catch(error => console.log(error));
  };

  // SSO 로그인
  const Rest_api_key_kakao = "b4ec8b14bab859d5b3e6789c5103b72b"; //REST API KEY for Kakao
  const GOOGLE_CLIENT_ID =
    "134380099254-jr83bp4ihmc6omh4fpcr0gh08ltfrmcv.apps.googleusercontent.com"; //Client ID for Google
  const NAVER_CLIENT_ID = "jAGmd6IHSiQiCmC22ZWe"; //Client ID for Naver
  const redirect_uri = "http://localhost:5173/auth"; //Redirect URI 페이지 SSO 사이트별로 만들어야함

  // oauth 요청 URL
  const kakaoURL = `https://kauth.kakao.com/oauth/authorize?client_id=${Rest_api_key_kakao}&redirect_uri=${redirect_uri}&response_type=code`;
  const googleURL = `https://accounts.google.com/o/oauth2/v2/auth?client_id=${GOOGLE_CLIENT_ID}&redirect_uri=${redirect_uri}&response_type=code&scope=openid email`;
  const naverURL = `https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=${NAVER_CLIENT_ID}&state=1234&redirect_uri=${redirect_uri}`;
  const handleKaKaoLogin = () => {
    window.location.href = kakaoURL;
  };
  const handleGoogleLogin = () => {
    window.location.href = googleURL;
  };
  const handleNaverLogin = () => {
    window.location.href = naverURL;
  };


  const onSignupButtonClick = () => {
    // '/signup'으로 내비게이션을 수행
    navigate('/signup');
  };
  
  return (
    <div>
      <div>
        <form onSubmit={onSubmitHandler}>
          <label>Email</label>
          <input type="email" value={Email} onChange={onEmailHandler} />
          <label>비밀번호</label>
          <input
            type="password"
            value={Password}
            onChange={onPasswordHandler}
          />
          <br />
          <button>로그인</button>
        </form>
      </div>
      <div>또는</div>
      <div>
        <>
          <button onClick={handleKaKaoLogin}>카카오 로그인</button>
          <button onClick={handleGoogleLogin}>구글 로그인</button>
          <button onClick={handleNaverLogin}>네이버 로그인</button>
        </>
      </div>

      <div>
        <Link to="/find_pw">
          <button>비밀번호 찾기</button>
        </Link>
        <Link to="/signup">
          <button>회원가입</button>
        </Link>
      </div>
    </div>
  );
};

export default Login;
