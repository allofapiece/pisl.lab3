package by.pisl.lab3;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * @version 1.0.0
 */
public class Lab3 {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);

        WebAppContext wac = new WebAppContext();

        wac.setDescriptor("web/WEB-INF/web.xml");
        wac.setResourceBase("web");

        Configuration.ClassList classlist = Configuration.ClassList
                .setServerDefault(server);
        classlist.addBefore(
                "org.eclipse.jetty.webapp.JettyWebXmlConfiguration",
                "org.eclipse.jetty.annotations.AnnotationConfiguration");

        server.setHandler(wac);

        server.start();
        server.join();
    }
}
