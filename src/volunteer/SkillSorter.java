package volunteer;

import interfaces.SkillSorterInterface;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SkillSorter implements SkillSorterInterface {
	private ArrayList<CommunityGroup> myGroups = new ArrayList<>();

	public SkillSorter() {
		loadGroups();// Load the volunteers inside the file
	}

	public boolean saveGroups() {
		File file = new File("Groups.txt");
		try {
			BufferedWriter br = new BufferedWriter(new FileWriter(file));
			for (CommunityGroup group : myGroups) {
				int groupSize = group.getVolunteers().size();
				String skillSetString = "";
				for (Volunteer volunteer : group.getVolunteers()) {
					skillSetString += volunteer.getSkill() + " ";
				}

				br.write(String.format("%d", groupSize));
				br.newLine();
				br.write(skillSetString);
				br.newLine();
			}
			br.close();
		} catch (IOException e) {
			System.out.println("File Save Error!");
			return false;
		}
		return true;
	}

	private void loadGroups() {
		for (int i = 1; i <= 5; i++) {
			CommunityGroup group = new CommunityGroup(i);
			myGroups.add(group);
		}

		File file = new File("Groups.txt");
		try {
			BufferedReader bw = new BufferedReader(new FileReader(file));
			String line = null;
			int index = 0;
			int groupIndex = 0;
			int size = 0;
			while ((line = bw.readLine()) != null) {
				if (index == 0) {
					size = Integer.parseInt(line);
					index++;
				} else {
					String[] skillsString = line.split(" ");
					for (int i = 0; i < size; i++) {
						Volunteer vol = new Volunteer(skillsString[i]);
						myGroups.get(groupIndex).addVolunteer(vol);
					}
					index = 0;
					groupIndex++;
					size = 0;
				}

			}
			bw.close();
		} catch (IOException e) {
			System.out.println("Load failure!");
		}
	}

	@Override
	public void addVolunteer(Volunteer vol) {
		double totalVariance[] = new double[5];

		/*
		 *  Test the smallest variance of total skills in each group and find the
		 *  smallest variance of each skill inside each group.
		 */
		for (int i = 0; i < 5; i++) {
			myGroups.get(i).addVolunteer(vol);

			// calculate the variance of total skills in each group
			double groupVariance = groupVariance(myGroups);
			// caculate the variance of each skill inside each group
			double skillVariance[] = new double[5];
			skillVariance = skillVariance(myGroups);

			for (int j = 0; j < 5; j++) {
				totalVariance[i] += skillVariance[j];
			}
			// Get the variance of total skills in each group
			totalVariance[i] += groupVariance;

			/*
			 * After testing, remove the volunteers which had been used for testing
			 */
			myGroups.get(i).removeVolunteer(vol);
		}
		/*
		 * Find the smallest variance of total skills in each group and the
		 * variance of each skill inside each group.
		 */
		int pos = 0;
		for (int i = 0; i < 5; i++) {
			if (totalVariance[i] < totalVariance[pos]) {
				pos = i;
			}
		}
		myGroups.get(pos).addVolunteer(vol);
	}

	private double groupVariance(ArrayList<CommunityGroup> myGroups) {
		double avg = 0;
		double variance = 0;

		// Calculate the average number of total skills in groups
		for (CommunityGroup group : myGroups) {
			avg += 1.0 * group.getTotalNum() / myGroups.size();
		}

		// Calculate the variance of total skills in each group
		for (int i = 0; i < myGroups.size(); i++) {
			variance += 1.0 * (myGroups.get(i).getTotalNum() - avg) * (myGroups.get(i).getTotalNum() - avg)
					/ myGroups.size();
		}
		return variance;
	}

	private double[] skillVariance(ArrayList<CommunityGroup> myGroups) {
		double skillVariance[] = new double[5];

		// Get the variance of skills inside each group
		for (int i = 0; i < myGroups.size(); i++) {
			double avg = 0;
			double variance = 0;

			// Calculate the average number of skills inside each group
			for (int j = 0; j < 5; j++) {
				avg += myGroups.get(i).getSkillNum()[j] / 5.0;
			}

			// Calculate the variance of each skill inside each group
			for (int j = 0; j < 5; j++) {
				variance += (myGroups.get(i).getSkillNum()[j] - avg) * (myGroups.get(i).getSkillNum()[j] - avg) / 5.0;
			}
			skillVariance[i] = variance;
		}
		return skillVariance;
	}

	/*
	 * Move a volunteer with this skillset (eg AAA, BCD) from one group to another
	 */
	@Override
	public void moveVolunteer(String skillSet, CommunityGroup from, CommunityGroup to) {

		ArrayList<Volunteer> volunteers = from.getVolunteers();

		boolean find = false;
		for (Volunteer volunteer : volunteers) {
			if (volunteer.getSkill().equals(skillSet)) {
				from.removeVolunteer(volunteer);
				to.addVolunteer(volunteer);
				find = true;
				break;
			}
		}
		if (!find) {
			System.out.println("There is no volunteers with this skill in the group!");
		}
	}

	/*
	 * Delete a volunteer with this skillset from this CommunityGroup
	 */
	@Override
	public void deleteVolunteer(String skillSet, CommunityGroup from) {
		ArrayList<Volunteer> volunteers = from.getVolunteers();

		// Find the volunteer with the skillSet
		boolean find = false;
		for (Volunteer volunteer : volunteers) {
			if (volunteer.getSkill().equals(skillSet)) {
				from.removeVolunteer(volunteer);
				find = true;
				break;
			}
		}
		if (!find) {
			System.out.println("There is no volunteers with this skill in the group!");
		}
	}

	/*
	 * Delete all volunteers from all CommunityGroups
	 */
	@Override
	public void deleteAllVolunteers() {
		for (CommunityGroup group : myGroups) {
			group.removeAllVolunteers();
		}
	}

	@Override
	public ArrayList<CommunityGroup> getCommunityGroups() {
		return myGroups;
	}
}