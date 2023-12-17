package com.github.L_Ender.cataclysm.crafting;

import java.lang.reflect.Type;

import com.github.alexthe666.citadel.client.model.container.JsonUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.ShapedRecipe;

public class AltarOfAmethystRecipe {
    private final NonNullList<Ingredient> ingredients;
    private ItemStack result = ItemStack.EMPTY;
    private int time = 0;

    public AltarOfAmethystRecipe(NonNullList<Ingredient> ingredients, ItemStack result, int time) {
        this.result = result;
        this.ingredients = ingredients;
        this.time = time;
    }

    private static NonNullList<Ingredient> readIngredients(JsonArray ingredientArray) {
        NonNullList<Ingredient> nonnulllist = NonNullList.create();

        for (int i = 0; i < ingredientArray.size(); ++i) {
            Ingredient ingredient = Ingredient.fromJson(ingredientArray.get(i));
            if (!ingredient.isEmpty()) {
                nonnulllist.add(ingredient);
            }
        }
        return nonnulllist;
    }

    public ItemStack getResult() {
        return result;
    }

    public NonNullList<Ingredient> getIngredients() {
        return ingredients;
    }

    public int getTime() {
        return time;
    }

    public boolean matches(ItemStack... stacks) {
        IntList taken = new IntArrayList();
        ItemStack[] copy = new ItemStack[stacks.length];
        for (int j = 0; j < copy.length; j++) {
            copy[j] = stacks[j].copy();
            for (int i = 0; i < ingredients.size(); i++) {
                if (ingredients.get(i).test(copy[j])) {
                    taken.add(j);
                    copy[j].shrink(1);
                }
            }
        }
        return taken.size() >= ingredients.size();
    }

    public static class Deserializer implements JsonDeserializer<AltarOfAmethystRecipe> {

        @Override
        public AltarOfAmethystRecipe deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonobject = json.getAsJsonObject();
            int time = JsonUtils.getInt(jsonobject, "time");
            ItemStack result = ItemStack.EMPTY;
            if (jsonobject.has("result")) {
                result = ShapedRecipe.itemStackFromJson(JsonUtils.getJsonObject(jsonobject, "result"));
            }
            NonNullList<Ingredient> nonnulllist = readIngredients(JsonUtils.getJsonArray(jsonobject, "ingredients"));
            return new AltarOfAmethystRecipe(nonnulllist, result, time);
        }

    }
}
