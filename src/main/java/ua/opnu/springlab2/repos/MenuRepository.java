package ua.opnu.springlab2.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.opnu.springlab2.model.Menu;


@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
}