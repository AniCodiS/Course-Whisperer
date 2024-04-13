import React, {useEffect, useState} from 'react';
import '../styles/ChooseSubjects.css';
import axios from "axios";

const ChooseSubjects = () => {
    const [subjects, setSubjects] = useState([]);
    const [subjectName, setSubjectName] = useState("");
    const [schoolName, setSchoolName] = useState("");
    const [lecturer, setLecturer] = useState("");
    const [semester, setSemester] = useState("");
    const [creditScore, setCreditScore] = useState("");

    const username = localStorage.getItem("username")

    useEffect(() => {
        const getSubjects = async () => {
            const response = await axios.get('http://localhost:8081/api/subject/all', {
                params: {
                    username
                }
            })
            setSubjects(response.data)
        }
        getSubjects();

    }, [])

    useEffect(() => {
        const pressHandler = async (e) => {
            if (e.key === "Enter") {
                try {
                    const response = await axios.get('http://localhost:8081/api/subject/search', {
                        params: {
                            subjectName,
                            schoolName,
                            creditScore,
                            lecturer,
                            semester
                        }
                    });

                    setSubjects(response.data);
                    setSubjectName("");
                    setSchoolName("");
                    setLecturer("");
                    setSemester("");
                    setCreditScore("");
                } catch (error) {
                    console.error("Error fetching data:", error);
                }
            }
        };

        window.addEventListener("keydown", pressHandler);
        return () => {
            window.removeEventListener("keydown", pressHandler);
        };
    }, [subjectName, schoolName, creditScore, lecturer, semester]);



    return (<div className="container">
        <div style={{display: "flex", flex: 1, justifyContent: "space-between", alignItems: "center"}}>
            <span style={{fontSize: 24, fontWeight: "500", color: "#506C68"}}>Subjects</span>
            <div style={{display: "flex", gap: 20}}>
                <input className="input-field" placeholder="Subject Name" value={subjectName}
                       onChange={(event) => setSubjectName(event.target.value)}/>
                <input className="input-field" placeholder="School Name" value={schoolName}
                       onChange={(event) => setSchoolName(event.target.value)}/>
                <input className="input-field" placeholder="Lecturer" value={lecturer}
                       onChange={(event) => setLecturer(event.target.value)}/>
                <input className="input-field" placeholder="Semester" value={semester}
                       onChange={(event) => setSemester(event.target.value)}/>
                <input className="input-field" placeholder="Credit Score" value={creditScore}
                       onChange={(event) => setCreditScore(event.target.value)}/>
            </div>
        </div>
        <table style={{borderCollapse: 'collapse'}}>
            <thead>
            <tr style={{backgroundColor: "#2DAA944F", color: 'darkgreen'}}>
                <th style={styles.headerCell}>Subject Code</th>
                <th style={styles.headerCell}>Subject Name</th>
                <th style={styles.headerCell}>School Name</th>
                <th style={styles.headerCell}>Credit Score</th>
                <th style={styles.headerCell}>Lecturers</th>
                <th style={styles.headerCell}>Semester</th>
            </tr>
            </thead>
            <tbody>
            {subjects.map((subject) => (
                <tr key={subject.code}>
                    <td style={styles.cell}>{subject.code}</td>
                    <td style={styles.cell}>{subject.name}</td>
                    <td style={styles.cell}>{subject.schoolName}</td>
                    <td style={styles.cell}>{subject.creditScore}</td>
                    <td style={styles.cell}>{subject.lecturer}</td>
                    <td style={styles.cell}>{subject.semester}</td>
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

export default ChooseSubjects;
