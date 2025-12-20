package ma.casablanca.ensam.jeeproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.casablanca.ensam.jeeproject.dao.entities.InvoiceStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDto {
    private Long id;
    private Long amount;
    private InvoiceStatus status;
    private Long projectId;
}
