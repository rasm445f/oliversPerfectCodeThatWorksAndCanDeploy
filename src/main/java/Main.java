import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Guide;
import entities.Role;
import entities.User;
import facades.GuideFacade;
import facades.UserFacade;
import utils.EMF_Creator;

import javax.management.relation.RoleList;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("hello");

        EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
        UserFacade FACADE =  UserFacade.getUserFacade(EMF);
        GuideFacade GFACADE = GuideFacade.getGuideFacade(EMF);

        Role role = new Role("admin");
        List RoleList = new ArrayList<>();
        RoleList.add(role);

        User user = new User("Oliver","test123",RoleList);

        Guide guide = new Guide("male","1999","Great guide","kjawkjdw");

        //FACADE.createUser(user);
        GFACADE.createGuide(guide);
    }
}
