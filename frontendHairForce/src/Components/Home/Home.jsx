import React from 'react';
import { Link } from 'react-router-dom';

const Home = () => {
    return (
        <div className="home-container">
            <header className="home-header">
                <h1>Bem-vindo ao HairForce</h1>
                <p>Oferecemos os melhores serviços para cuidar do seu estilo!</p>
            </header>
            <section className="home-services">
                <h2>Nossos Serviços</h2>
                <ul>
                    <li>Login para acessar sua conta</li>
                    <li>Agendamento de serviços de forma rápida e prática</li>
                    <li>Visualização dos seus agendamentos</li>
                </ul>
            </section>
            <section className="home-links">
                <h2>Acesse nossos serviços</h2>
                <div className="links-container">
                    <Link to="/login" className="home-link">Login</Link>
                    <Link to="/novo-agendamento" className="home-link">Novo Agendamento</Link>
                    <Link to="/meus-agendamentos" className="home-link">Meus Agendamentos</Link>
                </div>
            </section>
        </div>
    );
};

export default Home;