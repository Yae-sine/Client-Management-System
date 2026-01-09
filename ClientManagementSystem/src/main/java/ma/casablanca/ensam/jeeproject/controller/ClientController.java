package ma.casablanca.ensam.jeeproject.controller;

import ma.casablanca.ensam.jeeproject.dto.ClientDto;
import ma.casablanca.ensam.jeeproject.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("clients")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping
    public String getClients(Model model) {
        List<ClientDto> clients = clientService.getClients();
        model.addAttribute("clients", clients);
        if (clients.isEmpty()) {
            model.addAttribute("message", "No Clients found");
        }
        return "clients";
    }

    @PostMapping("/add")
    public String addClient(ClientDto clientDto, RedirectAttributes redirectAttributes) {
        ClientDto newClient = clientService.addClient(clientDto);
        if(newClient == null){
            redirectAttributes.addFlashAttribute("message", "Client creation failed");
            return "redirect:/clients";
        }
        redirectAttributes.addFlashAttribute("message", "Client created successfully!");
        return "redirect:/clients";
    }

    @PutMapping("/update")
    public String updateClient(ClientDto clientDto, RedirectAttributes redirectAttributes) {
        ClientDto updatedClient = clientService.updateClient(clientDto);
        if (updatedClient == null) {
            redirectAttributes.addFlashAttribute("message", "Client update failed");
            return "redirect:/clients";
        }
        redirectAttributes.addFlashAttribute("message", "Client updated successfully!");
        return "redirect:/clients";
    }

    @DeleteMapping("/delete")
    public String deleteClient(@RequestParam Long id, RedirectAttributes redirectAttributes) {
        boolean isDeleted = clientService.deleteClient(id);
        if (isDeleted) {
            redirectAttributes.addFlashAttribute("message", "Client deleted successfully!");
            return "redirect:/clients";
        }
        redirectAttributes.addFlashAttribute("message", "Client delete failed");
        return "redirect:/clients";
    }

    @GetMapping("/{id}")
    public String getClientDetails(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<ClientDto> client = clientService.getClient(id);
        if (client.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Client not found");
            return "redirect:/clients";
        }
        model.addAttribute("client", client.get());
        return "clientDetails";
    }
}
