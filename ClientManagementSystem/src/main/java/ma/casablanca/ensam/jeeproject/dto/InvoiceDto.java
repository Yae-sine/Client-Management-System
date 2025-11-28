package ma.casablanca.ensam.jeeproject.dto;

import ma.casablanca.ensam.jeeproject.dao.entities.InvoiceStatus;

public class InvoiceDto {
    private Long id;
    private Long amount;
    private InvoiceStatus status;
    private Long projectId;
}
