import axios from "axios";

export const BackendRequest = axios.create({
	baseURL: import.meta.env.VITE_API_URL_BACKEND,
});
