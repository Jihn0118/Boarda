import { createBrowserRouter } from "react-router-dom";
import Home from "../pages/Home";
import MoimList from "../pages/moim/MoimList";
// 라우터 일단 모달로 대체하면서 지워둠.. 칠드런에 넣을 수 있으면 넣어보기
// import MoimMake from "../pages/moim/MoimMake";
// import MoimDetail from "../pages/moim/MoimDetail";
import GameList from "../pages/game/GameList";
import RootLayout from "../pages/rootLayout/RootLayout";
import Thumbnail from "../pages/thumbnail/Thumbnail";

// ----------------------------------------------------------------
import Login from "../pages/user/Login";
import Signup from "../pages/user/Signup";
import MyPage from "../pages/mypage/MyPage";
import Arcade from "../pages/arcade/Arcade";
import ErrorPage from "../pages/ErrorPage";
// ----------------------------------------------------------------

const routes = createBrowserRouter([
  { path: "", element: <Thumbnail></Thumbnail> }, // 첫 시작화면(썸네일) -> 시작하기 누르면 홈으로
  {
    path: "/",
    element: <RootLayout />, // 헤더 껍데기
    children: [
      { path: "home", element: <Home></Home> }, // 홈화면 (실질적인 첫 화면)
      {
        path: "moim", // 나중에 지도화면으로 element 바꿔야 함
        element: <Home />,
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
      // -----------------------------------
      {
        path: "login",
        element: <Login />,
      },
      {
        path: "signup",
        element: <Signup />,
      },
      // mypage routes
      {
        path: "mypage",
        children: [{ path: ":menu", element: <MyPage /> }],
      },

      // arcade routes
      {
        path: "arcade",
        element: <Arcade />,
      },
      // -----------------------------------
    ],
  },
  // { path: "/*", element: <ErrorPage></ErrorPage> },
]);
export default routes;
