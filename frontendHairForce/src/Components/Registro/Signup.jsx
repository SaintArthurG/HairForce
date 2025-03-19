import { Link } from "react-router-dom";
import { FaUser, FaLock, FaEnvelope } from "react-icons/fa";
import { useState } from "react";


const Signup = () => {
    const [formData, setFormData] = useState({
        name: "",
        login: "",
        password: "",
        confirmPassword: "",
    });

    const [error, setError] = useState("");

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

        const { name, login, password, confirmPassword } = formData;

        if (!name || !login || !password || !confirmPassword) {
            setError("Preencha todos os campos!");
            return;
        }

        if (!isValidEmail(login) && !isValidCPF(login)) {
            setError("Digite um e-mail ou CPF válido!");
            return;
        }

        if (password.length < 6) {
            setError("A senha deve ter no mínimo 6 caracteres!");
            return;
        }

        if (password !== confirmPassword) {
            setError("As senhas não coincidem!");
            return;
        }

        console.log("Registro enviado:", formData);
    };

    return (
        <div className="signup-container">
        <h1>Cadastro</h1>
        <form onSubmit={handleSubmit}>
          <input
            type="text"
            name="name"
            value={formData.name}
            onChange={handleChange}
            placeholder="Nome"
          />
          <input
            type="text"
            name="login"
            value={formData.login}
            onChange={handleChange}
            placeholder="E-mail ou CPF"
          />
          <input
            type="password"
            name="password"
            value={formData.password}
            onChange={handleChange}
            placeholder="Senha"
          />
          <input
            type="password"
            name="confirmPassword"
            value={formData.confirmPassword}
            onChange={handleChange}
            placeholder="Confirmar Senha"
          />
          <button type="submit">Registrar</button>
        </form>
      </div>
    );
}

export default Signup;
