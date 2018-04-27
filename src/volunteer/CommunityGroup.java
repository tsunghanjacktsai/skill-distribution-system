package volunteer;

import java.util.ArrayList;

import interfaces.CommunityGroupInterface;

public class CommunityGroup implements CommunityGroupInterface {
	private int groupID;// The ID of the group

	private int skillsNum[];// The number of skills inside the group

	private ArrayList<Volunteer> VolunteerList;// The list of volunteers

	public CommunityGroup(int id) {
		VolunteerList = new ArrayList<Volunteer>();
		groupID = id;
		skillsNum = new int[5];// Five skills
	}

	public void addVolunteer(Volunteer volunteer) {
		// Add the volunteer into the list
		VolunteerList.add(volunteer);

		char skills[] = new char[3];// Each volunteer owns 3 skills

		// The statistics of skills in each group
		for (int i = 0; i < 3; i++) {
			skills[i] = volunteer.getSkill().charAt(i);
			switch (skills[i]) {
			case 'A':
				skillsNum[0]++;
				break;
			case 'B':
				skillsNum[1]++;
				break;
			case 'C':
				skillsNum[2]++;
				break;
			case 'D':
				skillsNum[3]++;
				break;
			case 'E':
				skillsNum[4]++;
				break;
			default:
				break;
			}
		}
	}

	public void removeVolunteer(Volunteer volunteer) {

		char skills[] = new char[3];// Each volunteer has 3 skills

		for (int i = 0; i < 3; i++) {
			skills[i] = volunteer.getSkill().charAt(i);
			switch (skills[i]) {
			case 'A':
				skillsNum[0]--;
				break;
			case 'B':
				skillsNum[1]--;
				break;
			case 'C':
				skillsNum[2]--;
				break;
			case 'D':
				skillsNum[3]--;
				break;
			case 'E':
				skillsNum[4]--;
				break;
			default:
				break;
			}
		}

		VolunteerList.remove(volunteer);

	}

	public ArrayList<Volunteer> getVolunteers() {
		return VolunteerList;
	}

	/*
	 * Reset all volunteers inside the groups
	 */
	public void removeAllVolunteers() {
		VolunteerList.clear();
		for (int i = 0; i < 5; i++) {
			skillsNum[i] = 0;
		}
	}

	/*
	 * Get the number of each skill inside the group
	 */
	public int[] getSkillNum() {
		return skillsNum;
	}

	/*
	 * Get the total number of skills inside the group
	 */
	public int getTotalNum() {
		int total = 0;
		for (int i = 0; i < 5; i++) {
			// total = total+skillsNum[i];
			total += skillsNum[i];
		}
		return total;
	}

	/*
	 * Return the total number of volunteers in this community group
	 */
	@Override
	public int howManyVolunteers() {
		return VolunteerList.size();
	}
	
	/*
	 * Return the total number of each skill in a String. For example:
	 * Skill A: 13, Skill B: 20, Skill C: 23, Skill D: 5, Skill E: 41 
	 */
	@Override
	public String getSkillsTotals() {
		int total = skillsNum[0] + skillsNum[1] + skillsNum[2] + skillsNum[3] + skillsNum[4];
		String stringMsg = String.format("Skill A: %d  Skill B: %d  Skill C: %d  Skill D: %d  Skill E: %d Total: %d",
				skillsNum[0], skillsNum[1], skillsNum[2], skillsNum[3], skillsNum[4], total);
		return stringMsg;
	}
}
