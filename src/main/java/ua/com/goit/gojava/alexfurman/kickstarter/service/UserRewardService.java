package ua.com.goit.gojava.alexfurman.kickstarter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.goit.gojava.alexfurman.kickstarter.entity.Reward;
import ua.com.goit.gojava.alexfurman.kickstarter.entity.User;
import ua.com.goit.gojava.alexfurman.kickstarter.entity.UserReward;
import ua.com.goit.gojava.alexfurman.kickstarter.repository.RewardRepository;
import ua.com.goit.gojava.alexfurman.kickstarter.repository.UserRepository;
import ua.com.goit.gojava.alexfurman.kickstarter.repository.UserRewardRepository;

@Service
public class UserRewardService {

	@Autowired
	private UserRewardRepository userRewardRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RewardRepository rewardRepository;

	public List<UserReward> findByUserName(String name) {
		User user = userRepository.findByName(name);
		return userRewardRepository.findByUser(user);
	}
	
	public void save(UserReward userReward, String name) {
		User user = userRepository.findByName(name);
		userReward.setUser(user);
		Reward reward = rewardRepository.findOne(userReward.getId());
		userReward.setAmount(reward.getAmount());
		userReward.setDescription(reward.getDescription());
		userRewardRepository.save(userReward);
	}

}
