package ma.casablanca.ensam.jeeproject.controller;
import ma.casablanca.ensam.jeeproject.dao.entities.Invoice;
import ma.casablanca.ensam.jeeproject.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/invoices")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    @PostMapping("/create")
    public String createInvoice(Model model , Invoice invoice ,RedirectAttributes redirectAttributes){
        Invoice invoice1 = invoiceService.addInvoice(invoice);
        redirectAttributes.addFlashAttribute("message", "Invoice created successfully");
        model.addAttribute("invoice", invoice1);
        return "redirect:/invoices";
    }


    @PutMapping("/update")
    public String updateInvoice(Model model , Invoice invoice , RedirectAttributes redirectAttributes){
        Invoice updatedInvoice = invoiceService.updateInvoice(invoice);
        redirectAttributes.addFlashAttribute("message", "Invoice updated successfully");
        model.addAttribute("invoice", updatedInvoice);
        return "redirect:/invoices";
    }

    @DeleteMapping("/delete")
    public String deleteInvoice(Model model , Long id , RedirectAttributes redirectAttributes){
        Invoice deletedInvoice = invoiceService.deleteInvoice(id);
        if(deletedInvoice != null){
            redirectAttributes.addFlashAttribute("message", "Invoice deleted successfully");
            return "redirect:/invoices";
        }
        redirectAttributes.addFlashAttribute("message", "Invoice delete failed");
        return "redirect:/invoices";
    }
    @GetMapping("projectInvoices/{projectId}")
    public String getInvoicesByProjectId(Model model , @PathVariable Long projectId ){
        List<Invoice> invoices = invoiceService.getInvoicesByProjectId(projectId);
        model.addAttribute("invoices" , invoices);
        if(invoices.isEmpty()){
            model.addAttribute("message", "No Invoices");
        }
        return "invoices";
    }

    @GetMapping("/{id}")
    public String getInvoice(Model model , @PathVariable Long id , RedirectAttributes redirectAttributes){
        Invoice invoice = invoiceService.getInvoice(id);
        if(invoice == null){
            redirectAttributes.addFlashAttribute("message", "Invoice not found");
            return "redirect:/invoices";
        }
        model.addAttribute("invoice" , invoice);
        return "invoiceDetails";
    }
}
