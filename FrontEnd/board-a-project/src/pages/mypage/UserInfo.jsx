import React from "react";
import { useRecoilState } from "recoil";
import { loginUserState } from "../../recoil/atoms/userState";

export default function UserInfo() {
  const [loginUser, setLoginUser] = useRecoilState(loginUserState);

  return (
    <div>
      <div>
        <p>프로필 이미지</p>
        <input type="text" />
      </div>
      <div>
        <p>아이디</p>
        <input type="text" />
      </div>
      <div>
        <p>비밀번호</p>
        <input type="text" />
      </div>
      <div>
        <p>닉네임</p>
        <input type="text" />
      </div>
      <div>
        <p>생년월일</p>
        <input type="text" />
      </div>
      <div>
        <p>연락처</p>
        <input type="text" />
      </div>
      <div>
        <p>성별</p>
        <input type="radio" name="gender" value="female" />
        <input type="radio" name="gender" value="male" />
      </div>
    </div>
  );
}
