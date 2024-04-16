import React, {useEffect, useState} from 'react';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';
import '../styles/Auth.css';

const SignUp = () => {
    const [formData, setFormData] = useState({
        firstName: '',
        lastName: '',
        email: '',
        year: '',
        faculty: '',
        username: '',
        password: '',
        confirmPassword: ''
    });
    const [passwordError, setPasswordError] = useState('');
    const navigate = useNavigate();

    useEffect(() => {
        // Reset password error when form data changes
        setPasswordError('');
    }, [formData]);

    const handleInputChange = event => {
        const { name, value } = event.target;
        setFormData({ ...formData, [name]: value });
    };

    const validatePassword = () => {
        const { password } = formData;
        // Password validation criteria
        const regexUpperCase = /[A-Z]/;
        const regexLowerCase = /[a-z]/;
        const regexSpecialChar = /[!@#$%^&*(),.?":{}|<>]/;
        if (
            password.length < 8 ||
            !regexUpperCase.test(password) ||
            !regexLowerCase.test(password) ||
            !regexSpecialChar.test(password)
        ) {
            setPasswordError('Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, and one special character.');
            return false;
        }
        setPasswordError('');
        return true;
    };

    const handleSubmit = async event => {
        event.preventDefault();
        if (!validatePassword()) {
            return;
        }
        try {
            // Create user
            const userResponse = await axios.post('http://localhost:8081/api/user/create', {
                email: formData.email,
                username: formData.username,
                password: formData.password
            });

            // Create personal information
            await axios.post('http://localhost:8081/api/personal-information/create', {
                firstName: formData.firstName,
                lastName: formData.lastName,
                email: formData.email,
                year: formData.year,
                faculty: formData.faculty
            });

            navigate('/');
        } catch (error) {
            console.error('Error signing up:', error);
        }
    };

    return (
        <div className="auth-container">
            <h2>Sign Up</h2>
            <form className="auth-form" onSubmit={handleSubmit}>
                <label htmlFor="firstName">First Name:</label>
                <input
                    type="text"
                    id="firstName"
                    name="firstName"
                    value={formData.firstName}
                    onChange={handleInputChange}
                />
                <label htmlFor="lastName">Last Name:</label>
                <input
                    type="text"
                    id="lastName"
                    name="lastName"
                    value={formData.lastName}
                    onChange={handleInputChange}
                />
                <label htmlFor="email">Email:</label>
                <input
                    type="email"
                    id="email"
                    name="email"
                    value={formData.email}
                    onChange={handleInputChange}
                />
                <label htmlFor="year">Year:</label>
                <input
                    type="text"
                    id="year"
                    name="year"
                    value={formData.year}
                    onChange={handleInputChange}
                />
                <label htmlFor="faculty">Faculty:</label>
                <input
                    type="text"
                    id="faculty"
                    name="faculty"
                    value={formData.faculty}
                    onChange={handleInputChange}
                />
                <label htmlFor="username">Username:</label>
                <input
                    type="text"
                    id="username"
                    name="username"
                    value={formData.username}
                    onChange={handleInputChange}
                />
                <label htmlFor="password">Enter Password:</label>
                <input
                    type="password"
                    id="password"
                    name="password"
                    value={formData.password}
                    onChange={handleInputChange}
                />
                {passwordError && <p className="error">{passwordError}</p>}
                <label htmlFor="confirmPassword">Confirm Password:</label>
                <input
                    type="password"
                    id="confirmPassword"
                    name="confirmPassword"
                    value={formData.confirmPassword}
                    onChange={handleInputChange}
                />
                <button type="submit">Sign Up</button>
            </form>
            <div className="auth-footer">
                <Link to="/">Back to Login</Link>
            </div>
        </div>
    );
};

export default SignUp;
