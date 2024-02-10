import React, { useEffect, useState } from 'react';
import { getGameDetail } from "../../api/gameAPI";
import { CardBlog } from "../../mui-treasury/card-blog/CardBlog";
import DetailModal from "../../components/DetailModal";

const GameDetail = ({ gameId, isModalOpen, setIsModalOpen }) => {
    const [gameDetail, setGameDetail] = useState([]);
    const [feedList, setFeedList] = useState([]);

    const getGameDetailData = async() => {
        console.log(gameId)
        try {
            const data = await getGameDetail(gameId);
            setGameDetail(data.boardGame);
            const tempimageList = [];
            data.imageReviews.forEach(reviewObj => {
                tempimageList.push(reviewObj.image.name);
            });
            setFeedList(tempimageList);
        } catch (error) {
          console.error('게임 저장 중 에러가 발생했습니다:', error);
        }
    };

    useEffect(() => {
        getGameDetailData();
    }, [gameId]); 

    return (
        <div>
            <DetailModal isOpen={isModalOpen} onClose={() => setIsModalOpen(false)}>
            {gameDetail ? (
                <>
                  <CardBlog
                    title = {gameDetail.title}
                    minNum={gameDetail.minNum}
                    maxNum={gameDetail.maxNum}
                    playTime={gameDetail.playTime}
                    difficulty={gameDetail.difficulty}
                    year = {gameDetail.year}
                    age = {gameDetail.age}
                    imageUrl = {gameDetail.image}
                    feed = {feedList}
                  >
                  </CardBlog>
                </>

            ) : (
                <p>게임 정보를 불러오는 중...</p>
            )}
            </DetailModal>
        </div>
    );
};

export default GameDetail;