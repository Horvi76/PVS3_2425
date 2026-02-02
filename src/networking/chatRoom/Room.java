package networking.chatRoom;

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

    public String getName() {
        return name;
    }

    public boolean isLobby() {
        return isLobby;
    }

    public void addMember(ClientHandler session){
        members.add(session);
    }

    public void removeMember(ClientHandler session){
        members.remove(session);
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
