// import React from 'react';
// import {Link} from "react-router-dom";

// const Header = () => {
//   return (
//     <header>
//       <Link to="/home">홈</Link>
//       &nbsp;&nbsp;|&nbsp;&nbsp;
//       <Link to="/moim/list">모임</Link>
//       &nbsp;&nbsp;|&nbsp;&nbsp;
//       <Link to="/game">게임</Link>
//       <hr/>
//     </header>
//   );
// };

// export default Header;

import React from 'react';
import { Link as RouterLink } from 'react-router-dom';
import { AppBar, Toolbar, IconButton, Typography, Link, Box } from '@mui/material';
import HomeIcon from '@mui/icons-material/Home';
import GroupIcon from '@mui/icons-material/Group';
import GameIcon from '@mui/icons-material/Gamepad';

const Header = () => {
  return (
    <AppBar position="static" style={{ background: 'linear-gradient(45deg, #FE6B8B 30%, #FF8E53 90%)' }}>
      <Toolbar>
        <Link color="inherit" component={RouterLink} to="/home">
          <Box display="flex" alignItems="center" marginRight={2}>
            <IconButton edge="start" color="inherit" aria-label="home" marginRight={1}>
              <HomeIcon />
            </IconButton>
            <Typography variant="h6" component="div">
              홈
            </Typography>
          </Box>
        </Link>

        <Link color="inherit" component={RouterLink} to="/moim/list">
          <Box display="flex" alignItems="center" marginRight={2}>
            <IconButton edge="start" color="inherit" aria-label="moim" marginRight={1}>
              <GroupIcon />
            </IconButton>
            <Typography variant="h6" component="div">
              모임
            </Typography>
          </Box>
        </Link>

        <Link color="inherit" component={RouterLink} to="/game">
          <Box display="flex" alignItems="center">
            <IconButton edge="start" color="inherit" aria-label="game" marginRight={1}>
              <GameIcon />
            </IconButton>
            <Typography variant="h6" component="div">
              게임
            </Typography>
          </Box>
        </Link>
      </Toolbar>
    </AppBar>
  );
};

export default Header;