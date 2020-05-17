package io.github.vampirestudios.raa.generation.dimensions;

import io.github.vampirestudios.cab.api.AstralBodyModifier;
import io.github.vampirestudios.raa.generation.dimensions.data.DimensionData;
import io.github.vampirestudios.raa.utils.Color;
import io.github.vampirestudios.raa.utils.Utils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;

import java.util.Set;

public class CustomDimension extends Dimension implements AstralBodyModifier {

    private final DimensionType dimensionType;
    private final DimensionData dimensionData;
    private final Set<Biome> biomeSet;
    private final Block stoneBlock;

    public CustomDimension(World world, DimensionType dimensionType, DimensionData data, Set<Biome> biomeSet, Block stoneBlock) {
        super(world, dimensionType, 0.0F);
        this.dimensionType = dimensionType;
        this.dimensionData = data;
        this.biomeSet = biomeSet;
        this.stoneBlock = stoneBlock;
    }

//    @Override
//    public ChunkGenerator createChunkGenerator() {
//        CaveCavityCarver.setSeed(world.getSeed());
//        return this.dimensionData.getDimensionChunkGenerator().getChunkGenerator(this.world, RandomlyAddingAnything.DIMENSIONAL_BIOMES.
//                applyConfig(new DimensionalBiomeSourceConfig(this.world.getLevelProperties()).setBiomes(biomeSet)), this.dimensionData, this.stoneBlock);
//    }

    @Override
    public float getSkyAngle(long timeOfDay, float tickDelta) {
        if (!Utils.checkBitFlag(dimensionData.getFlags(), Utils.LUCID) && dimensionData.getCustomSkyInformation().hasSky()) {
            double fractionalPart = MathHelper.fractionalPart((double) timeOfDay / 24000.0D - 0.25D);
            double v1 = 0.5D - Math.cos(fractionalPart * 3.141592653589793D) / 2.0D;
            return (float) (fractionalPart * 2.0D + v1) / 3.0F;
        } else {
            return 0.0F;
        }
    }

    @Environment(EnvType.CLIENT)
    public float[] getBackgroundColor(float skyAngle, float tickDelta) {
        return null;
    }

    @Override
    public boolean hasVisibleSky() {
        return !Utils.checkBitFlag(dimensionData.getFlags(), Utils.LUCID) && dimensionData.getCustomSkyInformation().hasSky();
    }

//    @Override
//    public boolean hasGround() {
//        return !dimensionData.getDimensionChunkGenerator().equals(DimensionChunkGenerators.FLOATING) &&
//                !dimensionData.getDimensionChunkGenerator().equals(DimensionChunkGenerators.LAYERED_FLOATING) &&
//                !dimensionData.getDimensionChunkGenerator().equals(DimensionChunkGenerators.PRE_CLASSIC_FLOATING);
//    }

//    @Override
//    public float getCloudHeight() {
//        return dimensionData.getCloudHeight();
//    }

//    @Override
//    @Environment(EnvType.CLIENT)
//    public Vec3d modifyFogColor(Vec3d fogColor, float tickDelta) {
//        if (Utils.checkBitFlag(dimensionData.getFlags(), Utils.LUCID)) {
//            return fogColor.multiply(0.15000000596046448D);
//        }
//        int fogColor2 = dimensionData.getDimensionColorPalette().getFogColor();
//        int[] rgbColor = Color.intToRgb(fogColor2);
//        return new Vec3d(rgbColor[0] / 255.0, rgbColor[1] / 255.0, rgbColor[2] / 255.0);
//    }

    @Override
    public boolean canPlayersSleep() {
        return dimensionData.canSleep();
    }

//    @Override
//    public boolean doesWaterVaporize() {
//        return dimensionData.doesWaterVaporize();
//    }



//    @Override
//    @Environment(EnvType.CLIENT)
//    public boolean isFogThick(int x, int z) {
//        return dimensionData.hasThickFog();
//    }

    @Override
    public DimensionType getType() {
        return dimensionType;
    }

    @Override
    public BlockPos getSpawningBlockInChunk(long l, ChunkPos pos, boolean checkMobSpawnValidity) {
        for (int startX = pos.getStartX(); startX <= pos.getEndX(); ++startX) {
            for (int startZ = pos.getStartZ(); startZ <= pos.getEndZ(); ++startZ) {
                BlockPos topSpawningBlockPosition = this.getTopSpawningBlockPosition(l, startX, startZ, checkMobSpawnValidity);
                if (topSpawningBlockPosition != null) {
                    return topSpawningBlockPosition;
                }
            }
        }
        return null;
    }

    @Override
    public BlockPos getTopSpawningBlockPosition(long l, int x, int z, boolean checkMobSpawnValidity) {
        BlockPos.Mutable mutable = new BlockPos.Mutable(x, 0, z);
        Biome biome = this.world.getBiomeAccess().getBiome(mutable);
        BlockState topMaterial = biome.getSurfaceConfig().getTopMaterial();
        if (checkMobSpawnValidity && !topMaterial.getBlock().isIn(BlockTags.VALID_SPAWN)) {
            return null;
        } else {
            Chunk chunk = this.world.getChunk(x >> 4, z >> 4);
            int heightmap = chunk.sampleHeightmap(Heightmap.Type.MOTION_BLOCKING, x & 15, z & 15);
            if (heightmap < 0) {
                return null;
            } else if (chunk.sampleHeightmap(Heightmap.Type.WORLD_SURFACE, x & 15, z & 15) > chunk.sampleHeightmap(Heightmap.Type.OCEAN_FLOOR, x & 15, z & 15)) {
                return null;
            } else {
                for (int i = heightmap + 1; i >= 0; --i) {
                    mutable.set(x, i, z);
                    BlockState blockState = this.world.getBlockState(mutable);
                    if (!blockState.getFluidState().isEmpty()) {
                        break;
                    }

                    if (blockState.equals(topMaterial)) {
                        return mutable.up().toImmutable();
                    }
                }

                return null;
            }
        }
    }

    public Block getStoneBlock() {
        return stoneBlock;
    }

    public DimensionData getDimensionData() {
        return dimensionData;
    }

    @Override
    @Environment(EnvType.CLIENT)
    public float getSunSize() {
        return dimensionData.getCustomSkyInformation().getSunSize();
    }

    @Override
    @Environment(EnvType.CLIENT)
    public float getMoonSize() {
        return dimensionData.getCustomSkyInformation().getMoonSize();
    }

    @Override
    @Environment(EnvType.CLIENT)
    public Vector3f getSunTint() {
        float fogColor2 = dimensionData.getCustomSkyInformation().getSunTint();
        float[] rgbColor = Color.floatToRgb(fogColor2);
        return new Vector3f(rgbColor[0], rgbColor[1], rgbColor[2]);
    }

    @Override
    @Environment(EnvType.CLIENT)
    public Vector3f getMoonTint() {
        float fogColor2 = dimensionData.getCustomSkyInformation().getMoonTint();
        float[] rgbColor = Color.floatToRgb(fogColor2);
        return new Vector3f(rgbColor[0], rgbColor[1], rgbColor[2]);
    }

    @Override
    public Identifier getSunTexture() {
        return dimensionData.getTexturesInformation().getSunTexture();
    }

    @Override
    public Identifier getMoonTexture() {
        return dimensionData.getTexturesInformation().getMoonTexture();
    }

    @Override
    public boolean hasCustomAstralBody() {
        return true;
    }

}