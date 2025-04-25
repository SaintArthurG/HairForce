import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from "./Components/Login/Login";
import Registro from "./Components/Registro/Registro";
import SignupBarber from "./Components/Registro/SignupBarber";
import Agendamentos from "./Components/Agendamentos/Agendamentos"
import EsqueciSenha from "./Components/EsqueciSenha/EsqueciSenha"
import Header from "./Components/Header/Header";

import './App.css'


function App() {
    return (
        <Router>
            <Header />
            <Routes>
                <Route path="/" element={<Login />} />
                <Route path="/registro" element={<Registro />} />
                <Route path="/signupbarber" element={<SignupBarber />} />
                <Route path="/agendamentos" element={<Agendamentos />} />
                <Route path="/esqueciSenha" element={<EsqueciSenha />} />
            </Routes>
        </Router>
    );
}

export default App;

