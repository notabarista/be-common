package org.notabarista;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@ComponentScan("org.notabarista")
@EnableJpaRepositories
@EntityScan
@EnableAsync
@EnableConfigurationProperties(LibraryProperties.class)
@EnableAutoConfiguration
public class CommonLibApplication {

}
