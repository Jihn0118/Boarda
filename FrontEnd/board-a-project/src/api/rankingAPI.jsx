import api from "./api";

const END_POINT = "ranking";

export const getRankingList = async () => {
  try {
    const response = await api.get(`${END_POINT}`);
    console.log(response.data)
    return response.data;
  } catch (error) {
    console.error("데이터를 가져오는 중 에러 발생:", error);
  }
};
