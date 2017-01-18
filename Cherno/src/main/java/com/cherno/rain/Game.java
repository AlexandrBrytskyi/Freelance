package com.cherno.rain;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import javax.swing.*;

import com.cherno.rain.client_server.Client;
import com.cherno.rain.client_server.IServer;
import com.cherno.rain.client_server.Server;
import com.cherno.rain.entity.mob.Player;
import com.cherno.rain.graphics.Screen;
import com.cherno.rain.input.Keyboard;
import com.cherno.rain.input.Mouse;
import com.cherno.rain.level.Level;
import com.cherno.rain.level.TileCoordinate;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.remoting.rmi.RmiServiceExporter;

public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 1L;

    private static int width = 300;
    private static int height = width / 16 * 9;
    private static int scale = 3;
    private static String title = "Rain";
    private String userName;
    private ChatFrame chatFrame;

    Integer frames = 0;
    Integer updates = 0;

    private Thread thread;
    private JFrame frame;
    private Keyboard key;
    private boolean running = false;

    private Screen screen;
    private Level level;
    private Player player;

    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

    // Constructor
    public Game() {
        userName = JOptionPane.showInputDialog("Enter your name, please");
        boolean isServer = askType();
        Dimension size = new Dimension(width * scale, height * scale);
        setPreferredSize(size);
        screen = new Screen(width, height);
        frame = new JFrame();
        level = Level.spawn;
        if (isServer) {
            Server server = new Server(Game.this, level);
            chatFrame = new ChatFrame(userName, server);
            RmiServiceExporter exporter = new RmiServiceExporter();
            exporter.setServiceName("RMI_SERVICE");
            exporter.setService(server);
            exporter.setServiceInterface(IServer.class);
            exporter.setRegistryHost("localhost");
            exporter.setAlwaysCreateRegistry(true);
            try {
                exporter.setRegistry(LocateRegistry.createRegistry(9999));
                appendToOutput("Registry created :\n" + LocateRegistry.getRegistry().toString());
                exporter.afterPropertiesSet();
            } catch (RemoteException e) {
                e.printStackTrace();
                appendToOutput(e.getMessage());
                appendToOutput("try again");
            }
            server.addServerPlayer();
            player = level.getPlayer(0);
            key = player.getInput();
            addKeyListener(key);
            Mouse mouse = level.getPlayer(0).getMouse();
            addMouseListener(mouse);
            addMouseMotionListener(mouse);

        } else {
            try {
// start creating stub
                String host = JOptionPane.showInputDialog(
                        Game.this, "Ok, " + userName + " now we have connect to client, enter host",
                        "Server host",
                        JOptionPane.INFORMATION_MESSAGE);
                String port = JOptionPane.showInputDialog(
                        Game.this, "Ok, " + userName + " now enter port",
                        "Server host",
                        JOptionPane.INFORMATION_MESSAGE);
                RmiProxyFactoryBean stub = new RmiProxyFactoryBean();
                stub.setServiceUrl("rmi://" + host + ":" + port + "/RMI_SERVICE");
                stub.setServiceInterface(IServer.class);
                stub.afterPropertiesSet();
                IServer server = (IServer) stub.getObject();
//     stub created
                Client client = new Client(server, userName, Game.this, level);
                chatFrame = new ChatFrame(userName, client);
                player = level.getPlayer(client.getPlayersIdentifier());
                key = player.getInput();
                addKeyListener(key);
                addMouseListener(level.getPlayer(client.getPlayersIdentifier()).getMouse());
                addMouseMotionListener(level.getPlayer(client.getPlayersIdentifier()).getMouse());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    } // end constructor

    private boolean askType() {
        return (JOptionPane.showConfirmDialog(Game.this, "Do you want to create server?", "Type", JOptionPane.INFORMATION_MESSAGE)
                == JOptionPane.YES_OPTION);
    }

    public synchronized void start() {
        running = true;
        thread = new Thread(this, "Display");
        thread.start();
    } // end start() method

    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    } // end stop() method


    /*previous realisation just overloaded processor with counting all the time
    * we can just sleep time we need*/
    @Override
    public void run() {
//        long lastTime = System.nanoTime();
//        long timer = System.currentTimeMillis();
//        final double ns = 1000000000.0 / 5;
//		final double ns = 1000000000.0 / 60.0;
//        double delta = 0;
//        Integer frames = 0; // how many frames we have time to render every second
//        Integer updates = 0; // how many times update method gets called every second, should be 60 in our case
        Thread timerTh = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.currentThread().sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    frames = 1000 / updates;
                    System.out.println(updates + " ups, " + frames + " fps");
                    frame.setTitle(title + " | " + updates + " ups, " + frames + " fps");
                    updates = 0;
                    frames = 0;
                }
            }
        });
        timerTh.start();
        requestFocus();
        while (running) {
            try {
                Thread.currentThread().sleep(1000 / 67);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            long now = System.nanoTime();
//            delta += (now - lastTime) / ns;
//            lastTime = now;
//            while (delta >= 1) { // will only happen 60 times a second
            update();
            updates++;
//            delta--;
//            }
            render();
        } // end while
        stop();
    } // end run() method

    private void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        screen.clear();
        double xScroll = player.getX() - screen.width / 2;
        double yScroll = player.getY() - screen.height / 2;
        level.render((int) xScroll, (int) yScroll, screen);

        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = screen.pixels[i];
        }

        Graphics g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Verdana", 0, 50));
//		g.drawString("X: " + player.x + ", Y: " + player.y, 450, 400);
        g.dispose();
        bs.show();
    } // end render() method

    public static int getWindowWidth() {
        return width * scale;
    }

    public static int getWindowHeight() {
        return height * scale;
    }

    private void update() {
        key.update();
        level.update();
    } // end update() method

    // The main starts here
    public static void main(String args[]) {
        Game game = new Game();
        game.frame.setResizable(false);
        game.frame.setTitle(Game.title);
        game.frame.add(game);
        game.frame.pack();
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.frame.setLocationRelativeTo(null);
        game.frame.setVisible(true);
        game.start(); // start the game at last
    } // end main() method


    public void appendToOutput(String string) {
        chatFrame.appendToOutput(string);
    }
} // end the whole class Game
