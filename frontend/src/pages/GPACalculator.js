import React, { useState, useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';
import '../styles/GPACalculator.css'; // Import CSS file for GPA Calculator page

const GPACalculator = () => {
    const [subjects, setSubjects] = useState([]);
    const [selectedSubject, setSelectedSubject] = useState('');
    const [grade, setGrade] = useState('');
    const [subjectList, setSubjectList] = useState([]);

    const navigate = useNavigate();

    useEffect(() => {
        const fetchSubjects = async () => {
            try {
                const response = await axios.get('http://localhost:8081/api/subject/all');
                setSubjects(response.data);
            } catch (error) {
                console.error('Error fetching subjects:', error);
            }
        };

        fetchSubjects();
    }, []);

    const handleSubjectChange = event => {
        setSelectedSubject(event.target.value);
    };

    const handleGradeChange = event => {
        setGrade(event.target.value);
    };

    const handleAddSubject = () => {
        if (selectedSubject && grade) {
            const subjectToAdd = subjects.find(subject => subject.name === selectedSubject);
            const subjectWithGrade = { ...subjectToAdd, grade };
            setSubjectList([...subjectList, subjectWithGrade]);
        }
    };

    const calculateGPA = async () => {
        try {
            // Assuming subjects and grades are arrays containing corresponding data
            const courses = subjects.map((subject, index) => ({
                grade: grades[index], // Assuming grades and subjects arrays have the same length
                subjectName: subject
            }));

            const calculateGPADTO = {
                courses: courses
            };

            const response = await axios.post('http://localhost:8081/api/gpa-calculator/calculate', calculateGPADTO);
            console.log('GPA Calculation Result:', response.data);
        } catch (error) {
            console.error('Error calculating GPA:', error);
        }
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
                            <option key={subject.code} value={subject.name}>{subject.name}</option>
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
            <button className="add-button" onClick={handleAddSubject}>Add Subject</button>
            <div className="subject-list">
                <h3>Selected Subjects</h3>
                <ul>
                    {subjectList.map((subject, index) => (
                        <li key={index}>{subject.name} - Grade: {subject.grade}</li>
                    ))}
                </ul>
            </div>
            <button className="calculate-button" onClick={calculateGPA}>Calculate GPA</button>
            <div className="auth-footer">
                <Link to="/">Back to Home</Link>
            </div>
        </div>
    );
};

export default GPACalculator;
