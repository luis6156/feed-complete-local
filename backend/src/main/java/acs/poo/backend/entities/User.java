package acs.poo.backend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "app_users")
public class User {
    @Id
    private String uid;

    private String imgUrl;
}
