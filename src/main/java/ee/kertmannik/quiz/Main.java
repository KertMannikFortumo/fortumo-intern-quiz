package ee.kertmannik.quiz;

import org.eclipse.jetty.server.Server;

public class Main {

    public static void main(String[] args) throws Exception{
        int port = Integer.parseInt(System.getenv("PORT"));
        Server server = new Server(port);
        server.setHandler(new HelloWorldHandler());
        server.start();
        server.join();
    }
}
