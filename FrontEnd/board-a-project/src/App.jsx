import React from "react";
import routes from "./routes/routes";
import { RouterProvider } from "react-router-dom";
// import BasicModal from "./pages/moim/BasicModal";

export default function App() {
  return (
    <>
      <RouterProvider router={routes}></RouterProvider>
      {/* <BasicModal></BasicModal> */}
    </>
  );
}
