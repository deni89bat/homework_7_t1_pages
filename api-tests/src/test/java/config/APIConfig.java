package config;

import org.aeonbits.owner.Config;

public interface APIConfig extends Config {

    @Key("baseURI")
    @DefaultValue("http://9b142cdd34e.vps.myjino.ru:49268")
    String baseURI();

    @Key("endpoints.register")
    @DefaultValue("/register")
    String registerEndpoint();

    @Key("endpoints.login")
    @DefaultValue("/login")
    String loginEndpoint();

    @Key("endpoints.products")
    @DefaultValue("/products")
    String productsEndpoint();

    @Key("endpoints.cart")
    @DefaultValue("/cart")
    String cartEndpoint();


    @Key("logging.enabled")
    @DefaultValue("true")
    boolean loggingEnabled();

}
