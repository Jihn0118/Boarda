import api from "./api";


const END_POINT = 'mypage';


export default myPageAPI = {
    // 회원탈퇴 -> profile?id={id}로만 보내기
    userWithdraw(userId) {
        return api({
            method: 'delete',
            url: `${END_POINT}/profile`,
            params: {
                id: userId
            }
        });
    },
    // 비밀번호 찾기 -> forgetpwd?id=a@a.com
    userFindPwd(userId) {
        return api({
            method: 'post',
            url: `${END_POINT}/forgetpwd`,
            params: {
                id: userId
            }
        });

    },

    // 비밀번호 변경 put -> modifypwd -> body에 유저 정보 다 담아서 주기
    // 이 메서드 파라미터는 유저 객체
    userChangePwd(user) {
        return api({
            method: 'put',
            url: `${END_POINT}/modifypwd`,
            data: user
        });

    },

    // 팔로우 하기 post인데 쿼리스트링으로 ->
    // follow?id=what2@w.com&nickname=박소영&flag=F
    userMakeFollow(userId, followNickname,flag) {
        return api({
            method: 'post',
            url: `${END_POINT}/modifypwd`,
            params: {
                id: userId,
                nickname: followNickname,
                flag: flag
            }
        });

    },


}
