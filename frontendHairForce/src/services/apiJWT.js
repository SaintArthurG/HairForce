import axios from "axios";

const apiJWT = axios.create({
  baseURL: "http://localhost:8080", // sua API
});

// Interceptor para adicionar o token em toda requisição
apiJWT.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem("token");
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => Promise.reject(error)
);

apiJWT.interceptors.response.use(
  response => response,
  error => {
    if (error.response && error.response.status === 401) {
      // Redireciona para login ou remove token inválido
      localStorage.removeItem("token");
      window.location.href = "/"; // Redireciona para a página de login
    }
    return Promise.reject(error);
  }
);



export default apiJWT;
