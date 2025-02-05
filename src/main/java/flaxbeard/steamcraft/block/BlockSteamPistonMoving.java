package flaxbeard.steamcraft.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import flaxbeard.steamcraft.tile.TileEntitySteamPiston;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Facing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class BlockSteamPistonMoving extends BlockContainer {

    public BlockSteamPistonMoving() {
        super(Material.piston);
        this.setHardness(-1.0F);
    }

    public static TileEntity getTileEntity(Block p_149962_0_, int p_149962_1_, int p_149962_2_, boolean p_149962_3_, boolean p_149962_4_) {
        return new TileEntitySteamPiston(p_149962_0_, p_149962_1_, p_149962_2_, p_149962_3_, p_149962_4_);
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return null;
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World worldIn, int x, int y, int z) {
    }

    public void breakBlock(World worldIn, int x, int y, int z, Block blockBroken, int meta) {
        TileEntity tileentity = worldIn.getTileEntity(x, y, z);

        if (tileentity instanceof TileEntitySteamPiston) {
            ((TileEntitySteamPiston) tileentity).clearPistonTileEntity();
        } else {
            super.breakBlock(worldIn, x, y, z, blockBroken, meta);
        }
    }

    /**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    public boolean canPlaceBlockAt(World worldIn, int x, int y, int z) {
        return false;
    }

    /**
     * checks to see if you can place this block can be placed on that side of a block: BlockLever overrides
     */
    public boolean canPlaceBlockOnSide(World worldIn, int x, int y, int z, int side) {
        return false;
    }

    /**
     * The type of render function that is called for this block
     */
    public int getRenderType() {
        return -1;
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube() {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock() {
        return false;
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World worldIn, int x, int y, int z, EntityPlayer player, int side, float subX, float subY, float subZ) {
        if (!worldIn.isRemote && worldIn.getTileEntity(x, y, z) == null) {
            worldIn.setBlockToAir(x, y, z);
            return true;
        } else {
            return false;
        }
    }

    public Item getItemDropped(int meta, Random random, int fortune) {
        return null;
    }

    /**
     * Drops the block items with a specified chance of dropping the specified items
     */
    public void dropBlockAsItemWithChance(World worldIn, int x, int y, int z, int meta, float chance, int fortune) {
        if (!worldIn.isRemote) {
            TileEntitySteamPiston TileEntitySteamPiston = this.func_149963_e(worldIn, x, y, z);

            if (TileEntitySteamPiston != null) {
                TileEntitySteamPiston.getStoredBlockID().dropBlockAsItem(worldIn, x, y, z, TileEntitySteamPiston.getBlockMetadata(), 0);
            }
        }
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor Block
     */
    public void onNeighborBlockChange(World worldIn, int x, int y, int z, Block neighbor) {
        if (!worldIn.isRemote) {
            worldIn.getTileEntity(x, y, z);
        }
    }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World worldIn, int x, int y, int z) {
        TileEntitySteamPiston TileEntitySteamPiston = this.func_149963_e(worldIn, x, y, z);

        if (TileEntitySteamPiston == null) {
            return null;
        } else {
            float f = TileEntitySteamPiston.func_145860_a(0.0F);

            if (TileEntitySteamPiston.isExtending()) {
                f = 1.0F - f;
            }

            return this.func_149964_a(worldIn, x, y, z, TileEntitySteamPiston.getStoredBlockID(), f, TileEntitySteamPiston.getPistonOrientation());
        }
    }

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    public void setBlockBoundsBasedOnState(IBlockAccess worldIn, int x, int y, int z) {
        TileEntitySteamPiston TileEntitySteamPiston = this.func_149963_e(worldIn, x, y, z);

        if (TileEntitySteamPiston != null) {
            Block block = TileEntitySteamPiston.getStoredBlockID();

            if (block == this || block.getMaterial() == Material.air) {
                return;
            }

            block.setBlockBoundsBasedOnState(worldIn, x, y, z);
            float f = TileEntitySteamPiston.func_145860_a(0.0F);

            if (TileEntitySteamPiston.isExtending()) {
                f = 1.0F - f;
            }

            int l = TileEntitySteamPiston.getPistonOrientation();
            this.minX = block.getBlockBoundsMinX() - (double) ((float) Facing.offsetsXForSide[l] * f);
            this.minY = block.getBlockBoundsMinY() - (double) ((float) Facing.offsetsYForSide[l] * f);
            this.minZ = block.getBlockBoundsMinZ() - (double) ((float) Facing.offsetsZForSide[l] * f);
            this.maxX = block.getBlockBoundsMaxX() - (double) ((float) Facing.offsetsXForSide[l] * f);
            this.maxY = block.getBlockBoundsMaxY() - (double) ((float) Facing.offsetsYForSide[l] * f);
            this.maxZ = block.getBlockBoundsMaxZ() - (double) ((float) Facing.offsetsZForSide[l] * f);
        }
    }

    public AxisAlignedBB func_149964_a(World p_149964_1_, int p_149964_2_, int p_149964_3_, int p_149964_4_, Block p_149964_5_, float p_149964_6_, int p_149964_7_) {
        if (p_149964_5_ != this && p_149964_5_.getMaterial() != Material.air) {
            AxisAlignedBB axisalignedbb = p_149964_5_.getCollisionBoundingBoxFromPool(p_149964_1_, p_149964_2_, p_149964_3_, p_149964_4_);

            if (axisalignedbb == null) {
                return null;
            } else {
                if (Facing.offsetsXForSide[p_149964_7_] < 0) {
                    axisalignedbb.minX -= (double) ((float) Facing.offsetsXForSide[p_149964_7_] * p_149964_6_);
                } else {
                    axisalignedbb.maxX -= (double) ((float) Facing.offsetsXForSide[p_149964_7_] * p_149964_6_);
                }

                if (Facing.offsetsYForSide[p_149964_7_] < 0) {
                    axisalignedbb.minY -= (double) ((float) Facing.offsetsYForSide[p_149964_7_] * p_149964_6_);
                } else {
                    axisalignedbb.maxY -= (double) ((float) Facing.offsetsYForSide[p_149964_7_] * p_149964_6_);
                }

                if (Facing.offsetsZForSide[p_149964_7_] < 0) {
                    axisalignedbb.minZ -= (double) ((float) Facing.offsetsZForSide[p_149964_7_] * p_149964_6_);
                } else {
                    axisalignedbb.maxZ -= (double) ((float) Facing.offsetsZForSide[p_149964_7_] * p_149964_6_);
                }

                return axisalignedbb;
            }
        } else {
            return null;
        }
    }

    private TileEntitySteamPiston func_149963_e(IBlockAccess p_149963_1_, int p_149963_2_, int p_149963_3_, int p_149963_4_) {
        TileEntity tileentity = p_149963_1_.getTileEntity(p_149963_2_, p_149963_3_, p_149963_4_);
        return tileentity instanceof TileEntitySteamPiston ? (TileEntitySteamPiston) tileentity : null;
    }

    /**
     * Gets an item for the block being called on. Args: world, x, y, z
     */
    @SideOnly(Side.CLIENT)
    public Item getItem(World worldIn, int x, int y, int z) {
        return Item.getItemById(0);
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg) {
        this.blockIcon = reg.registerIcon("piston_top_normal");
    }
}
