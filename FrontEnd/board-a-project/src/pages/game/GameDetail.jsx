import React, { useEffect, useState } from "react";
import { getGameDetail } from "../../api/gameAPI";
import { CardBlog } from "../../mui-treasury/card-blog/CardBlog";
import DetailModal from "../../components/DetailModal";
import Modal from "@mui/material/Modal";
import GameFeed from "./GameFeed";

const GameDetail = ({ gameId, isModalOpen, setIsModalOpen }) => {
  const [gameDetail, setGameDetail] = useState([]);
  const [reviewList, setReviewList] = useState([]);

  const [feedDetailOpen, setFeedDetailOpen] = useState(false); // FeedDetail Modal의 열림 상태
  const [selectedReview, setSelectedReview] = useState(null);

  const getGameDetailData = async () => {
    console.log(gameId);
    try {
      const data = await getGameDetail(gameId);
      setGameDetail(data.boardGame);
      const tempReviewList = [];
      data.imageReviews.forEach((reviewObj) => {
        tempReviewList.push(reviewObj.review);
      });
      setReviewList(tempReviewList);
    } catch (error) {
      console.error("게임 저장 중 에러가 발생했습니다:", error);
    }
  };

  const handleImageClick = (review) => {
    // 여기에서 리뷰 데이터를 처리하거나 다른 컴포넌트로 전달
    console.log(review);
    setSelectedReview(review); // 선택된 reviewObj 설정
    setFeedDetailOpen(true);
  };

  const handleFeedDetailClose = () => {
    setFeedDetailOpen(false); // FeedDetail Modal 닫기
  };

  useEffect(() => {
    getGameDetailData();
  }, [gameId]);

  return (
    <>
      <DetailModal isOpen={isModalOpen} onClose={() => setIsModalOpen(false)}>
        <CardBlog
          title={gameDetail.title}
          people={`인원수: ${gameDetail.minNum} ~ ${gameDetail.maxNum} 명`}
          playTime={`플레이: ${gameDetail.playTime} 분`}
          difficulty={`난이도: ${gameDetail.difficulty} 점`}
          year={`출시일: ${gameDetail.year} 년`}
          age={`연령: ${gameDetail.age} 세 이상`}
          imageUrl={gameDetail.image}
          review={reviewList}
          onImageClick={handleImageClick}
        ></CardBlog>
      </DetailModal>
      <Modal open={feedDetailOpen} onClose={handleFeedDetailClose}>
        <GameFeed info={selectedReview} />
      </Modal>
    </>
  );
};

export default GameDetail;
