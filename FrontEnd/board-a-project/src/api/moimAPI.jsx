import api from "./api";

const END_POINT = "moim";

export const moimAPI = {
  // 지금까지 속했던 모든 모임 목록 get -> /moim/mymoimlist?user_num={user_num}
  getMoimHistory(userNum) {
    return api({
      method: "get",
      url: `${END_POINT}/mymoimlist`,
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
    const response = await api.get(
      `${END_POINT}/list?location=${location}&sort=${sort}`
    );
    console.log(response.data);
    return response.data;
  } catch (error) {
    console.error("데이터를 가져오는 중 에러 발생:", error);
  }
};

export const checkRoom = async (num) => {
  try {
    const response = await api.get(`${END_POINT}/checkroom`, {
      params: {
        num,
      },
    });
    return response.data;
  } catch (error) {
    console.log(error);
  }
};

export const saveMoim = async (moim) => {
  try {
    const response = await api.post(`${END_POINT}/room`, moim);
    return response.data;
  } catch (error) {
    console.error("모임 저장 중 에러가 발생했습니다:", error);
  }
};

export const joinMoim = async (join) => {
  console.log(join);
  try {
    const response = await api.post(`${END_POINT}/join`, join);
    return response.data;
  } catch (error) {
    console.error("모임 저장 중 에러가 발생했습니다:", error);
  }
};
