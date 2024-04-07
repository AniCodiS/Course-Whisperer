import React, { useState, useEffect } from 'react';
import axios from 'axios';
import '../styles/StudyGroups.css';

const StudyGroups = () => {
    const [studyGroups, setStudyGroups] = useState([]);
    const [newGroupName, setNewGroupName] = useState('');
    const [newGroupTiming, setNewGroupTiming] = useState('');
    const [newGroupSubject, setNewGroupSubject] = useState('');

    useEffect(() => {
        const fetchStudyGroups = async () => {
            try {
                const response = await axios.get('http://localhost:8081/api/study-group/all');
                setStudyGroups(response.data);
            } catch (error) {
                console.error('Error fetching study groups:', error);
            }
        };

        fetchStudyGroups();
    }, []);

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

    const handleJoinGroup = async (groupName, currentMemberCount, maxMemberCount) => {
        try {
            // Check if the user is already in the group
            const isUserInGroup = studyGroups.find(group => group.groupName === groupName);
            if (isUserInGroup) {
                // Do nothing if the user is already in the group
                return;
            }

            // Check if the group is full
            if (currentMemberCount === maxMemberCount) {
                alert('The group is full.');
                return;
            }

            // Join the group
            await axios.post('http://localhost:8081/api/study-group-member/create', {
                groupName: groupName,
                memberUsername: "username"
                // Add other necessary data like username
            });

            // Update the UI (optional)
            const updatedGroups = studyGroups.map(group => {
                if (group.groupName === groupName) {
                    return { ...group, currentMemberCount: group.currentMemberCount + 1 };
                }
                return group;
            });
            setStudyGroups(updatedGroups);
        } catch (error) {
            console.error('Error joining study group:', error);
        }
    };

    const handleLeaveGroup = async (groupName, currentMemberCount) => {
        try {
            // Leave the group
            await axios.delete('http://localhost:8081/api/study-group-member/delete', {
                data: { groupName: groupName, /* Add other necessary data like username */ }
            });

            // Update the UI (optional)
            const updatedGroups = studyGroups.map(group => {
                if (group.groupName === groupName) {
                    return { ...group, currentMemberCount: group.currentMemberCount - 1 };
                }
                return group;
            });
            setStudyGroups(updatedGroups);
        } catch (error) {
            console.error('Error leaving study group:', error);
        }
    };

    return (
        <div className="study-groups-container">
            <h2>Study Groups</h2>
            <form onSubmit={handleCreateGroup}>
                {/* Form inputs for creating a new group */}
            </form>
            <div className="groups-list">
                {studyGroups.map(group => (
                    <div key={group.id} className="group-item">
                        <h3>{group.groupName}</h3>
                        <p><strong>Timing:</strong> {group.meetingTime}</p>
                        <p><strong>Subject:</strong> {group.subjectName}</p>
                        <p><strong>Current Members:</strong> {group.currentMemberCount}/{group.maxMemberCount}</p>
                        <button onClick={() => handleJoinGroup(group.groupName, group.currentMemberCount, group.maxMemberCount)}>
                            Join Group
                        </button>
                        <button onClick={() => handleLeaveGroup(group.groupName, group.currentMemberCount)}>
                            Leave Group
                        </button>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default StudyGroups;
