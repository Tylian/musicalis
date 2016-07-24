package net.tylian.musicalis.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tylian.musicalis.Musicalis;
import net.tylian.musicalis.common.constants.ConstResources;
import net.tylian.musicalis.common.items.BaseItem;
import net.tylian.musicalis.common.items.misc.ItemGrassHorn;

public class RegisteryManager {

	public static Item GRASS_HORN;

	public static CreativeTabs CREATIVE_TAB = new CreativeTabs(Musicalis.MOD_ID) {
		@Override
		public String getTabLabel() {
			return Musicalis.MOD_ID;
		}

		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem() {
			return GRASS_HORN;
		}
	};

	public static void init() {
		registerItems();
	}

	public static void registerItems() {
		GRASS_HORN = new ItemGrassHorn();
	}
}
