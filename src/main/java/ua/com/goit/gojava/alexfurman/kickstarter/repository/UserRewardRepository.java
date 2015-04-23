package ua.com.goit.gojava.alexfurman.kickstarter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.com.goit.gojava.alexfurman.kickstarter.entity.User;
import ua.com.goit.gojava.alexfurman.kickstarter.entity.UserReward;

public interface UserRewardRepository extends JpaRepository<UserReward, Integer> {

	List<UserReward> findByUser(User user);

}
