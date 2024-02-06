import { atom } from "recoil";

// 채팅방이 열린 상태인지 닫힌 상태인지 저장
export const chatOpenState = atom({
  key: "chatOpenState",
  default: false,
});

// 지금까지의 채팅 메시지들
export const prevChatMessageState = atom({
  key: "chatMessageState",
  default: [],
});

// 지금 입력하고 있는 메시지
export const nowChatMessageState = atom({
  key: "nowChatMessageState",
  default: "",
});

// 소켓
export const webSocket = atom({
  key: "webSocket",
  default: null,
});
