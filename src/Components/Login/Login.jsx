import { Link } from "react-router-dom";
import { FaUser, FaLock } from 'react-icons/fa'
import { useState } from 'react'
import "./Login.css"

const Login = () => {
  const [formData, setFormData] = useState({ login: "", password: "" });
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

      if (!formData.login || !formData.password) {
          setError("Preencha todos os campos!");
          return;
      }

      if (!isValidEmail(formData.login) && !isValidCPF(formData.login)) {
          setError("Digite um e-mail ou CPF válido!");
          return;
      }

      console.log("Enviando login:", formData);
  };
  return (
    <div className='container'>
      <form onSubmit={handleSubmit}>
        <h1>Acesse o sistema</h1>

        <div className="input-container">
            <input
                type="text"
                name="login"
                placeholder="E-mail ou CPF"
                onChange={handleChange}
                value={formData.login}
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
                <input type="checkbox" /> Lembre de mim
            </label>
            <a href="/forgot-password">Esqueceu a senha?</a>
         </div>

         <button disabled={!formData.login || !formData.password}>
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
