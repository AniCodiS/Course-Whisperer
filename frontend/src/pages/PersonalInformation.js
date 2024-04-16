import React, { useState, useEffect } from 'react';
import axios from 'axios';
import '../styles/PersonalInformation.css';

const PersonalInformation = () => {
    const [personalInfo, setPersonalInfo] = useState(null);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchPersonalInfo = async () => {
            try {
                const username = localStorage.getItem('username');

                const response = await axios.get(`http://localhost:8081/api/personal-information/get/${username}`);
                setPersonalInfo(response.data);
            } catch (error) {
                setError(error.message);
            }
        };

        fetchPersonalInfo();
    }, []);

    const routeChange = () => {
        window.location.href = 'http://localhost:3000/homepage';
    }

    return (
        <div className="personal-info-container">
            <div className="personal-info-title">
                <h2>Personal Information</h2>
            </div>
            {error && <p className="error-message">Error: {error}</p>}
            <div className="bubble-grid">
                <div className="bubble-row">
                    <Rectangle label="Name"
                               value={personalInfo ? `${personalInfo.firstName} ${personalInfo.lastName}` : ''}/>
                    <Rectangle label="Email" value={personalInfo ? personalInfo.email : ''}/>
                </div>
                <div className="bubble-row">
                    <Rectangle label="Faculty" value={personalInfo ? personalInfo.faculty : ''}/>
                    <Rectangle label="Year" value={personalInfo ? personalInfo.year : ''}/>
                </div>
            </div>
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

const Rectangle = ({label, value}) => {
    const [hovered, setHovered] = useState(false);

    return (
        <div
            className={`info-rectangle ${hovered ? 'hovered' : ''}`}
            onMouseEnter={() => setHovered(true)}
            onMouseLeave={() => setHovered(false)}
        >
            <div className={`info-label ${hovered ? 'hidden' : ''}`}>{label}</div>
            <div className={`info-value ${hovered ? 'visible' : ''}`}>{value}</div>
        </div>
    );
};

export default PersonalInformation;
