import { useState } from "react";
import axios from "axios";
import "./Signup"


const SignupBarber = () => {
  const [formData, setFormData] = useState({
    nome: ""
  });

  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
    setError("");
  };


  const handleSubmit = (event) => {
    event.preventDefault();
    const { nome } = formData;

    if (!nome) {
      setError("Preencha o campo!");
      return;
    }

    axios
      .post("http://localhost:8080/barbeiros", formData)
      .then((response) => {
        console.log("Barbeiro cadastrado com sucesso", response.data);
        setSuccess("Cadastro realizado com sucesso!");
        setFormData({ nome: ""});
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
      <h1>Cadastro Barbeiro</h1>
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
        <button type="submit">Registrar</button>
      </form>
    </div>
  );
};

export default SignupBarber;
