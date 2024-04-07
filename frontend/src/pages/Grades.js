import React from 'react';
import '../styles/Grades.css';

const Grades = () => {
    // hardcoded data for grades
    const gradesData = [
        {subjectName: 'Mathematics', creditScore: 3, semester: 'Spring 2023', grade: 'A'},
        {subjectName: 'Physics', creditScore: 4, semester: 'Fall 2022', grade: 'B+'},
        {subjectName: 'Computer Science', creditScore: 3, semester: 'Spring 2022', grade: 'A-'},
        {subjectName: 'English', creditScore: 2, semester: 'Fall 2021', grade: 'B'},
    ];

    return (
        <div className="grades-container">
            <h2>Grades</h2>
            <table className="grades-table">
                <thead>
                <tr>
                    <th>Subject Name</th>
                    <th>Credit Score</th>
                    <th>Semester</th>
                    <th>Grade</th>
                </tr>
                </thead>
                <tbody>
                {gradesData.map((grade, index) => (
                    <tr key={index}>
                        <td>{grade.subjectName}</td>
                        <td>{grade.creditScore}</td>
                        <td>{grade.semester}</td>
                        <td>{grade.grade}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default Grades;