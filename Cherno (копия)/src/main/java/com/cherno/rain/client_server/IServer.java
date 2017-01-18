package com.cherno.rain.client_server;


public interface IServer {

    ConnectToGameResponse connect(ConnectToGameRequest request);

    PlayersCharacteristicsContainer getCharacteristicContainer();

    void sendPlayerKeyMouse(PlayerKeyMouse playerKeyMouse);

    void sendMessage(String message, int id);

    String getMessages(int identifier);

}
