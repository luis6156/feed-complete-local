package acs.poo.backend.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
public class PostDTO {
    @NotNull
    private String content;
}
