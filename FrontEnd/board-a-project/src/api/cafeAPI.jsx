import api from "./api";

const END_POINT = 'cafe';

export const getCafeList = async (location, brand) => {
    try {
      const response = await api.get(`${END_POINT}/list`,{
        params:{
            location: location,
            brand: brand
        }
      });
    //   console.log(response.data)
      return response.data;
    } catch (error) {
      console.error('데이터를 가져오는 중 에러 발생:', error);
    }
  };