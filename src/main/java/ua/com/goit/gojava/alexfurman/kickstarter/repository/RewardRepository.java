package ua.com.goit.gojava.alexfurman.kickstarter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.com.goit.gojava.alexfurman.kickstarter.entity.Project;
import ua.com.goit.gojava.alexfurman.kickstarter.entity.Reward;

public interface RewardRepository extends JpaRepository<Reward, Integer> {

	List<Reward> findByProject(Project project);

}
