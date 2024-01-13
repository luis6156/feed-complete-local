package acs.poo.backend.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LikeDTO {
    @NotNull
    private String userId;
    @NotNull
    private String postId;
}
