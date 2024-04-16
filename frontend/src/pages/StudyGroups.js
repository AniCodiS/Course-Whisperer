import React, { useState, useEffect } from 'react';
import axios from 'axios';
import '../styles/StudyGroups.css';

const StudyGroups = () => {
    const [studyGroups, setStudyGroups] = useState([]);
    const [newGroupName, setNewGroupName] = useState('');
    const [newGroupTiming, setNewGroupTiming] = useState('');
    const [newGroupSubject, setNewGroupSubject] = useState('');
    const [filterSubject, setFilterSubject] = useState('');

    useEffect(() => {
        fetchStudyGroups();
    }, []);

    const fetchStudyGroups = async () => {
        try {
            let url = 'http://localhost:8081/api/study-group/all';
            if (filterSubject) {
                url = `http://localhost:8081/api/study-group/filter?subjectName=${filterSubject}`;
            }
            const response = await axios.get(url);
            setStudyGroups(response.data);
        } catch (error) {
            console.error('Error fetching study groups:', error);
        }
    };

    const handleCreateGroup = async event => {
        event.preventDefault();
        try {
            const username = localStorage.getItem('username');

            const response = await axios.post('http://localhost:8081/api/study-group/create', {
                subjectName: newGroupSubject,
                meetingTime: newGroupTiming,
                groupName: newGroupName,
                creatorUsername: username
            });
            setStudyGroups([...studyGroups, response.data]);
            setNewGroupName('');
            setNewGroupTiming('');
            setNewGroupSubject('');
        } catch (error) {
            console.error('Error creating study group:', error);
        }
    };

    const handleJoinGroup = async (groupName) => {
        try {
            const username = localStorage.getItem('username');
            await axios.post('http://localhost:8081/api/study-group-member/create', {
                groupName: groupName,
                memberUsername: username
            });
            fetchStudyGroups();
        } catch (error) {
            console.error('Error joining study group:', error);
        }
    };

    const handleLeaveGroup = async (groupName) => {
        try {
            const username = localStorage.getItem('username');
            await axios.delete('http://localhost:8081/api/study-group-member/delete', {
                data: {
                    groupName: groupName,
                    memberUsername: username
                }
            });
            fetchStudyGroups();
        } catch (error) {
            console.error('Error leaving study group:', error);
        }
    };

    const handleFilterChange = event => {
        setFilterSubject(event.target.value);
    };

    const handleSearch = () => {
        fetchStudyGroups();
    };

    const routeChange = () => {
        window.location.href = 'http://localhost:3000/homepage';
    }

    return (
        <div className="study-groups-container">
            <div className="create-group">
                <h3>Create Group</h3>
                <form onSubmit={handleCreateGroup}>
                    <div className="input-group">
                        <label htmlFor="newGroupName">Group Name:</label>
                        <input
                            type="text"
                            id="newGroupName"
                            value={newGroupName}
                            onChange={event => setNewGroupName(event.target.value)}
                        />
                    </div>
                    <div className="input-group">
                        <label htmlFor="newGroupTiming">Meeting Time:</label>
                        <input
                            type="text"
                            id="newGroupTiming"
                            value={newGroupTiming}
                            onChange={event => setNewGroupTiming(event.target.value)}
                        />
                    </div>
                    <div className="input-group">
                        <label htmlFor="newGroupSubject">Subject:</label>
                        <input
                            type="text"
                            id="newGroupSubject"
                            value={newGroupSubject}
                            onChange={event => setNewGroupSubject(event.target.value)}
                        />
                    </div>
                    <button type="submit">Create Group</button>
                </form>
            </div>
            <div className="filter-section">
                <input
                    type="text"
                    placeholder="Filter by subject name"
                    value={filterSubject}
                    onChange={handleFilterChange}
                />
                <button onClick={handleSearch}>Search</button>
            </div>
            <div className="groups-list">
                <h2>Study Groups</h2>
                {studyGroups.map(group => (
                    <div key={group.id} className="group-item">
                        <h3>{group.groupName}</h3>
                        <p>Subject: {group.subjectName}</p>
                        <p>Meeting Time: {new Date(group.meetingTime).toLocaleString()}</p>
                        <button onClick={() => handleJoinGroup(group.groupName)}>Join</button>
                        <button onClick={() => handleLeaveGroup(group.groupName)}>Leave</button>
                    </div>
                ))}
            </div>
            <button style={{
                position: 'fixed',
                top: '20px',
                left: '20px',
                maxWidth: 200,
                borderRadius: 32,
                padding: "10px 24px",
                textAlign: "center",
                fontSize: 14,
                color: 'white',
                backgroundColor: '#1e90ff',
                cursor: 'pointer',
            }} onClick={routeChange}>Go to Homepage
            </button>
        </div>
    );
};

export default StudyGroups;
