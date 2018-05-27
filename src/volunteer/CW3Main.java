package volunteer;

import java.util.Scanner;

public class CW3Main {

	private static boolean exit;

	public static void main(String[] args) {
		System.out.println("Welcome to the Auto Volunteer Distribution System !");
		System.out.println();
		SkillSorter sorter = new SkillSorter();

		exit = false;
		Scanner sc = new Scanner(System.in);
		String command = "";
		while (!exit) {
			System.out.println("Menu Functions");
			System.out.println("1-Add volunteer");
			System.out.println("2-Move volunteer");
			System.out.println("3-Delete volunteer");
			System.out.println("4-Delete all volunteers");
			System.out.println("5-Display groups");
			System.out.println("6-Save and exit");
			System.out.println("Please input your command:");
			command = sc.nextLine();

			switch (command) {
			case "1":
				// Add volunteers
				Volunteer volunteer = new Volunteer();
				try {
					volunteer.getSkillSet();
				} catch (Exception e) {
					System.out.println(e.getMessage());
					break;
				}
				sorter.addVolunteer(volunteer);
				break;

			case "2":
				// Move volunteers
				System.out.println("Please input the volunteer's skill you want to move:");
				String skill = sc.next();
				System.out.println("Please input the GroupID you want to move from:");
				int fromID = sc.nextInt();
				System.out.println("Please input the GroupID you want to move to:");
				int toID = sc.nextInt();
				sorter.moveVolunteer(skill, sorter.getCommunityGroups().get(fromID - 1),
				sorter.getCommunityGroups().get(toID - 1));
				break;

			case "3":
				// Delete volunteers
				System.out.println("Please input the volunteer's skill want to delete:");
				String Del_skill = sc.next();
				System.out.println("Please input the GroupID you want to delete from:");
				int Del_fromID = sc.nextInt();
				sorter.deleteVolunteer(Del_skill, sorter.getCommunityGroups().get(Del_fromID - 1));
				break;

			case "4":
				// Remove all volunteers and all skillsets in the group
				sorter.deleteAllVolunteers();
				break;

			case "5":
				// Display all groups and all skillsets in groups
				for (CommunityGroup group : sorter.getCommunityGroups()) {
					System.out.println(group.getSkillsTotals());
				}
				break;

			case "6":
				// Exit the program and save all volunteers to the file
				exit = true;
				sorter.saveGroups();
				System.out.println("The volunteers have been saved. Have a nice day !");
				break;

			default:
				System.out.println("Invalid option");
				break;
			}
			System.out.println();
		}
	}
}
