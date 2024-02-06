import React, { useEffect, useState } from "react";
import Grid from "@mui/material/Grid";
import { Outlet, useLocation, useNavigate, useParams } from "react-router-dom";
import reviewAPI from "../../api/reviewAPI";

export default function Feed() {
  const [feeds, setFeeds] = useState([]); // 피드 정보 백에서 받아와서 뿌리기
  // 파람 기준 id로 보내면 백에서 피드 셀렉 후 리스트로 넘겨줄 것
  const params = useParams();
  const location = useLocation();

  useEffect(() => {
    // 랜더링 될 때 feed정보 요청
    // 비동기로 받아오고 랜더링할 것
    const fetchData = async () => {
      try {
        const res = await reviewAPI.getMyReview(params.userId);
        console.log(res.data);
        setFeeds(res.data);
      } catch (err) {
        console.log("피드 받아오기 실패");
      }
    };
    fetchData();
    if (location.pathname.endsWith("d")) {
      setShowDetail(false);
    }
  }, [location.pathname]);
  // 백과 통신해서 피드 목록을 받아오고 상태값으로 저장함 -> 그리드에 뿌리기
  // 받아온 feeds 반복문으로 뿌림, 날아온 데이터 파싱해서 최신순으로 정렬
  const sortedArr = feeds.slice().sort((a, b) => {
    return new Date(b.createdAt) - new Date(a.createdAt);
  });

  // 피드 이미지 클릭 -> 피드 상세정보 창 뜨고 상위 컴포넌트는 안보이게 조건부 랜더링 걸기
  const [showDetail, setShowDetail] = useState(false);
  const [feedDetailInfo, setFeedDetailInfo] = useState({});
  const navigate = useNavigate();

  return (
    <>
      {!showDetail && (
        <Grid container spacing={2}>
          {sortedArr.map((e, index) => (
            <Grid item xs={3} key={e.id}>
              <img
                src={e.imgSrc}
                alt="피드이미지"
                onClick={() => {
                  setShowDetail(!showDetail);
                  setFeedDetailInfo(e);
                  navigate(`${e.id}`);
                }}
              />
            </Grid>
          ))}
        </Grid>
      )}
      {showDetail && <Outlet info={feedDetailInfo} />}
    </>
  );
}
