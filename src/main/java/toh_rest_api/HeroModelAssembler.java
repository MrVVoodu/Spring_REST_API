package toh_rest_api;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class HeroModelAssembler implements RepresentationModelAssembler<Hero, EntityModel<Hero>> {

    @Override
    public EntityModel<Hero> toModel(Hero hero) {

        return EntityModel.of(hero, //
                linkTo(methodOn(HeroController.class).getHero(hero.getId())).withSelfRel(), //
//                linkTo(methodOn(HeroController.class).deleteHero(hero.getId())).withSelfRel(), //
//                linkTo(methodOn(HeroController.class).updateHero(hero, hero.getId())).withSelfRel(),//
                linkTo(methodOn(HeroController.class).getHeroes()).withRel("get heroes"), //
                linkTo(methodOn(HeroController.class).addHero(hero)).withRel("add hero")//
                //linkTo(methodOn(HeroController.class).getHealthStatus()).withRel("health")//
        );
    }
}
