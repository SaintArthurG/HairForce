import { useEffect, useState } from "react";
import axios from "axios";

const ServiceList = () => {
  const [services, setServices] = useState([]);

  useEffect(() => {
    fetchServices();
  }, []);

  const fetchServices = async () => {
    try {
      const response = await axios.get("http://localhost:8080/servicos");
      setServices(response.data);
    } catch (error) {
      console.error("Erro ao buscar serviços:", error);
    }
  };

  return (
    <div className="service-list">
      <h2>Serviços Cadastrados</h2>
      <table>
        <thead>
          <tr>
            <th>Nome</th>
            <th>Preço</th>
          </tr>
        </thead>
        <tbody>
          {services.map((service) => (
            <tr key={service.id}>
              <td>{service.nome}</td>
              <td>R$ {service.preco.toFixed(2)}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default ServiceList;