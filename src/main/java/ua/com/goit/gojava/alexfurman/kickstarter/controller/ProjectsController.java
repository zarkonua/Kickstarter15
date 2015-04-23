package ua.com.goit.gojava.alexfurman.kickstarter.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.com.goit.gojava.alexfurman.kickstarter.entity.Project;
import ua.com.goit.gojava.alexfurman.kickstarter.entity.QuestionAndAnswers;
import ua.com.goit.gojava.alexfurman.kickstarter.entity.Reward;
import ua.com.goit.gojava.alexfurman.kickstarter.entity.UserReward;
import ua.com.goit.gojava.alexfurman.kickstarter.service.CategoryService;
import ua.com.goit.gojava.alexfurman.kickstarter.service.ProjectsService;
import ua.com.goit.gojava.alexfurman.kickstarter.service.QuestionAndAnswersService;
import ua.com.goit.gojava.alexfurman.kickstarter.service.RewardService;
import ua.com.goit.gojava.alexfurman.kickstarter.service.UserRewardService;

@Controller
public class ProjectsController {

	@Autowired
	private ProjectsService projectsService;

	@Autowired
	private QuestionAndAnswersService questionAndAnswersService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private RewardService rewardService;
	
	@Autowired
	private UserRewardService userRewardService;

	@ModelAttribute("questionandanswers")
	public QuestionAndAnswers constructQuestionAndAnswers() {
		return new QuestionAndAnswers();
	}

	@ModelAttribute("reward")
	public Reward constructReward() {
		return new Reward();
	}
	
	@ModelAttribute("userreward")
	public UserReward constructUserReward() {
		return new UserReward();
	}

	@RequestMapping("/projects/{id}")
	public String projects(Model model, @PathVariable int id) {
		model.addAttribute("projects", projectsService.findByCategory(id));
		return "projects";
	}

	@RequestMapping("/project/{id}")
	public String project(Model model, @PathVariable int id) {
		Project pr = projectsService.findOne(id);
		List<QuestionAndAnswers> qa = questionAndAnswersService
				.findByProject(pr);
		List<Reward> rewardList = rewardService.findByProject(pr);
		model.addAttribute("qa", qa);
		model.addAttribute("rewardList", rewardList);
		model.addAttribute("project", pr);
		return "project";
	}

	@RequestMapping("/project/payment")
	public String payment(@RequestParam(value = "id") Integer projectId,
			@RequestParam(value = "inputAmount") Integer inputAmount) {
		projectsService.addPayment(projectsService.findOne(projectId),
				inputAmount);
		return "payment";
	}

	@RequestMapping(value = "/project/{id}", method = RequestMethod.POST)
	public String doAddQuestionAndAnswers(
			Model model,
			@Valid @ModelAttribute("questionandanswers") QuestionAndAnswers questionAndAnswers,
			BindingResult result, Principal principal) {
		String name;
		if (principal == null) {
			name = "Guest";
		} else {
			name = principal.getName();
		}
		questionAndAnswersService.save(questionAndAnswers, name);
		return "redirect:/project/" + questionAndAnswers.getProject().getId()
				+ ".html";
	}

	@RequestMapping(value = "/project/updatequestion")
	public String updateQuestion(
			@RequestParam(value = "id") Integer questionId,
			@RequestParam(value = "projectid") Integer projectId,
			@RequestParam(value = "answer") String answer) {
		questionAndAnswersService.addAnswer(questionId, answer);
		return "redirect:/project/" + projectId + ".html";
	}

	@RequestMapping(value = "/project/addreward", method = RequestMethod.POST)
	public String doAddReward(Model model,
			@Valid @ModelAttribute("reward") Reward reward,
			BindingResult result, Principal principal) {
		String name = principal.getName();
		rewardService.save(reward, name);
		return "redirect:/project/" + reward.getProject().getId() + ".html";
	}

	@RequestMapping(value = "/project/getreward", method = RequestMethod.POST)
	public String doAddRewardToUser(Model model,
			@Valid @ModelAttribute("reward") Reward reward,
			BindingResult result, Principal principal) {
		String name = principal.getName();
		rewardService.addUserToReward(reward, name);
		return "redirect:/project/" + reward.getProject().getId() + ".html";
	}
}
