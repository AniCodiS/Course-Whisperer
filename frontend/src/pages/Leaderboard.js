// Leaderboard.js

import React, { useState } from 'react';
import '../styles/Leaderboard.css'; // Import CSS file for Leaderboard page

const Leaderboard = () => {
    // Hardcoded data for leaderboard
    const [users, setUsers] = useState([
        { id: 1, name: 'John Doe', score: 95 },
        { id: 2, name: 'Alice Smith', score: 88 },
        { id: 3, name: 'Bob Johnson', score: 82 },
        // Add more users as needed
    ]);

    return (
        <div className="leaderboard-container">
            <h2>Leaderboard</h2>
            <div className="users-list">
                <div className="column">
                    <p className="header">Place</p>
                    {users.map((user, index) => (
                        <p key={user.id}>{index + 1}</p>
                    ))}
                </div>
                <div className="column">
                    <p className="header">Name</p>
                    {users.map((user) => (
                        <p key={user.id}>{user.name}</p>
                    ))}
                </div>
                <div className="column">
                    <p className="header">Score</p>
                    {users.map((user) => (
                        <p key={user.id}>{user.score}</p>
                    ))}
                </div>
            </div>
        </div>
    );
};

export default Leaderboard;
