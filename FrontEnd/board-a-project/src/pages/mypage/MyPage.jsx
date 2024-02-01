import React from "react";
import { Link, Route, useParams } from "react-router-dom";
import MyFeed from "./MyFeed";
import MyPosts from "./MyPosts";
import UserInfoChange from "./UserInfoChange";
import MyGroupHistory from "./MyGroupHistory";
import FollowBlock from "./FollowBlock";
import ParticipatingGroups from "./ParticipatingGroups";

const MyPage = () => {
  const { menu } = useParams();

  const renderContent = () => {
    switch (menu) {
      case "my_feed":
        return <MyFeed />;
      case "my_posts":
        return <MyPosts />;
      case "user_info_change":
        return <UserInfoChange />;
      case "my_group_history":
        return <MyGroupHistory />;
      case "follow_block":
        return <FollowBlock />;
      case "participating_groups":
        return <ParticipatingGroups />;
      default:
        return <div>선택된 메뉴가 없습니다.</div>;
    }
  };

  return (
    <div>
      <div>
        <Link to={`/mypage/my_feed`}>
          <button>나의 피드</button>
        </Link>

        <Link to={`/mypage/my_posts`}>
          <button>나의 작성글</button>
        </Link>

        <Link to={`/mypage/user_info_change`}>
          <button>회원정보 수정</button>
        </Link>

        <Link to={`/mypage/my_group_history`}>
          <button>내 그룹 이력</button>
        </Link>

        <Link to={`/mypage/follow_block`}>
          <button>팔로우/차단</button>
        </Link>
      </div>
      <div>{renderContent()}</div>
    </div>
  );
};

export default MyPage;
