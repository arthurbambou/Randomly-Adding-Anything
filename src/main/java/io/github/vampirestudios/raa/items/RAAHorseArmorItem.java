package io.github.vampirestudios.raa.items;

import com.ibm.icu.text.MessageFormat;
import io.github.vampirestudios.raa.RandomlyAddingAnything;
import io.github.vampirestudios.raa.api.enums.TextureTypes;
import io.github.vampirestudios.raa.generation.materials.Material;
import io.github.vampirestudios.raa.utils.Rands;
import net.minecraft.item.DyeableHorseArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import org.apache.commons.lang3.text.WordUtils;

public class RAAHorseArmorItem extends DyeableHorseArmorItem {

    private final Identifier entityTexture;
    private Material material;

    public RAAHorseArmorItem(Material material) {
        super(material.getArmorMaterial().getHorseArmorBonus(), material.getName().toLowerCase(), (new Item.Settings()).maxCount(1)
                .group(RandomlyAddingAnything.RAA_ARMOR));
        this.material = material;
        this.entityTexture = Rands.list(TextureTypes.HORSE_ARMOR_MODEL_TEXTURES);
    }

    @Override
    public Text getName(ItemStack itemStack_1) {
        MessageFormat format = new MessageFormat(new TranslatableText("text.raa.item.horse_armor").asString());
        Object[] data = {WordUtils.capitalize(material.getName()), WordUtils.uncapitalize(material.getName()),
                WordUtils.uncapitalize(material.getName()).charAt(0), WordUtils.uncapitalize(material.getName()).charAt(material.getName().length() - 1)};
        return new LiteralText(format.format(data));
    }

    @Override
    public int getColor(ItemStack itemStack_1) {
        return material.getColor();
    }

    @Override
    public Identifier getEntityTexture() {
        return entityTexture;
    }

}
