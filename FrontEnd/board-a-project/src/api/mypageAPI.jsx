import axios from 'axios'

export const user_info = async(user_id) => {
    try {
        const response = await axios.get(`//http://localhost:8081/mypage/profile`,{ // ${END_POINT}/member/login
            params: {
                id : user_id,
            }
        });
        return response.data;
    } catch (error) {
        console.error('로그인 중 에러가 발생했습니다:', error);
    }
}