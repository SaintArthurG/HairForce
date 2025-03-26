import { useState } from "react";
import "./Signup.css";
import axios from "axios";

const Signup = () => {
  const [formData, setFormData] = useState({
    username: "",
    email: "",
    password: ""
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
    const { username, email, password } = formData;

    // Validações
    if (!username || !email) {
      setError("Preencha todos os campos!");
      return;
    }

    if (!isValidEmail(email)) {
      setError("Digite um e-mail válido!");
      return;
    }


    // Envio dos dados via axios
    axios
      .post("http://localhost:8080/api/usuarios/cadastro", formData)
      .then((response) => {
        console.log("Cadastro bem-sucedido", response.data);
        setSuccess("Cadastro realizado com sucesso! Você pode agora fazer login.");
        setFormData({ username: "", email: "", password: "" }); // Limpa os campos após sucesso
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
          name="username"
          value={formData.username}
          onChange={handleChange}
          placeholder="Username"
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
          name="password"
          value={formData.password}
          onChange={handleChange}
          placeholder="Password"
        />


        <button type="submit">Registrar</button>
      </form>
    </div>
  );
};

export default Signup;
