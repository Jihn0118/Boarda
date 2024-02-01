import api from "./api";

const END_POINT = 'moim';

export const getMoimList = async (location, sort) => {
  try {
    const response = await api.get(`${END_POINT}/list?location=${location}&sort=${sort}`);
    console.log(response.data)
    return response.data;
  } catch (error) {
    console.error('데이터를 가져오는 중 에러 발생:', error);
  }
};

export const checkRoom = async (num) => {
  try {
    const response = await api.get(`${END_POINT}/checkroom`, {
      params: {
        num
      }
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
        console.error('모임 저장 중 에러가 발생했습니다:', error);
    }
};

export const joinMoim = async (join) => {
    console.log(join);
    try {
      const response = await api.post(`${END_POINT}/join`, join);
      return response.data;
    } catch (error) {
      console.error('모임 저장 중 에러가 발생했습니다:', error);
    }
  };