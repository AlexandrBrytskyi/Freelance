package com.cherno.rain.client_server;


import com.cherno.rain.Game;
import com.cherno.rain.entity.mob.Player;
import com.cherno.rain.level.Level;
import com.cherno.rain.level.TileCoordinate;


import java.util.*;

public class Server implements KeyMouseObservable, IServer, MessageSendable {

    private Game gui;
    private PlayerCoordinatesUpdater coordinatesUpdater;
    private int playersIdentifier = 0;
    private volatile PlayersCharacteristicsContainer characteristicsContainer = new PlayersCharacteristicsContainer();
    private volatile Map<Integer, List<String>> messages = new HashMap<>();
    private volatile Map<Integer, Boolean> userMoves = new HashMap<>();

    public Server(Game gui, Level level) {
        this.gui = gui;
        characteristicsContainer.setPlayersCharact(new HashMap<Integer, PlayerKeyMouse>());
        coordinatesUpdater = new PlayerCoordinatesUpdater(level);

    }


    @Override
    public void valueChanged() {
        PlayerKeyMouse playerKeyMouse = characteristicsContainer.getPlayersCharact().get(0);
        coordinatesUpdater.setPlayerCharacteristics(playerKeyMouse);
    }

    @Override
    public ConnectToGameResponse connect(ConnectToGameRequest request) {
        int playerIdentifier = addNewClient();
        ConnectToGameResponse response = new ConnectToGameResponse(playerIdentifier, characteristicsContainer);
        setPlayersPositions(response.getContainer());
        return response;
    }

    private void setPlayersPositions(PlayersCharacteristicsContainer container) {
        for (Map.Entry<Integer, PlayerKeyMouse> integerPlayerKeyMouseEntry : container.getPlayersCharact().entrySet()) {
            integerPlayerKeyMouseEntry.getValue().setPlayerX(coordinatesUpdater.getLevel().getPlayer(integerPlayerKeyMouseEntry.getKey()).getX());
            integerPlayerKeyMouseEntry.getValue().setPlayerY(coordinatesUpdater.getLevel().getPlayer(integerPlayerKeyMouseEntry.getKey()).getY());
        }
    }

    @Override
    public PlayersCharacteristicsContainer getCharacteristicContainer() {
        return characteristicsContainer;
    }

    @Override
    public void sendPlayerKeyMouse(PlayerKeyMouse playerKeyMouse) {
//        System.out.println("resieved from client " + playerKeyMouse);
        setCharacteristics(playerKeyMouse);
    }

    @Override
    public void sendMessage(String message, int id) {
        if (id != 0) gui.appendToOutput(message);
        for (Map.Entry<Integer, List<String>> integerListEntry : messages.entrySet()) {
            if (!integerListEntry.getKey().equals(id)) {
                integerListEntry.getValue().add(message);
                System.out.println("Added message " + message);
            }
        }
    }

    @Override
    public String getMessages(int identifier) {
        StringBuilder builder = new StringBuilder();
        for (String mess : messages.get(identifier)) {
            builder.append(mess).append("\n");
        }
        messages.get(identifier).clear();
        return builder.toString();
    }

    private void setCharacteristics(PlayerKeyMouse characteristics) {
        if (!characteristics.equals(characteristicsContainer.getPlayersCharact().get(characteristics.getIdentifier()))) {
            userMoves.remove(characteristics.getIdentifier());
            userMoves.put(characteristics.getIdentifier(), true);
        }

        coordinatesUpdater.updatePlayerCharacteristics(characteristics);
    }


    public void addServerPlayer() {
        TileCoordinate playerSpawn = new TileCoordinate(19, 62);
        Player serverPlayer = new Player(playerSpawn.x(), playerSpawn.y(), new KeyboardMustNotify(Server.this), new MouseMustNotify(Server.this));
        List<String> messages = new LinkedList<>();
        this.messages.put(playersIdentifier, messages);
        characteristicsContainer.getPlayersCharact().put(playersIdentifier, new PlayerKeyMouse(false, false, false, false, -1, -1, -1, playersIdentifier));
        coordinatesUpdater.getLevel().add(serverPlayer, playersIdentifier++);
    }


    private synchronized int addNewClient() {
        TileCoordinate playerSpawn = new TileCoordinate(19, 62);
        Player newPlayer = new Player(playerSpawn.x(), playerSpawn.y(), new KeybordClient(), new MouseClient());
        characteristicsContainer.getPlayersCharact().put(playersIdentifier, new PlayerKeyMouse(false, false, false, false, -1, -1, -1, playersIdentifier));
        final List<String> messages = new LinkedList<>();
        userMoves.put(playersIdentifier, true);
        final int identif = new Integer(playersIdentifier).intValue();
        /*run thread which will watch either user moves
        * if user do not move he must be removed*/
        Thread userMoveTh = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(identif);
                boolean runing = true;
                while (runing) {
                    try {
                        Thread.currentThread().sleep(15000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (userMoves.get(identif) == false) {
                        userMoves.remove(identif);
                        characteristicsContainer.getPlayersCharact().remove(identif);
                        coordinatesUpdater.getLevel().getPlayers().remove(identif);
                        Server.this.messages.get(identif).clear();
                        Server.this.messages.get(identif).add("BANNED");
                        runing = false;
                    } else {
                        userMoves.remove(identif);
                        userMoves.put(identif, false);
                    }
                }
            }
        });
        userMoveTh.start();
        this.messages.put(playersIdentifier, messages);
        coordinatesUpdater.getLevel().add(newPlayer, playersIdentifier++);
        return playersIdentifier - 1;
    }


    @Override
    public void sendMessage(String message) {
        sendMessage(message, 0);
    }
}
