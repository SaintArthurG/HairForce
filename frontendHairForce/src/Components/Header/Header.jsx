import { Link, useNavigate } from "react-router-dom";

const Header = () => {
    const navigate = useNavigate(); // Hook para navegação
    const token = localStorage.getItem("token");

    const handleLogout = () => {
        localStorage.removeItem("token"); // Remove o token
        navigate("/"); // Redireciona para a página de login
    };

    return (
        <header className="header">
            <h1 className="logo">HairForce</h1>
            <nav className="nav">
                <ul>
                    <li><Link to="/">Login</Link></li>
                    <li><Link to="/signup">Cadastro Cliente</Link></li>
                    <li><Link to="/signupbarber">Cadastro Barbeiro</Link></li>
                    <li><Link to="/agendamentos">Agendamentos</Link></li>
                </ul>
            </nav>
            {token && <button onClick={handleLogout}>Logout</button>} {/* Só exibe o botão se houver token */}
        </header>
    );
};

export default Header;
