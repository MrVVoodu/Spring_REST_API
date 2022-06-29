package toh_rest_api.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import toh_rest_api.controller.HeroControllerAdvice;
import toh_rest_api.database.Hero;
import toh_rest_api.database.HeroRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class HeroService {

    private final HeroRepository heroRepository;

    public Hero saveHero(Hero hero) {
        return heroRepository.save(hero);
    }

    public List<Hero> getAllHeroes() {
        return (List<Hero>) heroRepository.findAll();
    }

    public Hero getHeroById(Long id) {
        return heroRepository.findById(id)
                .orElseThrow( ()-> new HeroControllerAdvice.HeroNotFoundException(id));
    }

    public void deleteHeroById(Long id) {
        heroRepository.deleteById(id);
    }

    public Hero updateHeroById(Hero newHero, Long id) {
        return heroRepository.findById(id)
                .map( hero -> {
                    hero.setName(newHero.getName());
                    return heroRepository.save(hero);
                } )
                .orElseGet( ()-> {
                    newHero.setId(id);
                    return heroRepository.save(newHero);
                });
    }
}
