import { Link, useNavigate } from "react-router-dom";
import { FaUser, FaLock } from 'react-icons/fa'
import { useState } from 'react'
import apiJWT from "../../services/apiJWT";


import axios from "axios";

import "./Login.css"

const Login = () => {
    const [formData, setFormData] = useState({ email: "", senha: "" });
    const [error, setError] = useState("");
    const navigate = useNavigate();

    const handleChange = (e) => {
        const { email, senha } = formData;
        setFormData({ ...formData, [e.target.name]: e.target.value });
        setError("");
    };

    const isValidEmail = (email) => {
        return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
    };

    const handleSubmit = (event) => {
        event.preventDefault();

        if (!formData.email || !formData.senha) {
            setError("Preencha todos os campos!");
            return;
        }

        if (!isValidEmail(formData.email)) {
            setError("Digite um e-mail válido!");
            return;
        }

        axios
            .post("http://localhost:8080/login", formData)
            .then((response) => {
                const token = response.data.token;
                if(!token){
                    setError("Erro ao receber o token do servidor");
                    return;
                }
                localStorage.setItem("token", token);
                console.log(token);
                
                navigate("/agendamentos");
            })
            .catch((err) => {
                if (err.response) {
                    console.log("Dados do erro:", err.response.data);
                    console.log("Status do erro:", err.response.status);

                    if (err.response.status === 400) {
                        setError("E-mail ou senha inválidos.");
                    } else if (err.response.status === 401) {
                        setError("Não autorizado. Verifique email ou senha.");
                    } else {
                        setError("Erro inesperado no servidor.");
                    }

                } else {
                    setError("Erro de conexão com o servidor.");
                }
            });
    };

    return (
        <div className='container'>
            <form onSubmit={handleSubmit}>
                <h1>Acesse o sistema</h1>

                <div className="input-container">
                    <input
                        type="text"
                        name="email"
                        placeholder="E-mail"
                        onChange={handleChange}
                        value={formData.email}
                    />
                    <FaUser className="icon" />
                </div>

                <div className="input-container">
                    <input
                        type="password"
                        name="senha"
                        placeholder="Senha"
                        onChange={handleChange}
                        value={formData.senha}
                    />
                    <FaLock className="icon" />
                </div>

                {error && <p className="error">{error}</p>}

                <div className="recall-forget">
                    <label>
                        <input type="checkbox" /> Lembre de mim
                    </label>
                    <label>
                        <Link to="/esqueci-senha">Esqueceu a senha?</Link>
                    </label>
                </div>

                <button disabled={!formData.email || !formData.senha}>
                    Entrar
                </button>

                <div className="App">
                    <label>
                        Não tem uma conta? <Link to="/cadastro">Cadastre-se</Link>
                    </label>
                </div>
            </form>
        </div>
    )
}

export default Login
