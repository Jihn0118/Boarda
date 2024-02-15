import api from "./api";

const END_POINT = "mypage";

const myPageAPI = {
  // 피드목록 요청 -> 해당 유저가 작성한 피드 리스트 리턴
  // get, /profile?id=userId
  getUserFeeds(id) {
    return api({
      method: "get",
      url: `${END_POINT}/profile`,
      params: {
        id: id,
      },
    });
  },

  // 회원탈퇴 -> profile?id={id}로만 보내기
  userWithdraw() {
    return api({
      method: "delete",
      url: `${END_POINT}/profile`,
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

  getFollowList() {
    return api({
      method: "get",
      url: `${END_POINT}/follow`,
    });
  },

  // 팔로우 하기 post인데 쿼리스트링으로 ->
  // follow?id=what2@w.com&nickname=박소영&flag=F
  userMakeFollow(followNickname, flag) {
    return api({
      method: "post",
      url: `${END_POINT}/follow`,
      params: {
        nickname: followNickname,
        flag: flag,
      },
    });
  },
};
export default myPageAPI;
