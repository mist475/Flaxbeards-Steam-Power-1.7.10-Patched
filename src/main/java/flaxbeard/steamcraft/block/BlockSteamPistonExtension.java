package flaxbeard.steamcraft.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import flaxbeard.steamcraft.SteamcraftBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Facing;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class BlockSteamPistonExtension extends Block {

    @SideOnly(Side.CLIENT)
    private IIcon field_150088_a;

    public BlockSteamPistonExtension() {
        super(Material.piston);
        this.setStepSound(soundTypePiston);
        this.setHardness(0.5F);
    }

    public static int getDirectionMeta(int p_150085_0_) {
        return p_150085_0_ & 7;
    }

    @SideOnly(Side.CLIENT)
    public void func_150086_a(IIcon p_150086_1_) {
        this.field_150088_a = p_150086_1_;
    }

    /**
     * Called when the block is attempted to be harvested
     */
    public void onBlockHarvested(World worldIn, int x, int y, int z, int meta, EntityPlayer player) {
        if (player.capabilities.isCreativeMode) {
            int i1 = getDirectionMeta(meta);
            Block block = worldIn.getBlock(x - Facing.offsetsXForSide[i1], y - Facing.offsetsYForSide[i1], z - Facing.offsetsZForSide[i1]);

            if (block == SteamcraftBlocks.steamPiston) {
                worldIn.setBlockToAir(x - Facing.offsetsXForSide[i1], y - Facing.offsetsYForSide[i1], z - Facing.offsetsZForSide[i1]);
            }
        }

        super.onBlockHarvested(worldIn, x, y, z, meta, player);
    }

    public void breakBlock(World worldIn, int x, int y, int z, Block blockBroken, int meta) {
        super.breakBlock(worldIn, x, y, z, blockBroken, meta);
        int i1 = Facing.oppositeSide[getDirectionMeta(meta)];
        x += Facing.offsetsXForSide[i1];
        y += Facing.offsetsYForSide[i1];
        z += Facing.offsetsZForSide[i1];
        Block block1 = worldIn.getBlock(x, y, z);

        if (block1 == SteamcraftBlocks.steamPiston) {
            meta = worldIn.getBlockMetadata(x, y, z);

            if (BlockPistonBase.isExtended(meta)) {
                block1.dropBlockAsItem(worldIn, x, y, z, meta, 0);
                worldIn.setBlockToAir(x, y, z);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public void func_150087_e() {
        this.field_150088_a = null;
    }

    /**
     * Gets the block's texture. Args: side, meta
     */
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        int k = getDirectionMeta(meta);
        return side == k ? (this.field_150088_a != null ? this.field_150088_a : ((meta & 8) != 0 ? BlockPistonBase.getPistonBaseIcon("piston_top_sticky") : BlockPistonBase.getPistonBaseIcon("piston_top_normal"))) : (k < 6 && side == Facing.oppositeSide[k] ? BlockPistonBase.getPistonBaseIcon("piston_top_normal") : BlockPistonBase.getPistonBaseIcon("piston_side"));
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg) {
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
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random random) {
        return 0;
    }

    /**
     * Adds all intersecting collision boxes to a list. (Be sure to only add boxes to the list if they intersect the
     * mask.) Parameters: World, X, Y, Z, mask, list, colliding entity
     */
    public void addCollisionBoxesToList(World worldIn, int x, int y, int z, AxisAlignedBB mask, List list, Entity collider) {
        int l = worldIn.getBlockMetadata(x, y, z);
        float f = 0.25F;
        float f1 = 0.375F;
        float f2 = 0.625F;
        float f3 = 0.25F;
        float f4 = 0.75F;

        switch (getDirectionMeta(l)) {
            case 0:
                this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.25F, 1.0F);
                super.addCollisionBoxesToList(worldIn, x, y, z, mask, list, collider);
                this.setBlockBounds(0.375F, 0.25F, 0.375F, 0.625F, 1.0F, 0.625F);
                super.addCollisionBoxesToList(worldIn, x, y, z, mask, list, collider);
                break;
            case 1:
                this.setBlockBounds(0.0F, 0.75F, 0.0F, 1.0F, 1.0F, 1.0F);
                super.addCollisionBoxesToList(worldIn, x, y, z, mask, list, collider);
                this.setBlockBounds(0.375F, 0.0F, 0.375F, 0.625F, 0.75F, 0.625F);
                super.addCollisionBoxesToList(worldIn, x, y, z, mask, list, collider);
                break;
            case 2:
                this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.25F);
                super.addCollisionBoxesToList(worldIn, x, y, z, mask, list, collider);
                this.setBlockBounds(0.25F, 0.375F, 0.25F, 0.75F, 0.625F, 1.0F);
                super.addCollisionBoxesToList(worldIn, x, y, z, mask, list, collider);
                break;
            case 3:
                this.setBlockBounds(0.0F, 0.0F, 0.75F, 1.0F, 1.0F, 1.0F);
                super.addCollisionBoxesToList(worldIn, x, y, z, mask, list, collider);
                this.setBlockBounds(0.25F, 0.375F, 0.0F, 0.75F, 0.625F, 0.75F);
                super.addCollisionBoxesToList(worldIn, x, y, z, mask, list, collider);
                break;
            case 4:
                this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.25F, 1.0F, 1.0F);
                super.addCollisionBoxesToList(worldIn, x, y, z, mask, list, collider);
                this.setBlockBounds(0.375F, 0.25F, 0.25F, 0.625F, 0.75F, 1.0F);
                super.addCollisionBoxesToList(worldIn, x, y, z, mask, list, collider);
                break;
            case 5:
                this.setBlockBounds(0.75F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
                super.addCollisionBoxesToList(worldIn, x, y, z, mask, list, collider);
                this.setBlockBounds(0.0F, 0.375F, 0.25F, 0.75F, 0.625F, 0.75F);
                super.addCollisionBoxesToList(worldIn, x, y, z, mask, list, collider);
        }

        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    public void setBlockBoundsBasedOnState(IBlockAccess worldIn, int x, int y, int z) {
        int l = worldIn.getBlockMetadata(x, y, z);
        float f = 0.25F;

        switch (getDirectionMeta(l)) {
            case 0:
                this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.25F, 1.0F);
                break;
            case 1:
                this.setBlockBounds(0.0F, 0.75F, 0.0F, 1.0F, 1.0F, 1.0F);
                break;
            case 2:
                this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.25F);
                break;
            case 3:
                this.setBlockBounds(0.0F, 0.0F, 0.75F, 1.0F, 1.0F, 1.0F);
                break;
            case 4:
                this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.25F, 1.0F, 1.0F);
                break;
            case 5:
                this.setBlockBounds(0.75F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        }
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor Block
     */
    public void onNeighborBlockChange(World worldIn, int x, int y, int z, Block neighbor) {
        int l = getDirectionMeta(worldIn.getBlockMetadata(x, y, z));
        Block block1 = worldIn.getBlock(x - Facing.offsetsXForSide[l], y - Facing.offsetsYForSide[l], z - Facing.offsetsZForSide[l]);

        if (block1 != SteamcraftBlocks.steamPiston) {
            worldIn.setBlockToAir(x, y, z);
        } else {
            block1.onNeighborBlockChange(worldIn, x - Facing.offsetsXForSide[l], y - Facing.offsetsYForSide[l], z - Facing.offsetsZForSide[l], neighbor);
        }
    }

    /**
     * Gets an item for the block being called on. Args: world, x, y, z
     */
    @SideOnly(Side.CLIENT)
    public Item getItem(World worldIn, int x, int y, int z) {
        int l = worldIn.getBlockMetadata(x, y, z);
        return Item.getItemFromBlock(SteamcraftBlocks.steamPiston);
    }
}
