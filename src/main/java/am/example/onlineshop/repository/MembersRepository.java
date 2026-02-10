package am.example.onlineshop.repository;

import am.example.onlineshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembersRepository extends JpaRepository<User, Integer> {
}
