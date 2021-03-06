package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenForest;
import net.minecraft.world.gen.feature.WorldGenerator;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaFlowerForest;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.feature.WorldGenFlowers;
import rtg.world.gen.feature.WorldGenGrass;
import rtg.world.gen.feature.WorldGenLog;
import rtg.world.gen.feature.tree.WorldGenTreeRTGPineBig;
import rtg.world.gen.feature.tree.WorldGenTreeRTGPineSmall;
import rtg.world.gen.feature.tree.WorldGenTreeRTGShrub;
import rtg.world.gen.feature.tree.WorldGenTreeRTGTrees;
import rtg.world.gen.surface.vanilla.SurfaceVanillaFlowerForest;
import rtg.world.gen.terrain.vanilla.TerrainVanillaFlowerForest;

import java.util.Random;

public class RealisticBiomeVanillaFlowerForest extends RealisticBiomeVanillaBase
{
    
    public static BiomeGenBase standardBiome = BiomeGenBase.forest;
    public static BiomeGenBase mutationBiome = BiomeGenBase.getBiome(standardBiome.biomeID + MUTATION_ADDEND);
    
    public static  IBlockState topBlock = mutationBiome.topBlock;
    public static IBlockState fillerBlock = mutationBiome.fillerBlock;
    
    public RealisticBiomeVanillaFlowerForest(BiomeConfig config)
    {
    
        super(config, 
            mutationBiome,
            BiomeGenBase.river,
            new TerrainVanillaFlowerForest(),
            new SurfaceVanillaFlowerForest(config, Blocks.grass.getDefaultState(), Blocks.dirt.getDefaultState(), false, null, 0f, 1.5f, 60f, 65f, 1.5f, Blocks.grass.getDefaultState(), 0.05f));
    }
    
    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river)
    {
        
        /**
         * Using rDecorateSeedBiome() to partially decorate the biome? If so, then comment out this method.
         */
        //rOreGenSeedBiome(world, rand, new BlockPos(chunkX, 0, chunkY), simplex, cell, strength, river, baseBiome);
    
        for (int f23 = 0; f23 < 16f * strength; f23++)
        {
            int j15 = chunkX + rand.nextInt(16) + 8;
            int j20 = chunkY + rand.nextInt(16) + 8;
            int j17 = world.getHeight(new BlockPos(j15, 0, j20)).getY();
            
            (new WorldGenFlowers(new int[] {0, 2, 3, 4, 5, 6, 7, 8, 9, 11, 14, 15})).generate(world, rand, new BlockPos(j15, j17, j20));
        }

        float l = simplex.noise2(chunkX / 80f, chunkY / 80f) * 60f - 15f;
        
        for (int b1 = 0; b1 < l * strength; b1++)
        {
            if (rand.nextInt(4) == 0)
            {
                int j6 = chunkX + rand.nextInt(16) + 8;
                int k10 = chunkY + rand.nextInt(16) + 8;
                int z52 = world.getHeight(new BlockPos(j6, 0, k10)).getY();
                
                if (z52 < 110)
                {
                    if (rand.nextBoolean()) {
                        WorldGenerator worldgenerator = new WorldGenTreeRTGPineBig(11 + rand.nextInt(11), 15 + rand.nextInt(15), 1, 1);
                        worldgenerator.generate(world, rand, new BlockPos(j6, z52, k10));
                    }
                    else {
                        WorldGenerator worldgenerator = new WorldGenTreeRTGPineBig(11 + rand.nextInt(11), 15 + rand.nextInt(15), 0, 0);
                        worldgenerator.generate(world, rand, new BlockPos(j6, z52, k10));
                    }
                }
            }
        }
        
        if (l > 5f)
        {
            for (int b2 = 0; b2 < 3f * strength; b2++)
            {
                if (rand.nextInt(4) == 0) {
                    
                    int j6 = chunkX + rand.nextInt(16) + 8;
                    int k10 = chunkY + rand.nextInt(16) + 8;
                    int z52 = world.getHeight(new BlockPos(j6, 0, k10)).getY();
                    
                    if (z52 < 120)
                    {
                        WorldGenerator worldgenerator =
                            rand.nextInt(2) == 0 ? new WorldGenTreeRTGPineSmall(4 + rand.nextInt(7), 6 + rand.nextInt(9), 0)
                                : rand.nextInt(10) != 0 ? new WorldGenTreeRTGTrees(false) : new WorldGenForest(false, false);
                        worldgenerator.generate(world, rand, new BlockPos(j6, z52, k10));
                    }
                }
            }
        }
        
        if (this.config.getPropertyById(BiomeConfigVanillaFlowerForest.decorationLogsId).valueBoolean) {
        
            if (rand.nextInt((int) (12f / strength)) == 0)
            {
                int x22 = chunkX + rand.nextInt(16) + 8;
                int z22 = chunkY + rand.nextInt(16) + 8;
                int y22 = world.getHeight(new BlockPos(x22, 0, z22)).getY();
                
                if (y22 < 100)
                {
                    if (rand.nextBoolean()) {
                        (new WorldGenLog(Blocks.log, 0, Blocks.leaves, -1, 3 + rand.nextInt(4))).generate(world, rand, new BlockPos(x22, y22, z22));
                    }
                    else {
                        (new WorldGenLog(1, 3 + rand.nextInt(4), false)).generate(world, rand, new BlockPos(x22, y22, z22));
                    }
                }
            }
        }
        
        for (int f24 = 0; f24 < 3f * strength; f24++)
        {
            if (rand.nextInt(4) == 0) {
                
                int i1 = chunkX + rand.nextInt(16) + 8;
                int j1 = chunkY + rand.nextInt(16) + 8;
                int k1 = world.getHeight(new BlockPos(i1, 0, j1)).getY();
                if (k1 < 110)
                {
                    (new WorldGenTreeRTGShrub(rand.nextInt(4) + 1, 0, rand.nextInt(3))).generate(world, rand, new BlockPos(i1, k1, j1));
                }
            }
        }
        
        for (int l14 = 0; l14 < 12f * strength; l14++)
        {
            int l19 = chunkX + rand.nextInt(16) + 8;
            int k22 = rand.nextInt(128);
            int j24 = chunkY + rand.nextInt(16) + 8;
            (new WorldGenGrass(Blocks.tallgrass, 1)).generate(world, rand, new BlockPos(l19, k22, j24));
        }
        
        rDecorateSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, baseBiome);
    }
}
