import api from "./api";

const END_POINT = "game";

export const getGameList = async (time, num, keyword) => {
  try {
    const response = await api({
      method: "get",
      url: `${END_POINT}/list`,
      params: {
        time: time,
        num: num,
        keyword: keyword,
      },
    });
    console.log(response.data);
    return response.data;
  } catch (error) {
    console.error("데이터를 가져오는 중 에러 발생:", error);
  }
};

export const getGameDetail = async (gameId) => {
  try {
    const response = await api({
      method: "get",
      url: `${END_POINT}/detail`,
      params: {
        game_id: gameId,
      },
    });
    console.log(response.data);
    return response.data;
  } catch (error) {
    console.error("데이터를 가져오는 중 에러 발생:", error);
  }
};
