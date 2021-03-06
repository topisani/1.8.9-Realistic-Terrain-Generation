package rtg.world.gen.feature.tree;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

import static java.lang.Math.abs;
import static net.minecraft.block.material.Material.air;
import static net.minecraft.block.material.Material.vine;
import static net.minecraft.init.Blocks.*;
import static rtg.config.rtg.ConfigRTG.allowTreesToGenerateOnSand;

class WorldGenTreeRTGCocoaSmall extends WorldGenerator {
    public boolean generate(World world, Random rand, BlockPos blockPos) {
        return this.generate(world, rand, blockPos.getX(), blockPos.getY(), blockPos.getZ());
    }


    private static int[] cocoas = new int[]{
            2, 0, -2, 1,
            1, 1, -2, 0,
            0, 0, -2, -1,
            3, -1, -2, 0
    };

    public WorldGenTreeRTGCocoaSmall() {

    }

    public boolean generate(World world, Random rand, int x, int y, int z) {
        Block b = world.getBlockState(new BlockPos(x, y - 1, z)).getBlock();

        if (b == sand && !allowTreesToGenerateOnSand) {
            return false;
        }

        if (b != grass && b != dirt && b != sand) {
            return false;
        }

        Material m = world.getBlockState(new BlockPos(x, y, z)).getBlock().getMaterial();
        if (m != air && m != vine) {
            return false;
        }

        int h = y + 2 + rand.nextInt(3);
        for (; y < h; y++) {
            world.setBlockState(new BlockPos(x, y, z), log.getStateFromMeta(3), 0);
        }

        for (int i = -2; i <= 2; i++) {
            for (int j = -2; j <= 2; j++) {
                if (abs(i) + abs(j) < 3) {
                    buildBlock(world, x + i, y - 1, z + j, leaves, 3, 0);
                }
            }
        }

        world.setBlockState(new BlockPos(x, y - 1, z), log.getStateFromMeta(3), 0);
        buildBlock(world, x + 1, y, z, leaves, 3, 0);
        buildBlock(world, x - 1, y, z, leaves, 3, 0);
        buildBlock(world, x, y, z, leaves, 3, 0);
        buildBlock(world, x, y, z + 1, leaves, 3, 0);
        buildBlock(world, x, y, z - 1, leaves, 3, 0);

        for (int k = 0; k < 16; k += 4) {
            if (rand.nextInt(20) == 0) {
                buildBlock(world, x + cocoas[k + 1], y + cocoas[k + 2], z + cocoas[k + 3], cocoa, cocoas[k + 0] + 8, 0);
            }
        }

        return true;
    }

    private void buildBlock(World w, int x, int y, int z, Block b, int m, int u) {
        Material ma = w.getBlockState(new BlockPos(x, y, z)).getBlock().getMaterial();

        if (ma == air || ma == vine) {
            w.setBlockState(new BlockPos(x, y, z), b.getStateFromMeta(m), 0);
        }
    }
}
