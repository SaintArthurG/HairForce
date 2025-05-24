import { Link, useNavigate } from "react-router-dom";
import logo2 from "../../assets/logo.png";
import "./Header.css";

const Header = () => {
    const navigate = useNavigate();
    const token = localStorage.getItem("token");

    const handleLogout = () => {
        localStorage.removeItem("token");
        navigate("/");
    };

    return (
        <header className="header">
            <div className="header-container">
                 <img src={logo2} alt="Logo2" className="logo2" />
                <Link to={"/"}><h1 className="logo">HairForce</h1></Link>
                <nav className="nav">
                    <ul className="nav-list">
                        <li><Link to="/login">Login</Link></li>
                        <li><Link to="/novo-agendamento">Agendamentos</Link></li>
                        <li><Link to="/meus-agendamentos">Meus agendamentos</Link></li>
                        <li><Link to="/area-barbeiro" className="barber-area-link">Área do Barbeiro</Link></li>
                    </ul>
                </nav>
                {token && <button className="logout-btn" onClick={handleLogout}>Logout</button>}
            </div>
        </header>
    );
};

export default Header;
