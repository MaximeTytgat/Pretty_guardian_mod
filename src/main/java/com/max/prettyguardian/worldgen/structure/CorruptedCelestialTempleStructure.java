package com.max.prettyguardian.worldgen.structure;

import com.max.prettyguardian.util.ModStructureUtils;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.QuartPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pools.JigsawPlacement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.pools.alias.PoolAliasLookup;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public class CorruptedCelestialTempleStructure extends Structure {

    public static final MapCodec<CorruptedCelestialTempleStructure> CODEC = RecordCodecBuilder.<CorruptedCelestialTempleStructure>mapCodec(instance ->
            instance.group(Structure.settingsCodec(instance),
                    StructureTemplatePool.CODEC.fieldOf("start_pool").forGetter(structure -> structure.startPool),
                    ResourceLocation.CODEC.optionalFieldOf("start_jigsaw_name").forGetter(structure -> structure.startJigsawName),
                    Codec.intRange(0, 30).fieldOf("size").forGetter(structure -> structure.size),
                    HeightProvider.CODEC.fieldOf("start_height").forGetter(structure -> structure.startHeight),
                    Heightmap.Types.CODEC.optionalFieldOf("project_start_to_heightmap").forGetter(structure -> structure.projectStartToHeightmap),
                    Codec.intRange(1, 128).fieldOf("max_distance_from_center").forGetter(structure -> structure.maxDistanceFromCenter)
            ).apply(instance, CorruptedCelestialTempleStructure::new));

    private final Holder<StructureTemplatePool> startPool;
    private final Optional<ResourceLocation> startJigsawName;
    private final int size;
    private final HeightProvider startHeight;
    private final Optional<Heightmap.Types> projectStartToHeightmap;
    private final int maxDistanceFromCenter;

    public CorruptedCelestialTempleStructure(
            Structure.StructureSettings config,
            Holder<StructureTemplatePool> startPool,
            Optional<ResourceLocation> startJigsawName,
            int size,
            HeightProvider startHeight,
            Optional<Heightmap.Types> projectStartToHeightmap,
            int maxDistanceFromCenter
    ) {
        super(config);
        this.startPool = startPool;
        this.startJigsawName = startJigsawName;
        this.size = size;
        this.startHeight = startHeight;
        this.projectStartToHeightmap = projectStartToHeightmap;
        this.maxDistanceFromCenter = maxDistanceFromCenter;
    }

    @Override
    public GenerationStep.@NotNull Decoration step() {
        return GenerationStep.Decoration.SURFACE_STRUCTURES;
    }

    private static boolean checkLocation(Structure.GenerationContext context) {
        BlockPos blockpos = context.chunkPos().getMiddleBlockPosition(0);
        NoiseColumn blockReader = context.chunkGenerator().getBaseColumn(blockpos.getX(), blockpos.getZ(), context.heightAccessor(), context.randomState());
        return checkChunk(context)
                && !ModStructureUtils.isLavaLake(blockReader)
                && ModStructureUtils.verticalSpace(blockReader, 34, ModStructureUtils.getScaledNetherHeight(72), 24);
    }

    private static boolean checkChunk(Structure.GenerationContext context) {
        return context.validBiome().test(context.chunkGenerator().getBiomeSource().getNoiseBiome(QuartPos.fromBlock(context.chunkPos().getMiddleBlockX()), QuartPos.fromBlock(64), QuartPos.fromBlock(context.chunkPos().getMiddleBlockZ()), context.randomState().sampler()));
    }

    @Override
    public @NotNull Optional<Structure.GenerationStub> findGenerationPoint(@NotNull Structure.GenerationContext context) {
        if(!checkLocation(context)) {
            return Optional.empty();
        }

        BlockPos blockpos = ModStructureUtils.getElevation(context, 34, ModStructureUtils.getScaledNetherHeight(72));
        return JigsawPlacement.addPieces(context,
                this.startPool,
                this.startJigsawName,
                this.size,
                blockpos,
                false,
                this.projectStartToHeightmap,
                this.maxDistanceFromCenter,
                PoolAliasLookup.create(List.of(), blockpos, this.size)
        );
    }

    @Override
    public @NotNull StructureType<?> type() {
        return ModStructures.CORRUPTED_CELESTIAL_TEMPLE.get();
    }
}