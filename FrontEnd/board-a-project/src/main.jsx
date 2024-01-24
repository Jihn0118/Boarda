import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import Header from "./layout/Header";
import Footer from "./layout/Footer";
import {BrowserRouter} from "react-router-dom";

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <BrowserRouter>
      <Header/>
      <App/>
      <Footer/>
    </BrowserRouter>
  </React.StrictMode>
);