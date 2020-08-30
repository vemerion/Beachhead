package mod.vemerion.beachhead;

import java.util.List;
import java.util.Random;

import mod.vemerion.beachhead.capability.BeachheadGame;
import mod.vemerion.beachhead.capability.BeachheadGameMessage;
import mod.vemerion.beachhead.capability.BeachheadGameProvider;
import mod.vemerion.beachhead.entities.BeachheadAlbatrossEntity;
import mod.vemerion.beachhead.entities.BeachheadCrabEntity;
import mod.vemerion.beachhead.entities.BeachheadTurtleEntity;
import mod.vemerion.beachhead.entities.HighlightPathEntity;
import mod.vemerion.beachhead.entities.IndicatorEntity;
import mod.vemerion.beachhead.entities.ScrollProjectileEntity;
import mod.vemerion.beachhead.entities.TargetEntity;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.TickEvent.WorldTickEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.network.PacketDistributor;

@EventBusSubscriber(modid = Beachhead.MODID, bus = EventBusSubscriber.Bus.FORGE)
public class ForgeEventSubscriber {

	public static final ResourceLocation BEACHHEADGAME_CAP = new ResourceLocation(Beachhead.MODID, "beachheadgame");

	@SubscribeEvent
	public static void attachCapability(AttachCapabilitiesEvent<World> event) {
		event.addCapability(BEACHHEADGAME_CAP, new BeachheadGameProvider());
	}

	@SubscribeEvent
	public static void preventMobSpawn(LivingSpawnEvent.CheckSpawn event) {
		if (event.getWorld().getWorld().getWorldType() instanceof BeachheadWorldType) {
			event.setResult(Result.DENY);
		}
	}

	@SubscribeEvent
	public static void beachheadTick(WorldTickEvent event) {
		World world = event.world;
		if (world.getWorldType() instanceof BeachheadWorldType && event.phase == Phase.START) {
			BeachheadGame game = world.getCapability(Beachhead.BEACHHEADGAME_CAP)
					.orElseThrow(() -> new IllegalArgumentException("LazyOptional cannot be empty!"));

			game.tick();
			spawnEntities(world, game);
		}
	}

	private static BlockPos[] albatrossSpawns = new BlockPos[] { new BlockPos(0, 10, 10), new BlockPos(-2, 10, 10),
			new BlockPos(-4, 10, 10), new BlockPos(2, 10, 10), new BlockPos(4, 10, 10) };
	private static BlockPos[] turtleSpawns = new BlockPos[] { new BlockPos(0, 6, 1), new BlockPos(-2, 6, 1),
			new BlockPos(-4, 6, 1), new BlockPos(2, 6, 1), new BlockPos(4, 6, 1) };
	private static BlockPos[] crabSpawns = new BlockPos[] { new BlockPos(5, 6, 6), new BlockPos(-5, 6, 7) };

	private static void spawnEntities(World world, BeachheadGame game) {
		Random rand = world.getRandom();
		if (game.getEnemyCooldown() < 0) {
			game.setEnemyCooldown(rand.nextInt(60) + 40);
			if (rand.nextBoolean()) {
				BeachheadAlbatrossEntity albatross = new BeachheadAlbatrossEntity(Beachhead.BEACHHEAD_ALBATROSS_ENTITY,
						world);
				BlockPos position = albatrossSpawns[rand.nextInt(albatrossSpawns.length)];
				albatross.moveToBlockPosAndAngles(position, 180, 0);
				world.addEntity(albatross);
			} else {
				BeachheadCrabEntity crab = new BeachheadCrabEntity(Beachhead.BEACHHEAD_CRAB_ENTITY, world);
				BlockPos position = crabSpawns[rand.nextInt(crabSpawns.length)];
				crab.moveToBlockPosAndAngles(position, position.getX() > 0 ? 90 : -90, 0);
				world.addEntity(crab);
			}
		}

		if (game.getTurtleCooldown() < 0) {
			game.setTurtleCooldown(rand.nextInt(65) + 50);
			BeachheadTurtleEntity turtle = new BeachheadTurtleEntity(Beachhead.BEACHHEAD_TURTLE_ENTITY, world);
			BlockPos position = turtleSpawns[rand.nextInt(turtleSpawns.length)];
			turtle.moveToBlockPosAndAngles(position, 0, 0);
			world.addEntity(turtle);
		}
	}

	@SubscribeEvent
	public static void initBeachhead(PlayerLoggedInEvent event) {
		PlayerEntity player = event.getPlayer();
		World world = player.world;
		if (world.getWorldType() instanceof BeachheadWorldType) {
			player.addItemStackToInventory(new ItemStack(Beachhead.SCROLL));
			BeachheadGame game = world.getCapability(Beachhead.BEACHHEADGAME_CAP)
					.orElseThrow(() -> new IllegalArgumentException("LazyOptional cannot be empty!"));
			BeachheadGameMessage.INSTANCE.send(PacketDistributor.ALL.noArg(), new BeachheadGameMessage(game.getScore(),
					game.getTurtleMadeItTimer(), game.getTurtleMadeItTimer()));
			addSand(world);
			addPlants(world);
		}
	}

	private static void addPlants(World world) {
		// Lily pads
		for (int i = 0; i < 20; i++) {
			int x = world.getRandom().nextInt(40) - 20;
			int z = world.getRandom().nextInt(40) - 10;
			if (x < -5 || x > 5 || z > 7)
				world.setBlockState(new BlockPos(x, 6, z), Blocks.LILY_PAD.getDefaultState());
		}

		// Turtle spawn positions
		for (int i = 0; i < 5; i++) {
			world.setBlockState(new BlockPos(-4 + i * 2, 6, 1), Blocks.GRASS.getDefaultState());
		}

	}

	private static void addSand(World world) {
		// Platform
		for (int x = -5; x < 6; x++) {
			for (int y = 1; y < 6; y++) {
				for (int z = -5; z < 8; z++) {
					world.setBlockState(new BlockPos(x, y, z), Blocks.SAND.getDefaultState());
					world.getClosestPlayer(1, 1, 1, 5, false);
				}
			}
		}
	}

	@SubscribeEvent
	public static void updatePlayerPositionAndRotation(PlayerTickEvent event) {
		if (event.player.world.getWorldType() instanceof BeachheadWorldType) {
			PlayerEntity player = event.player;
			float pitch = MathHelper.clamp(player.rotationPitch, -40, 90);
			float yaw = MathHelper.clamp(player.rotationYaw, -90, 90);
			player.setPositionAndRotation(0.5, 6.2, 0.5, yaw, pitch);
			player.prevPosX = player.getPosX();
			player.prevPosY = player.getPosY();
			player.prevPosZ = player.getPosZ();
			player.prevRotationPitch = player.rotationPitch;
			player.prevRotationYaw = player.rotationYaw;
		}
	}

	@SubscribeEvent
	public static void createHighlightPath(PlayerTickEvent event) {
		if (event.phase == Phase.START && event.side == LogicalSide.SERVER) {
			PlayerEntity player = event.player;
			if (player.getHeldItemMainhand().getItem().equals(Beachhead.SCROLL)) {
				if (player.world.getEntitiesInAABBexcluding(player, player.getBoundingBox().grow(2),
						(e) -> e instanceof IndicatorEntity).isEmpty()) {
					Vec3d playerPos = player.getPositionVec();
					float playerPitch = player.rotationPitch;
					float playerYaw = player.rotationYaw;
					for (float i = 0; i < 20; i += 0.7f) {
						Vec3d position = ScrollProjectileEntity.projectilePosition(i, playerPos, playerPitch,
								playerYaw);

						// Target found?
						List<Entity> entities = player.world.getEntitiesInAABBexcluding(player,
								new AxisAlignedBB(new BlockPos(position)).grow(0.10),
								(e) -> e instanceof BeachheadCrabEntity || e instanceof BeachheadAlbatrossEntity);
						if (!player.world.isAirBlock(new BlockPos(position)) || !entities.isEmpty()) {
							TargetEntity target = new TargetEntity(player.world, player);
							if (!entities.isEmpty())
								position = new Vec3d(position.getX(), entities.get(0).getPosY(), position.getZ());
							target.setPositionAndRotation(position.getX(), position.getY(), position.getZ(),
									player.rotationYaw, 0);

							player.world.addEntity(target);
							break;
						} else {
							HighlightPathEntity path = new HighlightPathEntity(player.world, player);
							Vec3d nextPos = ScrollProjectileEntity.projectilePosition(i + 0.5f, playerPos, playerPitch,
									playerYaw);
							float pitch = ScrollProjectileEntity.projectilePitch(position, nextPos, playerPitch);
							path.setPositionAndRotation(position.getX(), position.getY(), position.getZ(),
									player.rotationYaw, pitch);

							player.world.addEntity(path);

						}

					}
				}
			}
		}
	}
}
