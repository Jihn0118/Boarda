import api from "./api";

const END_POINT = "review";

const reviewAPI = {
  // 모든 카페리스트 (모임 이력에 대해 후기 작성할 때 뿌려줄 목록)
  // get, /cafelist 파라미터 x
  getCafeList() {
    return api({
      method: "get",
      url: `${END_POINT}/cafelist`,
    });
  },

  // 내가 작성한 리뷰 리스트 요청 -> get, /myreview
  // 파라미터 -> int userNum (이후 토큰으로 수정 예정)
  getMyReview() {
    return api({
      method: "get",
      url: `${END_POINT}/myreview`,
    });
  },

  // 리뷰 삭체 요청
  // put, /delete?reviewId=int?userNum=int
  deleteMyReview(reviewId, userNum) {
    return api({
      method: "put",
      url: `${END_POINT}/delete`,
      params: {
        reviewId: reviewId,
        userNum: userNum,
      },
    });
  },

  // 리뷰 등록 요청 (멀티파트)
  // 이미지 리스트 + 유저 객체 같이 보낼 것
  // 폼데이터 직접 만들어서 파라미터로 넣기
  registMyReview(formData) {
    return api({
      method: "post",
      url: `${END_POINT}/regist`,
      headers: {
        "Content-Type": "multipart/form-data",
      }, // 멀티파트 헤더 추가
      data: formData,
    });
  },
};

export default reviewAPI;
