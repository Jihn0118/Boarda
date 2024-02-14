import api from "./api";

const END_POINT = "moim";

export const moimAPI = {
  // 지금까지 속했던 모든 모임 목록 get -> /moim/mymoimlist?user_num={user_num}
  getMoimHistory(userNum, jwt) {
    return api({
      method: "get",
      url: `${END_POINT}/mymoimlist`,
      withCredentials: true,
      headers: {
        jwt: jwt,
      },
      params: {
        user_num: userNum,
      },
    });
  },

  // 현재 참여중인 그룹 1개 조회 /moim/mymoim?user_num={user_num}
  getParticipatingMoim(userNum) {
    return api({
      method: "get",
      url: `${END_POINT}/mymoim`,
      params: {
        user_num: userNum,
      },
    });
  },
};

export const getMoimList = async (location, sort) => {
  try {
    const response = await api({
      method: "get",
      url: `${END_POINT}/list`,
      withCredentials:true,
      params: {
        location: location,
        sort: sort,
      },
      
    });
    console.log(response);
    return response.data;
  } catch (error) {
    console.error("데이터를 가져오는 중 에러 발생:", error);
  }
};

export const checkRoom = async (num) => {
  try {
    const response = await api({
      method: "get",
      url: `${END_POINT}/checkroom`,
      withCredentials:true,
      params: {
        num: num,
      },
    });
    return response.data;
  } catch (error) {
    console.log(error);
  }
};

export const saveMoim = async (moim) => {
  try {
    const response = await api({
      method: "post",
      url: `${END_POINT}/room`,
      withCredentials:true,
      data: moim,
    });
    return response.data;
  } catch (error) {
    console.error("모임 저장 중 에러가 발생했습니다:", error);
  }
};

export const joinMoim = async (join) => {
  console.log(join);
  try {
    const response = await api({
      method: "post",
      url: `${END_POINT}/join`,
      withCredentials:true,
      data: join,
    });
    return response.data;
  } catch (error) {
    console.error("모임 저장 중 에러가 발생했습니다:", error);
  }
};
