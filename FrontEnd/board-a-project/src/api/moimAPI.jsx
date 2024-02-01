import axios from 'axios';

// "//www.boarda.site/api/moim/checkroom"
// "//70.12.246.57:8081/api/moim/checkroom"

export const getMoimList = async (location, sort) => {
  try {
    const response = await axios.get(`//70.12.246.57:8081/api/moim/list?location=${location}&sort=${sort}`);
    console.log(response.data)
    return response.data;
  } catch (error) {
    console.error('데이터를 가져오는 중 에러 발생:', error);
  }
};

export const checkRoom = async (num) => {
  try {
    const response = await axios.get('//www.boarda.site/api/moim/checkroom', {
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
        const response = await axios.post(`//www.boarda.site/api/moim/room`, moim);
        return response.data;
    } catch (error) {
        console.error('모임 저장 중 에러가 발생했습니다:', error);
    }
};

export const joinMoim = async (join) => {
    console.log(join);
    try {
      const response = await axios.post(`//www.boarda.site/api/moim/join`, join);
      return response.data;
    } catch (error) {
      console.error('모임 저장 중 에러가 발생했습니다:', error);
    }
  };