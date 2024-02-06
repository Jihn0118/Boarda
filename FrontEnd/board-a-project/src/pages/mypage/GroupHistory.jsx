import React, { useEffect, useState } from "react";
import myPageAPI from "../../api/mypageAPI";
import { useParams } from "react-router-dom";

export default function GroupHistory() {
  const [history, setHistory] = useState([]);
  const params = useParams();

  useEffect(() => {
    // 랜더링 될 때 그룹이력  요청
    // 비동기로 받아오고 랜더링할 것 (유저 id 기준 get 요청)
    const fetchData = async () => {
      try {
        const res = await myPageAPI.getMoimHistory(params.userId);
        console.log(res);
        console.log(123);
        setHistory(res.data);
      } catch (err) {
        console.log("모임 이력 받아오기 실패");
      }
    };
    fetchData();
  });

  return <div>{history.map((e, index) => e)}</div>;
}
