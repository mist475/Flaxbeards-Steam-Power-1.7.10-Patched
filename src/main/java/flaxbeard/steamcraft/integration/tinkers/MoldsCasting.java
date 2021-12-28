package flaxbeard.steamcraft.integration.tinkers;

import java.util.ArrayList;
import java.util.List;

import flaxbeard.steamcraft.SteamcraftItems;
import flaxbeard.steamcraft.integration.CrossMod;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;
import tconstruct.TConstruct;
import tconstruct.library.TConstructRegistry;
import tconstruct.library.crafting.CastingRecipe;
import tconstruct.library.crafting.LiquidCasting;
import tconstruct.library.crafting.Smeltery;
import tconstruct.smeltery.TinkerSmeltery;

public class MoldsCasting {

	public static void addPlateRecipes() {
		String[] metals = new String[] {"copper","zinc","iron","gold","brass","thamium","terrasteel","elementium","fiery","lead","vibrant","enderium","gildedIron"};
		for(int index : getPlates()) {
			LiquidCasting tableCasting = TConstructRegistry.getTableCasting();
		    ItemStack scPlatecast = new ItemStack(SteamcraftItems.plateMold, 1, 0);
		    ItemStack plate = new ItemStack(SteamcraftItems.steamcraftPlate, 1, index);
			if(index==12) { //gilded plate casting
				Fluid fluid = FluidRegistry.getFluid("gold.molten");
				FluidStack fluidStack = new FluidStack(fluid, (int) (TConstruct.nuggetLiquidValue));
				CastingRecipe recipe = tableCasting.getCastingRecipe(fluidStack, new ItemStack(SteamcraftItems.nuggetMold, 1, 0));
				tableCasting.addCastingRecipe(plate, fluidStack, new ItemStack(SteamcraftItems.steamcraftPlate, 1, 2), true, recipe.coolTime);
			}
			else if (FluidRegistry.isFluidRegistered(metals[index] + ".molten")) {
				Fluid fluid = FluidRegistry.getFluid(metals[index] + ".molten");
			    tableCasting.addCastingRecipe(plate, new FluidStack(fluid, (int) (TConstruct.ingotLiquidValue*0.6666F)), scPlatecast, 34);
			    String oreString = "ingot"+metals[index].substring(0,1).toUpperCase()+metals[index].substring(1);
			    String blockString = "block"+metals[index].substring(0,1).toUpperCase()+metals[index].substring(1);
			    ItemStack ingot = OreDictionary.getOres(oreString).get(0);
			    ItemStack block = OreDictionary.getOres(blockString).get(0);
			    Smeltery.addMelting(plate, Block.getBlockFromItem(block.getItem()), block.getItemDamage(), Smeltery.getLiquifyTemperature(ingot), new FluidStack(fluid, (int) (TConstruct.ingotLiquidValue*0.6666F)));
			}
		}
		
	}
	
	private static List<Integer> getPlates(){
    	ArrayList<Integer> list = new ArrayList<Integer>();
    	list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        if (CrossMod.THAUMCRAFT) {
            list.add(5);
        }
        if (CrossMod.BOTANIA) {
            list.add(6);
            list.add(7);
        }
        if (CrossMod.TWILIGHT_FOREST) {
            list.add(8);
        }
        if (OreDictionary.getOres("ingotLead").size() > 0) {
            list.add(9);
        }
        if (CrossMod.ENDER_IO) {
            list.add(10);
        }
        if (CrossMod.THERMAL_FOUNDATION) {
            list.add(11);
        }
        list.add(12);
    	return list;
    }
	
	public static void addIngotRecipes() {
		LiquidCasting tableCasting = TConstructRegistry.getTableCasting();
		ItemStack ingotcast = new ItemStack(TinkerSmeltery.metalPattern, 1, 0);
		ItemStack scIngotcast = new ItemStack(SteamcraftItems.ingotMold, 1, 0);
		ArrayList<CastingRecipe> recipes = tableCasting.getCastingRecipes();
		ArrayList<CastingRecipe> goodRecipes = new ArrayList<CastingRecipe>();
		for (CastingRecipe recipe : recipes) {
			if (recipe.cast != null && recipe.cast.isItemEqual(ingotcast)) {
				goodRecipes.add(recipe);
			}
		}
		for (CastingRecipe recipe : goodRecipes) {
	        tableCasting.addCastingRecipe(recipe.output, recipe.castingMetal, scIngotcast, recipe.coolTime);
		}
	}
	
	public static void addNuggetRecipes() {
		LiquidCasting tableCasting = TConstructRegistry.getTableCasting();
		ItemStack nuggetcast = new ItemStack(TinkerSmeltery.metalPattern, 1, 27);
		ItemStack scNuggetcast = new ItemStack(SteamcraftItems.nuggetMold, 1, 0);
		ArrayList<CastingRecipe> recipes = tableCasting.getCastingRecipes();
		ArrayList<CastingRecipe> goodRecipes = new ArrayList<CastingRecipe>();
		for (CastingRecipe recipe : recipes) {
			if (recipe.cast != null && recipe.cast.isItemEqual(nuggetcast)) {
				goodRecipes.add(recipe);
			}
		}
		for (CastingRecipe recipe : goodRecipes) {
	        tableCasting.addCastingRecipe(recipe.output, recipe.castingMetal, scNuggetcast, recipe.coolTime);
		}
	}
}
