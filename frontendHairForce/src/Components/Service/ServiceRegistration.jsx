import { useState } from "react";
import axios from "axios";
import "./ServiceRegistration.css";
import apiJWT from "../../services/apiJWT";


const Card = ({ children }) => <div className="card">{children}</div>;
const CardContent = ({ children }) => <div>{children}</div>;
const Button = ({ children, ...props }) => <button className="button" {...props}>{children}</button>;
const Input = (props) => <input className="input" {...props} />;
const Label = ({ children, ...props }) => <label className="label" {...props}>{children}</label>;

const ServiceRegistration = () => {
  const [service, setService] = useState({ name: "", price: "" });
  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState("");

  const handleChange = (e) => {
    const { name, value } = e.target;
    if (name === "price" && !/^\d*\.?\d*$/.test(value)) return;
    setService({ ...service, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setMessage("");
    try {
      const response = await apiJWT.post("http://localhost:8080/barber/services", {
        name: service.name,
        price: parseFloat(service.price),
      });
      setMessage("Serviço cadastrado com sucesso!");
      setService({ name: "", price: "" });
    } catch (error) {
      setMessage("Erro ao cadastrar serviço.");
    }
    setLoading(false);
  };

  return (
    <div className="container">
      <Card>
        <CardContent>
          <h2 className="title">Cadastrar Serviço</h2>
          {message && <p className="mb-4 text-center">{message}</p>}
          <form onSubmit={handleSubmit}>
            <div className="mb-4">
              <Label htmlFor="name">Nome do Serviço</Label>
              <Input
                type="text"
                id="name"
                name="name"
                value={service.name}
                onChange={handleChange}
                required
              />
            </div>
            <div className="mb-4">
              <Label htmlFor="price">Preço</Label>
              <Input
                type="number"
                id="price"
                name="price"
                value={service.price}
                onChange={handleChange}
                step="0.01"
                min="0" 
                required
              />
            </div>
            <Button type="submit" disabled={loading}>
              {loading ? "Cadastrando..." : "Cadastrar"}
            </Button>
          </form>
        </CardContent>
      </Card>
    </div>
  );
};

export default ServiceRegistration;
