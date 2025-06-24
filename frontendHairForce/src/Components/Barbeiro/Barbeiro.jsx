import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { jwtDecode } from 'jwt-decode'; 
import apiJWT from "../../services/apiJWT";
import './Barbeiro.css';

const Barbeiro = () => {
    const [agendamentos, setAgendamentos] = useState([]);
    const [barbeiros, setBarbeiros] = useState([]);
    const [barbeiroSelecionado, setBarbeiroSelecionado] = useState('todos');
    const [dataInicio, setDataInicio] = useState('');
    const [dataFim, setDataFim] = useState('');
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

            const fetchBarbeiros = async () => {
                try {
                    const response = await apiJWT.get("http://localhost:8080/barbeiros");
                    setBarbeiros(response.data);
                } catch (err) {
                    console.error("Erro ao carregar barbeiros", err);
                }
            };

            fetchBarbeiros();
            fetchAgendamentos();

        } catch (error) {
            navigate('/login');
        }
    }, [navigate]);

    const fetchAgendamentos = async () => {
        setLoading(true);
        try {
            let queryParams = [];

            if (barbeiroSelecionado !== 'todos') {
                queryParams.push(`barbeiroId=${barbeiroSelecionado}`);
            }

            if (dataInicio) {
                queryParams.push(`dataInicio=${dataInicio}`);
            }

            if (dataFim) {
                queryParams.push(`dataFim=${dataFim}`);
            }

            const queryString = queryParams.length > 0 ? `?${queryParams.join('&')}` : '';
            const endpoint = `http://localhost:8080/agendamentos${queryString}`;

            const response = await apiJWT.get(endpoint);
            setAgendamentos(response.data);
            setError(null);
        } catch (err) {
            setError('Erro ao carregar os agendamentos.');
        } finally {
            setLoading(false);
        }
    };

    const handleFiltroBarbeiroChange = (e) => {
        setBarbeiroSelecionado(e.target.value);
    };

    const handleDataInicioChange = (e) => {
        setDataInicio(e.target.value);
    };

    const handleDataFimChange = (e) => {
        setDataFim(e.target.value);
    };

    const aplicarFiltros = () => {
        fetchAgendamentos();
    };

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

            <div className="filtro-container">
                <label htmlFor="barbeiro-select">Filtrar por barbeiro:</label>
                <select
                    id="barbeiro-select"
                    value={barbeiroSelecionado}
                    onChange={handleFiltroBarbeiroChange}
                >
                    <option value="todos">Todos</option>
                    {barbeiros.map((barbeiro) => (
                        <option key={barbeiro.id} value={barbeiro.id}>
                            {barbeiro.nome}
                        </option>
                    ))}
                </select>
                <br />
                <label htmlFor="data-inicio">Data Início:</label>
                <input
                    type="date"
                    id="data-inicio"
                    value={dataInicio}
                    onChange={handleDataInicioChange}
                />
                <label htmlFor="data-fim">Data Fim:</label>
                <input
                    type="date"
                    id="data-fim"
                    value={dataFim}
                    onChange={handleDataFimChange}
                />

                <button onClick={aplicarFiltros}>Aplicar Filtros</button>
            </div>

            {agendamentos.length === 0 ? (
                <p className="mensagem">Nenhum agendamento encontrado.</p>
            ) : (
                <ul className="lista-agendamentos">
                    {agendamentos.map((agendamento) => (
                        <li key={agendamento.id} className="item-agendamento">
                            <p><strong>Data:</strong> {agendamento.data.slice(0, 10)}</p>
                            <p><strong>Hora:</strong> {agendamento.data.slice(11, 16)}</p>
                            <p><strong>Serviços:</strong> {agendamento.servicos.join(', ').replace(/_/g, ' ')}</p>
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
