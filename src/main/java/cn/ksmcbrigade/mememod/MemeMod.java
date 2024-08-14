package cn.ksmcbrigade.mememod;

import cn.ksmcbrigade.mememod.items.Lighter;
import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class MemeMod implements ModInitializer {

    public static final String modID = "mememod";

    public static final Item LIGHTER = new Lighter();

    @Override
    public void onInitialize() {
        Registry.register(Registries.ITEM,new Identifier(modID,"lighter"),LIGHTER);
    }
}
