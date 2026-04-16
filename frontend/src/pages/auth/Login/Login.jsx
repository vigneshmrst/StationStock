import React, { useState } from "react";
import "./Login.css";
import { Link } from "react-router-dom";
const Login = () => {
  const [formData, setFormData] = useState({
    email: "",
    password: "",
  });

  const [message, setMessage] = useState("");

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();   

    try {
      const response = await fetch("http://localhost:8080/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          emailId: formData.emailId,
          password: formData.password,
        }),
      });

      if (response.ok) {
        const data = await response.json();
        console.log("Login Success:", data);
        setMessage("Login Successful ✅");
      } else {
        setMessage("Invalid Credentials ❌");
      }
    } catch (error) {
      console.error(error);
      setMessage("Server Error ⚠️");
    }
  };

  return (
    <div className="login-container">
      <div className="login-box">

        {/* Left Panel */}
        <div className="left-panel">
          <h2 className="text-center mt-3">STATION STOCK</h2>
          <p>
            Join our platform to securely manage your assets. Gain full control over asset allocation, usage history, and maintenance records with a reliable and user-friendly system.
          </p>
        </div>

        {/* Right Panel */}
        <div className="right-panel">
          <h2>Login</h2>

          <form onSubmit={handleSubmit}>
            <input
              type="email"
              name="emailId"
              placeholder="Enter Email"
              value={formData.emailId}
              onChange={handleChange}
              required
            />

            <input
              type="password"
              name="password"
              placeholder="Enter Password"
              value={formData.password}
              onChange={handleChange}
              required
            />

            <button type="submit">Login</button>
          </form>

          {message && <p className="message">{message}</p>}

          <p>
            Don't have an account? <Link to="/register">Sign up</Link>
          </p>
        </div>
      </div>
    </div>
  );
};

export default Login;