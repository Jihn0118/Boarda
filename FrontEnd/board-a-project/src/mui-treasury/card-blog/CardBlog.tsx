import React from "react";
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import CardMedia from "@mui/material/CardMedia";
import { Info, InfoEyebrow, InfoSubtitle, InfoTitle } from "../info-basic";
import dice from "../../assets/images/dice.png";

export function CardBlog({
  title,
  people,
  playTime,
  difficulty,
  year,
  age,
  imageUrl,
  review,
  onImageClick,
  onCafeButtonClick,
}) {
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
          justifyContent:"center",
          alignItems: "center",
        }}
      >
        <div style={{ display: "flex", alignItems: "center", paddingLeft: "30px",}}>
          <CardMedia
            image={imageUrl}
            sx={(theme) => ({
              width: 150, // 여기를 수정하세요. 원하는 너비 값으로
              height: 210,

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
                width: 150,
                // marginLeft: theme.spacing(-3),
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

          <CardContent
          sx={(theme) => ({
            width: 270,
          })}>
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
              
              <InfoEyebrow></InfoEyebrow>
              <InfoTitle>{title}</InfoTitle>
              <div
                style={{ borderBottom: "1px solid black", margin: "10px 0" }}
              ></div>
              <InfoSubtitle>
                {people} <br />
                {playTime} <br />
                {difficulty} <br />
                {year} <br />
                {age} <br />
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
                onClick={() => onCafeButtonClick()}
              >
                보유 매장
              </Button>
            </div>
          </CardContent>
        </div>
        <Box sx={{ display: "flex", overflow: "auto", pt: 2, maxWidth: "90%" }}>
          {review.map((reviewObj, index) => (
            <img
              key={index}
              src={import.meta.env.VITE_S3_BASE + reviewObj.images[0].name} // 첫 번째 이미지 URL을 사용
              alt={`review image ${index + 1}`} onError={(e) =>e.target.src=dice}
              style={{ width: "120px", height: "120px", marginRight: "10px", cursor: "pointer" }}
              onClick={() => onImageClick(reviewObj)}
            />
          ))}
        </Box>
      </div>
    </Card>
  );
}
