import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import './Agendamentos.css';
import apiJWT from "../../services/apiJWT";
import axios from "axios"

const Agendamentos = () => {
    const navigate = useNavigate();

    const [barbeiroId, setBarbeiroId] = useState("");
    const [data, setData] = useState("");
    const [horario, setHorario] = useState("");
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
                console.log(error);
                
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

        if (!data || !horario || servicos.length === 0 || !barbeiroId) {
            alert("Por favor, preencha todos os campos.");
            return;
        }

        // Combinar data e horário
        const dataHorario = `${data}T${horario}`;

        const serviceMap = {
            "Cortar Cabelo": "CORTAR_CABELO",
            "Fazer a Sobrancelha": "FAZER_SOBRANCELHA",
            "Fazer a Barba": "FAZER_BARBA"
        };

        const servicosFormatados = servicos.map((s) => serviceMap[s]);

        const requestBody = {
            barbeiroId: barbeiroId === "null" ? null : parseInt(barbeiroId, 10),
            dataHorario: dataHorario,
            servico: servicosFormatados
        };

        try {
            const response = await apiJWT.post("http://localhost:8080/agendamentos", requestBody);

            if (response.status === 201 || response.status === 200) {
                alert("Agendamento realizado com sucesso!");
                setBarbeiroId("");
                setData("");
                setHorario("");
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
            <h1>Agende seu Serviço</h1>
            {errorMessage && <p className="error">{errorMessage}</p>}
            <form onSubmit={handleSubmit}>
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
                    <label htmlFor="data">Escolha a Data</label>
                    <input
                        type="date"
                        id="data"
                        value={data}
                        onChange={(e) => {
                            setData(e.target.value);
                            setErrorMessage("");
                        }}
                        required
                    />
                </div>

                <div className="form-group">
                    <label htmlFor="horario">Escolha o Horário</label>
                    <select
                        id="horario"
                        value={horario}
                        onChange={(e) => {
                            setHorario(e.target.value);
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
