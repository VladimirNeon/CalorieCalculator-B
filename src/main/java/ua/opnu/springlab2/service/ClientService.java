package ua.opnu.springlab2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import ua.opnu.springlab2.model.Client;
import ua.opnu.springlab2.model.UserEntity;
import ua.opnu.springlab2.repos.ClientRepository;
import ua.opnu.springlab2.repos.UserRepository;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final UserRepository userRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository, UserRepository userRepository) {
        this.clientRepository = clientRepository;
        this.userRepository = userRepository;
    }

    private UserEntity getCurrentUser() {
        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<Client> getAllClientsForCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return clientRepository.findByUserUsername(username);
    }

    public Client addClient(Client client) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userRepository.findByUsername(username).orElseThrow(() ->
                new RuntimeException("User not found"));
        client.setUser(user);
        client.setRecommendedCalories(calculateRecommendedCalories(client));
        return clientRepository.save(client);
    }

    public void deleteClient(Long clientId) {
        UserEntity currentUser = getCurrentUser();
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        // Проверяем, принадлежит ли клиент текущему пользователю
        if (!client.getUser().equals(currentUser)) {
            throw new RuntimeException("You are not allowed to delete this client");
        }

        clientRepository.delete(client);
    }

    private double calculateRecommendedCalories(Client client) {
        return 447.6 + (9.2 * client.getWeight()) + (3.1 * client.getHeight()) - (4.3 * client.getAge());
    }
}
