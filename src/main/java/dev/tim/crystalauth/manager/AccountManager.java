package dev.tim.crystalauth.manager;

import dev.tim.crystalauth.CrystalAuth;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class AccountManager {

    private final CrystalAuth plugin;

    private YamlConfiguration accountsConfig;
    private File accountsFile;

    public AccountManager(CrystalAuth plugin){
        this.plugin = plugin;
    }

    public void loadAccountsFile(){
        accountsFile = new File(plugin.getDataFolder().getAbsolutePath() + "/accounts.yml");
        if(!accountsFile.exists()){
            try {
                accountsFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("Cant create accounts.yml file!");
            }
        }
        accountsConfig = YamlConfiguration.loadConfiguration(accountsFile);
    }

    public void saveAccountsFile(){
        try {
            accountsConfig.save(accountsFile);
        } catch (IOException e) {
            throw new RuntimeException("Cant save accounts.yml file!");
        }
    }

    public YamlConfiguration getAccountsConfig() {
        return accountsConfig;
    }
}
