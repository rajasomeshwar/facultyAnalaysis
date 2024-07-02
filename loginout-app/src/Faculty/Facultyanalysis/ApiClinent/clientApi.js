import axios from "axios";
export const apiClient = axios.create({
  baseURL: "https://pleasant-ambition-production.up.railway.app/",
});
// export const apiClient = axios.create({ baseURL: "http://localhost:8089" });
