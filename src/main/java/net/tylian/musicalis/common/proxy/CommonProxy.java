package net.tylian.musicalis.common.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.tylian.musicalis.common.RegisteryManager;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent event) {
		RegisteryManager.init();
	}

	public void init(FMLInitializationEvent event) {
		
	}

}
