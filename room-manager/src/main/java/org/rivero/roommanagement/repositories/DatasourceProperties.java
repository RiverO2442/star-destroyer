package org.rivero.roommanagement.repositories;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("dyson.datasource")
public class DatasourceProperties {
    private String url;
    private String username;
    private String password;
}
