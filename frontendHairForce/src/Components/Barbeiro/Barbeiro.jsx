import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { jwtDecode } from 'jwt-decode';
import apiJWT from "../../services/apiJWT"
import './Barbeiro.css';

const Barbeiro = () => {
    const [agendamentos, setAgendamentos] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    const navigate = useNavigate();

    useEffect(() => {
        const token = localStorage.getItem('token');
        if (!token) {
            navigate('/login');
            return;
        }

        try {
            const decodedToken = jwtDecode(token);
            if (decodedToken.role !== 'ROLE_PICA') {
                alert('Você não tem permissão para acessar esta área.');
                navigate('/');
                return;
            }

            const fetchAgendamentos = async () => {
                try {
                    const response = await apiJWT.get("http://localhost:8080/agendamentos");
                    setAgendamentos(response.data);
                } catch (err) {
                    setError('Erro ao carregar os agendamentos.');
                } finally {
                    setLoading(false);
                }
            };

            fetchAgendamentos();

        } catch (error) {
            navigate('/login');
        }
    }, [navigate]);

    if (loading) {
        return <p className="loading">Carregando...</p>;
    }

    if (error) {
        return <p className="error">{error}</p>;
    }

    return (
        <div className="agendamento-container">
            <h1 className="titulo">Agendamentos do Barbeiro</h1>
            <Link to="/cadastro-barbeiro" className="home-link">Cadastrar Barbeiro</Link>
            {agendamentos.length === 0 ? (
                <p className="mensagem">Nenhum agendamento encontrado.</p>
            ) : (
                <ul className="lista-agendamentos">
                    {agendamentos.map((agendamento) => (
                        <li key={agendamento.id} className="item-agendamento">
                            <p><strong>Horário:</strong> {agendamento.hora}</p>
                            <p><strong>Serviços:</strong> {agendamento.servicos.join(', ')}</p>
                            <p><strong>Barbeiro:</strong> {agendamento.nomeBarbeiro}</p>
                            <p><strong>Cliente:</strong> {agendamento.nomeUsuario}</p>
                        </li>
                    ))}
                </ul>
            )}
        </div>
    );
};

export default Barbeiro;
