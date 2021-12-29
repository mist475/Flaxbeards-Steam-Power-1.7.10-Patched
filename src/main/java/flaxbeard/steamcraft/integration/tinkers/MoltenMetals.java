package flaxbeard.steamcraft.integration.tinkers;

import flaxbeard.steamcraft.SteamcraftBlocks;
import flaxbeard.steamcraft.SteamcraftItems;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import tconstruct.smeltery.TinkerSmeltery;
import tconstruct.TConstruct;
import tconstruct.library.crafting.Smeltery;
import tconstruct.library.TConstructRegistry;
import tconstruct.library.crafting.CastingRecipe;
import tconstruct.library.crafting.LiquidCasting;
import tconstruct.library.crafting.FluidType;

public class MoltenMetals {
	public static Fluid brass;
	public static Fluid zinc;
	static ItemStack nuggetcast = new ItemStack(TinkerSmeltery.metalPattern, 1, 27);
	static ItemStack ingotcast = new ItemStack(TinkerSmeltery.metalPattern, 1, 0);
	static LiquidCasting tableCasting = TConstructRegistry.getTableCasting();
	static LiquidCasting basinCasting = TConstructRegistry.getBasinCasting();
	
	public static void register() {
		brass = TinkerSmeltery.registerFluid("brass");
		FluidType.registerFluidType("Brass", null, 0, 500, brass, true);
		MaterialBrass.registerMaterial();
		zinc = TinkerSmeltery.registerFluid("zinc");
		FluidType.registerFluidType("Zinc", null, 0, 420, zinc, true);
	}
	
	public static void alloying() {
		Fluid copper = FluidRegistry.getFluid("copper.molten");
		Smeltery.addAlloyMixing(new FluidStack(brass, 64), new FluidStack(copper, 48), new FluidStack(zinc, 16));
	}
	
	public static void melting() {
		meltingZinc();
		meltingBrass();
	}
	
	private static void meltingZinc() {
		FluidType typeZinc = FluidType.getFluidType(zinc);
		Smeltery.addDictionaryMelting("ingotZinc", typeZinc, -150, TConstruct.ingotLiquidValue);
		Smeltery.addDictionaryMelting("oreZinc", typeZinc, -100, TConstruct.oreLiquidValue);
		Smeltery.addDictionaryMelting("nuggetZinc", typeZinc, -200, TConstruct.nuggetLiquidValue);
		Smeltery.addDictionaryMelting("dustZinc", typeZinc, -175, TConstruct.ingotLiquidValue);
		Smeltery.addDictionaryMelting("blockZinc", typeZinc, 0, TConstruct.blockLiquidValue);
	}
	
	private static void meltingBrass() {
		FluidType typeBrass = FluidType.getFluidType(brass);
		Smeltery.addDictionaryMelting("ingotBrass", typeBrass, -150, TConstruct.ingotLiquidValue);
		Smeltery.addDictionaryMelting("nuggetBrass", typeBrass, -200, TConstruct.nuggetLiquidValue);
		Smeltery.addDictionaryMelting("dustBrass", typeBrass, -175, TConstruct.ingotLiquidValue);
		Smeltery.addDictionaryMelting("blockBrass", typeBrass, 0, TConstruct.blockLiquidValue);
	}
	
	public static void casting() {
		castingZinc();
		castingBrass();
		MaterialBrass.partCasting();
	}
	
	private static void castingZinc() {
		tableCasting.addCastingRecipe(new ItemStack(SteamcraftItems.steamcraftIngot, 1, 1), new FluidStack(zinc, TConstruct.ingotLiquidValue), ingotcast, 80);
		tableCasting.addCastingRecipe(new ItemStack(SteamcraftItems.steamcraftNugget, 1, 1), new FluidStack(zinc, TConstruct.nuggetLiquidValue), nuggetcast, 80);
		basinCasting.addCastingRecipe(new ItemStack(SteamcraftBlocks.blockZinc), new FluidStack(zinc, TConstruct.blockLiquidValue), 100);
	}
	
	private static void castingBrass() {
		tableCasting.addCastingRecipe(new ItemStack(SteamcraftItems.steamcraftIngot, 1, 2), new FluidStack(brass, TConstruct.ingotLiquidValue), ingotcast, 80);
		tableCasting.addCastingRecipe(new ItemStack(SteamcraftItems.steamcraftNugget, 1, 3), new FluidStack(brass, TConstruct.nuggetLiquidValue), nuggetcast, 80);
		basinCasting.addCastingRecipe(new ItemStack(SteamcraftBlocks.blockBrass), new FluidStack(brass, TConstruct.blockLiquidValue), 100);
	}
}
