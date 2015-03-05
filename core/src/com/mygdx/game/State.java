package com.mygdx.game;

public class State {

    States state;

    public void setState(States state) {
        this.state = state;
    }

    public States getState() {
        return state;
    }
}

enum States {
    START, PLAY, FINISH
}