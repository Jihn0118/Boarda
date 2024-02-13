import { atom } from "recoil";

export const userState = atom({
  key: "userState",
  default: [],
});

export const loginUserState = atom({
  key: "loginUserState",
  default: {
    id: "", // 이메일
    password: "", // 비밀번호
    nickname: "", //닉네임
    birth: "", // 980118
    gender: "", // M or W
    profileImage: "", // image.png
    jwt: "", // token : "aslndlkasn"
    num: "", // num : 1
    userName: "",
  },
});
