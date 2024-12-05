import axios from "axios";

const CLIENT_GET_REST_API_URL = "http://localhost:8080/api/clients";
const CLIENT_GETBYID_API_URL = "http://localhost:8080/api/client/";
const CLIENT_POST_REST_API_URL = "http://localhost:8080/api/add";
const CLIENT_PUT_REST_API_URL = "http://localhost:8080/api/update/";
const CLIENT_DELETE_REST_API_URL = "http://localhost:8080/api/delete/";

class ClientService {
    getAllClients() {
        return axios.get(CLIENT_GET_REST_API_URL);
    }

    getClientById(clientId) {
        return axios.get(CLIENT_GETBYID_API_URL + clientId);
    }

    saveClient(client) {
        return axios.post(CLIENT_POST_REST_API_URL, client)
    }

    updateClient(clientId, client) {
        return axios.put(CLIENT_PUT_REST_API_URL + clientId, client)
    }

    deleteClient(clientId) {
        return axios.delete(CLIENT_DELETE_REST_API_URL + clientId)
    }

}


export default new ClientService();