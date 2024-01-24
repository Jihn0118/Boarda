import { Route, Routes } from 'react-router-dom';
import MoimList from './routes/MoimList';
import Home from './routes/Home';
import React from 'react';
import MoimDetail from './routes/MoimDetail';
import MoimWrite from './routes/MoimWrite';
import MoimUpdate from './routes/MoimUpdate';

function App() {
  return (
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/board" element={<MoimList />} />
      <Route path="/board/:idx" element={<MoimDetail />} />
      <Route path="/write" element={<MoimWrite />} />
      <Route path="/update/:idx" element={<MoimUpdate />} />
    </Routes>
  );
}

export default App;