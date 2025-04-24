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

export default apiJWT;
