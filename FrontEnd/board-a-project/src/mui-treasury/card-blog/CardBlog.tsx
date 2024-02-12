import React from "react";
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import CardMedia from "@mui/material/CardMedia";
import { Info, InfoEyebrow, InfoSubtitle, InfoTitle } from "../info-basic";

export function CardBlog({ title, minNum, maxNum, playTime, difficulty, year, age, imageUrl, feed }) {
  const imageList = feed.slice(0, 5);
  console.log(imageList);
  return (
    <Card
      sx={(theme) => ({
        // width: 1000,
        margin: "auto",
        borderRadius: theme.spacing(2), // 16px
        transition: "0.3s",
        boxShadow: "0px 14px 80px rgba(34, 35, 58, 0.2)",
        position: "relative",
        maxWidth: 500,
        marginLeft: "auto",
        overflow: "initial",
        background: "#ffffff",
        display: "flex",
        flexDirection: "row",
        alignItems: "center",
        paddingBottom: theme.spacing(2),
        [theme.breakpoints.up("md")]: {
          flexDirection: "row",
          paddingTop: theme.spacing(2),
        },
      })}
    >
      <div
        style={{
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
        }}
      >
        <div style={{ display: "flex", alignItems: "center" }}>
          <CardMedia
            image={imageUrl}
            sx={(theme) => ({
              width: 200, // 여기를 수정하세요. 원하는 너비 값으로
              height: 200,

              // width: "88%",
              marginLeft: "auto",
              marginRight: "auto",
              marginTop: theme.spacing(-3),
              // height: 0,
              // paddingBottom: "48%",
              paddingBottom: "0%",
              borderRadius: theme.spacing(2),
              backgroundColor: "#fff",
              position: "relative",
              [theme.breakpoints.up("md")]: {
                // width: "100%",
                width: 400,
                marginLeft: theme.spacing(-3),
                marginTop: 0,
                transform: "translateX(-8px)",
              },
              "&:after": {
                content: '" "',
                position: "absolute",
                top: 0,
                left: 0,
                width: "100%",
                height: "100%",
                // backgroundImage: "linear-gradient(147deg, #fe8a39 0%, #fd3838 74%)",
                borderRadius: theme.spacing(2), // 16
                opacity: 0.5,
              },
            })}
          />

          <CardContent>
            <Info
              useStyles={(theme) => {
                const family =
                  "-apple-system,BlinkMacSystemFont,Segoe UI,Roboto,Oxygen,Ubuntu,Cantarell,Fira Sans,Droid Sans,Helvetica Neue,sans-serif";
                return {
                  eyebrow: {
                    textTransform: "uppercase",
                    letterSpacing: "1px",
                    fontSize: 12,
                    marginBottom: "0.875em",
                    display: "inline-block",
                  },
                  title: {
                    fontSize: 20,
                    fontWeight: "bold",
                    marginBottom: "0.35em",
                    fontFamily: family,
                  },
                  subtitle: {
                    marginBottom: theme.spacing(2),
                    fontSize: "0.8rem",
                    letterSpacing: "0.00938em",
                    fontFamily: family,
                  },
                };
              }}
            >
              <InfoEyebrow >출시일: {year}</InfoEyebrow>
              <InfoTitle>{title}</InfoTitle>
              <div style={{borderBottom: "1px solid black", margin: "10px 0"}}></div>
              <InfoSubtitle>
                인원수: {minNum} ~ {maxNum} 명 <br/>
                플레이: {playTime} 분 <br/>
                난이도: {difficulty} 점 <br/>
                출시일: {year} 년 <br/>
                연령: {age} 세 이상<br/>
                Git is a distributed version control system. Every dev has a
                working copy of the code and...
              </InfoSubtitle>
            </Info>
            <div style={{ textAlign: "right" }}>

            <Button
              sx={{
                backgroundImage:
                  "linear-gradient(147deg, #fe8a39 0%, #fd3838 74%)",
                boxShadow: "0px 4px 32px rgba(252, 56, 56, 0.4)",
                borderRadius: 100,
                paddingLeft: 3,
                paddingRight: 3,
                color: "#ffffff",
              }}
            >
              보유 매장
            </Button>
            </div>
          </CardContent>
        </div>
        <Box sx={{ display: "flex", overflow: "auto", pt: 2 }}>
          {imageList.map((image, index) => (
            <img
              key={index}
              src={import.meta.env.VITE_S3_BASE + image}
              alt={`review image ${index + 1}`}
              style={{ width: "120px", height: "120px", marginRight: "10px" }}
            />
          ))}
        </Box>
      </div>
    </Card>
  );
}
