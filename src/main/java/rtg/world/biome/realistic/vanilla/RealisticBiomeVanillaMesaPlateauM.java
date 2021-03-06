package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.*;
import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.feature.WorldGenCacti;
import rtg.world.gen.surface.vanilla.SurfaceVanillaMesaPlateauM;
import rtg.world.gen.terrain.vanilla.TerrainVanillaMesaPlateauM;

import java.util.Random;

public class RealisticBiomeVanillaMesaPlateauM extends RealisticBiomeVanillaBase
{
    public static BiomeGenBase standardBiome = BiomeGenBase.mesaPlateau;
    public static BiomeGenBase mutationBiome = BiomeGenBase.getBiome(standardBiome.biomeID + MUTATION_ADDEND);
    
    public static  IBlockState topBlock = mutationBiome.topBlock;
    public static IBlockState fillerBlock = mutationBiome.fillerBlock;
    
    public RealisticBiomeVanillaMesaPlateauM(BiomeConfig config)
    {
    
        super(config, 
            mutationBiome,
            BiomeGenBase.river,
            new TerrainVanillaMesaPlateauM(true, 15f, 260f, 50f, 30f, 79f),
            new SurfaceVanillaMesaPlateauM(config, Blocks.sand.getStateFromMeta(1), Blocks.sand.getStateFromMeta(1), 0));
        this.noLakes=true;
    }
    
    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river)
    {
        
        /**
         * Using rDecorateSeedBiome() to partially decorate the biome? If so, then comment out this method.
         */
        rOreGenSeedBiome(world, rand, new BlockPos(chunkX, 0, chunkY), simplex, cell, strength, river, baseBiome);
        for (int l = 0; l < 1; ++l)
        {
            int i1 = chunkX + rand.nextInt(16) + 8;
            int j1 = chunkY + rand.nextInt(16) + 8;
            int k1 = world.getHeight(new BlockPos(i1, 0, j1)).getY();
            if(k1 < 70)
            {
                (new WorldGenBlockBlob(Blocks.mossy_cobblestone, 0)).generate(world, rand, new BlockPos(i1, k1, j1));
            }
        }
        
        if(river > 0.8f)
        {
            for (int b1 = 0; b1 < 10; b1++)
            {
                int j6 = chunkX + rand.nextInt(16) + 8;
                int k10 = chunkY + rand.nextInt(16) + 8;
                int z52 = world.getHeight(new BlockPos(j6, 0, k10)).getY();
    
                WorldGenerator worldgenerator;
                worldgenerator = new WorldGenShrub(Blocks.log.getDefaultState(), Blocks.leaves.getDefaultState());
                worldgenerator.generate(world, rand, new BlockPos(j6, z52, k10));
            }
        }
        else
        {
            for (int b1 = 0; b1 < 5; b1++)
            {
                int j6 = chunkX + rand.nextInt(16) + 8;
                int k10 = chunkY + rand.nextInt(16) + 8;
                int z52 = world.getHeight(new BlockPos(j6, 0, k10)).getY();
    
                WorldGenerator worldgenerator;
                worldgenerator = new WorldGenShrub(Blocks.log.getDefaultState(), Blocks.leaves.getDefaultState());
                worldgenerator.generate(world, rand, new BlockPos(j6, z52, k10));
            }
            
            if(rand.nextInt(3) == 0) 
            {
                int i18 = chunkX + rand.nextInt(16) + 8;
                int i23 = chunkY + rand.nextInt(16) + 8;
                (new WorldGenReed()).generate(world, rand, new BlockPos(i18, 60 + rand.nextInt(8), i23));
            }
            
            if(rand.nextInt(28) == 0)
            {
                int j16 = chunkX + rand.nextInt(16) + 8;
                int j18 = rand.nextInt(128);
                int j21 = chunkY + rand.nextInt(16) + 8;
                (new WorldGenPumpkin()).generate(world, rand, new BlockPos(j16, j18, j21));
            }
            
            for(int i15 = 0; i15 < 5; i15++)
            {
                int i17 = chunkX + rand.nextInt(16) + 8;
                int i20 = rand.nextInt(160);
                int l22 = chunkY + rand.nextInt(16) + 8;
                (new WorldGenDeadBush()).generate(world, rand, new BlockPos(i17, i20, l22));
            }
            
            for(int k18 = 0; k18 < 25; k18++)
            {
                int k21 = chunkX + rand.nextInt(16) + 8;
                int j23 = rand.nextInt(160);
                int k24 = chunkY + rand.nextInt(16) + 8;
                (new WorldGenCacti(true)).generate(world, rand, new BlockPos(k21, j23, k24));
            }
        }
    }
}
