import api from "./api";

const END_POINT = "alarm";

export const alarmAPI = {
  // sse 연결 후 최신 알람 받아오기
  getStart() {
    return api({
      method: "get",
      url: `${END_POINT}/start`,
    });
  },
  // 알람 카운트 받아오기
  getCount() {
    return api({
      method: "get",
      url: `${END_POINT}/count`,
    });
  },
  // 알람 읽음
  readAlarm() {
    return api({
      method: "get",
      url: `${END_POINT}/read`,
    });
  },

  // 알람 리스트 받아오기
  getList() {
    return api({
      method: "get",
      url: `${END_POINT}/list`,
    });
  },



};
