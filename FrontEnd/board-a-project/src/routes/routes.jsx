import { createBrowserRouter } from "react-router-dom";
import Home from "../pages/Home";
import MoimList from "../pages/moim/MoimList";
// import MoimMake from "../pages/moim/MoimMake";
// import MoimDetail from "../pages/moim/MoimDetail";
import GameList from "../pages/game/GameList";
import RootLayout from "../pages/rootLayout/RootLayout";
import Thumbnail from "../pages/thumbnail/Thumbnail";

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
    ],
  },
]);
export default routes;
