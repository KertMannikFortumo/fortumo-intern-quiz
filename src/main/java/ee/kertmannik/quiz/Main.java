package ee.kertmannik.quiz;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class Main {

    public static void main(String[] args) throws Exception {
        int port = Integer.parseInt(System.getenv("PORT"));
        Server server = new Server(port);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        context.addEventListener(new MyServletContextListener());
        server.setHandler(context);

        context.addServlet(AnswerServlet.class, "/answer");
        context.addServlet(QuestionServlet.class, "/question");
        context.addFilter(IdentificationFilter.class, "/*", 0);

        server.start();
        server.join();
    }
}
