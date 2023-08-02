package ru.rtu_mirea.javabank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rtu_mirea.javabank.entity.systemEntities.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    Group findByCode (String code);
}
