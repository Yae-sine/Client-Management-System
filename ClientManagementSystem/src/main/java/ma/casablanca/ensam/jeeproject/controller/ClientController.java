package ma.casablanca.ensam.jeeproject.controller;
import ma.casablanca.ensam.jeeproject.dao.entities.Client;
import ma.casablanca.ensam.jeeproject.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("clients")
public class ClientController {
    @Autowired
    private ClientService clientService;
    @GetMapping
    public String getClients(Model model) {
        List<Client> client = clientService.getClients();
        model.addAttribute("clients", client);
        if (client.isEmpty()) {
            model.addAttribute("message", "No Clients found");
        }
        return "clients";
    }

    @PostMapping("/add")
    public String addClient(Client client, Model model , RedirectAttributes redirectAttributes) {
        Client newClient = clientService.addClient(client);
        if(newClient== null){
            redirectAttributes.addFlashAttribute("message", "Client creation failed");
            return "redirect:/clients";
        }
        redirectAttributes.addFlashAttribute("client", newClient);
        return "redirect:/clients";
    }

    @PutMapping("/update")
    public String updateClient(Client client, Model model , RedirectAttributes redirectAttributes) {
        Client updatedClient = clientService.updateClient(client);
        if (updatedClient == null) {
            redirectAttributes.addFlashAttribute("message", "Client update failed");
            return "redirect:/clients";
        }
        redirectAttributes.addFlashAttribute("message", "Client updated successfully!");
        model.addAttribute("client", updatedClient);
        return "redirect:/clients";
    }

    @DeleteMapping("/delete")
    public String deleteClient(Client client, RedirectAttributes redirectAttributes) {
        boolean isDeleted = clientService.deleteClient(client.getId());
        if (isDeleted) {
            redirectAttributes.addFlashAttribute("message", "Client deleted successfully!");
            return "redirect:/clients";
        }
        redirectAttributes.addFlashAttribute("message", "Client delete failed");
        return "redirect:/clients";
    }
    @GetMapping("/{id}")
    public String getClientDetails(Long id, Model model , RedirectAttributes redirectAttributes) {
        Client client = clientService.getClient(id);
        if (client == null) {
            redirectAttributes.addFlashAttribute("message", "Client not found");
            return "redirect:/clients";
        }
        model.addAttribute("client", client);
        return "clientDetails";
    }
}
