package ma.casablanca.ensam.jeeproject.controller;

import ma.casablanca.ensam.jeeproject.dto.InvoiceDto;
import ma.casablanca.ensam.jeeproject.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/invoices")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    @GetMapping
    public String getInvoices(Model model){
        List<InvoiceDto> invoices = invoiceService.getInvoices();
        model.addAttribute("invoices", invoices);
        if(invoices.isEmpty()){
            model.addAttribute("message", "No Invoices");
        }
        return "invoices";
    }

    @PostMapping("/create")
    public String createInvoice(InvoiceDto invoiceDto, RedirectAttributes redirectAttributes){
        InvoiceDto newInvoice = invoiceService.addInvoice(invoiceDto);
        if(newInvoice == null){
            redirectAttributes.addFlashAttribute("message", "Invoice creation failed");
            return "redirect:/invoices";
        }
        redirectAttributes.addFlashAttribute("message", "Invoice created successfully");
        return "redirect:/invoices";
    }

    @PutMapping("/update")
    public String updateInvoice(InvoiceDto invoiceDto, RedirectAttributes redirectAttributes){
        InvoiceDto updatedInvoice = invoiceService.updateInvoice(invoiceDto);
        if(updatedInvoice == null){
            redirectAttributes.addFlashAttribute("message", "Invoice update failed");
            return "redirect:/invoices";
        }
        redirectAttributes.addFlashAttribute("message", "Invoice updated successfully");
        return "redirect:/invoices";
    }

    @DeleteMapping("/delete")
    public String deleteInvoice(@RequestParam Long id, RedirectAttributes redirectAttributes){
        boolean deletedInvoice = invoiceService.deleteInvoice(id);
        if(deletedInvoice){
            redirectAttributes.addFlashAttribute("message", "Invoice deleted successfully");
            return "redirect:/invoices";
        }
        redirectAttributes.addFlashAttribute("message", "Invoice delete failed");
        return "redirect:/invoices";
    }

    @GetMapping("projectInvoices/{projectId}")
    public String getInvoicesByProjectId(@PathVariable Long projectId, Model model){
        List<InvoiceDto> invoices = invoiceService.getInvoicesByProjectId(projectId);
        model.addAttribute("invoices", invoices);
        if(invoices.isEmpty()){
            model.addAttribute("message", "No Invoices");
        }
        return "invoices";
    }

    @GetMapping("/{id}")
    public String getInvoice(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes){
        Optional<InvoiceDto> invoice = invoiceService.getInvoice(id);
        if(invoice.isEmpty()){
            redirectAttributes.addFlashAttribute("message", "Invoice not found");
            return "redirect:/invoices";
        }
        model.addAttribute("invoice", invoice.get());
        return "invoiceDetails";
    }
}
