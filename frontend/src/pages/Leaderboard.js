import React, { useState, useEffect } from 'react';
import axios from 'axios';
import '../styles/Leaderboard.css';

const Leaderboard = () => {
    const [users, setUsers] = useState([]);

    useEffect(() => {
        const fetchLeaderboard = async () => {
            try {
                const response = await axios.get('http://localhost:8081/api/user-score/all');
                setUsers(response.data.sort((a, b) => b.score - a.score)); // Sort users based on score in descending order
            } catch (error) {
                console.error('Error fetching leaderboard:', error);
            }
        };

        fetchLeaderboard();
    }, []);

    return (
        <div className="leaderboard-container">
            <h2>Leaderboard</h2>
            <div className="users-list">
                <div className="column">
                    <p className="header">Place</p>
                    {users.map((user, index) => (
                        <p key={user.username}>{index + 1}</p>
                    ))}
                </div>
                <div className="column">
                    <p className="header">Username</p>
                    {users.map((user) => (
                        <p key={user.username}>{user.username}</p>
                    ))}
                </div>
                <div className="column">
                    <p className="header">Score</p>
                    {users.map((user) => (
                        <p key={user.username}>{user.score}</p>
                    ))}
                </div>
            </div>
        </div>
    );
};

export default Leaderboard;
