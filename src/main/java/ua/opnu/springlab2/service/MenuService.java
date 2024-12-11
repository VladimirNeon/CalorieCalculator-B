package ua.opnu.springlab2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.opnu.springlab2.model.Client;
import ua.opnu.springlab2.model.Menu;
import ua.opnu.springlab2.repos.ClientRepository;
import ua.opnu.springlab2.repos.MenuRepository;


import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuService {

    private final MenuRepository menuRepository;
    private final ClientRepository clientRepository;

    @Autowired
    public MenuService(MenuRepository menuRepository, ClientRepository clientRepository) {
        this.menuRepository = menuRepository;
        this.clientRepository = clientRepository;
    }

    public List<Menu> getAllMenus() {
        return menuRepository.findAll();
    }

    public Menu addMenu(Menu menu) {
        return menuRepository.save(menu);
    }

    public void deleteMenu(Long id) {
        menuRepository.deleteById(id);
    }

    public List<Menu> findClosestMenus(Long clientId) {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new RuntimeException("Client not found"));
        double recommendedCalories = client.getRecommendedCalories();

        return menuRepository.findAll().stream()
                .sorted(Comparator.comparingDouble(menu -> Math.abs(menu.getTotalCalories() - recommendedCalories)))
                .collect(Collectors.toList());
    }
}