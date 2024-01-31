import axios from 'axios';

export const getMoimList = async (location, sort) => {
  try {
    const response = await axios.get(`//www.boarda.site:8080/moim/list?location=${location}&sort=${sort}`);
    return response.data;
  } catch (error) {
    console.error('데이터를 가져오는 중 에러 발생:', error);
  }
};

export const checkRoom = async (num) => {
  try {
    const response = await axios.get('//www.boarda.site:8080/moim/checkroom', {
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
        const response = await axios.post(`//www.boarda.site:8080/moim/room`, moim);
        return response.data;
    } catch (error) {
        console.error('모임 저장 중 에러가 발생했습니다:', error);
    }
};

export const joinMoim = async (join) => {
    console.log(join);
    try {
      const response = await axios.post(`//www.boarda.site:8080/moim/join`, join);
      return response.data;
    } catch (error) {
      console.error('모임 저장 중 에러가 발생했습니다:', error);
    }
  };