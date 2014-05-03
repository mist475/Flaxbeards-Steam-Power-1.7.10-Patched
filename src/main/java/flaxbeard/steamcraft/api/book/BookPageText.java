package flaxbeard.steamcraft.api.book;

import java.util.Iterator;
import java.util.List;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.I18n;
import flaxbeard.steamcraft.gui.GuiSteamcraftBook;

public class BookPageText extends BookPage {
	private String text;
	
	public BookPageText(String string,String string2) {
		super(string);
		text = string2;
	}

	@Override
	public void renderPage(int x, int y, FontRenderer fontRenderer, GuiSteamcraftBook book, RenderItem renderer, boolean isFirstPage) {
		super.renderPage(x, y, fontRenderer, book, renderer, isFirstPage);
		
		int yOffset = y+30;
		if (isFirstPage) {
			yOffset = y+40;
		}
		String s = I18n.format(text);
		String stringLeft = s;
		while (stringLeft.indexOf("<br>") != -1) {
			String output = stringLeft.substring(0, stringLeft.indexOf("<br>"));
		    int l = fontRenderer.splitStringWidth(output, 110);
		    fontRenderer.drawSplitString(output, x +40, yOffset, 110, 0);
		    yOffset+=this.getSplitStringHeight(fontRenderer, output, x +40, yOffset, 110);
		    yOffset+=10;
		    stringLeft =  stringLeft.substring(stringLeft.indexOf("<br>")+4, stringLeft.length());

		}
		String output = stringLeft;
		int l = fontRenderer.splitStringWidth(output, 110);
	    fontRenderer.drawSplitString(output, x +40, yOffset, 110, 0);
	}
	

    protected int getSplitStringHeight(FontRenderer fontRenderer, String par1Str, int par2, int par3, int par4)
    {
        List list = fontRenderer.listFormattedStringToWidth(par1Str, par4);
        int initialPar3 = par3;
        for (Iterator iterator = list.iterator(); iterator.hasNext(); par3 += fontRenderer.FONT_HEIGHT)
        {
            String s1 = (String)iterator.next();
        }
        return par3 - initialPar3;
    }
}