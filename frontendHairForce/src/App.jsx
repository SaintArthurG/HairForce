import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from "./Components/Login/Login";
import Signup from "./Components/Registro/Signup";
import './App.css'
import SignupBarber from "./Components/Registro/SignupBarber";
import Agendamentos from "./Components/Agendamentos/Agendamentos"
import EsqueciSenha from "./Components/EsqueciSenha/EsqueciSenha"
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
                <Route path="/esqueciSenha" element={<EsqueciSenha />} />
            </Routes>
        </Router>
    );
}

export default App;

