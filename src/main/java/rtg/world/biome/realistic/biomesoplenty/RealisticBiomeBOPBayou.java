package rtg.world.biome.realistic.biomesoplenty;

import biomesoplenty.api.biome.BOPBiomes;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPBayou;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPBayou;

public class RealisticBiomeBOPBayou extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPBiomes.bayou.get();
	
	public static IBlockState topBlock = bopBiome.topBlock;
	public static IBlockState fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPBayou(BiomeConfig config)
	{
		super(config, 
			bopBiome, BiomeGenBase.river,
			new TerrainBOPBayou(),
			new SurfaceBOPBayou(config, topBlock, fillerBlock)
		);
	}
}
