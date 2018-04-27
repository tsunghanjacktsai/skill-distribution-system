
package interfaces;

import volunteer.CommunityGroup;
import volunteer.Volunteer;
import java.util.ArrayList;

//**DO NOT CHANGE ANYTHING HERE
public interface SkillSorterInterface { 
    public void addVolunteer(Volunteer vol);
    public void moveVolunteer(String skillSet, CommunityGroup from, CommunityGroup to);
    public void deleteVolunteer(String skillSet, CommunityGroup from);
    public void deleteAllVolunteers();
    public ArrayList<CommunityGroup> getCommunityGroups();
        
    
}
