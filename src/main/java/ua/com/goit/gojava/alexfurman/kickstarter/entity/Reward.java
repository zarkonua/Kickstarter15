package ua.com.goit.gojava.alexfurman.kickstarter.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Reward {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name = "reward_description")
	private String description;
	
	private int amount;
	
	@ManyToOne
	@JoinColumn(name = "project_id")
	private Project project;
		
	@ManyToMany
	@JoinTable(name = "reward_user", 
	joinColumns={@JoinColumn(name="reward_id")}, 
	inverseJoinColumns={@JoinColumn(name="user_id")})
	private List<User> users;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
}
