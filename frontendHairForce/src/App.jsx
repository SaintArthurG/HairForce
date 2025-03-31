import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from "./Components/Login/Login";
import Signup from "./Components/Registro/Signup";
import './App.css'
import SignupBarber from "./Components/Registro/SignupBarber";

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<Login />} />
                <Route path="/signup" element={<Signup />} />
                <Route path="/signupbarber" element={<SignupBarber />} />
            </Routes>
        </Router>
    );
}

export default App;

