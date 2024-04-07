import React from 'react';
import '../styles/ChooseSubjects.css';

const ChooseSubjects = () => {
    // hardcoded data for subjects
    const subjectsData = [
        {subjectName: 'Mathematics', creditScore: 3, semester: 'Spring 2023', avgRating: 4.5},
        {subjectName: 'Physics', creditScore: 4, semester: 'Fall 2022', avgRating: 4.2},
        {subjectName: 'Computer Science', creditScore: 3, semester: 'Spring 2022', avgRating: 4.8},
        {subjectName: 'English', creditScore: 2, semester: 'Fall 2021', avgRating: 4.0},
    ];

    return (
        <div className="choose-subjects-container">
            <h2>Choose Subjects</h2>
            <div className="search-bar">
                <div className="search-field">
                    <label htmlFor="subjectTitle">Choose Subject Title:</label>
                    <input type="text" id="subjectTitle"/>
                </div>
                <div className="search-field">
                    <label htmlFor="schoolName">School Name:</label>
                    <input type="text" id="schoolName"/>
                </div>
                <div className="search-field">
                    <label htmlFor="creditScore">Credit Score:</label>
                    <input type="number" id="creditScore"/>
                </div>
                <div className="search-field">
                    <label htmlFor="semester">Choose Semester:</label>
                    <select id="semester">
                        <option value="">Select</option>
                        <option value="Spring 2023">Spring 2023</option>
                        <option value="Fall 2022">Fall 2022</option>
                        {/* Add more options as needed */}
                    </select>
                </div>
                <button className="search-button">Search Subject</button>
            </div>
            <div className="subjects-list">
                {subjectsData.map((subject, index) => (
                    <div key={index} className="subject-item">
                        <h3>{subject.subjectName}</h3>
                        <p><strong>Credit Score:</strong> {subject.creditScore}</p>
                        <p><strong>Semester:</strong> {subject.semester}</p>
                        <p><strong>AVG Rating:</strong> {subject.avgRating}</p>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default ChooseSubjects;
