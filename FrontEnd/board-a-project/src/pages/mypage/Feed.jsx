import React, { useEffect, useState } from "react";
import Grid from "@mui/material/Grid";
import { Outlet, useLocation, useNavigate } from "react-router-dom";
import reviewAPI from "../../api/reviewAPI";
import styled from "@emotion/styled";
import { loginUserState } from "../../recoil/atoms/userState";
import { useRecoilValue } from "recoil";
import FeedDetail from "./FeedDetail";


const FeedItem = styled.img`
  &:hover {
    cursor: pointer;
  }
`;

export default function Feed() {
  const [feeds, setFeeds] = useState([]); // 피드 정보 백에서 받아와서 뿌리기
  // 파람 기준 id로 보내면 백에서 피드 셀렉 후 리스트로 넘겨줄 것
  const location = useLocation();

  const loginUser = useRecoilValue(loginUserState);
  // 백과 통신해서 피드 목록을 받아오고 상태값으로 저장함 -> 그리드에 뿌리기
  // 받아온 feeds 반복문으로 뿌림, 날아온 데이터 파싱해서 최신순으로 정렬

  // 피드 이미지 클릭 -> 피드 상세정보 창 뜨고 상위 컴포넌트는 안보이게 조건부 랜더링 걸기
  const [showDetail, setShowDetail] = useState(false);
  const [feedDetailInfo, setFeedDetailInfo] = useState({});
  const navigate = useNavigate();
  useEffect(() => {
    // 랜더링 될 때 feed정보 요청
    // 비동기로 받아오고 랜더링할 것
    const fetchData = async () => {
      try {
        let res = await reviewAPI.getMyReview();
        console.log(res);

        res.data.sort((a, b) => {
          new Date(b.createdAt) - new Date(a.createdAt);
        });
        setFeeds(res.data);
      } catch (err) {
        console.log("피드 받아오기 실패");
      }
    };
    fetchData();
    // 피드상세 -> 뒤로가기로 나온 경우
  }, [loginUser.id]);
  useEffect(() => {
    if (location.pathname.endsWith("d")) {
    console.log("ㅋㅋ")

      setShowDetail(false);
    }
  }, [location.pathname]);
  // 피드 상세
  const Detail = styled(Outlet)`
    text-align: center;
    margin: auto;
  `;
  return (
    <>
      {!showDetail && (
        <Grid container spacing={2}>
          {feeds.map((e, index) => {
            if (e.images.length !== 0) {
              return (
                <Grid item xs={3} key={e.id}>
                  <FeedItem
                    src={import.meta.env.VITE_S3_BASE + e.images[0].name}
                    alt="피드이미지"
                    onClick={() => {
                      console.log(showDetail)
                      setShowDetail(true);
                      setTimeout(10000)
                      console.log(showDetail)
                      setFeedDetailInfo(e);
                      setTimeout(10000)
                      console.log(e)
                      setTimeout(3000)

                      //navigate(`${e.id}`);
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
      {showDetail && <FeedDetail feedDetailInfo={feedDetailInfo} />}
    </>
  );
}
