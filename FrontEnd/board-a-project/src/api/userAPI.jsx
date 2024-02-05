import api from "./api";

// --- 자체 로그인 -------------------------------
export const login = async(user_info) => {
    try {
        const response = await axios.post(`${END_POINT}/member/login`,{ // ${END_POINT}/member/login
            params: {
                id : user_info.id,
                password : user_info.pw
            }
        });
        return response.data;
    } catch (error) {
        console.error('로그인 중 에러가 발생했습니다:', error);
    }
}


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

// --- 회원가입 -------------------------------
// 이메일(아이디) 중복 체크
export const isEmailUnique = async(Email) => {
    try {
        const response = await axios.get(`${END_POINT}/member/checkid`,{ // ${END_POINT}/member/checkid
            params: {
                id : Email,
            }
        });
        return response.data;
    } catch (error) {
        console.error('아이디 중복체크 중 에러가 발생했습니다:', error);
    }
}
// 닉네임 중복 체크
export const isNicknameUnique = async(nickname) => {
    try {
        const response = await axios.get(`${END_POINT}/member/checknickname`,{ // ${END_POINT}/member/checknickname
            params: {
                nickname : nickname,
            }
        });
        return response.data;
    } catch (error) {
        console.error('닉네임 중복체크 중 에러가 발생했습니다:', error);
    }
}

// 회원가입
export const signup = async(user_info) => {
    try {
        const response = await axios.post(`${END_POINT}/member/signup`,{ // ${END_POINT}/member/signup
            params: {
                id : user_info.id,
                password : user_info.pw,
                nick_name : user_info.nick_name,
                birth : user_info.birth,
                gender : user_info.gender,
                image : user_info.image,
            }
        });
        return response.data;
    } catch (error) {
        console.error('회원가입 중 에러가 발생했습니다:', error);
    }
}

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
export const changePW = async(user_info) => {
    try {
        const response = await axios.put(`${END_POINT}/member/modifypwd`,{ // ${END_POINT}/member/modifypwd
            params: {
                id : user_info.id,
                password : user_info.pw
            }
        });
        return response.data;
    } catch (error) {
        console.error('비밀번호 변경 중 에러가 발생했습니다:', error);
    }
}