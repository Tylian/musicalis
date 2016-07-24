package net.tylian.musicalis;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.tylian.musicalis.common.constants.ConstResources;
import net.tylian.musicalis.common.proxy.CommonProxy;

@Mod(modid = Musicalis.MOD_ID, name = Musicalis.MOD_NAME, version = Musicalis.VERSION)
public class Musicalis {
	
	public static final String MOD_ID = "musicalis";
	public static final String MOD_NAME = "Musicalis: Dynamics of Sound";
	public static final String BUILD = "GRADLE:BUILD";
	public static final String VERSION = "GRADLE:VERSION-" + BUILD;

	public static final String PROXY_SERVER = "net.tylian.musicalis.server.proxy.CommonProxy";
	public static final String PROXY_CLIENT = "net.tylian.musicalis.client.proxy.ClientProxy";

	@SidedProxy(serverSide = Musicalis.PROXY_SERVER, clientSide = Musicalis.PROXY_CLIENT)
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.preInit(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init(event);
	}

}
