package cn.ksmcbrigade.mememod.items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FoodComponents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.world.explosion.ExplosionBehavior;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Lighter extends Item {
    public Lighter() {
        super(new FabricItemSettings().food(FoodComponents.BEEF).maxDamage(64));
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        context.getWorld().setBlockState(context.getBlockPos().up(), Blocks.FIRE.getDefaultState());
        if(context.getPlayer()!=null && !context.getPlayer().isCreative()) context.getStack().setDamage(context.getStack().getDamage()+1);
        return super.useOnBlock(context);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        entity.setFireTicks(85);
        if(user!=null && !user.isCreative()) stack.setDamage(stack.getDamage()+1);
        return super.useOnEntity(stack, user, entity, hand);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        world.createExplosion(user,null,new ExplosionBehavior(),user.getPos(),5,true, World.ExplosionSourceType.TNT);
        if((user instanceof PlayerEntity player) && !player.isCreative()) stack.setCount(stack.getCount()-1);
        return super.finishUsing(stack, world, user);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.of("可食用"));
        super.appendTooltip(stack, world, tooltip, context);
    }
}
