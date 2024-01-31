import React from 'react';
import {Link} from "react-router-dom";

const Header = () => {
  return (
    <header>
      <Link to="/home">홈</Link>
      &nbsp;&nbsp;|&nbsp;&nbsp;
      <Link to="/moim/list">모임</Link>
      &nbsp;&nbsp;|&nbsp;&nbsp;
      <Link to="/game">게임</Link>
      <hr/>
    </header>
  );
};

export default Header;