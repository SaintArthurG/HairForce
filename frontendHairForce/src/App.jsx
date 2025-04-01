import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from "./Components/Login/Login";
import Signup from "./Components/Registro/Signup";
import './App.css'
import SignupBarber from "./Components/Registro/SignupBarber";
import Schedule from "./Components/Schedule/Schedule"
import ServiceRegistration from "./Components/Service/ServiceRegistration";

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<Login />} />
                <Route path="/signup" element={<Signup />} />
                <Route path="/signupbarber" element={<SignupBarber />} />
                <Route path="/schedules" element={<Schedule />} />
                <Route path="/services" element={<ServiceRegistration />} />
            </Routes>
        </Router>
    );
}

export default App;

