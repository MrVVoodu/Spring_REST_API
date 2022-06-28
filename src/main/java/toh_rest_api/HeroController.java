package toh_rest_api;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class HeroController {

    private final HeroRepository heroRepository;
    private final HeroModelAssembler assembler;

    HeroController(HeroRepository heroRepository, HeroModelAssembler assembler) {
        this.heroRepository = heroRepository;
        this.assembler = assembler;
    }

    @GetMapping("/heroes")
    CollectionModel<EntityModel<Hero>> getHeroes() {

        List<EntityModel<Hero>> heroes = StreamSupport.stream(heroRepository.findAll().spliterator(), false) //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(heroes, linkTo(methodOn(HeroController.class).getHeroes()).withSelfRel());
    }

    @GetMapping("/heroes/{id}")
    EntityModel<Hero> getHero(@PathVariable Long id) {

        Hero hero = heroRepository.findById(id) //
                .orElseThrow(() -> new HeroControllerAdvice.HeroNotFoundException(id));

        return assembler.toModel(hero);
    }

    @PostMapping("/heroes")
    ResponseEntity<EntityModel<Hero>> addHero(@RequestBody Hero newHero) {

        EntityModel<Hero> entityModel = assembler.toModel(heroRepository.save(newHero));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @DeleteMapping("/heroes/{id}")
    ResponseEntity<EntityModel<Hero>> deleteHero(@PathVariable Long id) {

        heroRepository.deleteById(id);

        return ResponseEntity //
                .noContent().build();
    }

    @PutMapping("/heroes/{id}")
    ResponseEntity<EntityModel<Hero>> updateHero(@RequestBody Hero newHero, @PathVariable Long id) {

        Hero updatedHero = heroRepository.findById(id)
                .map(hero -> {
                    hero.setName(newHero.getName());
                    return heroRepository.save(hero);
                }) //
                .orElseGet(() -> {
                    newHero.setId(id);
                    return heroRepository.save(newHero);
                });

        EntityModel<Hero> entityModel = assembler.toModel(updatedHero);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

//    @GetMapping("/health")
//    CollectionModel<EntityModel<Hero>> getHealthStatus() {
//
//        //CODE//
//
//        return CollectionModel.of(hero, linkTo(methodOn(HeroController.class).all()).withSelfRel());
//    }

}
