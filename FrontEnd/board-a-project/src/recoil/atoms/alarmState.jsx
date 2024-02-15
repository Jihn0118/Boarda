import { atom } from "recoil";

export const alarmState = atom({
    key: "alarmState",
    default: {
      alarmCount: "", // 미확인 알람 갯수 start로 확인
      
    },
  });
