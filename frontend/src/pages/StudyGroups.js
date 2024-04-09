import React, { useState, useEffect } from 'react';
import axios from 'axios';
import '../styles/StudyGroups.css';

const StudyGroups = () => {
    const [studyGroups, setStudyGroups] = useState([]);
    const [newGroupName, setNewGroupName] = useState('');
    const [newGroupTiming, setNewGroupTiming] = useState('');
    const [newGroupSubject, setNewGroupSubject] = useState('');

    useEffect(() => {
        fetchStudyGroups();
    }, []);

    const fetchStudyGroups = async () => {
        try {
            const response = await axios.get('http://localhost:8081/api/study-group/all');
            setStudyGroups(response.data);
        } catch (error) {
            console.error('Error fetching study groups:', error);
        }
    };

    const handleCreateGroup = async event => {
        event.preventDefault();
        try {
            const response = await axios.post('http://localhost:8081/api/study-group/create', {
                subjectName: newGroupSubject,
                meetingTime: newGroupTiming,
                groupName: newGroupName
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
            await axios.post('http://localhost:8081/api/study-group-member/create', {
                groupName: groupName,
                memberUsername: 'aniodiss' // TODO[AO] Replace 'username' with actual username
            });
            fetchStudyGroups();
        } catch (error) {
            console.error('Error joining study group:', error);
        }
    };

    const handleLeaveGroup = async (groupName) => {
        try {
            await axios.delete('http://localhost:8081/api/study-group-member/delete', {
                data: {
                    groupName: groupName,
                    memberUsername: 'aniodiss' // TODO[AO] Replace 'username' with actual username
                }
            });
            fetchStudyGroups(); // Fetch study groups again after leaving
        } catch (error) {
            console.error('Error leaving study group:', error);
        }
    };

    return (
        <div className="study-groups-container">
            <h2>Study Groups</h2>
            <form onSubmit={handleCreateGroup}>
                <label htmlFor="newGroupName">Group Name:</label>
                <input
                    type="text"
                    id="newGroupName"
                    value={newGroupName}
                    onChange={event => setNewGroupName(event.target.value)}
                />
                <label htmlFor="newGroupTiming">Meeting Time:</label>
                <input
                    type="text"
                    id="newGroupTiming"
                    value={newGroupTiming}
                    onChange={event => setNewGroupTiming(event.target.value)}
                />
                <label htmlFor="newGroupSubject">Subject:</label>
                <input
                    type="text"
                    id="newGroupSubject"
                    value={newGroupSubject}
                    onChange={event => setNewGroupSubject(event.target.value)}
                />
                <button type="submit">Create Group</button>
            </form>
            <div className="groups-list">
                {studyGroups.map(group => (
                    <div key={group.id}>
                        <h3>{group.groupName}</h3>
                        <p>Subject: {group.subjectName}</p>
                        <p>Meeting Time: {group.meetingTime}</p>
                        <button onClick={() => handleJoinGroup(group.groupName)}>Join</button>
                        <button onClick={() => handleLeaveGroup(group.groupName)}>Leave</button>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default StudyGroups;
