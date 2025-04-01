import { useState, useEffect } from "react";
import axios from "axios";

const Schedule = () => {
    const [barberId, setBarberId] = useState("");
    const [time, setTime] = useState("");
    const [services, setServices] = useState([]);
    const [barbers, setBarbers] = useState([]); // Estado para armazenar os barbeiros

    useEffect(() => {
        // Função para buscar barbeiros do backend
        const fetchBarbers = async () => {
            try {
                const response = await axios.get("http://localhost:8080/barbers");
                setBarbers(response.data); // Atualiza o estado com os barbeiros
            } catch (error) {
                console.error("Erro ao buscar barbeiros:", error);
            }
        };

        fetchBarbers(); // Chama a função ao carregar o componente
    }, []);

    const handleServiceChange = (event) => {
        const { value, checked } = event.target;
        setServices((prev) =>
            checked ? [...prev, value] : prev.filter((service) => service !== value)
        );
    };

    const handleSubmit = async (event) => {
        event.preventDefault();

        if (!barberId || !time || services.length === 0) {
            alert("Por favor, preencha todos os campos.");
            return;
        }

        const requestBody = {
            barberId: parseInt(barberId, 10), // Converte para número
            time: `${new Date().toISOString().split("T")[0]}T${time}:00`, // Converte para formato ISO
            services: services
        };

        console.log(requestBody);

        try {
            const response = await axios.post("http://localhost:8080/schedules", requestBody, {
                headers: { "Content-Type": "application/json" }
            });

            if (response.status === 201 || response.status === 200) {
                alert("Agendamento realizado com sucesso!");
                setBarberId("");
                setTime("");
                setServices([]);
            }
        } catch (error) {
            console.error("Erro ao enviar requisição:", error);
            alert("Erro ao agendar. Tente novamente.");
        }
    };

    return (
        <div className="container">
            <h1>Agende seu Serviço</h1>
            <form onSubmit={handleSubmit}>
                <fieldset>
                    <legend>Escolha os Serviços:</legend>
                    {["Cortar Cabelo", "Fazer a Sobrancelha", "Fazer a Barba"].map((service) => (
                        <label key={service}>
                            <input
                                type="checkbox"
                                name="servicos"
                                value={service}
                                onChange={handleServiceChange}
                                checked={services.includes(service)}
                            />
                            <span></span> {service}
                        </label>
                    ))}
                </fieldset>

                <label htmlFor="barbeiro">Escolha o Barbeiro Abaixo</label>
                <select id="barbeiro" value={barberId} onChange={(e) => setBarberId(e.target.value)} required>
                    <option value="" disabled>Selecione um barbeiro</option>
                    {/* Preenche o select com os barbeiros vindos do backend */}
                    {barbers.map((barber) => (
                        <option key={barber.id} value={barber.id}>
                            {barber.name} {/* Ou qualquer atributo que você tenha em Barber */}
                        </option>
                    ))}
                </select>

                <label htmlFor="horario">Escolha o Horário Abaixo</label>
                <select id="horario" value={time} onChange={(e) => setTime(e.target.value)} required>
                    <option value="" disabled>Selecione um horário</option>
                    <option value="09:00">09:00</option>
                    <option value="10:00">10:00</option>
                    <option value="11:00">11:00</option>
                    <option value="14:00">14:00</option>
                    <option value="15:00">15:00</option>
                </select>

                <button type="submit">Agendar</button>
            </form>
        </div>
    );
};

export default Schedule;
