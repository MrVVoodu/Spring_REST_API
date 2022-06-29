package toh_rest_api.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HeroDatabase {

    private static final Logger log = LoggerFactory.getLogger(HeroDatabase.class);

    @Bean
    CommandLineRunner initDatabase(HeroRepository heroRepository) {

        return args-> {
            heroRepository.save(new Hero(12L,"Dr. Nice"));
            heroRepository.save(new Hero(13L,"Bombasto"));
            heroRepository.save(new Hero(14L,"Celeritas"));
            heroRepository.save(new Hero(15L,"Magneta"));
            heroRepository.save(new Hero(16L,"RubberMan"));
            heroRepository.save(new Hero(17L,"Dynamo"));
            heroRepository.save(new Hero(18L,"Le Coq"));
            heroRepository.save(new Hero(19L,"Magman"));
            heroRepository.save(new Hero(20L,"Tornadhoe"));

            heroRepository.findAll().forEach(hero -> log.info("Preloaded " + hero));
        };
    }
}



/*[
      { id: 12, name: 'Dr. Nice' },
      { id: 13, name: 'Bombasto' },
      { id: 14, name: 'Celeritas' },
      { id: 15, name: 'Magneta' },
      { id: 16, name: 'RubberMan' },
      { id: 17, name: 'Dynama' },
      { id: 18, name: 'Dr. IQ' },
      { id: 19, name: 'Magma' },
      { id: 20, name: 'Tornado' }
    ];*/