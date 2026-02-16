package networking.chatRoom;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Room {
//    int capacity; // future possibility
    private String name;
    private final boolean isLobby;
    private final Set<ClientHandler> members = ConcurrentHashMap.newKeySet();

    public Room(String name, boolean isLobby) {
        this.name = name;
        this.isLobby = isLobby;
    }

    public List<ClientHandler> getMembers() {
        return new ArrayList<>(members);
    }

    public String getName() {
        return name;
    }

    public boolean isLobby() {
        return isLobby;
    }

    public void addMember(ClientHandler session){
        if (members.add(session))
            broadCastChat("SYS_INFO", session.getClientID() + " se pripojil");
        System.out.println("Pocet klientu v room je " + members.size());
    }

    public void removeMember(ClientHandler session){
        if (members.remove(session))
            broadCastChat("SYS_INFO", session.getClientID() + " se odpojil");
        System.out.println("Pocet klientu v room je " + members.size());
    }

    public boolean isEmpty(){
        return members.isEmpty();
    }

    public void broadCastChat(String userID, String message){
        String line = "[" + name + "] " + userID  + ": " + message;
        for (ClientHandler member : members){
            member.send(line);
        }
    }
}
