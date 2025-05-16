import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import {jwtDecode}  from 'jwt-decode';

const Barbeiro = () => {
    const navigate = useNavigate();

    useEffect(() => {
        const token = localStorage.getItem('token');
        if (!token) {
            navigate('/login');
            return;
        }

        try {
            const decodedToken = jwtDecode(token);
            console.log(decodedToken);
            // Verifica se o token contém a role 'ROLE_PICA'
            
            if (decodedToken.role !== 'ROLE_PICA') {
                alert('Você não tem permissão para acessar esta área.');
                navigate('/');
            }
        } catch (error) {
            console.error('Invalid token', error);
            navigate('/login');
        }
    }, [navigate]);

    return (
        <div>
            <h1>Área do Barbeiro</h1>
            <p>Bem-vindo à área exclusiva para barbeiros!</p>
        </div>
    );
};

export default Barbeiro;