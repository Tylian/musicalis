package net.tylian.musicalis.client.proxy;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.tylian.musicalis.common.RegisteryManager;
import net.tylian.musicalis.common.constants.ConstResources;
import net.tylian.musicalis.common.items.BaseItem;
import net.tylian.musicalis.common.proxy.CommonProxy;

public class ClientProxy extends CommonProxy {

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
		registerModels();
	}

	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
	}

	public void registerModels() {
		for(BaseItem item : BaseItem.registeredItems) {
			String[] variants = item.getVariants();
			for(int i = 0; i < variants.length; i++) {
				String name = ConstResources.PREFIX_MOD + variants[i];
				System.out.println("Setting model for " + name);
				ModelLoader.setCustomModelResourceLocation(item, i, new ModelResourceLocation(name, "inventory"));
			}
			
		}
	}
}
