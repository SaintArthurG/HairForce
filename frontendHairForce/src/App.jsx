import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from "./Components/Login/Login";
import Signup from "./Components/Registro/Signup";
import './App.css'
import SignupBarber from "./Components/Registro/SignupBarber";
import Schedule from "./Components/Schedule/Schedule"

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<Login />} />
                <Route path="/signup" element={<Signup />} />
                <Route path="/signupbarber" element={<SignupBarber />} />
                <Route path="/schedule" element={<Schedule />} />
            </Routes>
        </Router>
    );
}

export default App;

