package dev.tim.crystalauth.util;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import dev.tim.crystalauth.CrystalAuth;
import org.bukkit.entity.Player;

public class BungeeCordUtil {

    public static void connectToServer(CrystalAuth plugin, Player player, String serverName){
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(serverName);
        player.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
    }

}
