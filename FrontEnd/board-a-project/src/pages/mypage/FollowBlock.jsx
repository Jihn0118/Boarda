import React, { useEffect, useState } from "react";
import { useRecoilValue } from "recoil";
import myPageAPI from "../../api/mypageAPI";
import { loginUserState } from "../../recoil/atoms/UserState";

export default function FollowBlock() {
  const loginUser = useRecoilValue(loginUserState);
  const [follow, setFollow] = useState({});

  // 백에서 로그인 id 기준 팔로우 목록 받아와서 뿌리기
  useEffect(() => {
    const fetchData = async () => {
      try {
        const res = await myPageAPI.getFollowList(loginUser.id);
        console.log(res);
        setFollow(res.data);
      } catch (error) {
        console.log(error);
      }
    };
    fetchData();
  }, [loginUser.id]);

  return <div>{follow.id}</div>;
}
