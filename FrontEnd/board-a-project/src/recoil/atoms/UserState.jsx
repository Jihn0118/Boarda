import { atom } from 'recoil';

export const loginUserState = atom({
    key: 'loginUserState',
    default: sessionStorage.getItem("loginUser") || null, 
})