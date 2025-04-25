import React, { useState } from 'react';
import axios from 'axios';
import { Link } from "react-router-dom";

import "./EsqueciSenha.css"

const EsqueciSenha = () => {
  const [email, setEmail] = useState('');
  const [message, setMessage] = useState('');
  const [error, setError] = useState('');

  const serverContext = "/"; // Defina o contexto do servidor (pode ser uma URL base configurada)

  const handleResetPassword = () => {
    axios
      .post(`${serverContext}user/resetPassword`, { email })
      .then((response) => {
        // Se a requisição for bem-sucedida, redireciona para a página de login com uma mensagem
        window.location.href = `${serverContext}login?message=${response.data.message}`;
      })
      .catch((err) => {
        if (err.response) {
          // Caso de erro do servidor (400, 500, etc)
          if (err.response.data.error && err.response.data.error.includes("MailError")) {
            window.location.href = `${serverContext}emailError.html`;
          } else {
            // Outros tipos de erro
            setError(err.response.data.message || 'Erro desconhecido');
          }
        } else {
          setError('Falha na conexão com o servidor.');
        }
      });
  };

  return (
    <div className="forgot-password">
      <h1>Redefinir Senha</h1>
      <div>
        <label htmlFor="email">Email:</label>
        <input
          type="email"
          id="email"
          name="email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          required
        />
      </div>
      {message && <div className="success-message">{message}</div>}
      {error && <div className="error-message">{error}</div>}
      <button onClick={handleResetPassword}>Redefinir Senha</button>
      <div>
        <a href="/registration.html"></a>
        <Link to="/signup">Cadastro</Link>
        <Link to="/">Login</Link>
      </div>
    </div>
  );
};

export default EsqueciSenha;
