import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import Login from "./Components/Login/Login";
import Signup from "./Components/Registro/Signup";
import './App.css'
import SignupBarber from "./Components/Registro/SignupBarber";
import Agendamentos from "./Components/Agendamentos/Agendamentos"
import Header from "./Components/Header/Header";

function App() {
    return (
        <Router>
            <Header />
            <Routes>
                <Route path="/" element={<Login />} />
                <Route path="/signup" element={<Signup />} />
                <Route path="/signupbarber" element={<SignupBarber />} />
                <Route path="/agendamentos" element={<Agendamentos />} />
            </Routes>
        </Router>
    );
}

export default App;

