import React, { useState } from "react";
import axios from "axios";

const Login = () => {
  // 회원가입
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
    const request = axios.post("http://localhost:5000/api/signup", body)
      .then(response => response.data)
      .catch(error => console.log(error));
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
          <button>회원가입</button>
        </form>
      </div>
    </div>
  );
};

export default Login;
