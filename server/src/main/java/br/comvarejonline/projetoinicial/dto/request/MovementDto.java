package br.comvarejonline.projetoinicial.dto.request;

import lombok.Data;
import org.springframework.lang.Nullable;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Data
public class MovementDto {

    @NotNull
    @Min(1)
    private Long productId;

    @NotBlank
    @Pattern(regexp = "ENTRADA|AJUSTE_ENTRADA|SAÍDA|AJUSTE_SAÍDA")
    private String movement;

    @NotNull
    private Long amount;

    @NotNull
    private LocalDateTime movementDate;

    @NotBlank
    private String reason;

    @Nullable
    @NotBlank
    private String document;

    @AssertTrue(message = "Movimentações do tipo ENTRADA ou SAÍDA devem ser enviadas com o documento")
    public boolean MovementValid() {
        if (this.movement.equals("ENTRADA") || this.movement.equals("SAÍDA")) {
            return this.document != null;
        }

        return true;
    }
}
