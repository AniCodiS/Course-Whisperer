import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';
import '../styles/Auth.css';

const Login = () => {
    const [formData, setFormData] = useState({
        username: '',
        password: ''
    });
    const navigate = useNavigate();

    const handleInputChange = event => {
        const { name, value } = event.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleLoginSuccess = username => {
        // Store username in localStorage
        localStorage.setItem('username', username);
        // Redirect to homepage
        navigate('/homepage');
    };

    const handleSubmit = async event => {
        event.preventDefault();
        try {
            const response = await axios.get('http://localhost:8081/api/user/login', {
                params: {
                    username: formData.username,
                    password: formData.password
                }
            });

            if (response.status === 200) {
                // If login is successful, pass the username to handleLoginSuccess function
                handleLoginSuccess(formData.username);
            } else {
                console.error('Login failed:', response.data);
            }
        } catch (error) {
            console.error('Error logging in:', error);
        }
    };

    return (
        <div className="auth-container">
            <h2>Login</h2>
            <form className="auth-form" onSubmit={handleSubmit}>
                <label htmlFor="username">Username:</label>
                <input
                    type="text"
                    id="username"
                    name="username"
                    value={formData.username}
                    onChange={handleInputChange}
                />
                <label htmlFor="password">Password:</label>
                <input
                    type="password"
                    id="password"
                    name="password"
                    value={formData.password}
                    onChange={handleInputChange}
                />
                <button type="submit">Login</button>
            </form>
            <div className="auth-footer">
                <Link to="/signup">Sign Up</Link>
            </div>
        </div>
    );
};

export default Login;
