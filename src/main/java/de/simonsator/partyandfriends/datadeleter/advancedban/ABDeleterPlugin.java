package de.simonsator.partyandfriends.datadeleter.advancedban;

import de.simonsator.partyandfriends.api.PAFExtension;
import de.simonsator.partyandfriends.api.events.message.SimpleMessageEvent;
import de.simonsator.partyandfriends.api.pafplayers.PAFPlayerManager;
import de.simonsator.partyandfriends.main.Main;
import me.leoko.advancedban.bungee.event.PunishmentEvent;
import me.leoko.advancedban.manager.PunishmentManager;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.io.File;
import java.io.IOException;

public class ABDeleterPlugin extends PAFExtension implements Listener {
	private String mutedMessage;

	@Override
	public void onEnable() {
		try {
			mutedMessage = new ABDeleterMessages(new File(getConfigFolder(), "messages.yml"), this).getString("YouAreMuted");
			registerAsExtension();
			getAdapter().registerListener(this, this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@EventHandler
	public void onBan(PunishmentEvent pEvent) {
		if (pEvent.getPunishment().getDuration(true).equals("permanent")) {
			PAFPlayerManager.getInstance().getPlayer(pEvent.getPunishment().getUuid()).deleteAccount();
		}
	}

	@EventHandler
	public void onPAFChat(SimpleMessageEvent pEvent) {
		if (PunishmentManager.get().isMuted(pEvent.getSender().getUniqueId().toString())) {
			pEvent.setCancelled(true);
			pEvent.getSender().sendMessage(mutedMessage);
		}
	}
}
