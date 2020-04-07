package io.github.vampirestudios.raa.generation.materials;

import com.google.gson.JsonElement;
import io.github.vampirestudios.raa.api.enums.OreType;
import io.github.vampirestudios.raa.api.enums.TextureTypes;
import io.github.vampirestudios.raa.generation.materials.data.*;
import io.github.vampirestudios.raa.effects.MaterialEffects;
import io.github.vampirestudios.raa.utils.Rands;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;

import java.util.Map;

public class Material {
    private OreInformation oreInformation;
    private Identifier id;
    private String name;
    private MaterialTexturesInformation texturesInformation;
    private Map<MaterialEffects, JsonElement> specialEffects;
    private int color;
    private int miningLevel;
    private boolean armor;
    private CustomArmorMaterial armorMaterial;
    private boolean tools;
    private boolean weapons;
    private CustomToolMaterial toolMaterial;
    private MaterialFoodData foodData;
    private boolean glowing;
    private boolean oreFlower;
    private boolean food;
    private float compostableAmount;
    private boolean compostable;
    private boolean beaconBase;

    Material(OreInformation oreInformation, Identifier id, String name, MaterialTexturesInformation texturesInformation, int color, int miningLevel, boolean armor,
             CustomArmorMaterial armorMaterial, boolean tools, boolean weapons, CustomToolMaterial toolMaterial, boolean glowing, boolean oreFlower, boolean food,
             MaterialFoodData materialFoodData, float compostableAmount, boolean compostable, boolean beaconBase, Map<MaterialEffects, JsonElement> specialEffects) {
        this.oreInformation = oreInformation;
        this.id = id;
        this.name = name;
        this.color = color;
        this.miningLevel = miningLevel;
        this.texturesInformation = texturesInformation;
        this.armor = armor;
        this.armorMaterial = armorMaterial;
        this.tools = tools;
        this.weapons = weapons;
        this.toolMaterial = toolMaterial;
        this.glowing = glowing;
        this.oreFlower = oreFlower;
        this.food = food;
        this.foodData = materialFoodData;
        this.compostableAmount = compostableAmount;
        this.compostable = compostable;
        this.beaconBase = beaconBase;
        this.specialEffects = specialEffects;
    }

    public OreInformation getOreInformation() {
        return oreInformation;
    }

    public Identifier getId() {
        return id;
    }

    public String getName() {
        return name.toLowerCase();
    }

    @Deprecated
    public void setName(String name) {
        this.name = name;
    }

    public MaterialTexturesInformation getTexturesInformation() {
        return texturesInformation;
    }

    public int getColor() {
        return color;
    }

    public boolean hasArmor() {
        return armor;
    }

    public boolean hasTools() {
        return tools;
    }

    public boolean hasWeapons() {
        return weapons;
    }

    public boolean isGlowing() {
        return glowing;
    }

    public boolean hasOreFlower() {
        return oreFlower;
    }

    public boolean hasFood() {
        return food;
    }

    public CustomToolMaterial getToolMaterial() {
        return toolMaterial;
    }

    public CustomArmorMaterial getArmorMaterial() {
        return armorMaterial;
    }

    public int getMiningLevel() {
        return miningLevel;
    }

    public MaterialFoodData getFoodData() {
        return foodData;
    }

    public float getCompostableAmount() {
        return compostableAmount;
    }

    public boolean isCompostable() {
        return compostable;
    }

    public boolean isBeaconBase() {
        return beaconBase;
    }

    public Map<MaterialEffects, JsonElement> getSpecialEffects() {
        return specialEffects;
    }

    public static class Builder {

        private OreType oreType;
        private Identifier id;
        private String name;
        private int RGB = -1;
        private Identifier generatesIn;
        private int oreCount;
        private CustomArmorMaterial armorMaterial;
        private CustomToolMaterial toolMaterial;
        private MaterialFoodData foodData;
        private Map<MaterialEffects, JsonElement> specialEffects;
        private boolean armor = false;
        private boolean tools = false;
        private boolean weapons = false;
        private boolean glowing = false;
        private boolean oreFlower = false;
        private boolean food = false;
        private int minXPAmount = 0;
        private int maxXPAmount = 10;
        private int oreClusterSize = 9;
        private int miningLevel;
        private float compostableAmount;
        private boolean compostable;
        private boolean beaconBase;

        protected Builder() {
            oreCount = Rands.randInt(30) + 1;
            miningLevel = Rands.randInt(5);
        }

        @Deprecated
        public static Builder create() {
            return new Builder();
        }

        public static Builder create(Identifier id, String name) {
            Builder builder = new Builder();
            builder.id = id;
            builder.name = name;
            return builder;
        }

        public Builder oreCount(int oreCount) {
            this.oreCount = oreCount;
            return this;
        }

        public Builder miningLevel(int miningLevel) {
            this.miningLevel = miningLevel;
            return this;
        }

        public Builder id(Identifier id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder oreType(OreType oreType) {
            this.oreType = oreType;
            return this;
        }

        public Builder color(int RGB) {
            this.RGB = RGB;
            return this;
        }

        public Builder target(Identifier target) {
            this.generatesIn = target;
            return this;
        }

        public Builder armor(boolean armor) {
            this.armor = armor;
            return this;
        }

        public Builder compostbleAmount(float compostbleAmount) {
            this.compostableAmount = compostbleAmount;
            return this;
        }

        public Builder compostable(boolean compostable) {
            this.compostable = compostable;
            return this;
        }

        public Builder armor(CustomArmorMaterial armorMaterial) {
            this.armor = true;
            this.armorMaterial = armorMaterial;
            return this;
        }

        public Builder foodData(MaterialFoodData foodData) {
            this.foodData = foodData;
            return this;
        }

        public Builder tools(boolean tools) {
            this.tools = tools;
            return this;
        }

        public Builder tools(CustomToolMaterial toolMaterial) {
            this.tools = true;
            this.toolMaterial = toolMaterial;
            return this;
        }

        public Builder weapons(boolean weapons) {
            this.weapons = weapons;
            return this;
        }

        public Builder weapons(CustomToolMaterial toolMaterial) {
            this.weapons = true;
            this.toolMaterial = toolMaterial;
            return this;
        }

        public Builder glowing(boolean glowing) {
            this.glowing = glowing;
            return this;
        }

        public Builder oreFlower(boolean oreFlower) {
            this.oreFlower = oreFlower;
            return this;
        }

        public Builder food(boolean food) {
            this.food = food;
            return this;
        }

        public Builder minXPAmount(int minXPAmount) {
            this.minXPAmount = minXPAmount;
            return this;
        }

        public Builder maxXPAmount(int maxXPAmount) {
            this.maxXPAmount = maxXPAmount;
            return this;
        }

        public Builder oreClusterSize(int oreClusterSize) {
            this.oreClusterSize = oreClusterSize;
            return this;
        }

        public Builder beaconBase(boolean beaconBase) {
            this.beaconBase = beaconBase;
            return this;
        }

        public Builder specialEffects(Map<MaterialEffects, JsonElement> specialEffects) {
            this.specialEffects = specialEffects;
            return this;
        }

        public Material build() {
            if (id == null || name == null) {
                throw new IllegalStateException("A Material must not have a null name or identifier");
            }

            if (armor && armorMaterial == null) {
                this.armorMaterial = CustomArmorMaterial.generate(id, oreType);
            }
            if ((tools || weapons) && toolMaterial == null) {
                this.toolMaterial = CustomToolMaterial.generate(id, oreType, miningLevel);
            }

            Identifier overlayTexture;
            if (oreType == OreType.METAL) overlayTexture = Rands.list(TextureTypes.METAL_ORE_TEXTURES);
            else if (oreType == OreType.GEM) overlayTexture = Rands.list(TextureTypes.GEM_ORE_TEXTURES);
            else overlayTexture = Rands.list(TextureTypes.CRYSTAL_ORE_TEXTURES);

            Identifier storageBlockTexture;
            if (oreType == OreType.METAL) storageBlockTexture = Rands.list(TextureTypes.METAL_BLOCK_TEXTURES);
            else if (oreType == OreType.GEM) storageBlockTexture = Rands.list(TextureTypes.GEM_BLOCK_TEXTURES);
            else storageBlockTexture = Rands.list(TextureTypes.CRYSTAL_BLOCK_TEXTURES);

            Identifier resourceItemTexture;
            if (oreType == OreType.METAL) resourceItemTexture = Rands.list(TextureTypes.INGOT_TEXTURES);
            else if (oreType == OreType.GEM) resourceItemTexture = Rands.list(TextureTypes.GEM_ITEM_TEXTURES);
            else resourceItemTexture = Rands.list(TextureTypes.CRYSTAL_ITEM_TEXTURES);

            Identifier nuggetTexture;
            if (oreType == OreType.METAL) nuggetTexture = Rands.list(TextureTypes.METAL_NUGGET_TEXTURES);
            else nuggetTexture = null;

            Identifier plateTexture;
            if (oreType == OreType.METAL) plateTexture = Rands.list(TextureTypes.METAL_PLATE_TEXTURES);
            else plateTexture = null;

            Identifier gearTexture;
            if (oreType == OreType.METAL) gearTexture = Rands.list(TextureTypes.METAL_GEAR_TEXTURES);
            else gearTexture = null;

            Identifier dustTexture;
            if (oreType == OreType.METAL) dustTexture = Rands.list(TextureTypes.DUST_TEXTURES);
            else dustTexture = null;

            Identifier smallDustTexture;
            if (oreType == OreType.METAL) smallDustTexture = Rands.list(TextureTypes.SMALL_DUST_TEXTURES);
            else smallDustTexture = null;

            Map.Entry<Identifier, Identifier> pickaxe = Rands.map(TextureTypes.PICKAXES);
            Map.Entry<Identifier, Identifier> axe = Rands.map(TextureTypes.AXES);
            Map.Entry<Identifier, Identifier> hoe = Rands.map(TextureTypes.HOES);
            Map.Entry<Identifier, Identifier> sword = Rands.map(TextureTypes.SWORDS);
            Map.Entry<Identifier, Identifier> shovel = Rands.map(TextureTypes.SHOVELS);

            MaterialTexturesInformation texturesInformation = MaterialTexturesInformation.Builder.create()
                    .pickaxeTexture(new Pair<>(pickaxe.getKey(), pickaxe.getValue()))
                    .axeTexture(new Pair<>(axe.getKey(), axe.getValue()))
                    .hoeTexture(new Pair<>(hoe.getKey(), hoe.getValue()))
                    .swordTexture(new Pair<>(sword.getKey(), sword.getValue()))
                    .shovelTexture(new Pair<>(shovel.getKey(), shovel.getValue()))
                    .helmetTexture(Rands.list(TextureTypes.HELMET_TEXTURES))
                    .chestplateTexture(Rands.list(TextureTypes.CHESTPLATE_TEXTURES))
                    .leggingsTexture(Rands.list(TextureTypes.LEGGINGS_TEXTURES))
                    .bootsTexture(Rands.list(TextureTypes.BOOTS_TEXTURES))
                    .overlayTexture(overlayTexture)
                    .storageBlockTexture(storageBlockTexture)
                    .resourceItemTexture(resourceItemTexture)
                    .dustTexture(dustTexture)
                    .smallDustTexture(smallDustTexture)
                    .gearTexture(gearTexture)
                    .plateTexture(plateTexture)
                    .nuggetTexture(nuggetTexture)
                    .fruitTexture(Rands.list(TextureTypes.FRUIT_TEXTURES))
                    .build();

            OreInformation oreInformation = new OreInformation(oreType, generatesIn, oreCount, minXPAmount, maxXPAmount, oreClusterSize);

            return new Material(oreInformation, id, name, texturesInformation, RGB, miningLevel, armor, armorMaterial, tools, weapons, toolMaterial, glowing, oreFlower,
                    food, foodData, compostableAmount, compostable, beaconBase, specialEffects);
        }
    }

}