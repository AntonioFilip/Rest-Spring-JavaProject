package com.antonio.trial;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class PlayerController {
    private PlayerRepository playerRepository;
    private ModelAssembler modelAssembler;

    public PlayerController(PlayerRepository playerRepository, ModelAssembler modelAssembler) {
        this.playerRepository = playerRepository;
        this.modelAssembler = modelAssembler;
    }

    @GetMapping("/players")
    CollectionModel<EntityModel<Player>> all(){

        List<EntityModel<Player>> players = playerRepository.findAll().stream().map( modelAssembler::toModel).collect(Collectors.toList());
        /*List<EntityModel<Player>> players = monsterRepository.findAll().stream().map( monster ->
                modelAssembler.toModel(player)).collect(Collectors.toList());*/
        //monsterRepository.findAll().stream().forEach(modelAssembler::toModel);

        return CollectionModel.of(players, linkTo(methodOn(PlayerController.class).all()).withSelfRel());

    }

/*    EntityModel<Player> transform(Player player){
        return EntityModel.of(player,linkTo(methodOn(MonsterController.class).findMonsterInstance(monster.getId())).withSelfRel(),
                linkTo(methodOn(MonsterController.class).all()).withRel("players"));
    }*/

    @GetMapping("/players/{id}")
    EntityModel<Player> findPlayerInstance(@PathVariable Long id){
        Player player = playerRepository.findById(id).orElseThrow( () -> new PlayerNotFoundException(id));

        return modelAssembler.toModel(player);
    }

    @PostMapping("/players")
    Player createPlayer(@RequestBody Player newPlayer){
        return playerRepository.save(newPlayer);
    }

    @PutMapping("/players/{id}")
    Player replacePlayer(@RequestBody Player newPlayer, @PathVariable Long id){
        return playerRepository.findById(id).map(player -> {
            player.setPosition(newPlayer.getPosition());
            player.setName(newPlayer.getName());
            player.setHeight(newPlayer.getHeight());
            return playerRepository.save(player);
        }).orElseGet( () ->{
                newPlayer.setId(id);
                return playerRepository.save(newPlayer);
                }
        );
    }

    @DeleteMapping("/players/{id}")
    void deletePlayer(@PathVariable Long id){
        playerRepository.deleteById(id);
    }


}
