package io.github.vampirestudios.raa.items.dimension;

import com.ibm.icu.text.MessageFormat;
import io.github.vampirestudios.raa.generation.dimensions.data.DimensionData;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import org.apache.commons.lang3.text.WordUtils;

public class DimensionalAxeItem extends AxeItem {

    private DimensionData dimensionData;

    public DimensionalAxeItem(DimensionData dimensionData, ToolMaterial toolMaterial_1, float int_1, float float_1, Settings item$Settings_1) {
        super(toolMaterial_1, int_1, float_1, item$Settings_1);
        this.dimensionData = dimensionData;
    }

    @Override
    public Text getName(ItemStack itemStack_1) {
        MessageFormat format = new MessageFormat(new TranslatableText("text.raa.item.axe").asString());
        Object[] data = {WordUtils.capitalize(dimensionData.getName()), WordUtils.uncapitalize(dimensionData.getName()),
                WordUtils.uncapitalize(dimensionData.getName()).charAt(0), WordUtils.uncapitalize(dimensionData.getName()).charAt(dimensionData.getName().length() - 1)};
        return new LiteralText(format.format(data));
    }

}
