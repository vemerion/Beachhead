package mod.vemerion.beachhead;

import net.minecraft.block.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.provider.BiomeProviderType;
import net.minecraft.world.biome.provider.SingleBiomeProviderSettings;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.ChunkGeneratorType;
import net.minecraft.world.gen.FlatChunkGenerator;
import net.minecraft.world.gen.FlatGenerationSettings;
import net.minecraft.world.gen.FlatLayerInfo;

public class BeachheadWorldType extends WorldType {

	public BeachheadWorldType() {
		super("beachhead");
	}

	@Override
	public ChunkGenerator<?> createChunkGenerator(World world) {
		FlatChunkGenerator generator = new FlatChunkGenerator(world, BiomeProviderType.FIXED.create(new SingleBiomeProviderSettings(world.getWorldInfo())), getGenerator());
		return generator;
	}

	private static FlatGenerationSettings getGenerator() {
		FlatGenerationSettings flatgenerationsettings = ChunkGeneratorType.FLAT.createSettings();
		flatgenerationsettings.setBiome(Biomes.PLAINS);
		flatgenerationsettings.getFlatLayers().add(new FlatLayerInfo(1, Blocks.BEDROCK));
		flatgenerationsettings.getFlatLayers().add(new FlatLayerInfo(5, Blocks.WATER));
		flatgenerationsettings.updateLayers();
		return flatgenerationsettings;
	}
}
