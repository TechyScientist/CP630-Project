package net.johnnyconsole.cp630.project.client;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ContextUtil {

    public static InitialContext getInitialContext() throws NamingException {
        Properties properties = new Properties();
        properties.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
        properties.setProperty("java.naming.factory.url.pkgs",  "org.jboss.naming");
        properties.setProperty(Context.PROVIDER_URL, "http-remoting://localhost:8080");
        properties.put(Context.SECURITY_PRINCIPAL, "quickstartUser");
        properties.put(Context.SECURITY_CREDENTIALS, "quickstartPwd1!");
        properties.put("jboss.naming.client.ejb.context", true);
        return new InitialContext(properties);
    }

}