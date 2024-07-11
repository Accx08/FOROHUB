package com.aluracursos.foro_hub.api.domain.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Table(name = "user")
@Entity(name = "UserName")
public class UserName {

    @Id
    private Long id;
    @Getter
    @Setter
    @JoinColumn(name = "token_id")
    private Long tokenId;

}
