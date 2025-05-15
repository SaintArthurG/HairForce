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
                <h1 className="logo"> <Link to={"/"} >HairForce</Link></h1>
                <nav className="nav">
                    <ul className="nav-list">
                        <li><Link to="/login">Login</Link></li>
                        <li><Link to="/signupbarber">Cadastro Barbeiro</Link></li>
                        <li><Link to="/agendamentos">Agendamentos</Link></li>
                        <li><Link to="/meus-agendamentos">Meus agendamentos</Link></li>
                    </ul>
                </nav>
                {token && <button className="logout-btn" onClick={handleLogout}>Logout</button>}
            </div>
        </header>
    );
};

export default Header;
