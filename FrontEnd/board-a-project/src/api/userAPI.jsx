import api from "./api";

const END_POINT = "member";

export const userAPI = {
  // 로그인 요청 POST /login

  login(loginData) {
    return api({
      method: "post",
      url: `${END_POINT}/login`,
      data: loginData,
    });
  },

  // 회원가입 @PostMapping("/signup")
  // 이미지까지 싹 합쳐서 formData로 보내기
  signUp(formData) {
    return api({
      method: "post",
      url: `${END_POINT}/signup`,
      headers: {
        "Content-Type": "multipart/form-data",
      }, // 멀티파트 헤더 추가
      data: formData,
    });
  },

  // 이메일(아이디) 중복확인
  checkId(id) {
    return api({
      method: "get",
      url: `${END_POINT}/checkid`,
      params: {
        id: id,
      },
    });
  },

  // 닉네임 중복확인
  checkNickname(nickname) {
    return api({
      method: "get",
      url: `${END_POINT}/checknickname`,
      params: {
        nickname: nickname,
      },
    });
  },
};

// --- SSO 로그인 -------------------------------
const KAKAO_API_KEY = import.meta.env.VITE_KAKAO_API_KEY; //REST API KEY for Kakao
const GOOGLE_CLIENT_ID = import.meta.env.VITE_GOOGLE_CLIENT_ID; //Client ID for Google
const NAVER_CLIENT_ID = import.meta.env.VITE_NAVER_CLIENT_ID; //Client ID for Naver

const redirect_uri = `https://www.boarda.site/auth`; // ${END_POINT}/auth

// oauth 요청 URL
const kakaoURL = `https://kauth.kakao.com/oauth/authorize?client_id=${KAKAO_API_KEY}&redirect_uri=${redirect_uri}&response_type=code`;
const googleURL = `https://accounts.google.com/o/oauth2/v2/auth?client_id=${GOOGLE_CLIENT_ID}&redirect_uri=${redirect_uri}&response_type=code&scope=openid email`;
const naverURL = `https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=${NAVER_CLIENT_ID}&state=1234&redirect_uri=${redirect_uri}`;

export const ssoLogin = (site) => {
  let url;
  switch (site) {
    case "kakao":
      url = kakaoURL;
      break;
    case "google":
      url = googleURL;
      break;
    case "naver":
      url = naverURL;
      break;

    default:
      break;
  }
  if (url) {
    window.location.href = url;
  }
};

// --- 비밀번호 찾기 -------------------------------
// export const findPW = async(Email) => {
//     try {
//         const response = await axios.post(`//www.boarda.site:8080/member/???`,{ // ${END_POINT}/member/login
//             params: {
//                 id : Email,
//             }
//         });
//         return response.data;
//     } catch (error) {
//         console.error('비밀번호 찾기 중 에러가 발생했습니다:', error);
//     }
// }

// --- 비밀번호 변경 -------------------------------
export const changePW = async (user_info) => {
  try {
    const response = await axios.put(`${END_POINT}/member/modifypwd`, {
      // ${END_POINT}/member/modifypwd
      params: {
        id: user_info.id,
        password: user_info.pw,
      },
    });
    return response.data;
  } catch (error) {
    console.error("비밀번호 변경 중 에러가 발생했습니다:", error);
  }
};
