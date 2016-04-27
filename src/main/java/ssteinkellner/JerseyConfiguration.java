package ssteinkellner;

import javax.inject.Named;

import org.glassfish.jersey.server.ResourceConfig;

@Named
public class JerseyConfiguration extends ResourceConfig {
    public JerseyConfiguration() {
        this.register(UserManager.class);
    }
}
