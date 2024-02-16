import React, { useEffect, useState } from "react";
import { Modal, TextField, Button } from "@mui/material";
import { Box } from "@mui/system";
import reviewAPI from "../../api/reviewAPI";
import { useRecoilValue } from "recoil";
import { loginUserState } from "../../recoil/atoms/userState";


function ReviewRegistModal() {
  // 이 리뷰를 작성하는 로그인 유저 정보는 리코일에서 받아오기
  // props로 하위 모달에 정보 넘겨줄 것
  // 지금 로그인, 그룹이력 구현이 안 되어 있는데 나중에 거기서 상태값 받아온 다음 prop으로 넘겨주게 바꾸면 됨
  const loginUser = useRecoilValue(loginUserState);



  

  

  return (
    <div>
      <Button variant="contained" color="primary" onClick={handleOpen}>
        피드작성
      </Button>
      <ReviewModal userNum={loginUser.userNum}  ></ReviewModal>
    </div>
  );
}

export default ReviewRegistModal;
