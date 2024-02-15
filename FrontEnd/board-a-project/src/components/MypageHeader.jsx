import styled from "@emotion/styled";
import React from "react";
import { useRecoilValue } from "recoil";
import { loginUserState } from "../recoil/atoms/userState";

const MypageDiv = styled.div`
  width: 80vw;
  height: 16vh;
  background-color: #bad8fc;

  margin: auto;
  margin-top: 3rem;
  margin-bottom: 3rem;
  text-align: center;
`;

export default function MypageHeader() {
  // 로그인 유저 정보를 받아옴
  const loginUser = useRecoilValue(loginUserState);

  const profileSrc = loginUser.profile;
  const nickname = loginUser.nickname;
  return (
    <MypageDiv className="flex content-center justify-between rounded-3xl">
      {/* 프로필 이미지 */}
      <div className="container flex items-center px-10">
        <div style={{width: '60px', height: '60px', margin: '20px'}}>
        <img src={`${import.meta.env.VITE_S3_BASE}${profileSrc}`} alt="profile img" style={{widht:'100%', height:'100%'}}/>
        </div>
        <span className="text-sm font-bold">{nickname}</span>
      </div>

      {/* 팔로워 수 */}
      <div className="flex items-end space-x-10 py-5 px-5 text-white font-bold">
        {/* feed 수 */}
        <div>
          <div>1234</div>
          <div>Posts</div>
        </div>

        {/* follower 수 */}
        <div>
          <div>5678</div>
          <div>Followers</div>
        </div>

        {/* following 수 */}
        <div>
          <div>9101</div>
          <div>Following</div>
        </div>
      </div>
    </MypageDiv>
  );
}
