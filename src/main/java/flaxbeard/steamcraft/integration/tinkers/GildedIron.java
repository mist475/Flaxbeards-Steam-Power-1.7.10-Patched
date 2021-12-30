package flaxbeard.steamcraft.integration.tinkers;

import java.util.HashMap;

import flaxbeard.steamcraft.SteamcraftItems;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import tconstruct.TConstruct;
import tconstruct.library.TConstructRegistry;
import tconstruct.library.crafting.CastingRecipe;
import tconstruct.library.crafting.LiquidCasting;

public class GildedIron {
	
	static int delay;
	static LiquidCasting tableCasting;
	
	public static void registerGilding() {
		HashMap<String, Item> tools = SteamcraftItems.tools;
		tableCasting = TConstructRegistry.getTableCasting();
		delay = tableCasting.getCastingDelay(new FluidStack(FluidRegistry.getFluid("gold.molten"), (int) (TConstruct.nuggetLiquidValue)), new ItemStack(SteamcraftItems.nuggetMold, 1, 0));
		
		ingotGilding();
		gilding(new ItemStack(SteamcraftItems.steamcraftPlate, 1, 2), new ItemStack(SteamcraftItems.steamcraftPlate, 1, 12), 1);
		gilding(new ItemStack(Items.iron_pickaxe), new ItemStack(tools.get("pickGildedGold")), 3);
		gilding(new ItemStack(Items.iron_axe), new ItemStack(tools.get("axeGildedGold")), 3);
		gilding(new ItemStack(Items.iron_shovel), new ItemStack(tools.get("shovelGildedGold")), 1);
		gilding(new ItemStack(Items.iron_hoe), new ItemStack(tools.get("hoeGildedGold")), 2);
		gilding(new ItemStack(Items.iron_sword), new ItemStack(tools.get("swordGildedGold")), 2);
		gilding(new ItemStack(Items.iron_helmet), new ItemStack(tools.get("helmGildedGold")), 5);
		gilding(new ItemStack(Items.iron_chestplate), new ItemStack(tools.get("chestGildedGold")), 8);
		gilding(new ItemStack(Items.iron_leggings), new ItemStack(tools.get("legsGildedGold")), 7);
		gilding(new ItemStack(Items.iron_boots), new ItemStack(tools.get("feetGildedGold")), 4);
	}
	
	private static void ingotGilding() {
		Fluid fluid = FluidRegistry.getFluid("gold.molten");
		FluidStack fluidStack = new FluidStack(fluid, (int) (TConstruct.nuggetLiquidValue));
		ItemStack iron = new ItemStack(Items.iron_ingot, 1, 0);
		CastingRecipe recipe = tableCasting.getCastingRecipe(new FluidStack(fluid, (int) (TConstruct.ingotLiquidValue*2)), iron);
		tableCasting.getCastingRecipes().remove(recipe);
		tableCasting.addCastingRecipe(new ItemStack(SteamcraftItems.steamcraftIngot, 1, 3), fluidStack, iron, true, delay);
	}
	
	private static void gilding(ItemStack input, ItemStack output, int ingotCount) {
		Fluid fluid = FluidRegistry.getFluid("gold.molten");
		FluidStack fluidStack = new FluidStack(fluid, (int) (TConstruct.nuggetLiquidValue)*ingotCount);
		tableCasting.addCastingRecipe(output, fluidStack, input, true, delay*ingotCount);
	}
}
