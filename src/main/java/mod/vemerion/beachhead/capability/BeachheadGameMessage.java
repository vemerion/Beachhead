package mod.vemerion.beachhead.capability;

import java.util.function.Supplier;

import mod.vemerion.beachhead.Beachhead;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class BeachheadGameMessage {
	private static final String PROTOCOL_VERSION = "1";
	public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
			new ResourceLocation(Beachhead.MODID, "main"), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals,
			PROTOCOL_VERSION::equals);

	private int score;
	private int turtleMadeItTimer;
	private int cycleOfLifeTimer;

	public BeachheadGameMessage(int score, int turtleSavedTimer, int cycleOfLifeTimer) {
		this.score = score;
		this.turtleMadeItTimer = turtleSavedTimer;
		this.cycleOfLifeTimer = cycleOfLifeTimer;
	}

	public static void encode(final BeachheadGameMessage msg, final PacketBuffer buffer) {
		buffer.writeInt(msg.score);
		buffer.writeInt(msg.turtleMadeItTimer);
		buffer.writeInt(msg.cycleOfLifeTimer);
	}

	public static BeachheadGameMessage decode(final PacketBuffer buffer) {
		return new BeachheadGameMessage(buffer.readInt(), buffer.readInt(), buffer.readInt());
	}

	public static void handle(final BeachheadGameMessage msg, final Supplier<NetworkEvent.Context> supplier) {
		final NetworkEvent.Context context = supplier.get();
		context.setPacketHandled(true);
		context.enqueueWork(() -> DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
			ClientWorld world = Minecraft.getInstance().world;
			BeachheadGame game = world.getCapability(Beachhead.BEACHHEADGAME_CAP)
					.orElseThrow(() -> new IllegalArgumentException("LazyOptional cannot be empty!"));
			game.setScore(msg.score);
			game.setTurtleMadeItTimer(msg.turtleMadeItTimer);
			game.setCycleOfLifeTimer(msg.cycleOfLifeTimer);
		}));
	}
}
