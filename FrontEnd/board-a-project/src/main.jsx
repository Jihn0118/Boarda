// import React from 'react'
// import ReactDOM from 'react-dom/client'
// import App from './App.jsx'
// import Header from './components/Header.jsx';
// import Footer from './components/Footer.jsx'
// import { RecoilRoot } from 'recoil';

// ReactDOM.createRoot(document.getElementById('root')).render(
//   <React.StrictMode>
//     <RecoilRoot>
//       <Header />
//       <App />
//       <Footer />
//     </RecoilRoot>
//   </React.StrictMode>,
// )

import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.jsx'
import { RecoilRoot } from 'recoil';

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <RecoilRoot>
      <App />
    </RecoilRoot>
  </React.StrictMode>,
)