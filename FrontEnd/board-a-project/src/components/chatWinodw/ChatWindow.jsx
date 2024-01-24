import React, { useState } from "react";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import styled from "styled-components";

const ChatContainer = styled.div`
  display: flex;
  flex-direction: column;
  height: 40vh;
  padding: 16px;
  position: fixed;
  bottom: 1rem;
  right: 1rem;
`;

const ChatMessages = styled.div`
  flex: 1;
  overflow-y: auto;
  margin-bottom: 16px;
`;

const Message = styled.div`
  background-color: #f0f0f0;
  border-radius: 8px;
  padding: 8px;
  margin-bottom: 8px;
`;

const ChatInput = styled.div`
  display: flex;
`;

const StyledTextField = styled(TextField)`
  flex: 1;
  margin-right: 8px;
`;

const ChatWindow = () => {
  const [messages, setMessages] = useState([]);
  const [newMessage, setNewMessage] = useState("");

  const handleSendMessage = () => {
    if (newMessage.trim() !== "") {
      setMessages([...messages, { text: newMessage, sender: "user" }]);
      setNewMessage("");
    }
  };

  return (
    <ChatContainer>
      <ChatMessages>
        {messages.map((message, index) => (
          <Message key={index}>{message.text}</Message>
        ))}
      </ChatMessages>

      <ChatInput>
        <StyledTextField
          label="Type your message"
          value={newMessage}
          onKeyDown={handleSendMessage}
          onChange={(e) => setNewMessage(e.target.value)}
        />
        <Button variant="contained" color="primary" onClick={handleSendMessage}>
          Send
        </Button>
      </ChatInput>
    </ChatContainer>
  );
};

export default ChatWindow;
