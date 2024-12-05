package com.Client.Managment.Controller;

import com.Client.Managment.Domain.Client;
import com.Client.Managment.Exceptions.ClientNotFoundException;
import com.Client.Managment.Exceptions.InvalidClientIdException;
import com.Client.Managment.Service.ClientService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @GetMapping("/clients")
    public ResponseEntity<List<Client>> getAllClients(){
        List<Client> clients = clientService.findAllClients();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }


    @GetMapping("/client/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id){
        Client client = clientService.findByClientId(id);
        if (client == null){
            log.warn("GET: Client by id {} not found",id);
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(client,HttpStatus.OK);
    }

    @GetMapping("/byname/{clientName}")
    public ResponseEntity<List<Client>> getClientByName(@PathVariable String clientName){
        List<Client> clients = clientService.findByClientName(clientName);
        if (clients.isEmpty()){
            log.warn("GET: There not exist any client named by {}",clientName);
            return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(clients,HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> postNewClient(@Valid @RequestBody Client client, BindingResult result){
       if (clientService.existByEmail(client.getClientEmail())){
           return ResponseEntity.status(HttpStatus.CONFLICT)
                   .body("Client with this email already exists.");
       }
        Client saveClient = clientService.saveClient(client);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(client.getId())
                .toUri();
        return ResponseEntity.status(HttpStatus.CREATED).location(location).body(saveClient);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateClient(@PathVariable Long id, @RequestBody Client client){
        if (id == null){
            throw new InvalidClientIdException("Id should not be null");
        }
        client.setClientDate(LocalDateTime.now());

        Client updateclient = clientService.updateClient(client);
        if (updateclient == null){
            throw new ClientNotFoundException("Client not found");
        }
        return new ResponseEntity<>(updateclient,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCLient(@PathVariable Long id){
        Client deletedClient = clientService.deleteClient(id);
        if (deletedClient == null){
            throw new ClientNotFoundException("Client with ID " + id + " not found");
        }
        return new ResponseEntity<>(deletedClient,HttpStatus.NO_CONTENT);
    }

}
