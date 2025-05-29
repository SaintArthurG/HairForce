import { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import './Agendamentos.css';
import apiJWT from "../../services/apiJWT";

const Agendamentos = () => {
    const navigate = useNavigate();

    const [barbeiroId, setBarbeiroId] = useState("");
    const [hora, setHora] = useState("");
    const [servicos, setServicos] = useState([]);
    const [barbeiros, setBarbeiros] = useState([]);
    const [errorMessage, setErrorMessage] = useState("");

    const opcaoSemPreferencia = { id: null, nome: "Sem preferência" };

    // 🔐 Verificar se o usuário está logado
    useEffect(() => {
        const token = localStorage.getItem("token");
        if (!token) {
            navigate("/login");
            return;
        }

        const fetchBarbeiros = async () => {
            try {
                const response = await axios.get("http://localhost:8080/barbeiros");
                const barbeirosComOpcao = [opcaoSemPreferencia, ...response.data];
                setBarbeiros(barbeirosComOpcao);
            } catch (error) {
                setErrorMessage("Erro ao buscar barbeiros");
            }
        };

        fetchBarbeiros();
    }, [navigate]);

    const handleServiceChange = (event) => {
        const { value, checked } = event.target;
        setServicos((prev) =>
            checked ? [...prev, value] : prev.filter((service) => service !== value)
        );
        setErrorMessage("");
    };

    const handleSubmit = async (event) => {
        event.preventDefault();

        if (!hora || servicos.length === 0) {
            alert("Por favor, preencha todos os campos.");
            return;
        }

        const serviceMap = {
            "Cortar Cabelo": "CORTAR_CABELO",
            "Fazer a Sobrancelha": "FAZER_SOBRANCELHA",
            "Fazer a Barba": "FAZER_BARBA"
        };

        const servicosFormatados = servicos.map((s) => serviceMap[s]);

        const requestBody = {
            barbeiroId: barbeiroId === "null" ? null : parseInt(barbeiroId, 10),
            hora,
            servico: servicosFormatados
        };

        try {
            const response = await apiJWT.post("http://localhost:8080/agendamentos", requestBody);

            if (response.status === 201 || response.status === 200) {
                alert("Agendamento realizado com sucesso!");
                setBarbeiroId("");
                setHora("");
                setServicos([]);
            }
        } catch (error) {
            if (error.response) {
                setErrorMessage(error.response.data);
            } else if (error.request) {
                setErrorMessage("Erro ao se conectar com o servidor.");
            } else {
                setErrorMessage(error.message);
            }
        }
    };

    return (
        <div className="container">
            <form onSubmit={handleSubmit}>
            <h1>Agende seu Serviço</h1>
            {errorMessage && <p className="error">{errorMessage}</p>}
                <fieldset>
                    <legend>Escolha os Serviços:</legend>
                    <div className="checkbox-group">
                        {["Cortar Cabelo", "Fazer a Sobrancelha", "Fazer a Barba"].map((service) => (
                            <div className="checkbox-item" key={service}>
                                <input
                                    type="checkbox"
                                    id={`service-${service}`}
                                    name="servicos"
                                    value={service}
                                    onChange={handleServiceChange}
                                    checked={servicos.includes(service)}
                                />
                                <label htmlFor={`service-${service}`}>{service}</label>
                            </div>
                        ))}
                    </div>
                </fieldset>

                <div className="form-group">
                    <label htmlFor="barbeiro">Escolha o Barbeiro</label>
                    <select
                        id="barbeiro"
                        value={barbeiroId}
                        onChange={(e) => {
                            setBarbeiroId(e.target.value);
                            setErrorMessage("");
                        }}
                        required
                    >
                        <option value="" disabled>Selecione um barbeiro</option>
                        {barbeiros.map((barbeiro) => (
                            <option key={barbeiro.id} value={barbeiro.id}>
                                {barbeiro.nome}
                            </option>
                        ))}
                    </select>
                </div>

                <div className="form-group">
                    <label htmlFor="horario">Escolha o Horário</label>
                    <select
                        id="horario"
                        value={hora}
                        onChange={(e) => {
                            setHora(e.target.value);
                            setErrorMessage("");
                        }}
                        required
                    >
                        <option value="" disabled>Selecione um horário</option>
                        <option value="09:00">09:00</option>
                        <option value="10:00">10:00</option>
                        <option value="11:00">11:00</option>
                        <option value="14:00">14:00</option>
                        <option value="15:00">15:00</option>
                    </select>
                </div>

                <button type="submit">Agendar</button>
            </form>
        </div>
    );
};

export default Agendamentos;
