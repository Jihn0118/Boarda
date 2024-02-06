import api from "./api";

const END_POINT = "mypage";

const myPageAPI = {
  // 참여중인 그룹 요청 -> /grouplist?id={user_id}
  // 그룹 객체 리턴
  getParticipatingMoim(userId) {
    return api({
      method: "get",
      url: `${END_POINT}/grouplist`,
      params: {
        id: userId,
      },
    });
  },

  // 피드목록 요청 -> 해당 유저가 작성한 피드 리스트 리턴
  // get, /profile?id=userId
  getUserFeeds(userId) {
    return api({
      method: "get",
      url: `${END_POINT}/profile`,
      params: {
        id: userId,
      },
    });
  },

  // 회원탈퇴 -> profile?id={id}로만 보내기
  userWithdraw(userId) {
    return api({
      method: "delete",
      url: `${END_POINT}/profile`,
      params: {
        id: userId,
      },
    });
  },
  // 비밀번호 찾기 -> forgetpwd?id=a@a.com
  userFindPwd(userId) {
    return api({
      method: "post",
      url: `${END_POINT}/forgetpwd`,
      params: {
        id: userId,
      },
    });
  },

  // 비밀번호 변경 put -> modifypwd -> body에 유저 정보 다 담아서 주기
  // 이 메서드 파라미터는 유저 객체
  userChangePwd(user) {
    return api({
      method: "put",
      url: `${END_POINT}/modifypwd`,
      data: user,
    });
  },

  // get 팔로우 목록 보기 ->/follow?id={user_id}
  // 리턴 -> OK : 팔로우객체

  getFollowList(userId) {
    return api({
      method: "get",
      url: `${END_POINT}/follow`,
      params: {
        id: userId,
      },
    });
  },

  // 팔로우 하기 post인데 쿼리스트링으로 ->
  // follow?id=what2@w.com&nickname=박소영&flag=F
  userMakeFollow(userId, followNickname, flag) {
    return api({
      method: "post",
      url: `${END_POINT}/modifypwd`,
      params: {
        id: userId,
        nickname: followNickname,
        flag: flag,
      },
    });
  },

  // 지금까지 속했던 모든 모임 목록 get ->   /history?id={user_id}
  getMoimHistory(userId) {
    return api({
      method: "get",
      url: `${END_POINT}/history`,
      params: {
        id: userId,
      },
    });
  },
};
export default myPageAPI;