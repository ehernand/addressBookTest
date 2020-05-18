package com.code.challenge.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import javax.validation.constraints.*;

@Entity
@Data
@NoArgsConstructor
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    //@Size(max = 500)
    //@Column(name = "contenido", length = 500, nullable = false)
    private String firstName;

    @NotNull
    private String lastName;

    private String nickName;

    @NotNull
    private String email;

    private LocalDate dateOfBirth;

    private Boolean active;

}
