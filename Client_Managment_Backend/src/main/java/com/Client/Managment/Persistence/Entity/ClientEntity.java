package com.Client.Managment.Persistence.Entity;

import com.Client.Managment.Persistence.Converter.LocalDateTimeConverter;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;



import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "CLIENTS_TABLE")
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Client name can't be empty")
    @Size(max = 100, message = "Client name can't be longer than 100 characters")
    @Column(name = "CLIENT_NAME", length = 100)
    private String clientName;

    @Size(max = 100, message = "Client last name can't be longer than 100 characters")
    @Column(name = "CLIENT_LNAME", length = 100)
    private String clientLname;

    @Size(max = 250, message = "Client Email can't be longer than 100 characters")
    @Column(name = "CLIENT_EMAIL", length = 250)
    private String clientEmail;

    @Convert(converter = LocalDateTimeConverter.class)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "Client_Date")
    private LocalDateTime clientDate;



    @PrePersist
    public void setDefaultDate(){
        if (this.clientDate == null){
            this.clientDate = LocalDateTime.now();
        }
    }

}
