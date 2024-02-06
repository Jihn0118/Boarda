import React, { useState } from "react";
import {Link, useNavigate } from "react-router-dom";
import axios from "axios";
import { ssoLogin } from "../../api/userAPI";
import { useRecoilState } from 'recoil'
import { loginUserState } from "../../recoil/atoms/UserState";



const Login = () => {
  // 기본 로그인
  const navigate = useNavigate();
  const [Email, setEmail] = useState("");
  const [Password, setPassword] = useState("");

  const [loginUser, setLoginUser] = useRecoilState(loginUserState);

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
    // ssoLogin(body)
    // sessionStorage에 저장 일단은 로그인 됐다고 가정
    sessionStorage.setItem("loginUser", Email);
    setLoginUser(Email)
    //로그인이 되었다면 home 화면으로 이동
    navigate(`/home`)
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
          <button onClick={()=>ssoLogin('kakao')}>카카오 로그인</button>
          <button onClick={()=>ssoLogin('google')}>구글 로그인</button>
          <button onClick={()=>ssoLogin('naver')}>네이버 로그인</button>
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
