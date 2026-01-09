package ma.casablanca.ensam.jeeproject.service;

import ma.casablanca.ensam.jeeproject.dao.entities.Invoice;
import ma.casablanca.ensam.jeeproject.dao.repositories.InvoiceRepository;
import ma.casablanca.ensam.jeeproject.dto.InvoiceDto;
import ma.casablanca.ensam.jeeproject.mapper.InvoiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
public class InvoiceManager implements InvoiceService{
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private InvoiceMapper invoiceMapper;

    @Override
    public List<InvoiceDto> getInvoices() {
        return invoiceRepository.findAll().stream()
                .map(invoiceMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public InvoiceDto addInvoice(InvoiceDto invoiceDto) {
        Invoice invoice = invoiceMapper.toEntity(invoiceDto);
        Invoice savedInvoice = invoiceRepository.save(invoice);
        return invoiceMapper.toDto(savedInvoice);
    }

    @Override
    public InvoiceDto updateInvoice(InvoiceDto invoiceDto) {
        Invoice invoice = invoiceMapper.toEntity(invoiceDto);
        Invoice updatedInvoice = invoiceRepository.save(invoice);
        return invoiceMapper.toDto(updatedInvoice);
    }

    @Override
    public boolean deleteInvoice(Long id) {
        invoiceRepository.deleteById(id);
        return !invoiceRepository.existsById(id);
    }

    @Override
    public List<InvoiceDto> getInvoicesByProjectId(Long projectId) {
        return invoiceRepository.findByProjectId(projectId).stream()
                .map(invoiceMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<InvoiceDto> getInvoice(Long id) {
        return invoiceRepository.findById(id)
                .map(invoiceMapper::toDto);
    }
}
