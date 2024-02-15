package com.banana.AccountsService.model;

import com.banana.AccountsService.constraints.OpeningDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;


import javax.persistence.*;
import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "accounts")
@XmlRootElement
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(1)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 10)
    private String type;

    @DateTimeFormat
    //@Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}")
    @OpeningDate
    Date openingDate;

    @NotNull
    private int balance;

    @Min(1)
    @NotNull
    private Long ownerId;

    @Transient
    Customer owner;


}
