package com.Client.Managment.Domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    @JsonProperty("ID")
    private Long id;

    @JsonProperty("NAME")
    private String clientName;

    @JsonProperty("LAST_NAME")
    private String clientLName;

    @JsonProperty("EMAIL")
    private String clientEmail;

    @JsonProperty("DATE")
    private LocalDateTime clientDate;
}
