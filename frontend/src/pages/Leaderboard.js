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

    const routeChange = () => {
        window.location.href = 'http://localhost:3000/homepage';
    }

    return (
        <div className="leaderboard-container">
            <div className="leaderboard-title">
                <h2>Leaderboard</h2>
            </div>
            <div className="users-list">
                <div className="column">
                <div className="header-values">
                        <div className="header-item">Place</div>
                        <div className="header-item">Username</div>
                        <div className="header-item">Score</div>
                    </div>
                    {users.map((user, index) => (
                        <div key={user.username} className="header-values">
                            <div className="value">{index + 1}</div>
                            <div className="value">{user.username}</div>
                            <div className="value">{user.score}</div>
                        </div>
                    ))}
                </div>
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

export default Leaderboard;
