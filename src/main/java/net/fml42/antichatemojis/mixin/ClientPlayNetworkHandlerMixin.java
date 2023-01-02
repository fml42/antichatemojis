package net.fml42.antichatemojis.mixin;

import net.minecraft.client.network.ClientPlayNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {

	private static final String[] EMOJIS = {
			":)",";)", ":(",";(", ":D", "xD", "XD", "xP", "XP", ":|", ":p", ":P", ":/", "D:", "<3"
	};
	private static final String DECORATOR = "\u200c";

	@ModifyVariable(at = @At("HEAD"), method = "sendChatMessage(Ljava/lang/String;)V", ordinal = 0)
	private String init(String content) {
		//AntiChatEmojis.LOGGER.info("chat message: "+content);

		for (String emoji: EMOJIS) {
			if (content.contains(emoji)) {
				String emojiDecorated = decorate(emoji);
				content = content.replace(emoji, emojiDecorated);
			}
		}

		return content;
	}

	private String decorate(String str) {
		String[] chars = str.split("");
		return String.join(DECORATOR, chars);
	}


}
