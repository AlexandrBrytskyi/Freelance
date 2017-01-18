package com.cherno.rain.client_server;

import java.io.Serializable;
import java.util.Map;


public class ConnectToGameResponse implements Serializable {

    private int playerIdentifier;
    private PlayersCharacteristicsContainer container;

    public ConnectToGameResponse() {
    }

    public ConnectToGameResponse(int playerIdentifier, PlayersCharacteristicsContainer container) {
        this.playerIdentifier = playerIdentifier;
        this.container = container;
    }

    public int getPlayerIdentifier() {
        return playerIdentifier;
    }

    public void setPlayerIdentifier(int playerIdentifier) {
        this.playerIdentifier = playerIdentifier;
    }

    public PlayersCharacteristicsContainer getContainer() {
        return container;
    }

    public void setContainer(PlayersCharacteristicsContainer container) {
        this.container = container;
    }
}
