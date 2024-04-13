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
            const response = await axios.get('http://localhost:8081/api/passedSubject/retrieve-subjects', {
                params: {
                    username
                }
            })

            setGrades(response.data)
        }

        getGrades();

    }, [])

    useEffect(() => {
        const pressHandler = (e) => {
            if (e.key === "Enter" && subject && gradeScore) {
                setGrades(prev => [{
                    subject,
                    grade: "",
                    gradeScore
                }, ...prev])
                setSubject("")
                setGradeScore("")
            }
        }

        window.addEventListener("keydown", pressHandler)

        return () => {
            window.removeEventListener("keydown", pressHandler)
        }
    }, [subject, gradeScore])

    return (<div style={{
        overflowY: 'auto',
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
        <table style={{width: '100%', borderCollapse: 'collapse'}}>
            <thead>
            <tr style={{backgroundColor: "#2DAA944F", color: 'darkgreen'}}>
                <th style={styles.headerCell}>Subject</th>
                <th style={styles.headerCell}>Grade</th>
                <th style={styles.headerCell}>Grade Score</th>
            </tr>
            </thead>
            <tbody>
            {grades.map((grade, index) => (
                <tr key={index}>
                    <td style={styles.cell}>{grade.subject}</td>
                    <td style={styles.cell}>{grade.grade}</td>
                    <td style={styles.cell}>{grade.gradeScore}</td>
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