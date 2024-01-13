package acs.poo.backend.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
public class CommentDTO {
    @NotNull
    private String content;
}
