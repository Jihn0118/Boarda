import React, { useEffect, useState } from 'react';
import { getGameDetail } from "../../api/gameAPI";
import { useParams } from "react-router-dom";
import { CardBlog } from "../../mui-treasury/card-blog/CardBlog";
import { Feed } from '@mui/icons-material';

const GameDetail = () => {
    const { gameId } = useParams();
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
        <>
        <div>
            {gameDetail ? (
                <>
                  <CardBlog
                    year = {gameDetail.year}
                    title = {gameDetail.title}
                    imageUrl = {gameDetail.image}
                    feed = {feedList}
                  >
                  </CardBlog>
                </>

                // <>
                //     <h1>{gameDetail.title}</h1>
                //     <img src={gameDetail.image} alt={gameDetail.title} />
                //     <p>최소 플레이어 수: {gameDetail.minNum}</p>
                //     <p>최대 플레이어 수: {gameDetail.maxNum}</p>
                //     <p>플레이 시간: {gameDetail.playTime}분</p>
                //     <p>권장 연령: {gameDetail.age}세 이상</p>
                //     <p>난이도: {gameDetail.difficulty}</p>
                //     <p>출시 연도: {gameDetail.year}</p>
                // </>
            ) : (
                <p>게임 정보를 불러오는 중...</p>
            )}
        </div>
        </>
        // <div>
        //     {gameDetail.title ? (
        //         <>
        //         <CardBlog
        //             title = {gameDetail.title}
        //             imageUrl = {gameDetail.image}
        //         />
        //         </>
        //     ) : (
        //         <p>게임 정보를 불러오는 중...</p>
        //     )}
        // </div>
    );
};

export default GameDetail;