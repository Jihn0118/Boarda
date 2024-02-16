import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router";
import Tabs from "@mui/material/Tabs";
import Tab from "@mui/material/Tab";
import styled from "styled-components";
import { useParams } from "react-router-dom";
import { useRecoilValue } from "recoil";
import { loginUserState } from "../../recoil/atoms/userState";
import MypageHeader from "../../components/MypageHeader";
import { Outlet } from "react-router-dom";
import myPageAPI from "../../api/mypageAPI";

// 마이페이지 -> 로그인 한 유저가 접근하면 내 마이페이지로
// 타인 프로필을 클릭했으면 타인 피드로
// :userId 파라미터 이용해서 판단할 것

const StyledTabs = styled(Tabs)`
  && {
    width: 100%;
    margin-top: 10%;
    border: black solid;
    border-radius: 10%;
  }
`;
const StyledTab = styled(Tab)`
  && {
    &.Mui-selected {
      background-color: #bad8fc;
      color: white;
    }
    font-size: large;
    font-weight: bold;
  }
`;

// 마이페이지 rfc
export default function MyPage() {
  // 네비게이터로 이동시키기
  const navigate = useNavigate();
  const params = useParams(); // 파라미터가 키:밸류 형태로 뽑힘

  // 현재 로그인한 유저 상태
  const loginUser = useRecoilValue(loginUserState);
  const [userData, setUserData] = useState();
  const [info, setInfo] = useState();

  useEffect(() => {
    const fetchData = async () => {
      try {
        const res = await myPageAPI.getUserFeeds(params.userId);
        console.log(res);
        setUserData(res.data);
      } catch (error) {
        console.log(error);
      }
    };
    fetchData();
  }, [loginUser.id]);
  useEffect(() => {
    setInfo(userData);
  }, [userData]);

  // 넘어온 파라미터와 같으면 본인 마이페이지, 아니면 타인
  const isLoginUser = params.userId === loginUser.id;

  // 백과 통신하여 param으로 넘어온 유저의 정보 조회

  // // 로그인 여부 확인
  // if(!isAuthed()) {
  //   navigate("/home");
  //   alert("로그인한 유저만 접근할 수 있습니다.")
  // }

  // mui Tabs 사용 위한 상태 저장
  const [val, setVal] = useState(1);

  const loginUserMypage = (
    <StyledTabs
      value={val}
      onChange={(e, newVal) => {
        setVal(newVal);
      }}
      orientation="vertical"
    >
      <StyledTab
        label={`${loginUser.nickname}님의 피드`}
        value={1}
        onClick={() => navigate("feed")}
      ></StyledTab>
      <StyledTab
        label={`${loginUser.nickname}님의 작성글`}
        value={2}
        onClick={() => navigate("mypost")}
      ></StyledTab>
      <StyledTab
        label="회원정보 수정"
        value={3}
        onClick={() => navigate("userinfo")}
      ></StyledTab>
      <StyledTab
        label="내 그룹이력"
        value={4}
        onClick={() => navigate("group-history")}
      ></StyledTab>
      <StyledTab
        label="팔로우/차단"
        value={5}
        onClick={() => navigate("follow")}
      ></StyledTab>
      <StyledTab
        label="참여중인 그룹"
        value={6}
        onClick={() => navigate("group-now")}
      ></StyledTab>
    </StyledTabs>
  );

  const otherUserMypage = (
    <StyledTabs
      value={val}
      onChange={(e, newVal) => {
        setVal(newVal);
      }}
      orientation="vertical"
    >
      <StyledTab
        onClick={() => navigate("feed")}
        label={`${params.userId}의 피드`}
        value={1}
      ></StyledTab>
      <StyledTab
        onClick={() => navigate("mypost")}
        label={`${params.userId}의 작성글`}
        value={2}
      ></StyledTab>
    </StyledTabs>
  );

  const Container = styled.div`
    width: 100%;
  `;

  return (
    <>
      <MypageHeader info={info}></MypageHeader>

      {/* 로그인 유저와 같으면 본인 마이페이지로 아니면 다른사람페이지 */}
      <div className="flex ">
        <div className="flex-none">
          {isLoginUser && loginUserMypage}
          {!isLoginUser && otherUserMypage}
        </div>
        <Container>
          <Outlet></Outlet>
        </Container>
      </div>
    </>
  );
}
