import { useEffect, useState } from "react";
import axios from "axios";

const BarberList = () => {
  const [barbers, setBarbers] = useState([]);

  useEffect(() => {
    fetchBarbers();
  }, []);

  const fetchBarbers = async () => {
    try {
      const response = await axios.get("http://localhost:8080/barbeiros");
      setBarbers(response.data);
    } catch (error) {
      console.error("Erro ao buscar barbeiros:", error);
    }
  };

  return (
    <div className="barber-list">
      <h2>Barbeiros Cadastrados</h2>
      <ul>
        {barbers.map((barber) => (
          <li key={barber.id}>
            {barber.nome} - {barber.email}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default BarberList;