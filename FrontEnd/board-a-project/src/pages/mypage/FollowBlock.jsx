import React, { useEffect, useState } from "react";
import { useRecoilValue } from "recoil";
import myPageAPI from "../../api/mypageAPI";
import { loginUserState } from "../../recoil/atoms/userState";

export default function FollowBlock() {
  const loginUser = useRecoilValue(loginUserState);
  const [follow, setFollow] = useState({});

  // 백에서 로그인 id 기준 팔로우 목록 받아와서 뿌리기
  useEffect(() => {
    const fetchData = async () => {
      try {
        const res = await myPageAPI.getFollowList(loginUser.id);
        setFollow(res.data);
        console.log(res.data);
      } catch (error) {
        console.log(error);
      }
    };
    fetchData();
  }, [loginUser.id]);
  // Flag에 따라 차단/팔로우 디테일로 props 내려줄 것
  let followList = [];
  let blockList = [];
  followList = follow.filter((e) => e.flag === "F");
  blockList = follow.filter((e) => e.flag !== "F");

  return <div>{follow.id}</div>;
}
