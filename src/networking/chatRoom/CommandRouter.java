package networking.chatRoom;

public final class CommandRouter {

    private CommandRouter() {}

    public static void handleCommand(ClientHandler client, String input) {

       // ParsedCommand cmd = parse(input);

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
        // TODO
    }

    private static void cmdCreate(ClientHandler client, String room) {
        // TODO
    }

    private static void cmdJoin(ClientHandler client, String room) {
        // TODO
    }

    private static void cmdLeave(ClientHandler client) {
        // TODO
    }

    private static void cmdWhere(ClientHandler client) {
        // TODO
    }

    private static void cmdQuit(ClientHandler client) {
        // TODO
    }

    private static void cmdUnknown(ClientHandler client, String cmd) {
        // TODO
    }

}
