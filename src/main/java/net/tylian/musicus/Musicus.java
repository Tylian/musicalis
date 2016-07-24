package net.tylian.musicus;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = Musicus.MOD_ID, name = Musicus.MOD_NAME, version = Musicus.VERSION)
public class Musicus
{
	public static final String MOD_ID = "Musicus";
	public static final String MOD_NAME = "Musicus: Dynamics of Sound";
	public static final String BUILD = "GRADLE:BUILD";
	public static final String VERSION = "GRADLE:VERSION-" + BUILD;	
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // some example code
        System.out.println("DIRT BLOCK >> "+Blocks.DIRT.getUnlocalizedName());
    }
}
