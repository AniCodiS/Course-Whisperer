import React, {useEffect, useState} from 'react';
import '../styles/Grades.css';
import axios from "axios";

const Grades = () => {
    const [grades, setGrades] = useState([]);
    const [subject, setSubject] = useState("");
    const [gradeScore, setGradeScore] = useState("");

    const username = localStorage.getItem("username")

    useEffect(() => {
        const getGrades = async () => {
            try {
                const response = await axios.get('http://localhost:8081/api/passedSubject/retrieve-subjects', {
                    params: {
                        username
                    }
                })
                setGrades(response.data)
            } catch (error) {
                alert(error.response.data.messageDescription);
            }
        }
        getGrades();

    }, [])

    useEffect(() => {
        const pressHandler = async (e) => {
            if (e.key === "Enter" && subject && gradeScore) {
                try {
                    const response = await axios.post('http://localhost:8081/api/passedSubject/create-subject', {
                        username,
                        subject,
                        gradeScore
                    })
                    setGrades(prev => [response.data, ...prev])
                    setSubject("")
                    setGradeScore("")
                } catch (error) {
                    alert(error.response.data.messageDescription);
                }
            }
        }

        window.addEventListener("keydown", pressHandler)
        return () => {
            window.removeEventListener("keydown", pressHandler)
        }
    }, [subject, gradeScore])

    const handleDeleteRow = async (id, username) => {
        try {
            await axios.delete(`http://localhost:8081/api/passedSubject/remove/${id}`, {
                params: {
                    username: username
                }
            });
            setGrades(prevGrades => prevGrades.filter(grade => grade.id !== id));
        } catch (error) {
            console.error('Error deleting row:', error);
        }
    };

    return (<div style={{
        overflowY: 'auto',
        maxHeight: 'calc(100vh - 20px)',
        display: "flex",
        flexDirection: "column",
        padding: 32,
        gap: 24
    }}>
        <div style={{display: "flex", flex: 1, justifyContent: "space-between", alignItems: "center"}}>
            <span style={{fontSize: 24, fontWeight: "500", color: "#506C68"}}>Grades</span>
            <div style={{display: "flex", gap: 20}}>
                <input style={{padding: 12, outline: "none", borderRadius: 24, borderColor: "#2DAA944F"}}
                       placeholder="Subject" value={subject} onChange={(event) => setSubject(event.target.value)}/>
                <input style={{padding: 12, outline: "none", borderRadius: 24, borderColor: '#2DAA944F'}}
                       placeholder="Grade Score" value={gradeScore}
                       onChange={(event) => setGradeScore(event.target.value)}/>
            </div>
        </div>
        <table style={{borderCollapse: 'collapse'}}>
            <thead>
            <tr style={{backgroundColor: "#2DAA944F", color: 'darkgreen'}}>
                <th style={styles.headerCell}>Subject</th>
                <th style={styles.headerCell}>Grade</th>
                <th style={styles.headerCell}>Grade Score</th>
                <th style={styles.headerCell}>Action</th>
            </tr>
            </thead>
            <tbody>
            {grades.map((grade) => (
                <tr key={grade.id}>
                    <td style={styles.cell}>{grade.subject}</td>
                    <td style={styles.cell}>{grade.grade}</td>
                    <td style={styles.cell}>{grade.gradeScore}</td>
                    <td style={styles.cell}>
                        <button onClick={() => handleDeleteRow(grade.id, username)} style={{
                            backgroundColor: '#2DAA944F',
                            border: "none",
                            borderRadius: 32,
                            padding: "10px 24px",
                            textAlign: "center",
                            fontSize: 14,
                            color: 'white',
                            cursor: 'pointer',
                        }}> Delete
                        </button>
                    </td>
                </tr>
            ))}
            </tbody>
        </table>
    </div>)
};

const styles = {
    headerCell: {
        padding: '12px',
        textAlign: 'center',
        borderBottom: '1px solid #506C68',
        borderBottomWidth: 0.5,
        borderBottomColor: "#506C68",
    },
    cell: {
        padding: '12px',
        textAlign: 'center',
        borderBottom: '1px solid #506C68',
        borderBottomWidth: 0.5,
        borderBottomColor: "#506C68",
    },
};

export default Grades;