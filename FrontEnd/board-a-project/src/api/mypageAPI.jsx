import axios from 'axios'
import api from "./api";

export const user_info = async(user_id) => {
    try {
        const response = await api.get(`${END_POINT}/mypage/profile`,{ // ${END_POINT}/member/login
            params: {
                id : user_id,
            }
        });
        return response.data;
    } catch (error) {
        console.error('로그인 중 에러가 발생했습니다:', error);
    }
}