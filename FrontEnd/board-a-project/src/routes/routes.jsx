import { createBrowserRouter } from "react-router-dom";
import Home from "../pages/Home";
import MoimList from "../pages/moim/MoimList";
import MoimMake from "../pages/moim/MoimMake";
import MoimDetail from "../pages/moim/MoimDetail";
import GameList from "../pages/game/GameList";


const routes = createBrowserRouter([
  { path: "", element: <MoimList></MoimList> },

  {
    path: "/main",
    element: <Home />,
  },
  {
    path: "/moim",
    element: <Home />,
    children: [
      {
        path: "list",
        element: <MoimList></MoimList>,
        children: [
          { path: "id", element: <MoimDetail></MoimDetail> },
          {
            path: "make",
            element: <MoimMake></MoimMake>,
          },
        ],
      },
    ],
  },
  {
    path: "/game",
    element: <GameList />,
  },
]);
export default routes;
