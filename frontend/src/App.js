import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Login from './components/Login';
import SignUp from './components/SignUp';
import Homepage from './pages/Homepage';
import PersonalInformation from './pages/PersonalInformation';
import Grades from './pages/Grades';
import ChooseSubjects from './pages/ChooseSubjects';
import GPACalculator from './pages/GPACalculator';
import StudyGroups from './pages/StudyGroups';
import StudentForum from './pages/StudentForum/StudentForum';
import Leaderboard from './pages/Leaderboard';
import ChangePassword from "./pages/ChangePassword";

const App = () => {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<Login />} />
                <Route path="/signup" element={<SignUp />} />
                <Route path="/homepage" element={<Homepage />} />
                <Route path="/personal-information" element={<PersonalInformation />} />
                <Route path="/change-password" element={<ChangePassword />} />
                <Route path="/grades" element={<Grades />} />
                <Route path="/choose-subjects" element={<ChooseSubjects />} />
                <Route path="/gpa-calculator" element={<GPACalculator />} />
                <Route path="/study-groups" element={<StudyGroups />} />
                <Route path="/student-forum" element={<StudentForum />} />
                <Route path="/leaderboard" element={<Leaderboard />} />
            </Routes>
        </Router>
    );
};

export default App;
