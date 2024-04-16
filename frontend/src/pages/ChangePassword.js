import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';
import '../styles/Auth.css';

const ChangePassword = () => {
    const [formData, setFormData] = useState({
        oldPassword: '',
        newPassword: '',
        confirmPassword: ''
    });

    const [error, setError] = useState(null);
    const [success, setSuccess] = useState(false);
    const navigate = useNavigate();

    const username = localStorage.getItem('username');

    const handleInputChange = event => {
        const { name, value } = event.target;
        setFormData({ ...formData, [name]: value });
    };

    const validatePassword = () => {
        const { newPassword } = formData;
        const regexUpperCase = /[A-Z]/;
        const regexLowerCase = /[a-z]/;
        const regexSpecialChar = /[!@#$%^&*(),.?":{}|<>]/;
        if (
            newPassword.length < 8 ||
            !regexUpperCase.test(newPassword) ||
            !regexLowerCase.test(newPassword) ||
            !regexSpecialChar.test(newPassword)
        ) {
            setError('New password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, and one special character.');
            return false;
        }
        setError('');
        return true;
    };

    const handleSubmit = async event => {
        event.preventDefault();

        if (formData.newPassword === formData.oldPassword) {
            setError('New password cannot be the same as the old password.');
            return;
        }

        if (formData.newPassword !== formData.confirmPassword) {
            setError('New password and confirm password do not match.');
            return;
        }

        if (!validatePassword()) {
            setError('Invalid new password. Please try again.');
            return;
        }

        try {
            await axios.put(`http://localhost:8081/api/user/${username}/change-password`, {
                oldPassword: formData.oldPassword,
                newPassword: formData.newPassword,
                confirmNewPassword: formData.confirmPassword
            });
            setSuccess(true);
            setError(null);
            navigate('/');
        } catch (error) {
            if (error.response.status === 400) {
                setError('Incorrect old password. Please try again.');
            } else {
                setError(error.response.data);
            }
            setSuccess(false);
        }
    };

    const routeChange = () => {
        window.location.href = 'http://localhost:3000/homepage';
    }

    return (
        <div className="auth-container">
            <h2>Change Password</h2>
            <form className="auth-form" onSubmit={handleSubmit}>
                <label htmlFor="oldPassword">Old Password:</label>
                <input
                    type="password"
                    id="oldPassword"
                    name="oldPassword"
                    value={formData.oldPassword}
                    onChange={handleInputChange}
                />
                <label htmlFor="newPassword">New Password:</label>
                <input
                    type="password"
                    id="newPassword"
                    name="newPassword"
                    value={formData.newPassword}
                    onChange={handleInputChange}
                />
                <label htmlFor="confirmPassword">Confirm Password:</label>
                <input
                    type="password"
                    id="confirmPassword"
                    name="confirmPassword"
                    value={formData.confirmPassword}
                    onChange={handleInputChange}
                />
                <button type="submit">Change Password</button>
            </form>
            {error && <p className="error-message">{error}</p>}
            {success && <p className="success-message">Password changed successfully.</p>}
            <button style={{
                position: 'fixed',
                top: '20px',
                left: '20px',
                maxWidth: 200,
                borderRadius: 32,
                padding: "10px 24px",
                textAlign: "center",
                fontSize: 14,
                color: 'white',
                backgroundColor: '#1e90ff',
                cursor: 'pointer',
            }} onClick={routeChange}>Go to Homepage
            </button>
        </div>
    );
};

export default ChangePassword;
