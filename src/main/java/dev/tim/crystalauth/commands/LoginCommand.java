package dev.tim.crystalauth.commands;

import dev.tim.crystalauth.CrystalAuth;
import dev.tim.crystalauth.util.BungeeCordUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import static dev.tim.crystalauth.util.ChatUtil.translate;

public class LoginCommand implements CommandExecutor {

    private final CrystalAuth plugin;

    public LoginCommand(CrystalAuth plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player)){
            return true;
        }

        Player player = (Player) sender;
        String uuid = player.getUniqueId().toString();
        YamlConfiguration accountsConfig = plugin.getAccountManager().getAccountsConfig();
        FileConfiguration config = plugin.getConfig();

        if(!accountsConfig.contains(uuid)){
            player.sendMessage(translate(config.getString("not-registered")));
            return true;
        }

        if(args.length != 1){
            player.sendMessage(translate(config.getString("login-usage")));
            return true;
        }

        if(!accountsConfig.getString(uuid + ".password").equals(args[0])){
            player.kickPlayer(translate(config.getString("incorrect-password")));
            return true;
        }

        player.sendMessage(translate(config.getString("successfully-logged-in")));

        BungeeCordUtil.connectToServer(plugin, player, config.getString("bungeecord-server-names.lobby-server"));

        return true;
    }
}
