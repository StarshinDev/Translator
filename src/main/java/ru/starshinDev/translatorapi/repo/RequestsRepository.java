package ru.starshinDev.translatorapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.starshinDev.translatorapi.models.Request;

@Repository
public interface RequestsRepository extends JpaRepository<Request, Integer> {
}
