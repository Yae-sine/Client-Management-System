package ma.casablanca.ensam.jeeproject.service;

import ma.casablanca.ensam.jeeproject.dao.entities.Invoice;

import java.util.List;

public interface InvoiceService {
    public List<Invoice> getInvoices();
    public Invoice addInvoice(Invoice invoice);
    public Invoice updateInvoice(Invoice invoice);
    public Invoice deleteInvoice(Long id);
    public List<Invoice> getInvoicesByProjectId(Long projectId);
    public Invoice getInvoice(Long id);

}
