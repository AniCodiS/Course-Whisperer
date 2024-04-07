import React from 'react';
import {Link} from 'react-router-dom';
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
            <h1 className="title">Course Whisperer</h1>
            <div className="page-list">
                <Link to="/personal-information" className="page-link">
                    <img src={personalInformationImage} alt="Personal Information"/>
                    <span>Personal Information</span>
                </Link>
                <Link to="/change-password" className="page-link">
                    <img src={ChangePasswordImage} alt="Change Password"/>
                    <span>Change Password</span>
                </Link>
                <Link to="/grades" className="page-link">
                    <img src={gradesImage} alt="Grades"/>
                    <span>Grades</span>
                </Link>
                <Link to="/choose-subjects" className="page-link">
                    <img src={chooseSubjectsImage} alt="Choose Subjects"/>
                    <span>Choose Subjects</span>
                </Link>
                <Link to="/gpa-calculator" className="page-link">
                    <img src={gpaCalculatorImage} alt="GPA Calculator"/>
                    <span>GPA Calculator</span>
                </Link>
                <Link to="/study-groups" className="page-link">
                    <img src={studyGroupsImage} alt="Study Groups"/>
                    <span>Study Groups</span>
                </Link>
                <Link to="/student-forum" className="page-link">
                    <img src={studentForumImage} alt="Student Forum"/>
                    <span>Student Forum</span>
                </Link>
                <Link to="/leaderboard" className="page-link">
                    <img src={leaderboardImage} alt="Leaderboard"/>
                    <span>Leaderboard</span>
                </Link>
            </div>
        </div>
    );
};

export default Homepage;