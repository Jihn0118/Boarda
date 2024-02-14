import routes from "./routes/routes";
import { RouterProvider } from "react-router-dom";

export default function App() {


  return (
    <>
      <RouterProvider router={routes}></RouterProvider>
    </>
  );
}
