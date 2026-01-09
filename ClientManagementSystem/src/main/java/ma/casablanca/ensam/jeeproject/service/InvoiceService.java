package ma.casablanca.ensam.jeeproject.service;

import ma.casablanca.ensam.jeeproject.dto.InvoiceDto;

import java.util.List;
import java.util.Optional;

public interface InvoiceService {
    List<InvoiceDto> getInvoices();
    InvoiceDto addInvoice(InvoiceDto invoiceDto);
    InvoiceDto updateInvoice(InvoiceDto invoiceDto);
    boolean deleteInvoice(Long id);
    List<InvoiceDto> getInvoicesByProjectId(Long projectId);
    Optional<InvoiceDto> getInvoice(Long id);
}
