import React, { useEffect, useState } from "react";
import Grid from "@mui/material/Grid";
import { useParams } from "react-router-dom";
import reviewAPI from "../../api/reviewAPI";

export default function Feed() {
  const [feeds, setFeeds] = useState([]); // 피드 정보 백에서 받아와서 뿌리기
  // 파람 기준 id로 보내면 백에서 피드 셀렉 후 리스트로 넘겨줄 것
  const params = useParams();

  useEffect(() => {
    // 랜더링 될 때 feed정보 요청
    // 비동기로 받아오고 랜더링할 것
    const fetchData = async () => {
      try {
        const res = await reviewAPI.getMyReview(params.userId);
        console.log(res)
        console.log(123);
        setFeeds(res.data);
      } catch (err) {
        console.log("피드 받아오기 실패");
      }
    };
    fetchData();
  }, [params.userId]);
  // 백과 통신해서 피드 목록을 받아오고 상태값으로 저장함 -> 그리드에 뿌리기
  // 받아온 feeds 반복문으로 뿌림

  return (
    <Grid container spacing={2}>
      {feeds.map((e, index) => (
        <Grid item xs={3} key={index}>
          <img src={e.data} alt="피드이미지" />
        </Grid>
      ))}
    </Grid>
  );
}
