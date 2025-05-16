import { Link, useNavigate } from "react-router-dom";
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
                <Link to={"/"}><h1 className="logo">HairForce</h1></Link>
                <nav className="nav">
                    <ul className="nav-list">
                        <li><Link to="/login">Login</Link></li>
                        <li><Link to="/novo-agendamento">Agendamentos</Link></li>
                        <li><Link to="/meus-agendamentos">Meus agendamentos</Link></li>
                    </ul>
                </nav>
                {token && <button className="logout-btn" onClick={handleLogout}>Logout</button>}
                <Link to="/area-barbeiro" className="barber-area-link">Área do Barbeiro</Link>
            </div>
        </header>
    );
};

export default Header;
