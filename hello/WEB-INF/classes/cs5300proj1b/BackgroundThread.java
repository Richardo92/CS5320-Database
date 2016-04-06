package cs5300proj1b;

import java.io.*;

import javax.servlet.http.*;
import javax.servlet.*;

public class BackgroundThread implements ServletContextListener {
    private Thread t = null;
    public void contextInitialized(ServletContextEvent sce) {
        if ((t == null) || (!t.isAlive())) {
            t = new Thread() {
                public void run() {
                    try {
                        new FileOutputStream("/tmp/cs5300proj1b-tmp").close();
                    } catch (Exception e) {
                    }
                }
            };
            t.start();
        }
    }
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            t.interrupt();
        } catch (Exception ex) {
        }
    }
}
