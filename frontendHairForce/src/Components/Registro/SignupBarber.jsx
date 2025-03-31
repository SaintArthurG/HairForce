import { useState } from "react";
import axios from "axios";
import "./Signup"


const SignupBarber = () => {
  const [formData, setFormData] = useState({
    name: ""
  });

  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
    setError("");
  };


  const handleSubmit = (event) => {
    event.preventDefault();
    const { name } = formData;

    if (!name) {
      setError("Preencha o campo!");
      return;
    }

    axios
      .post("http://localhost:8080/barber/cadastro", formData)
      .then((response) => {
        console.log("Barbeiro cadastrado com sucesso", response.data);
        setSuccess("Cadastro realizado com sucesso!");
        setFormData({ name: ""});
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
          name="name"
          value={formData.username}
          onChange={handleChange}
          placeholder="Name"
        />
        <button type="submit">Registrar</button>
      </form>
    </div>
  );
};

export default SignupBarber;
