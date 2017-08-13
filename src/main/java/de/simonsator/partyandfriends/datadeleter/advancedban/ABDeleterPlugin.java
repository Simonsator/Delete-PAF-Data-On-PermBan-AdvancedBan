package de.simonsator.partyandfriends.datadeleter.advancedban;

import de.simonsator.partyandfriends.api.PAFExtension;
import de.simonsator.partyandfriends.api.pafplayers.PAFPlayer;
import de.simonsator.partyandfriends.api.pafplayers.PAFPlayerManager;
import de.simonsator.partyandfriends.main.Main;
import me.leoko.advancedban.bungee.event.PunishmentEvent;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

/**
 * @author Simonsator
 * @version 1.0.0 13.08.17
 */
public class ABDeleterPlugin extends PAFExtension implements Listener {

	@Override
	public void onEnable() {
		Main.getInstance().registerExtension(this);
		ProxyServer.getInstance().getPluginManager().registerListener(this, this);
	}

	@EventHandler
	public void onBan(PunishmentEvent pEvent) {
		if (pEvent.getPunishment().getDuration(true).equals("permanent")) {
			PAFPlayer player = PAFPlayerManager.getInstance().getPlayer(pEvent.getPunishment().getUuid());
			player.deleteAccount();
		}
	}
}
