package com.antonio.trial;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    private static final Logger logger = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initializeDatabase(PlayerRepository playerRepository){
             return args -> {
             logger.info(playerRepository.save(new Player("Garou", "Hunter" ,"Flowing", "Point-guard")).toString());
             logger.info(playerRepository.save(new Player("Saitama", "BaldCape","Punch", "Infinite")).toString());
        };
    }
}
