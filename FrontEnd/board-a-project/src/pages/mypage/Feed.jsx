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
        let res = await reviewAPI.getMyReview(params.userId);

        res.data.sort((a, b) => {
          new Date(b.createdAt) - new Date(a.createdAt);
        });
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

  // 피드 이미지 클릭 -> 피드 상세정보 창 뜨고 상위 컴포넌트는 안보이게 조건부 랜더링 걸기
  const [showDetail, setShowDetail] = useState(false);
  const [feedDetailInfo, setFeedDetailInfo] = useState({});
  const navigate = useNavigate();

  console.log(feeds);

  return (
    <>
      {!showDetail && (
        <Grid container spacing={2}>
          {feeds.map((e, index) => {
            if (e.images.length !== 0) {
              return (
                <Grid item xs={3} key={e.id}>
                  <img
                    src={import.meta.env.VITE_S3_BASE + e.images[0].name}
                    alt="피드이미지"
                    onClick={() => {
                      setShowDetail(!showDetail);
                      setFeedDetailInfo(e);
                      navigate(`${e.id}`);
                    }}
                  />
                </Grid>
              );
            } else {
              return;
            }
          })}
        </Grid>
      )}
      {showDetail && <Outlet info={feedDetailInfo} />}
    </>
  );
}
