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
    const [windowSize, setWindowSize] = useState({
        width: window.innerWidth,
        height: window.innerHeight,
    });

    const semesterTypes = [
        {name: 'Semester', value: ''},
        {name: 'ODD', value: 'ODD'},
        {name: 'EVEN', value: 'EVEN'},
        {name: 'BOTH', value: 'BOTH'}
    ];

    const username = localStorage.getItem("username");

    const handleResize = () => {
        setWindowSize({
            width: window.innerWidth,
            height: window.innerHeight,
        });
    };

    useEffect(() => {
        window.addEventListener('resize', handleResize);
        return () => {
            window.removeEventListener('resize', handleResize);
        };
    }, []);


    useEffect(() => {
        const getSubjects = async () => {
            try {
                const response = await axios.get('http://localhost:8081/api/subject/all', {
                    params: {
                        username
                    }
                })
                setSubjects(response.data)
            } catch (error) {
                alert(error.response.data.messageDescription);
            }
        }
        getSubjects();

    }, [])

    const searchData = async () => {
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
            alert(error.response.data.messageDescription);
        }
    };

    useEffect(() => {
        const pressHandler = async (e) => {
            if (e.key === "Enter") {
                await searchData();
            }
        };

        window.addEventListener("keydown", pressHandler);
        return () => {
            window.removeEventListener("keydown", pressHandler);
        };
    }, [subjectName, schoolName, creditScore, lecturer, semester]);

    const handleSearch = async () => {
        await searchData();
    };

    const handleChoice = async (username) => {
        try {
            const response = await axios.get(`http://localhost:8081/api/subject/choose`, {
                params: {
                    username: username,
                    schoolName,
                    creditScore,
                    semester
                }
            });
            setSubjects(response.data);
        } catch (error) {
            alert(error.response.data.messageDescription);
        }
    };


    return (
        <div className="container">
            <div style={{display: "flex", flex: 1, justifyContent: "space-between", alignItems: "center"}}>
                <span style={{marginRight: "10px", fontSize: 24, fontWeight: "500", color: "#506C68"}}>Subjects</span>
                <div style={{display: "flex", gap: 20}}>
                    <input className="input-field" placeholder="Subject Name" value={subjectName}
                           onChange={(event) => setSubjectName(event.target.value)}/>
                    <input className="input-field" placeholder="School Name" value={schoolName}
                           onChange={(event) => setSchoolName(event.target.value)}/>
                    <input className="input-field" placeholder="Lecturer" value={lecturer}
                           onChange={(event) => setLecturer(event.target.value)}/>
                    <select style={{
                        padding: 12,
                        borderRadius: 24,
                        border: "1px solid #2DAA944F",
                        fontSize: 14,
                        outline: 'none',
                        backgroundColor: 'white',
                    }}
                            value={semester}
                            onChange={(e) => setSemester(e.target.value)}>
                        {semesterTypes.map(type => (
                            <option key={type.value} value={type.value}>{type.name}</option>
                        ))}
                    </select>
                    <input className="input-field" placeholder="Credit Score" value={creditScore}
                           onChange={(event) => setCreditScore(event.target.value)}/>
                    <button onClick={() => handleSearch()} style={{
                        backgroundColor: '#2DAA944F',
                        border: "none",
                        borderRadius: 32,
                        padding: "10px 24px",
                        textAlign: "center",
                        fontSize: 14,
                        color: 'white',
                        cursor: 'pointer',
                    }}> Search
                    </button>
                    <button onClick={() => handleChoice(username)} style={{
                        backgroundColor: '#2DAA944F',
                        border: "none",
                        borderRadius: 32,
                        padding: "10px 24px",
                        textAlign: "center",
                        fontSize: 14,
                        color: 'white',
                        cursor: 'pointer',
                    }}> Choose
                    </button>
                </div>
            </div>
            <table className="table">
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
        </div>
    );
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
