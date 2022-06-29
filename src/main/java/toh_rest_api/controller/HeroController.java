package toh_rest_api.controller;


import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toh_rest_api.database.Hero;
import toh_rest_api.model.HeroModelAssembler;
import toh_rest_api.service.HeroService;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/heroes")
@AllArgsConstructor
public class HeroController {

    private final HeroModelAssembler assembler;
    private final HeroService heroService;



    @GetMapping
    public CollectionModel<EntityModel<Hero>> getHeroes() {

        List<Hero> allHeroes = heroService.getAllHeroes();

        List<EntityModel<Hero>> allHeroesEM = allHeroes.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(allHeroesEM,
                linkTo(methodOn(HeroController.class).getHeroes()).withSelfRel());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Hero>> getHero(@PathVariable Long id) {

        EntityModel<Hero> entityModel = assembler.toModel(heroService.getHeroById(id));

        return ResponseEntity //
                .ok(entityModel);
    }

    @PostMapping
    ResponseEntity<EntityModel<Hero>> addHero(@RequestBody Hero newHero) {

        EntityModel<Hero> entityModel = assembler.toModel(heroService.saveHero(newHero));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<EntityModel<Hero>> deleteHero(@PathVariable Long id) {

        heroService.deleteHeroById(id);

        return ResponseEntity //
                .noContent().build();
    }

    @PutMapping("/{id}")
    ResponseEntity<EntityModel<Hero>> updateHero(@RequestBody Hero newHero, @PathVariable Long id) {

        EntityModel<Hero> entityModel = assembler.toModel(heroService.updateHeroById(newHero,id));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

//Actuator also working on {hostname}:{port}/actuator

}
