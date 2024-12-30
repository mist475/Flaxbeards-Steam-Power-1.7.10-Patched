package flaxbeard.steamcraft.misc;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class BlockContainer extends Block {
    private Block myBlock;
    private int mySide;

    public BlockContainer(Block block, int side) {
        super(block.getMaterial());
        myBlock = block;
        mySide = side;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess blockAccess, int x, int y, int z, int side) {
        return side == mySide ? super.shouldSideBeRendered(blockAccess, x, y, z, side) : false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess worldIn, int x, int y, int z, int side) {
        return myBlock.getIcon(worldIn, x, y, z, side);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return myBlock.getIcon(side, meta);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon func_149735_b(int side, int meta) {
        return myBlock.getIcon(side, meta);
    }
}
