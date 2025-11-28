package ma.casablanca.ensam.jeeproject.mapper;

import ma.casablanca.ensam.jeeproject.dao.entities.Invoice;
import ma.casablanca.ensam.jeeproject.dto.InvoiceDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceMapper {
    @Autowired
    private ModelMapper mapper;

    public InvoiceDto toDto(Invoice invoice){
        return mapper.map(invoice , InvoiceDto.class);
    }

    public Invoice toEntity(InvoiceDto invoiceDto){
        return mapper.map(invoiceDto , Invoice.class);
    }
}
