package de.simonsator.partyandfriends.datadeleter.advancedban;

import de.simonsator.partyandfriends.utilities.Language;
import de.simonsator.partyandfriends.utilities.LanguageConfiguration;
import net.md_5.bungee.api.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class ABDeleterMessages extends LanguageConfiguration {

	protected ABDeleterMessages(File file, Plugin pPlugin) throws IOException {
		super(Language.ENGLISH, file, pPlugin);
		copyFromJar();
		readFile();
		saveFile();
		process(configuration);
	}
}
