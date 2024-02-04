import { atom } from "recoil";

export const userState = atom({
  key: "userState",
  default: [],
});

export const loginUserState = atom({
  key: "loginUserState",
  default: {
    id: '',
    password: '',
    nickname: '',
    birth: '',
    gender: '',
    profileImage: '',
    jwt: '',
    refresh: '',
  }
});
