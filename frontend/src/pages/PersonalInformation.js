import React, { useState, useEffect } from 'react';
import axios from 'axios';

const PersonalInformation = () => {
    const [personalInfo, setPersonalInfo] = useState(null);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchPersonalInfo = async () => {
            try {
                // Replace 'username' with the actual username you want to retrieve information for
                const response = await axios.get('http://localhost:8081/api/personal-information/get/aniodiss');
                setPersonalInfo(response.data);
            } catch (error) {
                setError(error.message);
            }
        };

        fetchPersonalInfo();
    }, []);

    return (
        <div>
            {error && <p>Error: {error}</p>}
            {personalInfo && (
                <div>
                    <h2>{personalInfo.firstName} {personalInfo.lastName}</h2>
                    <p>Faculty: {personalInfo.faculty}</p>
                    <p>Email: {personalInfo.email}</p>
                    <p>Year: {personalInfo.year}</p>
                </div>
            )}
        </div>
    );
};

export default PersonalInformation;
