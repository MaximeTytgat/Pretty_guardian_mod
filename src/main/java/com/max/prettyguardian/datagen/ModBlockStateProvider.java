package com.max.prettyguardian.datagen;

import com.max.prettyguardian.PrettyGuardian;
import com.max.prettyguardian.blocks.custom.crop.MintCropBlock;
import com.max.prettyguardian.blocks.custom.food.BaseCake;
import com.max.prettyguardian.blocks.custom.food.BaseThreeCake;
import com.max.prettyguardian.blocks.PrettyGuardianBlock;
import com.max.prettyguardian.blocks.custom.crop.StrawberryCropBlock;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Objects;
import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {
    private static final String CUTOUT = "cutout";
    private static final String BLOCK_PATH = "block/";
    private static final String TEXTURE_KEY = "texture";
    private static final String PARTICLE_KEY = "particle";
    private static final String TEXTURE_TAG = "#texture";

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, PrettyGuardian.MOD_ID, exFileHelper);
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    private void bottomTopBlockWithItem(Block block) {
        simpleBlockWithItem(block, models().cubeBottomTop(
                getBlockPath(block),
                blockTexture(block),
                new ResourceLocation(PrettyGuardian.MOD_ID, BLOCK_PATH + getBlockPath(block) + "_bottom"),
                new ResourceLocation(PrettyGuardian.MOD_ID, BLOCK_PATH + getBlockPath(block) + "_top")
        ));
    }

    private void topBlockWithItem(Block block) {
        simpleBlockWithItem(block, models().cubeTop(getBlockPath(block), blockTexture(block), new ResourceLocation(PrettyGuardian.MOD_ID, BLOCK_PATH + getBlockPath(block) + "_top")));
    }

    private ResourceLocation getBlockKey(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }

    private String getBlockPath(Block block) {
        return getBlockKey(block).getPath();
    }

    @Override
    protected void registerStatesAndModels() {
        makeStrawberryCrop((CropBlock) PrettyGuardianBlock.STRAWBERRY_CROP.get(), "strawberry_stage", "strawberry_stage");
        makeMintCrop((CropBlock) PrettyGuardianBlock.MINT_CROP.get(), "mint_stage", "mint_stage");

        blockWithItem(PrettyGuardianBlock.PINK_SAPPHIRE_BLOCK);
        blockWithItem(PrettyGuardianBlock.PINK_SAPPHIRE_ORE);
        blockWithItem(PrettyGuardianBlock.DEEPSLATE_PINK_SAPPHIRE_ORE);

        blockWithItem(PrettyGuardianBlock.RUBY_BLOCK);
        blockWithItem(PrettyGuardianBlock.RUBY_ORE);
        blockWithItem(PrettyGuardianBlock.DEEPSLATE_RUBY_ORE);

        blockWithItem(PrettyGuardianBlock.CHOCOLATE_BLOCK);
        topBlockWithItem(PrettyGuardianBlock.MARSHMELLO_BLOCK.get());
        bottomTopBlockWithItem(PrettyGuardianBlock.ROASTED_MARSHMELLO_BLOCK.get());

        simpleCake(PrettyGuardianBlock.CHOCOLATE_CAKE.get());
        simpleCake(PrettyGuardianBlock.CREAM_CAKE.get());
        simpleCake(PrettyGuardianBlock.RHUM_CAKE.get());
        simpleCake(PrettyGuardianBlock.STRAWBERRY_CAKE.get());
        simpleCake(PrettyGuardianBlock.BERRY_STRAWBERRY_CAKE.get());
        simpleCake(PrettyGuardianBlock.VELVET_CAKE.get());
        simpleCake(PrettyGuardianBlock.CREAM_STRAWBERRY_CAKE.get());
        simpleCake(PrettyGuardianBlock.STRAWBERRY_CHOCO_CAKE.get());

        simpleThreeCake(PrettyGuardianBlock.THREE_VELVET_CAKE.get());
        simpleThreeCake(PrettyGuardianBlock.THREE_CHOCO_CAKE.get());
        simpleThreeCake(PrettyGuardianBlock.THREE_STRAWBERRY_CAKE.get());
        simpleThreeCake(PrettyGuardianBlock.THREE_STRAWBERRY_CHOCO_CAKE.get());

        saplingBlock(PrettyGuardianBlock.PISTACHIO_SAPLING);
        saplingBlock(PrettyGuardianBlock.LEMON_SAPLING);
        saplingBlock(PrettyGuardianBlock.BOBA_SAPLING);

        simpleBlockWithItem(PrettyGuardianBlock.STRAWBERRY_CROP_FLOWER.get(), models().cross(blockTexture(PrettyGuardianBlock.STRAWBERRY_CROP_FLOWER.get()).getPath(),
                blockTexture(PrettyGuardianBlock.STRAWBERRY_CROP_FLOWER.get())).renderType(CUTOUT));

        simpleBlockWithItem(PrettyGuardianBlock.GEM_POLISHING_STATION.get(), new ModelFile.UncheckedModelFile(modLoc("block/gem_polishing_station")));
    }




    private void saplingBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(),
                models().cross(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get())).getPath(), blockTexture(blockRegistryObject.get())).renderType(CUTOUT));
    }

    public void makeStrawberryCrop(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> strawberryStates(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] strawberryStates(BlockState state, CropBlock block, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(((StrawberryCropBlock) block).getAgeProperty()),
                new ResourceLocation(PrettyGuardian.MOD_ID, BLOCK_PATH + textureName + state.getValue(((StrawberryCropBlock) block).getAgeProperty()))).renderType(CUTOUT));

        return models;
    }


    public void makeMintCrop(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> mintStates(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] mintStates(BlockState state, CropBlock block, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(((MintCropBlock) block).getAgeProperty()),
                new ResourceLocation(PrettyGuardian.MOD_ID, BLOCK_PATH + textureName + state.getValue(((MintCropBlock) block).getAgeProperty()))).renderType(CUTOUT));

        return models;
    }

    private void simpleCake(Block block) {
        getVariantBuilder(block)
                .partialState().with(BaseCake.BITES, 0).addModels(new ConfiguredModel(models().getBuilder(getBlockPath(block))
                        .texture(TEXTURE_KEY, blockTexture(block))
                        .texture(PARTICLE_KEY, blockTexture(block)).element()
                        .from(1, 0, 1).to(15, 7, 15)
                        .face(Direction.NORTH).uvs(8.5F, 1F, 15.5F, 5F).texture(TEXTURE_TAG).end()
                        .face(Direction.EAST).uvs(8.5F, 1F, 15.5F, 5F).texture(TEXTURE_TAG).end()
                        .face(Direction.SOUTH).uvs(8.5F, 1F, 15.5F, 5F).texture(TEXTURE_TAG).end()
                        .face(Direction.WEST).uvs(8.5F, 1F, 15.5F, 5F).texture(TEXTURE_TAG).end()
                        .face(Direction.UP).uvs(0.5F, 0.5F, 7.5F, 7.5F).texture(TEXTURE_TAG).end()
                        .face(Direction.DOWN).uvs(0.5F, 8.5F, 7.5F, 15.5F).texture(TEXTURE_TAG).end()
                        .end()))
                .partialState().with(BaseCake.BITES, 1).addModels(new ConfiguredModel(models().getBuilder(getBlockPath(block) + "_slice1")
                        .texture(TEXTURE_KEY, blockTexture(block))
                        .texture(PARTICLE_KEY, blockTexture(block)).element()
                        .from(3, 0, 1).to(15, 7, 15)
                        .face(Direction.NORTH).uvs(8.5F, 1F, 14.5F, 5F).texture(TEXTURE_TAG).end()
                        .face(Direction.EAST).uvs(8.5F, 1F, 15.5F, 5F).texture(TEXTURE_TAG).end()
                        .face(Direction.SOUTH).uvs(9.5F, 1F, 15.5F, 5F).texture(TEXTURE_TAG).end()
                        .face(Direction.WEST).uvs(8.5F, 11F, 15.5F, 15F).texture(TEXTURE_TAG).end()
                        .face(Direction.UP).uvs(1.5F, 0.5F, 7.5F, 7.5F).texture(TEXTURE_TAG).end()
                        .face(Direction.DOWN).uvs(0.5F, 8.5F, 7.5F, 15.5F).texture(TEXTURE_TAG).end()
                        .end()))
                .partialState().with(BaseCake.BITES, 2).addModels(new ConfiguredModel(models().getBuilder(getBlockPath(block) + "_slice2")
                        .texture(TEXTURE_KEY, blockTexture(block))
                        .texture(PARTICLE_KEY, blockTexture(block))
                        .element()
                        .from(5, 0, 1).to(15, 7, 15)
                        .face(Direction.NORTH).uvs(8.5F, 1F, 13.5F, 5F).texture(TEXTURE_TAG).end()
                        .face(Direction.EAST).uvs(8.5F, 1F, 15.5F, 5F).texture(TEXTURE_TAG).end()
                        .face(Direction.SOUTH).uvs(10.5F, 1F, 15.5F, 5F).texture(TEXTURE_TAG).end()
                        .face(Direction.WEST).uvs(8.5F, 6F, 15.5F, 10F).texture(TEXTURE_TAG).end()
                        .face(Direction.UP).uvs(2.5F, 0.5F, 7.5F, 7.5F).texture(TEXTURE_TAG).end()
                        .face(Direction.DOWN).uvs(2.5F, 8.5F, 7.5F, 15.5F).texture(TEXTURE_TAG).end()
                        .end()))
                .partialState().with(BaseCake.BITES, 3).addModels(new ConfiguredModel(models().getBuilder(getBlockPath(block) + "_slice3")
                        .texture(TEXTURE_KEY, blockTexture(block))
                        .texture(PARTICLE_KEY, blockTexture(block))
                        .element()
                        .from(7, 0, 1).to(15, 7, 15)
                        .face(Direction.NORTH).uvs(8.5F, 1F, 12.5F, 5F).texture(TEXTURE_TAG).end()
                        .face(Direction.EAST).uvs(8.5F, 1F, 15.5F, 5F).texture(TEXTURE_TAG).end()
                        .face(Direction.SOUTH).uvs(11.5F, 1F, 15.5F, 5F).texture(TEXTURE_TAG).end()
                        .face(Direction.WEST).uvs(8.5F, 11F, 15.5F, 15F).texture(TEXTURE_TAG).end()
                        .face(Direction.UP).uvs(3.5F, 0.5F, 7.5F, 7.5F).texture(TEXTURE_TAG).end()
                        .face(Direction.DOWN).uvs(3.5F, 8.5F, 7.5F, 15.5F).texture(TEXTURE_TAG).end()
                        .end()))
                .partialState().with(BaseCake.BITES, 4).addModels(new ConfiguredModel(models().getBuilder(getBlockPath(block) + "_slice4")
                        .texture(TEXTURE_KEY, blockTexture(block))
                        .texture(PARTICLE_KEY, blockTexture(block))
                        .element()
                        .from(9, 0, 1).to(15, 7, 15)
                        .face(Direction.NORTH).uvs(8.5F, 1F, 11.5F, 5F).texture(TEXTURE_TAG).end()
                        .face(Direction.EAST).uvs(8.5F, 1F, 15.5F, 5F).texture(TEXTURE_TAG).end()
                        .face(Direction.SOUTH).uvs(12.5F, 1F, 15.5F, 5F).texture(TEXTURE_TAG).end()
                        .face(Direction.WEST).uvs(8.5F, 11F, 15.5F, 15F).texture(TEXTURE_TAG).end()
                        .face(Direction.UP).uvs(4.5F, 0.5F, 7.5F, 7.5F).texture(TEXTURE_TAG).end()
                        .face(Direction.DOWN).uvs(4.5F, 8.5F, 7.5F, 15.5F).texture(TEXTURE_TAG).end()
                        .end()))
                .partialState().with(BaseCake.BITES, 5).addModels(new ConfiguredModel(models().getBuilder(getBlockPath(block) + "_slice5")
                        .texture(TEXTURE_KEY, blockTexture(block))
                        .texture(PARTICLE_KEY, blockTexture(block))
                        .element()
                        .from(11, 0, 1).to(15, 7, 15)
                        .face(Direction.NORTH).uvs(8.5F, 1F, 10.5F, 5F).texture(TEXTURE_TAG).end()
                        .face(Direction.EAST).uvs(8.5F, 1F, 15.5F, 5F).texture(TEXTURE_TAG).end()
                        .face(Direction.SOUTH).uvs(13.5F, 1F, 15.5F, 5F).texture(TEXTURE_TAG).end()
                        .face(Direction.WEST).uvs(8.5F, 6F, 15.5F, 10F).texture(TEXTURE_TAG).end()
                        .face(Direction.UP).uvs(5.5F, 0.5F, 7.5F, 7.5F).texture(TEXTURE_TAG).end()
                        .face(Direction.DOWN).uvs(5.5F, 8.5F, 7.5F, 15.5F).texture(TEXTURE_TAG).end()
                        .end()))
                .partialState().with(BaseCake.BITES, 6).addModels(new ConfiguredModel(models().getBuilder(getBlockPath(block) + "_slice6")
                        .texture(TEXTURE_KEY, blockTexture(block))
                        .texture(PARTICLE_KEY, blockTexture(block))
                        .element()
                        .from(13, 0, 1).to(15, 7, 15)
                        .face(Direction.NORTH).uvs(8.5F, 1F, 9.5F, 5F).texture(TEXTURE_TAG).end()
                        .face(Direction.EAST).uvs(8.5F, 1F, 15.5F, 5F).texture(TEXTURE_TAG).end()
                        .face(Direction.SOUTH).uvs(14.5F, 1F, 15.5F, 5F).texture(TEXTURE_TAG).end()
                        .face(Direction.WEST).uvs(8.5F, 11F, 15.5F, 15F).texture(TEXTURE_TAG).end()
                        .face(Direction.UP).uvs(6.5F, 0.5F, 7.5F, 7.5F).texture(TEXTURE_TAG).end()
                        .face(Direction.DOWN).uvs(6.5F, 8.5F, 7.5F, 15.5F).texture(TEXTURE_TAG).end()
                        .end()));
    }

    private void simpleThreeCake(Block block) {
        getVariantBuilder(block)
                .partialState().with(BaseThreeCake.BITES, 0).addModels(new ConfiguredModel(models().getBuilder(getBlockPath(block))
                        .texture(TEXTURE_KEY, blockTexture(block))
                        .texture(PARTICLE_KEY, blockTexture(block))
                        .element()
                        .from(1, 0, 1).to(15, 8, 15)
                        .face(Direction.NORTH).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture(TEXTURE_TAG).end()
                        .face(Direction.EAST).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture(TEXTURE_TAG).end()
                        .face(Direction.SOUTH).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture(TEXTURE_TAG).end()
                        .face(Direction.WEST).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture(TEXTURE_TAG).end()
                        .face(Direction.UP).uvs(5.84615F, 11.69231F, 10.15385F, 16F).texture(TEXTURE_TAG).end()
                        .face(Direction.DOWN).uvs(0.92308F, 11.69231F, 5.23077F, 16F).texture(TEXTURE_TAG).end()
                        .end()
                        .element()
                        .from(2, 8, 2).to(14, 15, 14)
                        .face(Direction.NORTH).uvs(1.23077F, 6.76923F, 4.92308F, 8.92308F).texture(TEXTURE_TAG).end()
                        .face(Direction.EAST).uvs(1.23077F, 6.76923F, 4.92308F, 8.92308F).texture(TEXTURE_TAG).end()
                        .face(Direction.SOUTH).uvs(1.23077F, 6.76923F, 4.92308F, 8.92308F).texture(TEXTURE_TAG).end()
                        .face(Direction.WEST).uvs(1.23077F, 6.76923F, 4.92308F, 8.92308F).texture(TEXTURE_TAG).end()
                        .face(Direction.UP).uvs(11.07692F, 12F, 14.76923F, 15.69231F).texture(TEXTURE_TAG).end()
                        .face(Direction.DOWN).uvs(5.84615F, 11.69231F, 10.15385F, 16F).texture(TEXTURE_TAG).end()
                        .end()
                        .element()
                        .from(3, 15, 3).to(13, 21, 13)
                        .face(Direction.NORTH).uvs(1.53846F, 4.92308F, 4.61538F, 6.76923F).texture(TEXTURE_TAG).end()
                        .face(Direction.EAST).uvs(1.53846F, 4.92308F, 4.61538F, 6.76923F).texture(TEXTURE_TAG).end()
                        .face(Direction.SOUTH).uvs(1.53846F, 4.92308F, 4.61538F, 6.76923F).texture(TEXTURE_TAG).end()
                        .face(Direction.WEST).uvs(1.53846F, 4.92308F, 4.61538F, 6.76923F).texture(TEXTURE_TAG).end()
                        .face(Direction.UP).uvs(0.92308F, 0.30769F, 5.17949F, 4.56411F).texture(TEXTURE_TAG).end()
                        .face(Direction.DOWN).uvs(5.84615F, 11.69231F, 10.15385F, 16F).texture(TEXTURE_TAG).end()
                        .end()))
                .partialState().with(BaseThreeCake.BITES, 1).addModels(new ConfiguredModel(models().getBuilder(getBlockPath(block) + "_slice1")
                        .texture(TEXTURE_KEY, blockTexture(block))
                        .texture(PARTICLE_KEY, blockTexture(block))
                        .element()
                        .from(1, 0, 1).to(15, 8, 15)
                        .face(Direction.NORTH).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture(TEXTURE_TAG).end()
                        .face(Direction.EAST).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture(TEXTURE_TAG).end()
                        .face(Direction.SOUTH).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture(TEXTURE_TAG).end()
                        .face(Direction.WEST).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture(TEXTURE_TAG).end()
                        .face(Direction.UP).uvs(5.84615F, 11.69231F, 10.15385F, 16F).texture(TEXTURE_TAG).end()
                        .face(Direction.DOWN).uvs(0.92308F, 11.69231F, 5.23077F, 16F).texture(TEXTURE_TAG).end()
                        .end()
                        .element()
                        .from(2, 8, 2).to(14, 15, 14)
                        .face(Direction.NORTH).uvs(1.23077F, 6.76923F, 4.92308F, 8.92308F).texture(TEXTURE_TAG).end()
                        .face(Direction.EAST).uvs(1.23077F, 6.76923F, 4.92308F, 8.92308F).texture(TEXTURE_TAG).end()
                        .face(Direction.SOUTH).uvs(1.23077F, 6.76923F, 4.92308F, 8.92308F).texture(TEXTURE_TAG).end()
                        .face(Direction.WEST).uvs(1.23077F, 6.76923F, 4.92308F, 8.92308F).texture(TEXTURE_TAG).end()
                        .face(Direction.UP).uvs(11.07692F, 12F, 14.76923F, 15.69231F).texture(TEXTURE_TAG).end()
                        .face(Direction.DOWN).uvs(5.84615F, 11.69231F, 10.15385F, 16F).texture(TEXTURE_TAG).end()
                        .end()
                        .element()
                        .from(5, 15, 3).to(13, 21, 13)
                        .face(Direction.NORTH).uvs(1.53846F, 4.92308F, 4F, 6.76923F).texture(TEXTURE_TAG).end()
                        .face(Direction.EAST).uvs(1.53846F, 4.92308F, 4.61538F, 6.76923F).texture(TEXTURE_TAG).end()
                        .face(Direction.SOUTH).uvs(2.15385F, 4.92308F, 4.61538F, 6.76923F).texture(TEXTURE_TAG).end()
                        .face(Direction.WEST).uvs(6.46154F, 4.92308F, 9.53846F, 6.76923F).texture(TEXTURE_TAG).end()
                        .face(Direction.UP).uvs(1.84615F, 0.30769F, 5.17949F, 4.56411F).texture(TEXTURE_TAG).end()
                        .face(Direction.DOWN).uvs(5.84615F, 11.69231F, 10.15385F, 16F).texture(TEXTURE_TAG).end()
                        .end()))
                .partialState().with(BaseThreeCake.BITES, 2).addModels(new ConfiguredModel(models().getBuilder(getBlockPath(block) + "_slice2")
                        .texture(TEXTURE_KEY, blockTexture(block))
                        .texture(PARTICLE_KEY, blockTexture(block))
                        .element()
                        .from(1, 0, 1).to(15, 8, 15)
                        .face(Direction.NORTH).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture(TEXTURE_TAG).end()
                        .face(Direction.EAST).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture(TEXTURE_TAG).end()
                        .face(Direction.SOUTH).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture(TEXTURE_TAG).end()
                        .face(Direction.WEST).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture(TEXTURE_TAG).end()
                        .face(Direction.UP).uvs(5.84615F, 11.69231F, 10.15385F, 16F).texture(TEXTURE_TAG).end()
                        .face(Direction.DOWN).uvs(0.92308F, 11.69231F, 5.23077F, 16F).texture(TEXTURE_TAG).end()
                        .end()
                        .element()
                        .from(2, 8, 2).to(14, 15, 14)
                        .face(Direction.NORTH).uvs(1.23077F, 6.76923F, 4.92308F, 8.92308F).texture(TEXTURE_TAG).end()
                        .face(Direction.EAST).uvs(1.23077F, 6.76923F, 4.92308F, 8.92308F).texture(TEXTURE_TAG).end()
                        .face(Direction.SOUTH).uvs(1.23077F, 6.76923F, 4.92308F, 8.92308F).texture(TEXTURE_TAG).end()
                        .face(Direction.WEST).uvs(1.23077F, 6.76923F, 4.92308F, 8.92308F).texture(TEXTURE_TAG).end()
                        .face(Direction.UP).uvs(11.07692F, 12F, 14.76923F, 15.69231F).texture(TEXTURE_TAG).end()
                        .face(Direction.DOWN).uvs(5.84615F, 11.69231F, 10.15385F, 16F).texture(TEXTURE_TAG).end()
                        .end()
                        .element()
                        .from(7, 15, 3).to(13, 21, 13)
                        .face(Direction.NORTH).uvs(1.53846F, 4.92308F, 3.38462F, 6.76923F).texture(TEXTURE_TAG).end()
                        .face(Direction.EAST).uvs(1.53846F, 4.92308F, 4.61538F, 6.76923F).texture(TEXTURE_TAG).end()
                        .face(Direction.SOUTH).uvs(2.76923F, 4.92308F, 4.61538F, 6.76923F).texture(TEXTURE_TAG).end()
                        .face(Direction.WEST).uvs(6.46154F, 4.92308F, 9.53846F, 6.76923F).texture(TEXTURE_TAG).end()
                        .face(Direction.UP).uvs(2.76923F, 0.30769F, 5.17949F, 4.56411F).texture(TEXTURE_TAG).end()
                        .face(Direction.DOWN).uvs(5.84615F, 11.69231F, 10.15385F, 16F).texture(TEXTURE_TAG).end()
                        .end()))
                .partialState().with(BaseThreeCake.BITES, 3).addModels(new ConfiguredModel(models().getBuilder(getBlockPath(block) + "_slice3")
                        .texture(TEXTURE_KEY, blockTexture(block))
                        .texture(PARTICLE_KEY, blockTexture(block))
                        .element()
                        .from(1, 0, 1).to(15, 8, 15)
                        .face(Direction.NORTH).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture(TEXTURE_TAG).end()
                        .face(Direction.EAST).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture(TEXTURE_TAG).end()
                        .face(Direction.SOUTH).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture(TEXTURE_TAG).end()
                        .face(Direction.WEST).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture(TEXTURE_TAG).end()
                        .face(Direction.UP).uvs(5.84615F, 11.69231F, 10.15385F, 16F).texture(TEXTURE_TAG).end()
                        .face(Direction.DOWN).uvs(0.92308F, 11.69231F, 5.23077F, 16F).texture(TEXTURE_TAG).end()
                        .end()
                        .element()
                        .from(2, 8, 2).to(14, 15, 14)
                        .face(Direction.NORTH).uvs(1.23077F, 6.76923F, 4.92308F, 8.92308F).texture(TEXTURE_TAG).end()
                        .face(Direction.EAST).uvs(1.23077F, 6.76923F, 4.92308F, 8.92308F).texture(TEXTURE_TAG).end()
                        .face(Direction.SOUTH).uvs(1.23077F, 6.76923F, 4.92308F, 8.92308F).texture(TEXTURE_TAG).end()
                        .face(Direction.WEST).uvs(1.23077F, 6.76923F, 4.92308F, 8.92308F).texture(TEXTURE_TAG).end()
                        .face(Direction.UP).uvs(11.07692F, 12F, 14.76923F, 15.69231F).texture(TEXTURE_TAG).end()
                        .face(Direction.DOWN).uvs(5.84615F, 11.69231F, 10.15385F, 16F).texture(TEXTURE_TAG).end()
                        .end()
                        .element()
                        .from(9, 15, 3).to(13, 21, 13)
                        .face(Direction.NORTH).uvs(1.53846F, 4.92308F, 2.76923F, 6.76923F).texture(TEXTURE_TAG).end()
                        .face(Direction.EAST).uvs(1.53846F, 4.92308F, 4.61538F, 6.76923F).texture(TEXTURE_TAG).end()
                        .face(Direction.SOUTH).uvs(3.38462F, 4.92308F, 4.61538F, 6.76923F).texture(TEXTURE_TAG).end()
                        .face(Direction.WEST).uvs(11.38462F, 4.92308F, 14.46154F, 6.76923F).texture(TEXTURE_TAG).end()
                        .face(Direction.UP).uvs(3.38462F, 0.30769F, 5.17949F, 4.56411F).texture(TEXTURE_TAG).end()
                        .face(Direction.DOWN).uvs(5.84615F, 11.69231F, 10.15385F, 16F).texture(TEXTURE_TAG).end()
                        .end()))
                .partialState().with(BaseThreeCake.BITES, 4).addModels(new ConfiguredModel(models().getBuilder(getBlockPath(block) + "_slice4")
                        .texture(TEXTURE_KEY, blockTexture(block))
                        .texture(PARTICLE_KEY, blockTexture(block))
                        .element()
                        .from(1, 0, 1).to(15, 8, 15)
                        .face(Direction.NORTH).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture(TEXTURE_TAG).end()
                        .face(Direction.EAST).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture(TEXTURE_TAG).end()
                        .face(Direction.SOUTH).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture(TEXTURE_TAG).end()
                        .face(Direction.WEST).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture(TEXTURE_TAG).end()
                        .face(Direction.UP).uvs(5.84615F, 11.69231F, 10.15385F, 16F).texture(TEXTURE_TAG).end()
                        .face(Direction.DOWN).uvs(0.92308F, 11.69231F, 5.23077F, 16F).texture(TEXTURE_TAG).end()
                        .end()
                        .element()
                        .from(2, 8, 2).to(14, 15, 14)
                        .face(Direction.NORTH).uvs(1.23077F, 6.76923F, 4.92308F, 8.92308F).texture(TEXTURE_TAG).end()
                        .face(Direction.EAST).uvs(1.23077F, 6.76923F, 4.92308F, 8.92308F).texture(TEXTURE_TAG).end()
                        .face(Direction.SOUTH).uvs(1.23077F, 6.76923F, 4.92308F, 8.92308F).texture(TEXTURE_TAG).end()
                        .face(Direction.WEST).uvs(1.23077F, 6.76923F, 4.92308F, 8.92308F).texture(TEXTURE_TAG).end()
                        .face(Direction.UP).uvs(11.07692F, 12F, 14.76923F, 15.69231F).texture(TEXTURE_TAG).end()
                        .face(Direction.DOWN).uvs(5.84615F, 11.69231F, 10.15385F, 16F).texture(TEXTURE_TAG).end()
                        .end()
                        .element()
                        .from(11, 15, 3).to(13, 21, 13)
                        .face(Direction.NORTH).uvs(1.53846F, 4.92308F, 2.15385F, 6.76923F).texture(TEXTURE_TAG).end()
                        .face(Direction.EAST).uvs(1.53846F, 4.92308F, 4.61538F, 6.76923F).texture(TEXTURE_TAG).end()
                        .face(Direction.SOUTH).uvs(4F, 4.92308F, 4.61538F, 6.76923F).texture(TEXTURE_TAG).end()
                        .face(Direction.WEST).uvs(6.46154F, 4.92308F, 9.53846F, 6.76923F).texture(TEXTURE_TAG).end()
                        .face(Direction.UP).uvs(4.30769F, 0.30769F, 5.17949F, 4.56411F).texture(TEXTURE_TAG).end()
                        .face(Direction.DOWN).uvs(5.84615F, 11.69231F, 10.15385F, 16F).texture(TEXTURE_TAG).end()
                        .end()))
                .partialState().with(BaseThreeCake.BITES, 5).addModels(new ConfiguredModel(models().getBuilder(getBlockPath(block) + "_slice5")
                        .texture(TEXTURE_KEY, blockTexture(block))
                        .texture(PARTICLE_KEY, blockTexture(block))
                        .element()
                        .from(1, 0, 1).to(15, 8, 15)
                        .face(Direction.NORTH).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture(TEXTURE_TAG).end()
                        .face(Direction.EAST).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture(TEXTURE_TAG).end()
                        .face(Direction.SOUTH).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture(TEXTURE_TAG).end()
                        .face(Direction.WEST).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture(TEXTURE_TAG).end()
                        .face(Direction.UP).uvs(5.84615F, 11.69231F, 10.15385F, 16F).texture(TEXTURE_TAG).end()
                        .face(Direction.DOWN).uvs(0.92308F, 11.69231F, 5.23077F, 16F).texture(TEXTURE_TAG).end()
                        .end()
                        .element()
                        .from(2, 8, 2).to(14, 15, 14)
                        .face(Direction.NORTH).uvs(1.23077F, 6.76923F, 4.92308F, 8.92308F).texture(TEXTURE_TAG).end()
                        .face(Direction.EAST).uvs(1.23077F, 6.76923F, 4.92308F, 8.92308F).texture(TEXTURE_TAG).end()
                        .face(Direction.SOUTH).uvs(1.23077F, 6.76923F, 4.92308F, 8.92308F).texture(TEXTURE_TAG).end()
                        .face(Direction.WEST).uvs(1.23077F, 6.76923F, 4.92308F, 8.92308F).texture(TEXTURE_TAG).end()
                        .face(Direction.UP).uvs(11.07692F, 12F, 14.76923F, 15.69231F).texture(TEXTURE_TAG).end()
                        .face(Direction.DOWN).uvs(5.84615F, 11.69231F, 10.15385F, 16F).texture(TEXTURE_TAG).end()
                        .end()))
                .partialState().with(BaseThreeCake.BITES, 6).addModels(new ConfiguredModel(models().getBuilder(getBlockPath(block) + "_slice6")
                        .texture(TEXTURE_KEY, blockTexture(block))
                        .texture(PARTICLE_KEY, blockTexture(block))
                        .element()
                        .from(1, 0, 1).to(15, 8, 15)
                        .face(Direction.NORTH).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture(TEXTURE_TAG).end()
                        .face(Direction.EAST).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture(TEXTURE_TAG).end()
                        .face(Direction.SOUTH).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture(TEXTURE_TAG).end()
                        .face(Direction.WEST).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture(TEXTURE_TAG).end()
                        .face(Direction.UP).uvs(5.84615F, 11.69231F, 10.15385F, 16F).texture(TEXTURE_TAG).end()
                        .face(Direction.DOWN).uvs(0.92308F, 11.69231F, 5.23077F, 16F).texture(TEXTURE_TAG).end()
                        .end()
                        .element()
                        .from(4, 8, 2).to(14, 15, 14)
                        .face(Direction.NORTH).uvs(1.23077F, 6.76923F, 4.30769F, 8.92308F).texture(TEXTURE_TAG).end()
                        .face(Direction.EAST).uvs(1.23077F, 6.76923F, 4.92308F, 8.92308F).texture(TEXTURE_TAG).end()
                        .face(Direction.SOUTH).uvs(1.84615F, 6.76923F, 4.92308F, 8.92308F).texture(TEXTURE_TAG).end()
                        .face(Direction.WEST).uvs(6.15385F, 2.76923F, 9.84615F, 4.92308F).texture(TEXTURE_TAG).end()
                        .face(Direction.UP).uvs(11.69231F, 12F, 14.76923F, 15.69231F).texture(TEXTURE_TAG).end()
                        .face(Direction.DOWN).uvs(5.84615F, 11.69231F, 10.15385F, 16F).texture(TEXTURE_TAG).end()
                        .end()))
                .partialState().with(BaseThreeCake.BITES, 7).addModels(new ConfiguredModel(models().getBuilder(getBlockPath(block) + "_slice7")
                        .texture(TEXTURE_KEY, blockTexture(block))
                        .texture(PARTICLE_KEY, blockTexture(block))
                        .element()
                        .from(1, 0, 1).to(15, 8, 15)
                        .face(Direction.NORTH).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture(TEXTURE_TAG).end()
                        .face(Direction.EAST).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture(TEXTURE_TAG).end()
                        .face(Direction.SOUTH).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture(TEXTURE_TAG).end()
                        .face(Direction.WEST).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture(TEXTURE_TAG).end()
                        .face(Direction.UP).uvs(5.84615F, 11.69231F, 10.15385F, 16F).texture(TEXTURE_TAG).end()
                        .face(Direction.DOWN).uvs(0.92308F, 11.69231F, 5.23077F, 16F).texture(TEXTURE_TAG).end()
                        .end()
                        .element()
                        .from(6, 8, 2).to(14, 15, 14)
                        .face(Direction.NORTH).uvs(1.23077F, 6.76923F, 3.69231F, 8.92308F).texture(TEXTURE_TAG).end()
                        .face(Direction.EAST).uvs(1.23077F, 6.76923F, 4.92308F, 8.92308F).texture(TEXTURE_TAG).end()
                        .face(Direction.SOUTH).uvs(2.46154F, 6.76923F, 4.92308F, 8.92308F).texture(TEXTURE_TAG).end()
                        .face(Direction.WEST).uvs(6.15385F, 2.76923F, 9.84615F, 4.92308F).texture(TEXTURE_TAG).end()
                        .face(Direction.UP).uvs(12.30769F, 12F, 14.76923F, 15.69231F).texture(TEXTURE_TAG).end()
                        .face(Direction.DOWN).uvs(5.84615F, 11.69231F, 10.15385F, 16F).texture(TEXTURE_TAG).end()
                        .end()))
                .partialState().with(BaseThreeCake.BITES, 8).addModels(new ConfiguredModel(models().getBuilder(getBlockPath(block) + "_slice8")
                        .texture(TEXTURE_KEY, blockTexture(block))
                        .texture(PARTICLE_KEY, blockTexture(block))
                        .element()
                        .from(1, 0, 1).to(15, 8, 15)
                        .face(Direction.NORTH).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture(TEXTURE_TAG).end()
                        .face(Direction.EAST).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture(TEXTURE_TAG).end()
                        .face(Direction.SOUTH).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture(TEXTURE_TAG).end()
                        .face(Direction.WEST).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture(TEXTURE_TAG).end()
                        .face(Direction.UP).uvs(5.84615F, 11.69231F, 10.15385F, 16F).texture(TEXTURE_TAG).end()
                        .face(Direction.DOWN).uvs(0.92308F, 11.69231F, 5.23077F, 16F).texture(TEXTURE_TAG).end()
                        .end()
                        .element()
                        .from(8, 8, 2).to(14, 15, 14)
                        .face(Direction.NORTH).uvs(1.23077F, 6.76923F, 3.07692F, 8.92308F).texture(TEXTURE_TAG).end()
                        .face(Direction.EAST).uvs(1.23077F, 6.76923F, 4.92308F, 8.92308F).texture(TEXTURE_TAG).end()
                        .face(Direction.SOUTH).uvs(3.07692F, 6.76923F, 4.92308F, 8.92308F).texture(TEXTURE_TAG).end()
                        .face(Direction.WEST).uvs(6.15385F, 2.76923F, 9.84615F, 4.92308F).texture(TEXTURE_TAG).end()
                        .face(Direction.UP).uvs(12.92308F, 12F, 14.76923F, 15.69231F).texture(TEXTURE_TAG).end()
                        .face(Direction.DOWN).uvs(5.84615F, 11.69231F, 10.15385F, 16F).texture(TEXTURE_TAG).end()
                        .end()))
                .partialState().with(BaseThreeCake.BITES, 9).addModels(new ConfiguredModel(models().getBuilder(getBlockPath(block) + "_slice9")
                        .texture(TEXTURE_KEY, blockTexture(block))
                        .texture(PARTICLE_KEY, blockTexture(block))
                        .element()
                        .from(1, 0, 1).to(15, 8, 15)
                        .face(Direction.NORTH).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture(TEXTURE_TAG).end()
                        .face(Direction.EAST).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture(TEXTURE_TAG).end()
                        .face(Direction.SOUTH).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture(TEXTURE_TAG).end()
                        .face(Direction.WEST).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture(TEXTURE_TAG).end()
                        .face(Direction.UP).uvs(5.84615F, 11.69231F, 10.15385F, 16F).texture(TEXTURE_TAG).end()
                        .face(Direction.DOWN).uvs(0.92308F, 11.69231F, 5.23077F, 16F).texture(TEXTURE_TAG).end()
                        .end()
                        .element()
                        .from(10, 8, 2).to(14, 15, 14)
                        .face(Direction.NORTH).uvs(1.23077F, 6.76923F, 2.46154F, 8.92308F).texture(TEXTURE_TAG).end()
                        .face(Direction.EAST).uvs(1.23077F, 6.76923F, 4.92308F, 8.92308F).texture(TEXTURE_TAG).end()
                        .face(Direction.SOUTH).uvs(3.69231F, 6.76923F, 4.92308F, 8.92308F).texture(TEXTURE_TAG).end()
                        .face(Direction.WEST).uvs(6.15385F, 2.76923F, 9.84615F, 4.92308F).texture(TEXTURE_TAG).end()
                        .face(Direction.UP).uvs(13.53846F, 12F, 14.76923F, 15.69231F).texture(TEXTURE_TAG).end()
                        .face(Direction.DOWN).uvs(5.84615F, 11.69231F, 10.15385F, 16F).texture(TEXTURE_TAG).end()
                        .end()))
                .partialState().with(BaseThreeCake.BITES, 10).addModels(new ConfiguredModel(models().getBuilder(getBlockPath(block) + "_slice10")
                        .texture(TEXTURE_KEY, blockTexture(block))
                        .texture(PARTICLE_KEY, blockTexture(block))
                        .element()
                        .from(1, 0, 1).to(15, 8, 15)
                        .face(Direction.NORTH).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture(TEXTURE_TAG).end()
                        .face(Direction.EAST).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture(TEXTURE_TAG).end()
                        .face(Direction.SOUTH).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture(TEXTURE_TAG).end()
                        .face(Direction.WEST).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture(TEXTURE_TAG).end()
                        .face(Direction.UP).uvs(5.84615F, 11.69231F, 10.15385F, 16F).texture(TEXTURE_TAG).end()
                        .face(Direction.DOWN).uvs(0.92308F, 11.69231F, 5.23077F, 16F).texture(TEXTURE_TAG).end()
                        .end()))
                .partialState().with(BaseThreeCake.BITES, 11).addModels(new ConfiguredModel(models().getBuilder(getBlockPath(block) + "_slice11")
                        .texture(TEXTURE_KEY, blockTexture(block))
                        .texture(PARTICLE_KEY, blockTexture(block))
                        .element()
                        .from(3, 0, 1).to(15, 8, 15)
                        .face(Direction.NORTH).uvs(0.92308F, 8.92308F, 4.61538F, 11.38462F).texture(TEXTURE_TAG).end()
                        .face(Direction.EAST).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture(TEXTURE_TAG).end()
                        .face(Direction.SOUTH).uvs(1.53846F, 8.92308F, 5.23077F, 11.38462F).texture(TEXTURE_TAG).end()
                        .face(Direction.WEST).uvs(10.76923F, 2.46154F, 15.07692F, 4.92308F).texture(TEXTURE_TAG).end()
                        .face(Direction.UP).uvs(6.46154F, 11.69231F, 10.15385F, 16F).texture(TEXTURE_TAG).end()
                        .face(Direction.DOWN).uvs(1.53846F, 11.69231F, 5.23077F, 16F).texture(TEXTURE_TAG).end()
                        .end()))
                .partialState().with(BaseThreeCake.BITES, 12).addModels(new ConfiguredModel(models().getBuilder(getBlockPath(block) + "_slice12")
                        .texture(TEXTURE_KEY, blockTexture(block))
                        .texture(PARTICLE_KEY, blockTexture(block))
                        .element()
                        .from(5, 0, 1).to(15, 8, 15)
                        .face(Direction.NORTH).uvs(0.92308F, 8.92308F, 4F, 11.38462F).texture(TEXTURE_TAG).end()
                        .face(Direction.EAST).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture(TEXTURE_TAG).end()
                        .face(Direction.SOUTH).uvs(2.15385F, 8.92308F, 5.23077F, 11.38462F).texture(TEXTURE_TAG).end()
                        .face(Direction.WEST).uvs(10.76923F, 2.46154F, 15.07692F, 4.92308F).texture(TEXTURE_TAG).end()
                        .face(Direction.UP).uvs(7.07692F, 11.69231F, 10.15385F, 16F).texture(TEXTURE_TAG).end()
                        .face(Direction.DOWN).uvs(2.15385F, 11.69231F, 5.23077F, 16F).texture(TEXTURE_TAG).end()
                        .end()))
                .partialState().with(BaseThreeCake.BITES, 13).addModels(new ConfiguredModel(models().getBuilder(getBlockPath(block) + "_slice13")
                        .texture(TEXTURE_KEY, blockTexture(block))
                        .texture(PARTICLE_KEY, blockTexture(block))
                        .element()
                        .from(7, 0, 1).to(15, 8, 15)
                        .face(Direction.NORTH).uvs(0.92308F, 8.92308F, 3.38462F, 11.38462F).texture(TEXTURE_TAG).end()
                        .face(Direction.EAST).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture(TEXTURE_TAG).end()
                        .face(Direction.SOUTH).uvs(2.76923F, 8.92308F, 5.23077F, 11.38462F).texture(TEXTURE_TAG).end()
                        .face(Direction.WEST).uvs(10.76923F, 2.46154F, 15.07692F, 4.92308F).texture(TEXTURE_TAG).end()
                        .face(Direction.UP).uvs(7.69231F, 11.69231F, 10.15385F, 16F).texture(TEXTURE_TAG).end()
                        .face(Direction.DOWN).uvs(2.76923F, 11.69231F, 5.23077F, 16F).texture(TEXTURE_TAG).end()
                        .end()))
                .partialState().with(BaseThreeCake.BITES, 14).addModels(new ConfiguredModel(models().getBuilder(getBlockPath(block) + "_slice14")
                        .texture(TEXTURE_KEY, blockTexture(block))
                        .texture(PARTICLE_KEY, blockTexture(block))
                        .element()
                        .from(9, 0, 1).to(15, 8, 15)
                        .face(Direction.NORTH).uvs(0.92308F, 8.92308F, 2.76923F, 11.38462F).texture(TEXTURE_TAG).end()
                        .face(Direction.EAST).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture(TEXTURE_TAG).end()
                        .face(Direction.SOUTH).uvs(3.38462F, 8.92308F, 5.23077F, 11.38462F).texture(TEXTURE_TAG).end()
                        .face(Direction.WEST).uvs(10.76923F, 2.46154F, 15.07692F, 4.92308F).texture(TEXTURE_TAG).end()
                        .face(Direction.UP).uvs(8.30769F, 11.69231F, 10.15385F, 16F).texture(TEXTURE_TAG).end()
                        .face(Direction.DOWN).uvs(3.38462F, 11.69231F, 5.23077F, 16F).texture(TEXTURE_TAG).end()
                        .end()))
                .partialState().with(BaseThreeCake.BITES, 15).addModels(new ConfiguredModel(models().getBuilder(getBlockPath(block) + "_slice15")
                        .texture(TEXTURE_KEY, blockTexture(block))
                        .texture(PARTICLE_KEY, blockTexture(block))
                        .element()
                        .from(12, 0, 1).to(15, 8, 15)
                        .face(Direction.NORTH).uvs(0.92308F, 8.92308F, 1.84615F, 11.38462F).texture(TEXTURE_TAG).end()
                        .face(Direction.EAST).uvs(0.92308F, 8.92308F, 5.23077F, 11.38462F).texture(TEXTURE_TAG).end()
                        .face(Direction.SOUTH).uvs(4.30769F, 8.92308F, 5.23077F, 11.38462F).texture(TEXTURE_TAG).end()
                        .face(Direction.WEST).uvs(10.76923F, 2.46154F, 15.07692F, 4.92308F).texture(TEXTURE_TAG).end()
                        .face(Direction.UP).uvs(9.23077F, 11.69231F, 10.15385F, 16F).texture(TEXTURE_TAG).end()
                        .face(Direction.DOWN).uvs(4.30769F, 11.69231F, 5.23077F, 16F).texture(TEXTURE_TAG).end()
                        .end()));
    }
}
