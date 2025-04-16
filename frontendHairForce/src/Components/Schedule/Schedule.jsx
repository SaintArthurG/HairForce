import { useState, useEffect } from "react";
import axios from "axios";
import './Schedule.css';

const Schedule = () => {
    const [id, setId] = useState("");
    const [time, setTime] = useState("");
    const [services, setServices] = useState([]);
    const [barbeiro, setBarbeiro] = useState([]);

    useEffect(() => {
        const fetchBarbeiros = async () => {
            try {
                const response = await axios.get("http://localhost:8080/barbeiros");
                setBarbeiro(response.data); 
            } catch (error) {
                console.error("Erro ao buscar barbeiros:", error);
            }
        };

        fetchBarbeiros(); 
    }, []);

    const handleServiceChange = (event) => {
        const { value, checked } = event.target;
        setServices((prev) =>
            checked ? [...prev, value] : prev.filter((service) => service !== value)
        );
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
      
        if (!id || !time || services.length === 0) {
          alert("Por favor, preencha todos os campos.");
          return;
        }

        const serviceMap = {
            "Cortar Cabelo": "CORTAR_CABELO",
            "Fazer a Sobrancelha": "FAZER_SOBRANCELHA",
            "Fazer a Barba": "FAZER_BARBA"
          };
      
        const requestBody = {
          id: parseInt(id, 10),
          time: time,
          services: services.map(service => serviceMap[service])
        };
      
        console.log("Enviando:", requestBody);
      
        try {
          const response = await axios.post("http://localhost:8080/schedules", requestBody, {
            headers: { "Content-Type": "application/json" }
          });
      
          if (response.status === 201 || response.status === 200) {
            alert("Agendamento realizado com sucesso!");
            setId("");
            setTime("");
            setServices([]);
          }
        } catch (error) {
          console.error("Erro ao enviar requisição:", error);
        }
      };

    return (
        <div className="container">
            <h1>Agende seu Serviço</h1>
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
                                    checked={services.includes(service)}
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
                        value={id} 
                        onChange={(e) => setId(e.target.value)} 
                        required
                    >
                        <option value="" disabled>Selecione um barbeiro</option>
                        {barbeiro.map((barbeiro) => (
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
                        value={time} 
                        onChange={(e) => setTime(e.target.value)} 
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

export default Schedule;