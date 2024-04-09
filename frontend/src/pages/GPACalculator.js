import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';
import '../styles/GPACalculator.css';

const GPACalculator = () => {
    const [subjects, setSubjects] = useState([]);
    const [selectedSubject, setSelectedSubject] = useState('');
    const [grade, setGrade] = useState('');
    const [subjectList, setSubjectList] = useState([]);
    const [gpaResult, setGpaResult] = useState(null);

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
            const courses = subjectList.map(subject => ({
                grade: subject.grade,
                subjectName: subject.name
            }));

            const calculateGPADTO = {
                courses: courses
            };

            const response = await axios.post('http://localhost:8081/api/gpa-calculator/calculate', calculateGPADTO);

            if (response.data.errorMessage) {
                console.error('Error calculating GPA:', response.data.errorMessage);
            } else {
                console.log('GPA Calculation Result:', response.data.gpa);
                setGpaResult(response.data.gpa);
            }
        } catch (error) {
            console.error('Error calculating GPA:', error);
        }
    };

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
                        {['A', 'B', 'C', 'D', 'E', 'F'].map((grade, index) => (
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
            {gpaResult !== null && (
                <div>
                    <h3>GPA Result: {gpaResult}</h3>
                </div>
            )}
            <div className="auth-footer">
                <Link to="/homepage">Back to Home</Link>
            </div>
        </div>
    );
};

export default GPACalculator;
