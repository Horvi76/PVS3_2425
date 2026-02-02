package networking.chatRoom;

public final class CommandRouter {

    private CommandRouter() {}

    public static void handleCommand(ClientHandler client, String input) {
        ParsedCommand cmd = parse(input);
        switch (cmd.name()) {
            case "help"   -> cmdHelp(client);
            case "msg"    -> cmdMsg(client, cmd.arg());
            case "list"   -> cmdList(client);
            case "create" -> cmdCreate(client, cmd.arg());
            case "join"   -> cmdJoin(client, cmd.arg());
            case "leave"  -> cmdLeave(client);
            case "where"  -> cmdWhere(client);
            case "quit"   -> cmdQuit(client);
            default       -> cmdUnknown(client, cmd.name());
        }
    }

    private static void cmdHelp(ClientHandler client) {
        client.send("Available commands:");
        client.send("  /help              - show this help");
        client.send("  /msg <text>        - send chat message to current room");
        client.send("  /list              - list available rooms");
        client.send("  /create <room>     - create a new room");
        client.send("  /join <room>       - join an existing room");
        client.send("  /leave             - leave current room and return to lobby");
        client.send("  /where             - show current room");
        client.send("  /quit              - disconnect");
        client.send("");
        client.send("Notes:");
        client.send("  Chat messages must always be sent using /msg.");
    }

    private static void cmdMsg(ClientHandler client, String text) {
        // TODO: broadcast message to current room
    }

    private static void cmdList(ClientHandler client) {
        //format: ["r1", "r2",....]
        client.send(RoomManager.ROOM_MANAGER.listRoomNames().toString());
    }

    private static void cmdCreate(ClientHandler client, String room) {
        client.send(RoomManager.ROOM_MANAGER.createRoom(room) ? "Room created" : "Could not create room");
        cmdJoin(client, room);
    }

    private static void cmdJoin(ClientHandler client, String room) {
        client.send(RoomManager.ROOM_MANAGER.joinRoom(client, room) ? "Joined room" : "Could not join room");
    }

    private static void cmdLeave(ClientHandler client) {
        if (RoomManager.ROOM_MANAGER.joinRoom(client, RoomManager.LOBBY_ROOM_NAME)){
            client.send("Back in lobby");
        } else {
            client.send("Already in lobby");
        }
    }

    private static void cmdWhere(ClientHandler client) {
        client.send(RoomManager.ROOM_MANAGER.getCurrentRoom(client));
    }

    private static void cmdQuit(ClientHandler client) {
        // TODO
    }

    private static void cmdUnknown(ClientHandler client, String cmd) {
       client.send("Unknown command, type /help for reference");
    }

    private static ParsedCommand parse(String line){
        String trimmed = line.trim();

        int space = trimmed.indexOf(' ');
        if (space < 0){
            return new ParsedCommand(trimmed.substring(1).toLowerCase(), null);
        }

        String name = trimmed.substring(1, space).toLowerCase();
        String args = trimmed.substring(space + 1);
        if (args.isEmpty()) args = null;
        return new ParsedCommand(name, args);
    }

    private record ParsedCommand(String name, String arg){}
}
