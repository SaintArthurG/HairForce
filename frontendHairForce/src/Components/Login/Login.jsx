import { Link, useNavigate } from "react-router-dom";
import { FaUser, FaLock } from 'react-icons/fa'
import { useState } from 'react'

import axios from "axios";

import "./Login.css"

const Login = () => {
    const [formData, setFormData] = useState({ email: "", password: "" });
    const [error, setError] = useState("");
    const navigate = useNavigate();

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
        setError("");
    };

    const isValidCPF = (cpf) => {
        return /^\d{3}\.\d{3}\.\d{3}-\d{2}$/.test(cpf) || /^\d{11}$/.test(cpf);
    };

    const isValidEmail = (email) => {
        return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
    };

    const handleSubmit = (event) => {
        event.preventDefault();

        if (!formData.email || !formData.password) {
            setError("Preencha todos os campos!");
            return;
        }

        if (!isValidEmail(formData.email) && !isValidCPF(formData.email)) {
            setError("Digite um e-mail ou CPF válido!");
            return;
        }

        axios
            .post("http://localhost:8080/login", formData)
            .then((response) => {
                console.log("Login bem-sucedido", response.data);
                navigate("/dashboard");
            })
            .catch((err) => {
                if (err.response) {
                    setError(err.response.data.message || "Erro ao realizar login. Tente novamente.");
                } else if (err.request) {
                    setError("Não foi possível se conectar ao servidor. Tente novamente mais tarde.");
                } else {
                    setError("Erro ao realizar a requisição.");
                }
                console.error("Erro:", err);
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
                        name="password"
                        placeholder="Senha"
                        onChange={handleChange}
                        value={formData.password}
                    />
                    <FaLock className="icon" />
                </div>

                {error && <p className="error">{error}</p>}

                <div className="recall-forget">
                    <label>
                        <input type="checkbox" /> Lembre de mim Godoy
                    </label>
                    <a href="/forgot-password">Esqueceu a senha?</a>
                </div>

                <button disabled={!formData.email || !formData.password}>
                    Entrar
                </button>

                <div className="App">
                    <label>
                        Não tem uma conta? <Link to="/registro">Registre-se</Link>
                    </label>
                </div>
            </form>
        </div>
    )
}

export default Login
