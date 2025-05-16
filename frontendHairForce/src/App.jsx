import { BrowserRouter as Router, Routes, Route } from "react-router-dom";


import './App.css'
import SetPassword from "./Components/SetPassord/SetPassword";
import Login from "./Components/Login/Login";
import Cadastro from "./Components/Cadastro/Cadastro";
import SignupBarber from "./Components/Cadastro/SignupBarber";
import Agendamentos from "./Components/Agendamentos/Agendamentos"
import EsqueciSenha from "./Components/EsqueciSenha/EsqueciSenha"
import Header from "./Components/Header/Header";
import MeusAgendamentos from "./Components/Agendamentos/Meus/MeusAgendamentos";
import Home from "./Components/Home/Home";
import Barbeiro from "./Components/Barbeiro/Barbeiro";


function App() {
    return (
        <Router>
            <Header />
            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/login" element={<Login />} />
                <Route path="/cadastro" element={<Cadastro />} />
                <Route path="/cadastro-barbeiro" element={<SignupBarber />} />
                <Route path="/novo-agendamento" element={<Agendamentos />} />
                <Route path="/esqueci-senha" element={<EsqueciSenha />} />
                <Route path="/nova-senha" element={<SetPassword />} />
                <Route path="/meus-agendamentos" element={<MeusAgendamentos />} />
                <Route path="/area-barbeiro" element={<Barbeiro />} />

            </Routes>
        </Router>
    );
}

export default App;

