package networking.chatRoom;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class RoomManager {
    private final Room lobby = new Room("lobby", true);


    //seznam vsech rooms
    private final ConcurrentMap<String, Room> rooms = new ConcurrentHashMap<>();


}
