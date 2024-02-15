import styled from "@emotion/styled";
import React from "react";

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
  return (
    <MypageDiv className="flex content-center justify-between rounded-3xl">
      {/* 프로필 이미지 */}
      <div className="container flex items-center px-10">
        <img src="" alt="profile img" />
        <span className="text-sm">닉네임</span>
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
