package com.antonio.trial;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
class ModelAssembler implements RepresentationModelAssembler<Player, EntityModel<Player>> {
    @Override
    public EntityModel<Player> toModel(Player player) {
        return EntityModel.of(player,linkTo(methodOn(PlayerController.class).findPlayerInstance(player.getId())).withSelfRel(),
                linkTo(methodOn(PlayerController.class).all()).withRel("players"));
    }
}
