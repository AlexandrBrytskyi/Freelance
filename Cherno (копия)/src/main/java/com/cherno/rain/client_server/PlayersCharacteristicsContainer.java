package com.cherno.rain.client_server;


import java.io.Serializable;
import java.util.Map;

public class PlayersCharacteristicsContainer implements Serializable {

    private Map<Integer, PlayerKeyMouse> playersCharact;

    public PlayersCharacteristicsContainer() {
    }

    public PlayersCharacteristicsContainer(Map<Integer, PlayerKeyMouse> playersCharact) {
        this.playersCharact = playersCharact;
    }

    public Map<Integer, PlayerKeyMouse> getPlayersCharact() {
        return playersCharact;
    }

    public void setPlayersCharact(Map<Integer, PlayerKeyMouse> playersCharact) {
        this.playersCharact = playersCharact;
    }

    @Override
    public String toString() {
        return "PlayersCharacteristicsContainer{" +
                "playersCharact=" + playersCharact +
                '}';
    }
}
