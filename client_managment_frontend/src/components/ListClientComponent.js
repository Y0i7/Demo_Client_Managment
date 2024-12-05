import React, { useEffect, useState } from 'react'
import ClientService from '../Services/ClientService';
import { Link } from 'react-router-dom';

function ListClientComponent() {

    const [clients, setClients] = useState([]);

    useEffect(() => {
        listClients();
    }, [])

    const listClients = () => {
        ClientService.getAllClients().then(response => {
            setClients(response.data);
            console.log(response.data);
        }).catch(error => {
            console.log(error);
        })
    }

    const deleteClient = (clientId) => {
        ClientService.deleteClient(clientId).then((response) => {
            window.alert('Client: ' + clientId + 'has been deleted')
            listClients();
        }).catch(error => {
            console.log(error);
        })
    }


    return (
        <div className='container'>
            <h2 className='text-center'>Clients</h2>
            <Link to='/add-client' className='btn btn-primary mb-2'>Add Client</Link>
            <table className='table table-bordered table-striped'>
                <thead>
                    <th>ID</th>
                    <th>NAME</th>
                    <th>LAST NAME</th>
                    <th>EMAIL</th>
                    <th>DATE</th>
                    <th>OPTION</th>
                </thead>
                <tbody>
                    {
                        clients.map(
                            client =>
                                <tr key={client.ID}>
                                    <td>{client.ID}</td>
                                    <td>{client.NAME}</td>
                                    <td>{client.LAST_NAME || 'N/A'}</td>
                                    <td>{client.EMAIL}</td>
                                    <td>{client.DATE}</td>
                                    <td>
                                        <Link className='btn btn-info' to={`/edit-client/${client.ID}`}>Update</Link>
                                        <button style={{ marginLeft: "10px" }} className='btn btn-danger' onClick={() => deleteClient(client.ID)}>Delete</button>
                                    </td>
                                </tr>
                        )
                    }
                </tbody>
            </table>
        </div>
    )
}

export default ListClientComponent;
