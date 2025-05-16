import { useState } from "react";
import "./Cadastro"
import apiJWT from "../../services/apiJWT";



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

    apiJWT
      .post("http://localhost:8080/barbeiros", formData)
      .then((response) => {
        setSuccess("Cadastro realizado com sucesso!");
        setFormData({ nome: ""});
      })
      .catch((err) => {
        if (err.response) {
          setError(err.response.data);
        } else {
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
