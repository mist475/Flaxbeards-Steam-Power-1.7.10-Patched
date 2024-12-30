package flaxbeard.steamcraft.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import flaxbeard.steamcraft.Steamcraft;
import flaxbeard.steamcraft.tile.TileEntityFishGenocideMachine;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockFishGenocideMachine extends BlockContainer {
    @SideOnly(Side.CLIENT)
	public IIcon top;
    public int pass = 0;
    
    @Override
    public boolean canRenderInPass(int x) {
    	pass = x;
    	return x <= 1;
    }
    
    public BlockFishGenocideMachine()
    {
        super(Material.iron);
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess worldIn, int x, int y, int z)
    {
    	float px = 1.0F/16.0F;
        this.setBlockBounds(0.0F+3*px, 0.0F, 0.0F+3*px, 1.0F-3*px, 1.0F, 1.0F-3*px);
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        return side == 1 ? this.top : (side == 0 ? this.top : this.blockIcon);
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg)
    {
        this.blockIcon = reg.registerIcon("steamcraft:blockBrass");
        this.top =  reg.registerIcon("steamcraft:blockBrass");
    }

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityFishGenocideMachine();
	}

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public int getRenderType()
    {
        return Steamcraft.genocideRenderID;
    }
}