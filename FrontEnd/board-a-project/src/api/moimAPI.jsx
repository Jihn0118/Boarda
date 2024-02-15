import api from "./api";

const END_POINT = "moim";

export const moimAPI = {
  // 방나가기 delete 요청
  exitMoim(moim_id) {
    return api({
      method: "delete",
      url: `${END_POINT}/exit`,
      params: {
        moim_id: moim_id,
      },
    });
  },

  // 지금까지 속했던 모든 모임 목록 get -> /moim/mymoimlist?user_num={user_num}
  getMoimHistory() {
    return api({
      method: "get",
      url: `${END_POINT}/mymoimlist`,
    });
  },

  // 현재 참여중인 그룹 1개 조회 /moim/mymoim?user_num={user_num}
  getParticipatingMoim() {
    return api({
      method: "get",
      url: `${END_POINT}/mymoim`,
    });
  },
};

export const getMoimList = async (location, sort) => {
  try {
    const response = await api({
      method: "get",
      url: `${END_POINT}/list`,
      params: {
        location: location,
        sort: sort,
      },
    });
    console.log(response.data);
    return response.data;
  } catch (error) {
    console.error("데이터를 가져오는 중 에러 발생:", error);
  }
};

export const checkRoom = async () => {
  try {
    const response = await api({
      method: "get",
      url: `${END_POINT}/checkroom`,
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
      data: join,
    });
    return response.data;
  } catch (error) {
    console.error("모임 저장 중 에러가 발생했습니다:", error);
  }
};

export const urgentMoim = () => {
  return api({
    method: "get",
    url: `${END_POINT}/deadline`,
  });
};
