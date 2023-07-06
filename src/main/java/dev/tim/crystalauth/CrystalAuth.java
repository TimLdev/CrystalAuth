package dev.tim.crystalauth;

import dev.tim.crystalauth.commands.LoginCommand;
import dev.tim.crystalauth.commands.LogoutCommand;
import dev.tim.crystalauth.commands.RegisterCommand;
import dev.tim.crystalauth.manager.AccountManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class CrystalAuth extends JavaPlugin {

    private AccountManager accountManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        accountManager = new AccountManager(this);
        accountManager.loadAccountsFile();

        getCommand("register").setExecutor(new RegisterCommand(this));
        getCommand("login").setExecutor(new LoginCommand(this));
        getCommand("logout").setExecutor(new LogoutCommand(this));

        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
    }

    @Override
    public void onDisable() {
        Bukkit.getMessenger().unregisterOutgoingPluginChannel(this);
    }

    public AccountManager getAccountManager() {
        return accountManager;
    }
}
