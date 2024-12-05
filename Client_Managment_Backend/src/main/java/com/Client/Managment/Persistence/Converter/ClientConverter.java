package com.Client.Managment.Persistence.Converter;

import com.Client.Managment.Domain.Client;
import com.Client.Managment.Persistence.Entity.ClientEntity;

import java.util.List;
import java.util.stream.Collectors;


public class ClientConverter {

    public static Client toDto(ClientEntity entity){
        if (entity == null){
            return null;
        }
        Client dto = new Client();
        dto.setId(entity.getId());
        dto.setClientName(entity.getClientName());
        dto.setClientLName(entity.getClientLname());
        dto.setClientEmail(entity.getClientEmail());
        dto.setClientDate(entity.getClientDate());

        return dto;
    }

    public static ClientEntity toEntity(Client dto){
        if (dto == null){
            return null;
        }
        ClientEntity entity = new ClientEntity();
        entity.setId(dto.getId());
        entity.setClientName(dto.getClientName());
        entity.setClientLname(dto.getClientLName());
        entity.setClientEmail(dto.getClientEmail());
        entity.setClientDate(dto.getClientDate());

        return entity;
    }

    public static List<Client> toDtoList(List<ClientEntity> entities){
        return entities.stream()
                .map(ClientConverter::toDto)
                .collect(Collectors.toList());
    }

    public static List<ClientEntity> toEntityList(List<Client> dtos){
        return dtos.stream()
                .map(ClientConverter::toEntity)
                .collect(Collectors.toList());
    }

}
