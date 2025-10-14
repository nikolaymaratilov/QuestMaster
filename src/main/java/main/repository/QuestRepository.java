package main.repository;

import main.model.Quest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface QuestRepository extends JpaRepository<Quest, UUID> {

}
