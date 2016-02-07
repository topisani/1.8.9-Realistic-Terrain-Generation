package rtg.api.biome.vanilla.config;

import rtg.api.biome.BiomeConfigProperty;
import rtg.api.biome.BiomeConfigProperty.Type;


public class BiomeConfigVanillaRoofedForestM extends BiomeConfigVanillaBase
{
    public static final String decorationLogsId = "decorationLogs";
    public static final String decorationLogsName = "RTG Decoration: Logs";
    
    public BiomeConfigVanillaRoofedForestM()
    {
        super("roofedforestm");
        
        this.addProperty(new BiomeConfigProperty(decorationLogsId, Type.BOOLEAN, decorationLogsName, "", true));
    }
}
