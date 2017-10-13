package ee.kertmannik.quiz;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

public class Main {

    public static void main(String[] args) throws Exception {
        int port = Integer.parseInt(System.getenv("PORT"));
        Server server = new Server(port);
        ServletHandler servletHandler = new ServletHandler();
        server.setHandler(servletHandler);
        servletHandler.addServletWithMapping(AnswerServlet.class, "/answer");
        servletHandler.addFilterWithMapping(IdentificationFilter.class, "/*", 0);
        servletHandler.addServletWithMapping(QuestionServlet.class, "/question");
        server.start();
        server.join();
    }
}
