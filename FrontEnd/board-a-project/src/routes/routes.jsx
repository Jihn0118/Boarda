import { createBrowserRouter } from "react-router-dom";
import Home from "../pages/Home";
import MoimList from "../pages/moim/MoimList";

// 라우터 일단 모달로 대체하면서 지워둠.. 칠드런에 넣을 수 있으면 넣어보기
// import MoimMake from "../pages/moim/MoimMake";
// import MoimDetail from "../pages/moim/MoimDetail";
import GameList from "../pages/game/GameList";
// import GameDetail from "../pages/game/GameDetail";
import RootLayout from "../pages/rootLayout/RootLayout";
import Thumbnail from "../pages/thumbnail/Thumbnail";

// ----------------------------------------------------------------
import Login from "../pages/user/Login";
import Signup from "../pages/user/Signup";
import Arcade from "../pages/arcade/Arcade";
import ErrorPage from "../pages/ErrorPage";
import Board from "../pages/board/Board";

import Cafe from "../pages/cafe/Cafe";
import MyPage from "../pages/mypage/MyPage";
import FollowBlock from "../pages/mypage/FollowBlock";
import MyPosts from "../pages/mypage/MyPosts";
import MoimMap from "../pages/moim/Moimmap";
import UserInfo from "../pages/mypage/UserInfo";
import GroupHistory from "../pages/mypage/GroupHistory";
import GroupNow from "../pages/mypage/GroupNow";
import Feed from "../pages/mypage/Feed";
import FeedDetail from "../pages/mypage/FeedDetail";
import FoodGame from "../components/FoodGame";

// ----------------------------------------------------------------

const routes = createBrowserRouter([
  { path: "", element: <Thumbnail></Thumbnail> }, // 첫 시작화면(썸네일) -> 시작하기 누르면 홈으로
  {
    path: "/",
    element: <RootLayout />, // 헤더 껍데기
    children: [
      {
        path: "my-page/:userId",
        element: <MyPage></MyPage>,

        children: [
          {
            path: "group-now",
            element: <GroupNow />, // 마이페이지 내 참여중인그룹
          },

          {
            path: "group-history",
            element: <GroupHistory />, // 마이페이지 내 그룹이력
          },

          {
            path: "userinfo",
            element: <UserInfo />, // 마이페이지 내 회원정보수정
          },

          {
            path: "follow",
            element: <FollowBlock />, // 마이페이지 내 팔로우 목록
          },
          {
            path: "feed",
            element: <Feed />, // 마이페이지 내 피드
            children: [
              {
                path: ":feedId",
                element: <FeedDetail></FeedDetail>, // 피드 이미지 누르면 피드 상세 페이지
              },
            ],
          },
          {
            path: "mypost",
            element: <MyPosts />, // 마이페이지 내 작성글
          },
        ],
      }, // 마이페이지
      { path: "board", element: <FoodGame></FoodGame> }, // 게시판
      { path: "cafe", element: <Cafe></Cafe> }, // 매장정보
      { path: "home", element: <Home></Home> }, // 홈화면 (실질적인 첫 화면)
      {
        path: "moim", // 나중에 지도화면으로 element 바꿔야 함
        element: <MoimMap />,
        children: [
          {
            path: "list",
            element: <MoimList></MoimList>,
            // children: [
            //   // { path: ":id", element: <MoimDetail></MoimDetail> },
            //   // {
            //   //   path: "make",
            //   //   element: <MoimMake></MoimMake>,
            //   // },
            // ],
          },
        ],
      },
      {
        path: "game",
        element: <GameList />,
      },
      // {
      //   path: "game/:gameId",
      //   element: <GameDetail />,
      // },
      // -----------------------------------
      {
        path: "login",
        element: <Login />,
      },
      {
        path: "signup",
        element: <Signup />,
      },

      // arcade routes
      {
        path: "arcade",
        element: <Arcade />,
      },
      // -----------------------------------
    ],
  },
  { path: "/*", element: <ErrorPage></ErrorPage> },
]);
export default routes;
