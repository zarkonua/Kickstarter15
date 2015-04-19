package ua.com.goit.gojava.alexfurman.kickstarter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.goit.gojava.alexfurman.kickstarter.entity.Project;
import ua.com.goit.gojava.alexfurman.kickstarter.entity.Reward;
import ua.com.goit.gojava.alexfurman.kickstarter.repository.RewardRepository;

@Service
public class RewardService {

	@Autowired
	private RewardRepository rewardRepository;

	public List<Reward> findByProject(Project project) {
		return rewardRepository.findByProject(project);
	}
	
	public void save(Reward reward, String name) {
		rewardRepository.save(reward);
	}

}
