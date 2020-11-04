package io.github.vampirestudios.raa.api;

import net.minecraft.world.IWorld;

import java.util.Random;

public interface TerrainPostProcessor {
	/**
	 * This function executes on creation of the chunk generator and is useful for setting up noise functions and randoms.
	 *
	 * @param seed the seed used to initialize noise functions and randoms.
	 */
	void init(long seed);

	/**
	 * This function executes during setup time, and is useful for configs.
	 */
	void setup();

	/**
	 * This function executes for every chunk being generated.
	 */
	void process(IWorld world, Random rand, int chunkX, int chunkZ, Heightmap heightmap);

	/**
	 * Returns the specified time for the post processor to be run.
	 */
	default PostProcessorTarget getTarget() {
		return PostProcessorTarget.FEATURES;
	}
}