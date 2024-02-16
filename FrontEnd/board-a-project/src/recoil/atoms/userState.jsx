import { atom } from "recoil";
const loginUser = JSON.parse(localStorage.getItem("loginUser"));

export const userState = atom({
  key: "userState",
  default: [],
});

export const loginUserState = atom({
  key: "loginUserState",
  default: {
    id: loginUser ? loginUser.id : "", // 이메일(아이디)
    nickname: loginUser ? loginUser.nickname : "", //닉네임
    profile: loginUser ? loginUser.profile : "", //프로필 경로
  },
});
