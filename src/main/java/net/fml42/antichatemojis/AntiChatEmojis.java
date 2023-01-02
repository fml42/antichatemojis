package net.fml42.antichatemojis;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AntiChatEmojis implements ModInitializer {
	public static final String MOD_ID = "antichatemojis";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info(MOD_ID + " initialized");
	}
}
