import React from "react";
import { Card, CardContent, CardMedia, Typography } from "@mui/material";
import dice from "../../assets/images/dice.png";

export default function GameFeed({info}) {

  return (
    <Card sx={{ maxHeight: 700, maxWidth: 300, width: "100%", height: "100%" }}>
      <CardMedia
        component="img"
        height="300"
        image={import.meta.env.VITE_S3_BASE + info.images[0].name}
        alt="피드 이미지"
        onError={(e) =>e.target.src=dice}
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
