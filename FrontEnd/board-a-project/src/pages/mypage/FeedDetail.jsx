import React, { useState } from "react";
import { Card, CardContent, CardMedia, Typography } from "@mui/material";
import { useOutletContext } from "react-router";
import styled from "styled-components";

// Outlet이라 일반적인 props로는 못 받고, useOutletContext로 받을 수 있다.
// 상위에서 feed 상세정보를 넘겨받음
// 실제 데이터 들어가면 info 찍어보고 변수 정리하는 식으로 리팩토링 필요
const SmallFeed = styled(CardMedia)`
  && {
    height: 30%;
    width: 30%;
    &:hover {
      cursor: pointer;
    }
    margin-left: 1rem;
  }
`;

const LargeFeed = styled(CardMedia)`
  && {
    height: 30%;
    width: 30%;
    margin-left: 3rem;
  }
`;

export default function FeedDetail({feedDetailInfo}) {
  // 클릭하면 크게 보일 대표 이미지
  const [refImage, setRefImage] = useState(feedDetailInfo.images[0].name);
  console.log(feedDetailInfo)

  return (
    <Card
      sx={{ maxHeight: 1000, maxWidth: 800, width: "100%", height: "100%" }}
    >
      <div className="flex flex-row">
        <LargeFeed
          className="grow-0"
          component="img"
          height="50%"
          width="50%"
          image={import.meta.env.VITE_S3_BASE + refImage}
          alt="피드 이미지"
        />
        <div className="flex ml-2 ">
          {feedDetailInfo.images.map((e, index) => (
            <SmallFeed
              className="grow"
              component="img"
              height="150"
              image={import.meta.env.VITE_S3_BASE + e.name}
              onClick={() => setRefImage(e.name)}
              alt="피드 이미지"
            />
          ))}
        </div>
      </div>
      <hr></hr>
      <CardContent>
        <Typography gutterBottom variant="h5" component="div">
          {feedDetailInfo.cafe.brand}
        </Typography>
        <Typography variant="body1" color="text.primary" component="p">
          {feedDetailInfo.content}
        </Typography>
      </CardContent>
    </Card>
  );
}
