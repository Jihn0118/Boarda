import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import Header from "./layout/Header";
import Footer from "./layout/Footer";
import { BrowserRouter } from "react-router-dom";
import { RecoilRoot } from 'recoil'; // Recoil을 사용하기 위해 RecoilRoot를 import합니다.

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <RecoilRoot>
      <BrowserRouter>
        <Header />
        <App />
        <Footer />
      </BrowserRouter>
    </RecoilRoot>
  </React.StrictMode>
);