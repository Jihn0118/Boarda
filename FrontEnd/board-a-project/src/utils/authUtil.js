

// 로그인 유저인지 확인하는 로직 작성
export function isAuthed() {
    if(!sessionStorage.getItem("loginUser")) {
        return false;
    }
    return true;
}