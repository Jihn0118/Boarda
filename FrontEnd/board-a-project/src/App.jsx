import React from "react";

import routes from "./routes/routes";
import { RouterProvider } from "react-router-dom";

export default function App() {
  return <RouterProvider router={routes}></RouterProvider>;
}

// import React from "react";
// import routes from "./routes/routes";
// import Header from './components/Header.jsx';
// import Footer from './components/Footer.jsx';
// import { BrowserRouter as Router } from 'react-router-dom';  // 불러오기 추가

// export default function App() {
//   return(
//     <div>
//       <Header />
//       <Router>
//         <RouterProvider routes={routes}>
//           <div>App afsdsa</div>
//         </RouterProvider>
//       </Router>
//       <Footer />
//     </div>
//   );
// }
