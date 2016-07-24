package net.tylian.musicalis.common.items;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tylian.musicalis.common.RegisteryManager;
import net.tylian.musicalis.common.constants.ConstResources;

public class BaseItem extends Item  {
	public static final List<BaseItem> registeredItems = new ArrayList();
	
	private final String[] variants;
	private final String name;
	
	public BaseItem(String name, String... variants) {
		setUnlocalizedName(name);
		setCreativeTab(RegisteryManager.CREATIVE_TAB);
		
		if(variants.length > 1) {
			setHasSubtypes(true);
		} else {
			variants = new String[] { name };
		}

		this.variants = variants;
		this.name = name;
		
		registeredItems.add(this);
	}

	@Override
	public Item setUnlocalizedName(String name) {
		super.setUnlocalizedName(name);
		GameRegistry.register(this, new ResourceLocation(ConstResources.PREFIX_MOD + name));

		return this;
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		int damage = stack.getItemDamage();
		
		String name;
		if(damage >= variants.length)
			name = this.name;
		else name = variants[damage];
		
		return "item." + name;
	}
    
    @Override
	public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> subItems) {
    	for(int i = 0; i < variants.length; i++)
    		subItems.add(new ItemStack(item, 1, i));
    }
    
    public String[] getVariants() {
		return variants;
	}

}
