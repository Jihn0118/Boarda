import React from "react";
import { Card, CardContent, CardMedia, Typography } from "@mui/material";
import { useOutletContext } from "react-router";

// Outlet이라 일반적인 props로는 못 받고, useOutletContext로 받을 수 있다.
// 상위에서 feed 상세정보를 넘겨받음
// 실제 데이터 들어가면 info 찍어보고 변수 정리하는 식으로 리팩토링 필요

export default function FeedDetail() {
  const info = useOutletContext();
  console.log(import.meta.env.VITE_S3_BASE + info.images[0].name);
  return (
    <Card sx={{ maxWidth: 300, width: "60%", height: "50%" }}>
      <CardMedia
        component="img"
        height="300"
        image={import.meta.env.VITE_S3_BASE + info.images[0].name}
        alt="피드 이미지"
      />
      <CardContent>
        <Typography gutterBottom variant="h5" component="div">
          {info.cafe.brand}
        </Typography>
        <Typography variant="body1" color="text.primary" component="p">
          {info.content}
        </Typography>
      </CardContent>
    </Card>
  );
}
