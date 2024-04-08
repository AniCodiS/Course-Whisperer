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

    const handleInputChange = event => {
        const { name, value } = event.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = async event => {
        event.preventDefault();

        if (formData.newPassword !== formData.confirmPassword) {
            setError('New password and confirm password do not match.');
            return;
        }

        try {
            // TODO[AO] change aniodiss to logged in user's username
            await axios.put(`http://localhost:8081/api/user/aniodiss/change-password`, {
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
            <div className="auth-footer">
                <Link to="/">Back to Home</Link>
            </div>
        </div>
    );
};

export default ChangePassword;
