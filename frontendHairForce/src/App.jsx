import { BrowserRouter as Router, Routes, Route } from "react-router-dom";


import './App.css'
import SetPassword from "./Components/SetPassord/SetPassword";
import Login from "./Components/Login/Login";
import Cadastro from "./Components/Cadastro/Cadastro";
import SignupBarber from "./Components/Cadastro/SignupBarber";
import Agendamentos from "./Components/Agendamentos/Agendamentos"
import EsqueciSenha from "./Components/EsqueciSenha/EsqueciSenha"
import Header from "./Components/Header/Header";


function App() {
    return (
        <Router>
            <Header />
            <Routes>
                <Route path="/" element={<Login />} />
                <Route path="/cadastro" element={<Cadastro />} />
                <Route path="/signupbarber" element={<SignupBarber />} />
                <Route path="/agendamentos" element={<Agendamentos />} />
                <Route path="/esqueciSenha" element={<EsqueciSenha />} />
                <Route path="/set-password" element={<SetPassword />} />
            </Routes>
        </Router>
    );
}

export default App;

