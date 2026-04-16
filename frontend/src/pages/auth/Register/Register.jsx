import React, { useState } from "react";
import axios from "axios";
import "bootstrap/dist/css/bootstrap.min.css";
import "./Register.css";
import { Link } from "react-router-dom";
const Register = () => {

    const [form, setForm] = useState({
        firstName: "",
        lastName: "",
        emailId: "",
        password: ""
    });

    const [message, setMessage] = useState("");

    const handleChange = (e) => {
        setForm({
            ...form,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const response = await axios.post(
                "http://localhost:8080/register",
                form
            );

            console.log(response.data);
            setMessage(response.data);

            // Clear form
            setForm({
                firstName: "",
                userName: "",
                email: "",
                password: ""
            });

        } catch (error) {
            console.error(error);
            setMessage(error.response?.data || "Registration Failed ❌");
        }
    };

    return (
        <div className="register-container">

            <div className="register-card">

                {/* LEFT PANEL */}
                <div className="left-panel">
                    
                    <p >
                        <h3 className = "text-center">STATION STOCK</h3>
                      <br />
                        Join our platform to securely manage your assets. Gain full control over asset allocation, usage history, and maintenance records with a reliable and user-friendly system.
                    </p>
                       <br />
                    <button className="login-btn">
                        About Us
                    </button>
                </div>

                {/* RIGHT PANEL */}
                <div className="right-panel">

                    <h3>Sign-up!</h3>

                    {message && (
                        <div className="alert alert-info">{message}</div>
                    )}

                    <form onSubmit={handleSubmit}>

                    <div className="mb-3">
                            <label>Firstname</label>
                            <input
                                type="text"
                                name="firstName"
                                className="form-control"
                                value={form.firstName}
                                onChange={handleChange}
                                required
                            />
                        </div>

                        <div className="mb-3">
                            <label>Lastname</label>
                            <input
                                type="text"
                                name="lastName"
                                className="form-control"
                                value={form.lastName}
                                onChange={handleChange}
                                required
                            />
                        </div>

                        <div className="mb-3">
                            <label>Email</label>
                            <input
                                type="email"
                                name="email"
                                className="form-control"
                                value={form.email}
                                onChange={handleChange}
                                required
                            />
                        </div>

                        <div className="mb-3">
                            <label>Password</label>
                            <input
                                type="password"
                                name="password"
                                className="form-control"
                                value={form.password}
                                onChange={handleChange}
                                required
                            />
                        </div>

                        <button className="register-btn" type="submit">
                            Create an account
                        </button>
                        <br />
                        <p>
  Already have an account? <Link to="/login">Login</Link>
</p>
                    </form>

                </div>

            </div>

        </div>
    );
};

export default Register;