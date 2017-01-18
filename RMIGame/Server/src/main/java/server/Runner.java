package server;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import server.gui.ServerGUI;

public class Runner {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        ServerGUI gui = context.getBean(ServerGUI.class);
        gui.initRMIService();
    }
}
