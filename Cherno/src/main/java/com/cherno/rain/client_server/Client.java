package com.cherno.rain.client_server;


import com.cherno.rain.Game;
import com.cherno.rain.entity.mob.Player;
import com.cherno.rain.level.Level;
import com.cherno.rain.level.TileCoordinate;

import javax.swing.*;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;


public class Client implements KeyMouseObservable, MessageSendable {


    private Game gui;
    private PlayerCoordinatesUpdater coordinatesUpdater;
    private int playersIdentifier = 0;
    PlayerKeyMouse myKeyMouse;
    private volatile boolean isReading;
    private IServer server;
    private String name;


    public Client(IServer server, String name, Game gui, Level level) throws IOException {
        this.gui = gui;
        this.name = name;
        this.server = server;
        coordinatesUpdater = new PlayerCoordinatesUpdater(level);
        connectToGame();
        initPlayersMovementWatcherThread();
        initMessagesRecieverThread();
    }

    private void initMessagesRecieverThread() {
        Thread messTh = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        /*update messages each second*/
                        Thread.currentThread().sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    String message = server.getMessages(playersIdentifier);
                    if (message.equals("BANNED\n")) {
                        JOptionPane.showMessageDialog(gui, "BANNED", "Bahn", JOptionPane.ERROR_MESSAGE);
                        System.exit(0);
                    }
                    if (message != null && !message.isEmpty()) gui.appendToOutput(message);
                }
            }
        });
        messTh.start();
    }

    private void initPlayersMovementWatcherThread() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    /*15 times in second update info about players*/
                    try {
                        Thread.currentThread().sleep(1000 / 15);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    parseCharacteristics(server.getCharacteristicContainer());
                }
            }
        });
        thread.start();
    }

    private void connectToGame() {
        ConnectToGameResponse response = server.connect(new ConnectToGameRequest());
        playersIdentifier = response.getPlayerIdentifier();
        PlayersCharacteristicsContainer characteristicsContainer = response.getContainer();
        TileCoordinate playerSpawn = new TileCoordinate(19, 62);
        Player clientPlayer = new Player(playerSpawn.x(), playerSpawn.y(),
                new KeyboardMustNotify(Client.this),
                new MouseMustNotify(Client.this));
        coordinatesUpdater.getLevel().add(clientPlayer, playersIdentifier);
//        System.out.println("identifier " + playersIdentifier);
//        System.out.println("Player " + clientPlayer);
        myKeyMouse = new PlayerKeyMouse(false, false, false, false, -1, -1, -1, playersIdentifier);
        parseCharacteristicsWhenConnect(characteristicsContainer);
    }


    @Override
    public void valueChanged() {
        coordinatesUpdater.setPlayerCharacteristics(myKeyMouse);
//        System.out.println("client sending " + myKeyMouse);
        server.sendPlayerKeyMouse(myKeyMouse);
    }


    private void parseCharacteristics(PlayersCharacteristicsContainer container) {
//        System.out.println("Recieved from server " + container);
        Iterator<Map.Entry<Integer, Player>> iterator =
                coordinatesUpdater.getLevel().getPlayers().entrySet().iterator();
        Map<Integer, PlayerKeyMouse> mapFromServer = container.getPlayersCharact();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Player> entry = iterator.next();
            if (!mapFromServer.containsKey(entry.getKey())) {
                iterator.remove();
            } else {
                if (entry.getKey() != playersIdentifier) {
                    coordinatesUpdater.updatePlayerCharacteristics(mapFromServer.get(entry.getKey()));
                }
            }
        }
    }

    private void parseCharacteristicsWhenConnect(PlayersCharacteristicsContainer characteristicsContainer) {
//        System.out.println("Recieved connected  " + characteristicsContainer);
        Level level = coordinatesUpdater.getLevel();
        for (Map.Entry<Integer, PlayerKeyMouse> characteristic : characteristicsContainer.getPlayersCharact().entrySet()) {
            if (!characteristic.getKey().equals(playersIdentifier)) {
                if (!level.getPlayers().containsKey(characteristic.getKey())) {
                    level.add(new Player(characteristic.getValue().getPlayerX().intValue(), characteristic.getValue().getPlayerY().intValue(), new KeybordClient(), new MouseClient()), characteristic.getKey());
                    coordinatesUpdater.updatePlayerCharacteristics(characteristic.getValue());
                }
            }
        }
    }


    public int getPlayersIdentifier() {
        return playersIdentifier;
    }

    public void sendMessage(String mess) {
        server.sendMessage(mess, playersIdentifier);
    }
}
