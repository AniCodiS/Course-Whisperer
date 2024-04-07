// StudyGroups.js

import React, { useState } from 'react';
import '../styles/StudyGroups.css'; // Import CSS file for Study Groups page

const StudyGroups = () => {
    // Hardcoded data for study groups
    const [studyGroups, setStudyGroups] = useState([
        { id: 1, name: 'Group 1', timing: 'Monday 6 PM', subject: 'Mathematics', joined: false },
        { id: 2, name: 'Group 2', timing: 'Tuesday 7 PM', subject: 'Physics', joined: true },
        { id: 3, name: 'Group 3', timing: 'Wednesday 5 PM', subject: 'Computer Science', joined: false },
        // Add more study groups as needed
    ]);

    const [newGroupName, setNewGroupName] = useState('');
    const [newGroupTiming, setNewGroupTiming] = useState('');
    const [newGroupSubject, setNewGroupSubject] = useState('');

    const handleCreateGroup = event => {
        event.preventDefault();
        const newGroup = {
            id: studyGroups.length + 1,
            name: newGroupName,
            timing: newGroupTiming,
            subject: newGroupSubject,
            joined: false
        };
        setStudyGroups([...studyGroups, newGroup]);
        setNewGroupName('');
        setNewGroupTiming('');
        setNewGroupSubject('');
    };

    const handleJoinGroup = id => {
        const updatedStudyGroups = studyGroups.map(group =>
            group.id === id ? { ...group, joined: !group.joined } : group
        );
        setStudyGroups(updatedStudyGroups);
    };

    return (
        <div className="study-groups-container">
            <h2>Study Groups</h2>
            <form onSubmit={handleCreateGroup}>
                <input
                    type="text"
                    placeholder="Group Name"
                    value={newGroupName}
                    onChange={e => setNewGroupName(e.target.value)}
                />
                <input
                    type="text"
                    placeholder="Timing"
                    value={newGroupTiming}
                    onChange={e => setNewGroupTiming(e.target.value)}
                />
                <input
                    type="text"
                    placeholder="Subject"
                    value={newGroupSubject}
                    onChange={e => setNewGroupSubject(e.target.value)}
                />
                <button type="submit">Create Group</button>
            </form>
            <div className="groups-list">
                {studyGroups.map(group => (
                    <div key={group.id} className="group-item">
                        <h3>{group.name}</h3>
                        <p><strong>Timing:</strong> {group.timing}</p>
                        <p><strong>Subject:</strong> {group.subject}</p>
                        {group.joined ? (
                            <button onClick={() => handleJoinGroup(group.id)}>Leave Group</button>
                        ) : (
                            <button onClick={() => handleJoinGroup(group.id)}>Join Group</button>
                        )}
                    </div>
                ))}
            </div>
        </div>
    );
};

export default StudyGroups;
