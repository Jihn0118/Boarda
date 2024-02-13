import React, { useEffect } from "react";
import routes from "./routes/routes";
import { RouterProvider } from "react-router-dom";
import { useSetRecoilState } from "recoil";
import { loginUserState } from "./recoil/atoms/userState";

export default function App() {
  const loginUser = localStorage.getItem("loginUser");
  const setLoginUser = useSetRecoilState(loginUserState);
  useEffect(() => {
    if (!!loginUser) {
      setLoginUser(JSON.parse(loginUser));
    }
  }, []);

  return (
    <>
      <RouterProvider router={routes}></RouterProvider>
    </>
  );
}
