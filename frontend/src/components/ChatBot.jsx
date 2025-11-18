import React, { useState, useRef, useEffect } from "react";
import axios from "axios";

const ChatBot = () => {
  const [messages, setMessages] = useState([
    { sender: "bot", text: "Hello! How can I assist you today?" }
  ]);
  const [input, setInput] = useState("");

  const chatEndRef = useRef(null);

 
  useEffect(() => {
    chatEndRef.current?.scrollIntoView({ behavior: "smooth" });
  }, [messages]);

  const sendMessage = async () => {
    if (!input.trim()) return;

    //  user message
    const newMessage = { sender: "user", text: input };
    setMessages((prev) => [...prev, newMessage]);

    
    setInput("");

    try {
      
      const res = await axios.post(
        "https://helpdesk-n0a1.onrender.com/api/v1/ai/gemini",
        input,   
        { headers: { "Content-Type": "application/json" } }
      );

      setMessages((prev) => [
        ...prev,
        { sender: "bot", text: formatMessage(res.data) }
      ]);

    } catch (error) {
      setMessages((prev) => [
        ...prev,
        { sender: "bot", text: "Something went wrong. Please try again." }
      ]);
    }
  };

  const handleKey = (e) => {
    if (e.key === "Enter") sendMessage();
  };

  
  const formatMessage = (text) => {
    if (!text || typeof text !== "string") return "";
    return text
      .replace(/\* /g, "• ")
      .replace(/\n/g, "<br/>");
  };

  return (
    <div
      className="container-fluid d-flex justify-content-center align-items-center p-0"
      style={{ height: "100vh" }}
    >
      <div className="row justify-content-center w-100 m-0">
        <div className="col-12 col-md-6 p-0">
          <div className="card shadow-lg" style={{ height: "100vh" }}>

           
            <div className="card-header bg-primary text-white text-center py-3">
              <h5 className="mb-0">Help Desk Assistant</h5>
            </div>

           
            <div
              className="card-body p-3"
              style={{
                overflowY: "auto",
                height: "calc(100vh - 160px)",
                background: "#f7f7f7"
              }}
            >
              {messages.map((msg, index) => (
                <div
                  key={index}
                  className={`d-flex mb-3 ${
                    msg.sender === "user"
                      ? "justify-content-end"
                      : "justify-content-start"
                  }`}
                >
                  <div
                    className={`p-2 rounded ${
                      msg.sender === "user"
                        ? "bg-primary text-white"
                        : "bg-light border"
                    }`}
                    style={{ maxWidth: "75%" }}
                    dangerouslySetInnerHTML={{ __html: msg.text }}
                  />
                </div>
              ))}

              <div ref={chatEndRef} />
            </div>

            {/* Input Box */}
            <div className="card-footer p-2">
              <div className="input-group">
                <input
                  type="text"
                  className="form-control"
                  placeholder="Type your message..."
                  value={input}
                  onChange={(e) => setInput(e.target.value)}
                  onKeyDown={handleKey}
                />
                <button className="btn btn-primary" onClick={sendMessage}>
                  Send
                </button>
              </div>
            </div>

          </div>
        </div>
      </div>
    </div>
  );
};

export default ChatBot;
