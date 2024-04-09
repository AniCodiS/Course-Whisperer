import React from 'react';
import { Link } from 'react-router-dom';
import personalInformationImage from '../images/person.png';
import ChangePasswordImage from '../images/password.png';
import gradesImage from '../images/grades.png';
import chooseSubjectsImage from '../images/subjects.png';
import gpaCalculatorImage from '../images/gpa.png';
import studyGroupsImage from '../images/group.png';
import studentForumImage from '../images/forum.png';
import leaderboardImage from '../images/medal.png';
import '../styles/Homepage.css';

const Homepage = () => {
    return (
        <div className="homepage-container">
            <h1 className="title">Welcome to Course Whisperer</h1>
            <div className="page-list">
                <Link to="/personal-information" className="page-link">
                    <div className="page-item">
                        <img src={personalInformationImage} alt="Personal Information" className="page-img" />
                        <span className="page-text">Personal Information</span>
                    </div>
                </Link>
                <Link to="/change-password" className="page-link">
                    <div className="page-item">
                        <img src={ChangePasswordImage} alt="Change Password" className="page-img" />
                        <span className="page-text">Change Password</span>
                    </div>
                </Link>
                <Link to="/grades" className="page-link">
                    <div className="page-item">
                        <img src={gradesImage} alt="Grades" className="page-img" />
                        <span className="page-text">Grades</span>
                    </div>
                </Link>
                <Link to="/choose-subjects" className="page-link">
                    <div className="page-item">
                        <img src={chooseSubjectsImage} alt="Choose Subjects" className="page-img" />
                        <span className="page-text">Choose Subjects</span>
                    </div>
                </Link>
                <Link to="/gpa-calculator" className="page-link">
                    <div className="page-item">
                        <img src={gpaCalculatorImage} alt="GPA Calculator" className="page-img" />
                        <span className="page-text">GPA Calculator</span>
                    </div>
                </Link>
                <Link to="/study-groups" className="page-link">
                    <div className="page-item">
                        <img src={studyGroupsImage} alt="Study Groups" className="page-img" />
                        <span className="page-text">Study Groups</span>
                    </div>
                </Link>
                <Link to="/student-forum" className="page-link">
                    <div className="page-item">
                        <img src={studentForumImage} alt="Student Forum" className="page-img" />
                        <span className="page-text">Student Forum</span>
                    </div>
                </Link>
                <Link to="/leaderboard" className="page-link">
                    <div className="page-item">
                        <img src={leaderboardImage} alt="Leaderboard" className="page-img" />
                        <span className="page-text">Leaderboard</span>
                    </div>
                </Link>
            </div>
        </div>
    );
};

export default Homepage;
