import React from "react";
import Card from "@mui/material/Card";
import CardMedia from "@mui/material/CardMedia";
import { CSSObject, styled } from "@mui/material/styles";
import {
  Info,
  InfoEyebrow,
  InfoSlotStyles,
  InfoSubtitle,
  InfoTitle,
} from "../info-basic";

const useStyles = (): CSSObject & Partial<InfoSlotStyles> => {
  return {
    eyebrow: {
      color: "rgba(255, 255, 255, 0.92)",
      fontFamily: '"Spartan", san-serif',
      lineHeight: 1.4,
      fontSize: "1.0625rem",
      letterSpacing: "1px",
      textTransform: "initial",
      marginBottom: 0,
    },
    title: {
      color: "#fff",
      fontSize: "1.25rem",
      fontWeight: "bold" as const,
      lineHeight: 1.2,
      marginBottom: 0, //타이틀, 섭타이틀 간격 줄일지 말지 결정하기
    },
    subtitle: {
      color: "rgba(255, 255, 255, 0.72)",
      lineHeight: 1.5,
      "&:last-child": {
        marginTop: "0",
      },
    },
  };
};

const StyledCard = styled(Card)({
  transition: 'transform 0.3s',
  '&:hover': {
    transform: 'scale(1.05)',
  },
  borderRadius: "1rem",
  boxShadow: "none",
  position: "relative",
  minWidth: 150,
  minHeight: 210,
  "&:after": {
    content: '""',
    display: "block",
    position: "absolute",
    width: "100%",
    height: "64%",
    bottom: 0,
    zIndex: 1,
    background: "linear-gradient(to top, #000, rgba(0,0,0,0))",
  },
  cursor: "pointer",
});

const StyledCardMedia = styled(CardMedia)({
  position: "absolute",
  width: "100%",
  height: "100%",
  top: 0,
  left: 0,
  zIndex: 0,
  backgroundPosition: "top",
  backgroundSize: "100% 100%",
});

const Content = styled("div")(({ theme }) => ({
  padding: theme.spacing(1.5, 2),
  position: "absolute",
  zIndex: 2,
  bottom: 0,
  width: "100%",
}));

export function CardGalaxy({ title, playTime, minNum, maxNum, imageUrl, onClick }) {
  return (
    <StyledCard onClick={onClick}>
      <StyledCardMedia image={imageUrl} />
      <Content>
        <Info useStyles={useStyles}>
          {/* <InfoEyebrow>Boarda</InfoEyebrow> */}
          <InfoTitle>{title}</InfoTitle>
          <InfoSubtitle>{playTime} 분 | {minNum}~{maxNum} 명</InfoSubtitle>
        </Info>
      </Content>
    </StyledCard>
  );
}