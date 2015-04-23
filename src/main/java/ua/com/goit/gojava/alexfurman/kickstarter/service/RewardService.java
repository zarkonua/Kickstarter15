package ua.com.goit.gojava.alexfurman.kickstarter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.com.goit.gojava.alexfurman.kickstarter.entity.Project;
import ua.com.goit.gojava.alexfurman.kickstarter.entity.Reward;
import ua.com.goit.gojava.alexfurman.kickstarter.entity.User;
import ua.com.goit.gojava.alexfurman.kickstarter.repository.RewardRepository;
import ua.com.goit.gojava.alexfurman.kickstarter.repository.UserRepository;

@Service
public class RewardService {

	@Autowired
	private RewardRepository rewardRepository;

	@Autowired
	private UserRepository userRepository;

	public List<Reward> findByProject(Project project) {
		return rewardRepository.findByProject(project);
	}

	public void save(Reward reward, String name) {
		rewardRepository.save(reward);
	}

	@Transactional
	public void addUserToReward(Reward reward, String name) {
		User user = userRepository.findByName(name);
		System.out.println("llllllllll "+reward.getAmount());
		Reward rewardToUpdate = rewardRepository.getOne(reward.getId());
		List<User> usersList = rewardToUpdate.getUsers();
		usersList.add(user);
		rewardRepository.save(rewardToUpdate);
	}

}
