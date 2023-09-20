package com.doudou.cutecore.util;

public interface CakeOvenConstants {

    int SLOT_COUNT = 6;
    int REMAINING_ITEMS = 4;
    int DATA_COUNT = 4;
    int INGREDIENT_SLOT_1 = 0;
    int INGREDIENT_SLOT_2 = 1;
    int INGREDIENT_SLOT_3 = 2;
    int INGREDIENT_SLOT_4 = 3;
    int FUEL_SLOT = 4;
    int RESULT_SLOT = 5;
    int INGREDIENT_SLOT_COUNT = 4;
    int DEFAULT_BURN_TIME = 200;
    int[] HOPPER_PULL_SLOTS = {RESULT_SLOT};
    int[] HOPPER_FEED_THROUGH_TOP_SLOTS = {INGREDIENT_SLOT_1, INGREDIENT_SLOT_2, INGREDIENT_SLOT_3, INGREDIENT_SLOT_4};
    int[] HOPPER_FEED_THROUGH_SIDE_SLOTS = {FUEL_SLOT};
}
