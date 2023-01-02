package net.fml42.antichatemojis.mixin;

import net.minecraft.client.network.ClientPlayNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {

	private static final String[] EMOJIS = {
			":)",";)", ":(",";(", ":D", "xD", "XD", "xP", "XP", ":|", ":p", ":P", ":/", "D:", "<3",
			//":o", ":O", ":0", ":>", ":<",
	};
	private static final String DECORATOR = "\u200c";
	private static final String WORDS_SEPARATOR = " ";

	@ModifyVariable(at = @At("HEAD"), method = "sendChatMessage(Ljava/lang/String;)V", ordinal = 0)
	private String init(String content) {
		//AntiChatEmojis.LOGGER.info("chat message: "+content);

		//remove links
		String[] words = content.split(WORDS_SEPARATOR);
		for (int i = 0; i<words.length; i++) {
			boolean isLink = words[i].startsWith("http");

			if (!isLink) {
				//break links
				words[i] = words[i].replace(".", DECORATOR+".");

				//break emojis
				for (String emoji: EMOJIS) {
					if (words[i].contains(emoji)) {
						String emojiDecorated = decorate(emoji);
						words[i] = words[i].replace(emoji, emojiDecorated);
					}
				}
			}
		}

		return String.join(WORDS_SEPARATOR, words);
	}

	private String decorate(String str) {
		String[] chars = str.split("");
		return String.join(DECORATOR, chars);
	}


}
