package app.web_listeners;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class SpringContextLoader implements ServletContextListener {


    public void contextInitialized(ServletContextEvent servletContextEvent) {

        String springLocation = servletContextEvent.getServletContext().getInitParameter("spring_context_loc");
        System.out.println(springLocation);
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext(springLocation);

        servletContextEvent.getServletContext().setAttribute("spring_context", applicationContext);
        System.out.println("Spring context created");
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
