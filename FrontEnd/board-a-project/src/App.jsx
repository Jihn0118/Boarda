import { Route, Routes } from 'react-router-dom';
import MoimList from './routes/MoimList';
import Home from './routes/Home';
import React from 'react';
import MoimDetail from './routes/MoimDetail';
import MoimWrite from './routes/MoimWrite';
import MoimUpdate from './routes/MoimUpdate';
import GameList from './routes/GameList';

function App() {
  return (
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/moim" element={<MoimList />} />
      <Route path="/moim/:id" element={<MoimDetail />} />
      <Route path="/write" element={<MoimWrite />} />
      <Route path="/update/:id" element={<MoimUpdate />} />
      <Route path="/game" element={<GameList />} />
    </Routes>
  );
}

export default App;