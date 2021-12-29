package flaxbeard.steamcraft.integration.tinkers;

import static net.minecraft.util.EnumChatFormatting.GOLD;
import cpw.mods.fml.common.event.FMLInterModComms;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;

public class MaterialBrass {
	public static void registerMaterial() {
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("Id", 50); // Unique material ID. Reseved IDs: 0-40 Tinker, 41-45 Iguana Tinker Tweaks, 100-200 ExtraTiC
		tag.setString("Name", "Brass"); // Unique material name
		tag.setInteger("HarvestLevel", 2);
		tag.setInteger("Durability", 200);
		tag.setInteger("MiningSpeed", 700);
		tag.setInteger("Attack", 2); // optional
		tag.setFloat("HandleModifier", 1.2f);
		tag.setInteger("Color", 255 << 24 | 181 << 16 | 166 << 8 | 66); // argb
		tag.setString("Style", GOLD.toString());
		FMLInterModComms.sendMessage("TConstruct", "addMaterial", tag);
	}
	
	public static void partCasting() {
		NBTTagCompound tag = new NBTTagCompound();
		(new FluidStack(MoltenMetals.brass, 1)).writeToNBT(tag);
		tag.setInteger("MaterialId", 50);
		FMLInterModComms.sendMessage("TConstruct", "addPartCastingMaterial", tag);
	}
}
