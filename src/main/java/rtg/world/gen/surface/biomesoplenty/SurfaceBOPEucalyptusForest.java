package rtg.world.gen.surface.biomesoplenty;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.ChunkPrimer;
import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.CliffCalculator;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.surface.SurfaceBase;

import java.util.Random;

public class SurfaceBOPEucalyptusForest extends SurfaceBase
{

    
    private IBlockState blockMixTop;
    private IBlockState blockMixFiller;
    private float floMixWidth;
    private float floMixHeight;
    private float floSmallWidth;
    private float floSmallStrength;
    
    public SurfaceBOPEucalyptusForest(BiomeConfig config, IBlockState top, IBlockState filler, IBlockState mixTop, IBlockState mixFiller,
         float mixWidth, float mixHeight, float smallWidth, float smallStrength)
    {
    
        super(config, top, filler);
        
        blockMixTop = mixTop;
        blockMixFiller = mixFiller;
        
        floMixWidth = mixWidth;
        floMixHeight = mixHeight;
        floSmallWidth = smallWidth;
        floSmallStrength = smallStrength;
    }
    
    @Override
    public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int y, int depth, World world, Random rand,
                             OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base)
    {
    
        float c = CliffCalculator.calc(x, y, noise);
        boolean cliff = c > 1.4f ? true : false;
        boolean mix = false;
        
        for (int k = 255; k > -1; k--)
        {
            Block b = primer.getBlockState((y * 16 + x) * 256 + k).getBlock();
            if (b == Blocks.air)
            {
                depth = -1;
            }
            else if (b == Blocks.stone)
            {
                depth++;
                
                if (cliff)
                {
                    if (depth > -1 && depth < 2)
                    {
                        if (rand.nextInt(3) == 0) {
                            
                            primer.setBlockState((y * 16 + x) * 256 + k, hcCobble(world, i, j, x, y, k));
                        }
                        else {
                            
                            primer.setBlockState((y * 16 + x) * 256 + k, hcStone(world, i, j, x, y, k));
                        }
                    }
                    else if (depth < 10)
                    {
                        primer.setBlockState((y * 16 + x) * 256 + k, hcStone(world, i, j, x, y, k));
                    }
                }
                else
                {
                    if (depth == 0 && k > 61)
                    {
                        if (simplex.noise2(i / floMixWidth, j / floMixWidth) + simplex.noise2(i / floSmallWidth, j / floSmallWidth)
                            * floSmallStrength > floMixHeight)
                        {
                            primer.setBlockState((y * 16 + x) * 256 + k, blockMixTop);
                            
                            mix = true;
                        }
                        else
                        {
                            primer.setBlockState((y * 16 + x) * 256 + k, topBlock);
                        }
                    }
                    else if (depth < 4)
                    {
                        if (mix)
                        {
                            primer.setBlockState((y * 16 + x) * 256 + k, blockMixFiller);
                        }
                        else
                        {
                            primer.setBlockState((y * 16 + x) * 256 + k, fillerBlock);
                        }
                    }
                }
            }
        }
    }
}
