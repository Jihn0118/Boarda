import React, { useState } from "react";
import Button from "@mui/material/Button";
import styled from "styled-components";
import ChatWindow from "../ChatWinodw/ChatWindow"; // ChatWindow 컴포넌트를 import

const FloatingChatButton = styled(Button)`
  && {
    position: fixed;
    bottom: 1rem;
    right: 1rem;
    z-index: 1000;
  }
`;

const ChatButton = () => {
  const [chatVisible, setChatVisible] = useState(false);

  const toggleChat = () => {
    setChatVisible((prev) => !prev);
  };

  return (
    <div>
      <FloatingChatButton
        variant="contained"
        color="primary"
        onClick={toggleChat}
      >
        {chatVisible ? "Close Chat" : "Open Chat"}
      </FloatingChatButton>

      {chatVisible && <ChatWindow />}
    </div>
  );
};

export default ChatButton;
