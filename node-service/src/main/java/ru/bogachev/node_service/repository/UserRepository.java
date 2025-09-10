package ru.bogachev.node_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.bogachev.node_service.data.entity.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
