package config;

import org.aeonbits.owner.Config;

public interface APIConfig extends Config {

    @Key("baseURI")
    @DefaultValue("http://9b142cdd34e.vps.myjino.ru:49268")
    String baseURI();

}
