import axios from "axios";
export const apiClient = axios.create({
  baseURL: "http://localhost:8089",
});
// export const apiClient = axios.create({ baseURL: "http://localhost:8089" });
// baseURL: "https://pleasant-ambition-production.up.railway.app",
apiClient.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem("jwtToken");
    if (token) {
      console.log("Interceptors  xx");
      config.headers["Authorization"] = `${token}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);
