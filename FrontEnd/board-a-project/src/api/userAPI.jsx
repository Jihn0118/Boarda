// SSO 로그인
const KAKAO_API_KEY = import.meta.env.VITE_KAKAO_API_KEY; //REST API KEY for Kakao
const GOOGLE_CLIENT_ID = import.meta.env.VITE_GOOGLE_CLIENT_ID; //Client ID for Google
const NAVER_CLIENT_ID = import.meta.env.VITE_NAVER_CLIENT_ID; //Client ID for Naver

const redirect_uri = "http://localhost:3000/auth"; // ${END_POINT}/auth

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
