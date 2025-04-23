import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import Login from "./Components/Login/Login";
import Signup from "./Components/Registro/Signup";
import './App.css';
import SignupBarber from "./Components/Registro/SignupBarber";
import Schedule from "./Components/Schedule/Schedule";
import ServiceRegistration from "./Components/Service/ServiceRegistration";
import AdminDashboard from "./Components/Admin/AdminDashboard"; 

const Header = () => {
  return (
    <header className="header">
      <h1 className="logo">HairForce</h1>
      <nav className="nav">
        <ul>
          <li><Link to="/">Login</Link></li>
          <li><Link to="/signup">Cadastro Cliente</Link></li>
          <li><Link to="/signupbarber">Cadastro Barbeiro</Link></li>
          <li><Link to="/schedules">Agendamentos</Link></li>
          <li><Link to="/services">Serviços</Link></li>
          <li><Link to="/admin-dashboard">Painel do Admin</Link></li>
        </ul>
      </nav>
    </header>
  );
};

function App() {
  return (
    <Router>
      <Header />
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/signup" element={<Signup />} />
        <Route path="/signupbarber" element={<SignupBarber />} />
        <Route path="/schedules" element={<Schedule />} />
        <Route path="/services" element={<ServiceRegistration />} />
        <Route path="/admin-dashboard" element={<AdminDashboard />} />
      </Routes>
    </Router>
  );
}

export default App;