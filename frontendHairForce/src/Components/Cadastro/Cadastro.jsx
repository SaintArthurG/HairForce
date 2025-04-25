import { useState } from "react";
import "./Cadastro.css"
import axios from "axios";

const Cadastro = () => {
  const [formData, setFormData] = useState({
    nome: "",
    email: "",
    senha: "",
    confirmarSenha: ""
  });

  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
    setError("");
  };

  const isValidEmail = (email) => {
    return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    const { nome, email, senha, confirmarSenha } = formData;

    if (!nome || !email || !senha || !confirmarSenha) {
      setError("Preencha todos os campos!");
      return;
    }

    if (!isValidEmail(email)) {
      setError("Digite um e-mail válido!");
      return;
    }

    if (senha !== confirmarSenha){
      setError("Senhas devem ser iguais!");
      return;
    }

    const dadosParaEnviar = {
      nome,
      email,
      senha
    };

    axios
      .post("http://localhost:8080/usuarios/cadastro", dadosParaEnviar)
      .then((response) => {        
        console.log("Cadastro bem-sucedido", response.data);
        setSuccess("Cadastro realizado com sucesso! Você pode agora fazer login.");
        setFormData({ nome: "", email: "", senha: "", confirmarSenha: "" }); 
      })
      .catch((err) => {
        if (err.response) {
          console.error("Erro no cadastro:", err.response.data);
          setError("Erro ao realizar cadastro. Tente novamente.");
        } else {
          console.error("Erro no servidor:", err.message);
          setError("Erro ao se conectar ao servidor.");
        }
      });
  };

  return (
    <div className="signup-container">
      <h1>Cadastro</h1>
      {error && <div className="error">{error}</div>}
      {success && <div className="success">{success}</div>}
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          name="nome"
          value={formData.nome}
          onChange={handleChange}
          placeholder="Nome"
        />
        <input
          type="email"
          name="email"
          value={formData.email}
          onChange={handleChange}
          placeholder="Email"
        />

        <input
          type="password"
          name="senha"
          value={formData.senha}
          onChange={handleChange}
          placeholder="Senha"
        />

        <input
          type="password"
          name="confirmarSenha"
          value={formData.confirmarSenha}
          onChange={handleChange}
          placeholder="Confirmar Senha"
        />

        <button type="submit">Registrar</button>
      </form>
    </div>
  );
};

export default Cadastro;
