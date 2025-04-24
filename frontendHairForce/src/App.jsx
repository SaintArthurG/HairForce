import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import Login from "./Components/Login/Login";
import Signup from "./Components/Registro/Signup";
import './App.css'
import SignupBarber from "./Components/Registro/SignupBarber";
import Agendamentos from "./Components/Agendamentos/Agendamentos"
import ServiceRegistration from "./Components/Service/ServiceRegistration";

const Header = () => {
    return (
      <header className="header">
        <h1 className="logo">HairForce</h1>
        <nav className="nav">
          <ul>
            <li><Link to="/">Login</Link></li>
            <li><Link to="/signup">Cadastro Cliente</Link></li>
            <li><Link to="/signupbarber">Cadastro Barbeiro</Link></li>
            <li><Link to="/agendamentos">Agendamentos</Link></li>
            <li><Link to="/services">Serviços</Link></li>
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
                <Route path="/agendamentos" element={<Agendamentos />} />
                <Route path="/services" element={<ServiceRegistration />} />
            </Routes>
        </Router>
    );
}

export default App;

