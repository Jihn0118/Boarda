import Login from "../pages/user/Login";
import Signup from "../pages/user/Signup";
import MyPage from "../pages/mypage/MyPage";
import Arcade from "../pages/arcade/Arcade";


export const routes = [
  // user routes
  { 
    path: "/login", 
    element: <Login />
  },
  { 
    path: "/signup", 
    element: <Signup />
  },
  // mypage routes
  { 
    path: "/mypage", 
    children: [
      {path: ":menu", element: <MyPage />}
    ]
  },

  // arcade routes
  {
    path: "/arcade",
    element: <Arcade />
  },
]
