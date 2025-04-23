import { useState } from "react";
import { useNavigate } from "react-router-dom";
import ServiceList from "./ServiceList";
import BarberList from "./BarberList";

const AdminDashboard = () => {
  const [activeTab, setActiveTab] = useState("services");
  const navigate = useNavigate();

  return (
    <div className="Admin-dashboard">
      <h1>Painel do ADMIN</h1>
      
      <div className="dashboard-tabs">
        <button
          onClick={() => setActiveTab("services")}
          className={activeTab === "services" ? "active" : ""}
        >
          Serviços
        </button>
        <button
          onClick={() => setActiveTab("barbers")}
          className={activeTab === "barbers" ? "active" : ""}
        >
          Barbeiros
        </button>
      </div>

      <div className="dashboard-content">
        {activeTab === "services" && (
          <>
            <button 
              onClick={() => navigate("/services")} 
              className="btn-add"
            >
              + Novo Serviço
            </button>
            <ServiceList />
          </>
        )}

        {activeTab === "barbers" && (
          <>
            <button 
              onClick={() => navigate("/signupbarber")} 
              className="btn-add"
            >
              + Novo Barbeiro
            </button>
            <BarberList />
          </>
        )}
      </div>
    </div>
  );
};

export default AdminDashboard;