// GPACalculator.js

import React, { useState } from 'react';
import '../styles/GPACalculator.css'; // Import CSS file for GPA Calculator page

const GPACalculator = () => {
    // Hardcoded data for subjects
    const subjects = [
        { id: 1, name: 'Mathematics', creditScore: 3 },
        { id: 2, name: 'Physics', creditScore: 4 },
        { id: 3, name: 'Computer Science', creditScore: 3 },
        { id: 4, name: 'English', creditScore: 2 },
        // Add more subjects as needed
    ];

    const [selectedSubject, setSelectedSubject] = useState('');
    const [grade, setGrade] = useState('');

    const handleSubjectChange = event => {
        setSelectedSubject(event.target.value);
    };

    const handleGradeChange = event => {
        setGrade(event.target.value);
    };

    const calculateGPA = () => {
        // Calculate GPA logic here
        // For simplicity, let's just log the selected subject and grade
        console.log('Selected Subject:', selectedSubject);
        console.log('Grade:', grade);
    };

    // Possible grades
    const grades = ['A', 'B', 'C', 'D', 'E', 'F'];

    return (
        <div className="gpa-calculator-container">
            <h2>GPA Calculator</h2>
            <div className="input-fields">
                <div className="field">
                    <label htmlFor="subject">Subject:</label>
                    <select id="subject" value={selectedSubject} onChange={handleSubjectChange}>
                        <option value="">Select Subject</option>
                        {subjects.map(subject => (
                            <option key={subject.id} value={subject.name}>{subject.name}</option>
                        ))}
                    </select>
                </div>
                <div className="field">
                    <label htmlFor="grade">Grade:</label>
                    <select id="grade" value={grade} onChange={handleGradeChange}>
                        <option value="">Select Grade</option>
                        {grades.map((grade, index) => (
                            <option key={index} value={grade}>{grade}</option>
                        ))}
                    </select>
                </div>
            </div>
            <button className="calculate-button" onClick={calculateGPA}>Calculate</button>
        </div>
    );
};

export default GPACalculator;
