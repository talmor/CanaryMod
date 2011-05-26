import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.minecraft.server.MinecraftServer;

/**
 * Player.java - Interface for eo so mods don't have to update often.
 * 
 * @author James
 */
public class Player extends HumanEntity implements MessageReceiver {

    private static final Logger log                = Logger.getLogger("Minecraft");
    private int                 id                 = -1;
    private String              prefix             = "";
    private String[]            commands           = new String[] { "" };
    private ArrayList<String>   groups             = new ArrayList<String>();
    private String[]            ips                = new String[] { "" };
    private boolean             ignoreRestrictions = false;
    private boolean             admin              = false;
    private boolean             canModifyWorld     = false;
    private boolean             muted              = false;
    private PlayerInventory     inventory;
    private List<String>        onlyOneUseKits     = new ArrayList<String>();
    private Pattern             badChatPattern     = Pattern.compile("[^ !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ\\[\\\\\\]^_'abcdefghijklmnopqrstuvwxyz{|}~\u2302\u00C7\u00FC\u00E9\u00E2\u00E4\u00E0\u00E5\u00E7\u00EA\u00EB\u00E8\u00EF\u00EE\u00EC\u00C4\u00C5\u00C9\u00E6\u00C6\u00F4\u00F6\u00F2\u00FB\u00F9\u00FF\u00D6\u00DC\u00F8\u00A3\u00D8\u00D7\u0192\u00E1\u00ED\u00F3\u00FA\u00F1\u00D1\u00AA\u00BA\u00BF\u00AE\u00AC\u00BD\u00BC\u00A1\u00AB\u00BB]");

    /**
     * Creates an empty player. Add the player by calling {@link #setUser(es)}
     */
    public Player() {
    }

    public Player(OEntityPlayerMP player) {
        setUser(player);
    }

    /**
     * Returns the entity we're wrapping.
     * 
     * @return
     */
    public OEntityPlayerMP getEntity() {
        return (OEntityPlayerMP) entity;
    }

    /**
     * Returns if the player is still connected
     * 
     * @return
     */
    public boolean isConnected() {
        return !getEntity().a.c;
    }

    /**
     * Kicks player with the specified reason
     * 
     * @param reason
     */
    public void kick(String reason) {
        getEntity().a.a(reason);
    }

    /**
     * Sends player a notification
     * 
     * @param message
     */
    public void notify(String message) {
        if (message.length() > 0)
            sendMessage(Colors.Rose + message);
    }

    /**
     * Sends a message to the player
     * 
     * @param message
     */
    public void sendMessage(String message) {
        getEntity().a.msg(message);
    }

    /**
     * Gives an item to the player
     * 
     * @param item
     */
    public void giveItem(Item item) {
        inventory.addItem(item);
        inventory.update();
    }

    /**
     * Makes player send message.
     * 
     * @param message
     */
    public void chat(String message) {
        if (message.length() > 100) {
            kick("Chat message too long");
            return;
        }
        message = message.trim();
        Matcher m = badChatPattern.matcher(message);
        if (m.find()) {
            kick("Illegal characters '" + m.group() + "' hex: " + Integer.toHexString(message.charAt(m.start())) + " in chat");
            return;
        }
        if (message.startsWith("/"))
            command(message);
        else {
            if (isMuted()) {
                sendMessage(Colors.Rose + "You are currently muted.");
                return;
            }
            if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.CHAT, new Object[] { this, message }))
                return;

            String chat = "<" + getColor() + getName() + Colors.White + "> " + message;
            log.log(Level.INFO, "<" + getName() + "> " + message);
            etc.getServer().messageAll(chat);
        }
    }

    /**
     * Makes player use command.
     *
     * TODO: redo this in the same way as the server commands.
     * @param command
     * 
     */
    public void command(String command) {
        try {
            if (etc.getInstance().isLogging())
                log.info("Command used by " + getName() + " " + command);

            String[] split = command.split(" ");
            String cmd = split[0];

            if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.COMMAND, this, split))
                return; // No need to go on, commands were parsed.
            if (!canUseCommand(cmd) && !cmd.startsWith("/#")) {
                if (etc.getInstance().showUnknownCommand())
                    sendMessage(Colors.Rose + "Unknown command.");
                return;
            } else if ((command.startsWith("/#")) && (etc.getMCServer().f.h(getName()))) {
                String str = command.substring(2);
                log.info(getName() + " issued server command: " + str);
                etc.getMCServer().a(str, getEntity().a);
            }

            // Remove '/' before checking.
            if (!ServerConsoleCommands.parseServerConsoleCommand(this, cmd.substring(1), split)
                    && !PlayerCommands.parsePlayerCommand(this, cmd.substring(1), split)) {
                log.info(getName() + " tried command " + command);
                if (etc.getInstance().showUnknownCommand())
                    sendMessage(Colors.Rose + "Unknown command");
            }

        } catch (Throwable ex) { // Might as well try and catch big exceptions
            // before the server crashes from a stack
            // overflow or something
            log.log(Level.SEVERE, "Exception in command handler (Report this on github unless you did something dumb like enter letters as numbers):", ex);
            if (isAdmin())
                sendMessage(Colors.Rose + "Exception occured. Check the server for more info.");
        }
    }

    /**
     * Gives an item to the player
     * 
     * @param itemId
     * @param amount
     */
    public void giveItem(int itemId, int amount) {
        inventory.giveItem(itemId, amount);
        inventory.update();
    }

    /**
     * Gives the player this item by dropping it in front of them
     * 
     * @param item
     */
    public void giveItemDrop(Item item) {
        giveItemDrop(item.getItemId(), item.getAmount());
    }

    /**
     * Gives the player this item by dropping it in front of them
     * 
     * @param itemId
     * @param amount
     */
    public void giveItemDrop(int itemId, int amount) {
        OEntityPlayerMP player = getEntity();
        if (amount == -1)
            player.b(new OItemStack(itemId, 255, 0));
        else {
            int temp = amount;
            do {
                if (temp - 64 >= 64)
                    player.b(new OItemStack(itemId, 64, 0));
                else
                    player.b(new OItemStack(itemId, temp, 0));
                temp -= 64;
            } while (temp > 0);
        }
    }

    /**
     * Returns true if this player can use the specified command
     * 
     * @param command
     * @return
     */
    public boolean canUseCommand(String command) {
        for (String str : commands)
            if (str.equalsIgnoreCase(command))
                return true;

        for (String str : groups) {
            Group g = etc.getDataSource().getGroup(str);
            if (g != null)
                if (recursiveUseCommand(g, command))
                    return true;
        }

        if (hasNoGroups()) {
            Group def = etc.getInstance().getDefaultGroup();
            if (def != null)
                if (recursiveUseCommand(def, command))
                    return true;
        }

        return false;
    }

    private boolean recursiveUseCommand(Group g, String command) {
        for (String str : g.Commands)
            if (str.equalsIgnoreCase(command) || str.equals("*"))
                return true;

        if (g.InheritedGroups != null)
            for (String str : g.InheritedGroups) {
                Group g2 = etc.getDataSource().getGroup(str);
                if (g2 != null)
                    if (recursiveUseCommand(g2, command))
                        return true;
            }
        return false;
    }

    /**
     * Checks to see if this player is in the specified group
     * 
     * @param group
     * @return
     */
    public boolean isInGroup(String group) {
        if (group != null)
            if (etc.getInstance().getDefaultGroup() != null)
                if (group.equalsIgnoreCase(etc.getInstance().getDefaultGroup().Name))
                    return true;
        for (String str : groups)
            if (recursiveUserInGroup(etc.getDataSource().getGroup(str), group))
                return true;
        return false;
    }

    private boolean recursiveUserInGroup(Group g, String group) {
        if (g == null || group == null)
            return false;

        if (g.Name.equalsIgnoreCase(group))
            return true;

        if (g.InheritedGroups != null)
            for (String str : g.InheritedGroups) {
                if (g.Name.equalsIgnoreCase(str))
                    return true;

                Group g2 = etc.getDataSource().getGroup(str);
                if (g2 != null)
                    if (recursiveUserInGroup(g2, group))
                        return true;
            }
        return false;
    }

    /**
     * Returns true if this player has control over the other player
     * 
     * @param player
     * @return true if player has control
     */
    public boolean hasControlOver(Player player) {
        boolean isInGroup = false;

        if (player.hasNoGroups())
            return true;
        for (String str : player.getGroups()) {
            if (isInGroup(str))
                isInGroup = true;
            else
                continue;
            break;
        }

        return isInGroup;
    }

    /**
     * Returns the player's current location
     * 
     * @return
     */
    public Location getLocation() {
        Location loc = new Location();
        loc.x = getX();
        loc.y = getY();
        loc.z = getZ();
        loc.rotX = getRotation();
        loc.rotY = getPitch();
        return loc;
    }

    /**
     * Returns the IP of this player
     * 
     * @return
     */
    public String getIP() {
        return getEntity().a.b.b().toString().split(":")[0].substring(1);
    }

    /**
     * Returns true if this player is an admin.
     * 
     * @return
     */
    public boolean isAdmin() {
        if (admin)
            return true;

        for (String str : groups) {
            Group group = etc.getDataSource().getGroup(str);
            if (group != null)
                if (group.Administrator)
                    return true;
        }
        return false;
    }

    /**
     * Don't use this! Use isAdmin
     * 
     * @return
     */
    public boolean getAdmin() {
        return admin;
    }

    /**
     * Sets whether or not this player is an administrator
     * 
     * @param admin
     */
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    /**
     * Returns false if this player can not modify terrain, edit chests, etc.
     * 
     * @return
     */
    public boolean canBuild() {
        if (canModifyWorld)
            return true;

        for (String str : groups) {
            Group group = etc.getDataSource().getGroup(str);
            if (group != null)
                if (group.CanModifyWorld)
                    return true;
        }

        if (hasNoGroups())
            if (etc.getInstance().getDefaultGroup().CanModifyWorld)
                return true;

        return false;
    }

    /**
     * Don't use this, use canBuild()
     * 
     * @return
     */
    public boolean canModifyWorld() {
        return canModifyWorld;
    }

    /**
     * Sets whether or not this player can modify the world terrain
     * 
     * @param canModifyWorld
     */
    public void setCanModifyWorld(boolean canModifyWorld) {
        this.canModifyWorld = canModifyWorld;
    }

    /**
     * Set allowed commands
     * 
     * @return
     */
    public String[] getCommands() {
        return commands;
    }

    /**
     * Sets this player's allowed commands
     * 
     * @param commands
     */
    public void setCommands(String[] commands) {
        this.commands = commands;
    }

    /**
     * Returns this player's groups
     * 
     * @return
     */
    public String[] getGroups() {
        String[] strGroups = new String[groups.size()];
        groups.toArray(strGroups);
        return strGroups;
    }

    /**
     * Sets this player's groups
     * 
     * @param groups
     */
    public void setGroups(String[] groups) {
        this.groups.clear();
        for (String s : groups)
            if (s.length() > 0)
                this.groups.add(s);
    }

    /**
     * Adds the player to the specified group
     * 
     * @param group
     *            group to add player to
     */
    public void addGroup(String group) {
        groups.add(group);
    }

    /**
     * Removes specified group from list of groups
     * 
     * @param group
     *            group to remove
     */
    public void removeGroup(String group) {
        groups.remove(group);
    }

    /**
     * Returns the sql ID.
     * 
     * @return
     */
    public int getSqlId() {
        return id;
    }

    /**
     * Sets the sql ID. Don't touch this.
     * 
     * @param id
     */
    public void setSqlId(int id) {
        this.id = id;
    }

    /**
     * If the user can ignore restrictions this will return true. Things like
     * item amounts and such are unlimited, etc.
     * 
     * @return
     */
    public boolean canIgnoreRestrictions() {
        if (admin || ignoreRestrictions)
            return true;

        for (String str : groups) {
            Group group = etc.getDataSource().getGroup(str);
            if (group != null)
                if (group.Administrator || group.IgnoreRestrictions)
                    return true;
        }
        return false;
    }

    /**
     * Don't use. Use canIgnoreRestrictions()
     * 
     * @return
     */
    public boolean ignoreRestrictions() {
        return ignoreRestrictions;
    }

    /**
     * Sets ignore restrictions
     * 
     * @param ignoreRestrictions
     */
    public void setIgnoreRestrictions(boolean ignoreRestrictions) {
        this.ignoreRestrictions = ignoreRestrictions;
    }

    /**
     * Returns allowed IPs
     * 
     * @return
     */
    public String[] getIps() {
        return ips;
    }

    /**
     * Sets allowed IPs
     * 
     * @param ips
     */
    public void setIps(String[] ips) {
        this.ips = ips;
    }

    /**
     * Returns the correct color/prefix for this player
     * 
     * @return
     */
    public String getColor() {
        if (prefix != null)
            if (!prefix.equals(""))
                return "§" + prefix;
        if (groups.size() > 0) {
            Group group = etc.getDataSource().getGroup(groups.get(0));
            if (group != null)
                return "§" + group.Prefix;
        }
        Group def = etc.getInstance().getDefaultGroup();
        return def != null ? "§" + def.Prefix : "";
    }

    /**
     * Returns the prefix. NOTE: Don't use this, use getColor() instead.
     * 
     * @return
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * Sets the prefix
     * 
     * @param prefix
     */
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    /**
     * Gets the actual user class.
     * 
     * @return
     */
    public OEntityPlayerMP getUser() {
        return getEntity();
    }

    /**
     * Sets the user. Don't use this.
     * 
     * @param er
     */
    public void setUser(OEntityPlayerMP player) {
        entity = player;
        inventory = new PlayerInventory(this);
    }

    public void teleportTo(double x, double y, double z, float rotation, float pitch) {
        OEntityPlayerMP player = getEntity();

        // If player is in vehicle - eject them before they are teleported.
        if (player.aF != null)
            player.b(player.aF);
        player.a.a(x, y, z, rotation, pitch);
    }

    /**
     * Returns true if the player is muted
     * 
     * @return
     */
    public boolean isMuted() {
        return muted;
    }

    /**
     * Toggles mute
     * 
     * @return
     */
    public boolean toggleMute() {
        muted = !muted;
        return muted;
    }

    /**
     * Checks to see if this player is in any groups
     * 
     * @return true if this player is in any group
     */
    public boolean hasNoGroups() {
        if (groups.isEmpty())
            return true;
        if (groups.size() == 1)
            return groups.get(0).equals("");
        return false;
    }

    /**
     * Returns item id in player's hand
     * 
     * @return
     */
    public int getItemInHand() {
        return getEntity().a.getItemInHand();
    }

    /**
     * Returns this player's inventory
     * 
     * @return inventory
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Returns whether or not this Player is currently sneaking (crouching)
     * 
     * @return true if sneaking
     */
    public boolean getSneaking() {
        return getEntity().h;
    }

    /**
     * Force this Player to be sneaking or not
     * 
     * @param sneaking
     *            true if sneaking
     */
    public void setSneaking(boolean sneaking) {
        getEntity().h = sneaking;
    }

    /**
     * Returns the one-use only kits
     */
    public List<String> getOnlyOneUseKits() {
        return new ArrayList(onlyOneUseKits);
    }

    /**
     * Returns a String representation of this Player
     * 
     * @return String representation of this Player
     */
    @Override
    public String toString() {
        return String.format("Player[id=%d, name=%s]", id, getName());
    }

    /**
     * Tests the given object to see if it equals this object
     * 
     * @param obj
     *            the object to test
     * @return true if the two objects match
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Player other = (Player) obj;
        return getName().equals(other.getName());
    }

    /**
     * Returns a unique hashcode for this Player
     * 
     * @return hashcode
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + id;
        return hash;
    }
}
