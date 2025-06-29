import React, { useEffect, useState } from 'react';
import apiJWT from "../../../services/apiJWT";
import './MeusAgendamentos.css';

const MeusAgendamentos = () => {
    const [agendamentos, setAgendamentos] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const dateRegex = /^[0-9]{1,2}\/[0-9]{1,2}\/[0-9]{4}$/;

    useEffect(() => {
        const fetchAgendamentos = async () => {
            try {
                const response = await apiJWT.get("http://localhost:8080/agendamentos/meus-agendamentos");
                setAgendamentos(response.data);
                console.log(response.data);
                
            } catch (err) {
                if (!localStorage.getItem('token')) {
                    setError('Você precisa estar logado para acessar esta página.');
                } else {
                    setError('Erro ao carregar os agendamentos.');
                }
            } finally {
                setLoading(false);
            }
        };

        fetchAgendamentos();
    }, []);

    if (loading) {
        return <p className="loading">Carregando...</p>;
    }

    if (error) {
        return <p className="error">{error}</p>;
    }

    if (!localStorage.getItem('token')) {
        return <p className="error">Você precisa estar logado para acessar esta página.</p>;
    }

    return (
        <div className="agendamento-container">
            <h1 className="titulo">Meus Agendamentos</h1>
            {agendamentos.length === 0 ? (
                <p className="mensagem">Você não possui agendamentos.</p>
            ) : (
                <ul className="lista-agendamentos">
                    {agendamentos.map((agendamento) => (
                        <li key={agendamento.id} className="item-agendamento">
                            <p><strong>Data:</strong> {agendamento.data.slice(0,10)}</p>
                            <p><strong>Horário:</strong> {agendamento.data.slice(11,16)}</p>
                            <p><strong>Serviços:</strong> {agendamento.servicos.join(', ').replace("_", " ")}</p>
                            <p><strong>Barbeiro:</strong> {agendamento.nomeBarbeiro}</p>
                        </li>
                    ))}
                </ul>
            )}
        </div>
    );
};

export default MeusAgendamentos;
