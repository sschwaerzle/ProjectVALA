package de.hhnracing.client;

import com.sun.xml.internal.ws.api.message.Packet;
import javafx.application.Platform;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

public class ClientThread extends Thread {

    /*
    Wieso 2 Threads?
    Der erste Thread (ClientThread) sorgt im Prinzip nur dafür, dass Networking nicht im JFX Thread ausgeführt wird
    -> kein angehaltenes UI bei zB Timeout

    Der zweite Thread wartet dauerhaft auf eingehende Packete, das kann der ClientThread nicht machen,
    sonst ist er so lange blockiert, bis ein Packet eingegangen ist.
    Da ohne Request keine Answer kommt, wird im Prinzip sofort auch das absenden von Anfragen unmöglich
    -> der Socket macht gar nichts


    Exceptions:
    IOException wird bei den Streams geworfen, wenn der Socket geschlossen wurde (socket.isConnected() gibt true zurück, auch wenn er nur mal verbunden war)
    ClassNotFoundException, wenn die empfangene Klasse nicht existiert. Der Client sollte dadurch beendet werden, der Server aber nicht (Sabotage dadurch möglich).

    Für jeden Client braucht man serverseitig nur einen Thread, weil dieser nur nach dem System Anfrage -> sofortige Antwort arbeitet.
     */

    private final String host;
    private final int port;

    private final Queue<Packet> outgoingPackets = new LinkedList<>();
    private final Queue<Packet> incomingPackets = new LinkedList<>();

    private Socket server;

    private ObjectOutputStream out;
    private ObjectInputStream in;

    private Thread receiverThread;

    public ClientThread(String host, int port) {
        super("Memberlist Client-Thread");

        this.host = host;
        this.port = port;

        start();
    }

    @Override
    public void run() {
        while (!isInterrupted()){
            if(server.isConnected()){
                if(!incomingPackets.isEmpty()){
                    Platform.runLater(new PacketProcessor(incomingPackets.remove()));
                }

                if(!outgoingPackets.isEmpty()){
                    try {
                        out.writeObject(outgoingPackets.remove());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void connect() throws IOException {
        if(server != null){
            throw new IllegalStateException("Socket is already connected");
        }

        server = new Socket(host, port);
        out = new ObjectOutputStream(server.getOutputStream());
        in = new ObjectInputStream(server.getInputStream());

        receiverThread = new Thread(() -> {
            while (!isInterrupted()){
                try {
                    incomingPackets.add((Packet) in.readObject());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void sendPacket(Packet p){
        outgoingPackets.add(p);
    }

    private static class PacketProcessor implements Runnable{
        private final Packet packet;

        public PacketProcessor(Packet packet) {
            this.packet = packet;
        }

        @Override
        public void run() {
            //Ergebnisse berechnen
        }
    }
}
