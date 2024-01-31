import React from "react";

import { routes } from "./routes/routes";
import { createBrowserRouter, RouterProvider } from "react-router-dom";

const RouterObject = createBrowserRouter(routes)
export default function App() {
  return (
    <RouterProvider router={RouterObject}>
      <div>App afsdsa</div>
    </RouterProvider>
  );
}
