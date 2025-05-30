import { Link, useNavigate, useSearchParams } from "react-router-dom";
import { FaLock } from 'react-icons/fa'
import { useState } from 'react'

import axios from "axios";

const SetPassord = () => {
    const [formData, setFormData] = useState({ novaSenha: "" });
    const [error, setError] = useState("");
    const navigate = useNavigate();

    const handleChange = (e) => {
        const { novaSenha } = formData;
        setFormData({ ...formData, [e.target.name]: e.target.value });
        setError("");
    };

    const [searchParams] = useSearchParams();
    const token = searchParams.get("token");


    const handleSubmit = (event) => {
        event.preventDefault();

        if (!formData.novaSenha) {
            setError("Preencha todos os campos!");
            return;
        }
        axios
            .put(`http://localhost:8080/usuarios/set-password?token=${token}`, formData)
            .then((response) => {
                alert("Senha redefinida com sucesso!");
                navigate("/");
            })
            .catch((err) => {
                if (err.response) {
                    if (err.response.status === 400) {
                        setError("E-mail ou senha inválidos.");
                    } else if (err.response.status === 401) {
                        setError("Não autorizado. Verifique se a senha segue os criterios.");
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
                <h1>Resete a senha</h1>
                <div className="input-container">
                    <input
                        type="password"
                        name="novaSenha"
                        placeholder="novaSenha"
                        onChange={handleChange}
                        value={formData.novaSenha}
                    />
                    <FaLock className="icon" />
                </div>

                {error && <p className="error">{error}</p>}

                <button disabled={!formData.novaSenha}>
                    <span>Redefinir Senha</span>
                </button>

                <div className="App">
                    <label>
                        Não tem uma conta? <Link to="/registro">Cadastre-se</Link>
                    </label>
                </div>
            </form>
        </div>
    )
}

export default SetPassord;
