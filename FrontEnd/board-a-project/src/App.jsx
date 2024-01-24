import { Route, Routes } from "react-router-dom";
import MoimList from "./routes/MoimList";
import Home from "./routes/Home";
import React from "react";
import MoimDetail from "./routes/MoimDetail";
import MoimWrite from "./routes/MoimWrite";
import MoimUpdate from "./routes/MoimUpdate";
import ChatButton from "./components/chatButton/ChatButton";

function App() {
  return (
    <div>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/board" element={<MoimList />} />
        <Route path="/board/:idx" element={<MoimDetail />} />
        <Route path="/write" element={<MoimWrite />} />
        <Route path="/update/:idx" element={<MoimUpdate />} />
      </Routes>
      <div id="chatting-ing">
        <ChatButton></ChatButton>
      </div>
    </div>
  );
}

export default App;
