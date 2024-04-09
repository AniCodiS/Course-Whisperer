import React, { useState, useEffect } from 'react';
import axios from 'axios';
import '../styles/PersonalInformation.css';

const PersonalInformation = () => {
    const [personalInfo, setPersonalInfo] = useState(null);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchPersonalInfo = async () => {
            try {
                // Retrieve the username from localStorage
                const username = localStorage.getItem('username');

                // Make API call to fetch personal information for the logged-in user
                const response = await axios.get(`http://localhost:8081/api/personal-information/get/${username}`);
                setPersonalInfo(response.data);
            } catch (error) {
                setError(error.message);
            }
        };

        fetchPersonalInfo();
    }, []);

    return (
        <div className="personal-info-container">
            {error && <p className="error-message">Error: {error}</p>}
            {personalInfo && (
                <div className="personal-info">
                    <h2>{personalInfo.firstName} {personalInfo.lastName}</h2>
                    <p><strong>Faculty:</strong> {personalInfo.faculty}</p>
                    <p><strong>Email:</strong> {personalInfo.email}</p>
                    <p><strong>Year:</strong> {personalInfo.year}</p>
                </div>
            )}
        </div>
    );
};

export default PersonalInformation;
