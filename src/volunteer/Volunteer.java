package volunteer;

import java.util.Scanner;

import interfaces.VolunteerInterface;

public class Volunteer implements VolunteerInterface {

	private String skills;

	public Volunteer() {

	}

	public Volunteer(String skill) {
		skills = skill;
	}

	public String getSkill() {
		return skills;
	}

	@Override
	public String getSkillSet() {
		System.out.println("Please input three volunteers skills in a format of BBB, ABC, CDD etc");
		Scanner sc = new Scanner(System.in);
		skills = sc.next();
		if (skills.length() != 3) {
			throw new RuntimeException("Skills Error. Please input again");
		} else if (!(skills.matches("[A-E][A-E][A-E]"))) {
			throw new RuntimeException("Skills Error. Please input again");
		}
		return skills;
	}
}
