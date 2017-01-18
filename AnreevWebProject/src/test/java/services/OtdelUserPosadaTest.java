package services;

import app.dao.OtdelDao;
import app.dao.UserDao;
import app.model.*;
import app.model.exceptions.UserMustRegisterException;
import app.model.exceptions.WrongDataException;
import app.services.*;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.LinkedList;

@ContextConfiguration(locations = "classpath:app_context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class OtdelUserPosadaTest {


    @Autowired
    RegistrationLoginService registrationLoginService;


    @Autowired
    OtdelManipulatingService otdelManipulatingService;


    @Autowired
    PosadasManipulatingService posadasManipulatingService;


    @Autowired
    WorkerManimulationService workerManimulationService;


    @Autowired
    PersonalDataChangeService personalDataChangeService;

    @Autowired
    UserDao userDao;

    @Autowired
    OtdelDao otdelDao;

    static User admin;
    static User nachOtd;
    static User worker;
    static Otdel predpriyztie;
    static Otdel podotdel;
    static Posada nachOtPosada;
    static Posada workerPosada;
    static String adminToken;
    static int adminLogin;


    @Test
    public void _001addAdminCreateOtdel() {

        predpriyztie = new Otdel("prepriyatie", "predpriyatie", null, new LinkedList<Otdel>(), null, null);
        otdelDao.add(predpriyztie);

        admin = new User(new UserInfo("ADMIN", "ADMIN", "ADMIN", "ADMIN", "ADMIN"), null,
                predpriyztie, null, new WorkHistory(new LinkedList<>(), new LinkedList<>()), true, "00000");

        admin.getWorkHistory().setUser(admin);
        admin.getWorkHistory().getChangesInPosada().add(new ChangesInPosada(admin.getWorkHistory(), null, null, "admin now", new Date()));

        predpriyztie.setMentor(admin);

        admin = userDao.add(admin);
        adminLogin = admin.getUserPersonalNum();

        System.out.println("admin personal" + adminLogin);
        otdelDao.update(predpriyztie);


    }

    @Test
    public void _002adminLogin() {
        try {
            System.out.println("admin login " + adminLogin);
            adminToken = registrationLoginService.login(adminLogin, "00000");

            System.out.println("admin token " + adminToken);
        } catch (WrongDataException e) {
            e.printStackTrace();
        } catch (UserMustRegisterException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Ignore
    public void _003addPodotdelAndMentor() {

        podotdel = otdelManipulatingService.creatPodeOtdel(adminToken, "Sellers work here", "Sellers work here", predpriyztie.getId());

        nachOtPosada = posadasManipulatingService.createNewPosada("Nach Manaing Otdel", "sdsda", podotdel.getId(), 5000, adminToken);

        nachOtd = posadasManipulatingService.addNewUser(adminToken, podotdel.getId(), true);

        otdelManipulatingService.setMentor(adminToken, podotdel.getId(), nachOtd.getUserPersonalNum());

    }

}
