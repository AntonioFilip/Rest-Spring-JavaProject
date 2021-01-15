package com.antonio.trial;

class PlayerNotFoundException extends RuntimeException{

    PlayerNotFoundException(Long id) {
        super("Player no" + id + "was not found " );
    }
}
