package dushanes.caloriecounterub.Database;

class foodContract {

    public foodContract () {}

    private static class foodInfo{
        private static final String tableName = "foods";
        private static final String columnFoodName = "Name";
        private static final String columnBrand = "Brand";
        private static final String columnMeal = "Meal";
        private static final String columnCalories = "Calories";
        private static final String columnProtein = "Protein";
        private static final String columnCarbs = "Carbs";
        private static final String columnFat = "Fat";
    }

    public static final String sqlCreateEntry =
            "CREATE TABLE " + foodInfo.tableName + " (" +
                    foodInfo.columnFoodName + "TEXT," +
                    foodInfo.columnBrand + "TEXT," +
                    foodInfo.columnMeal + "TEXT," +
                    foodInfo.columnCalories + " INTEGER," +
                    foodInfo.columnProtein + " INTEGER," +
                    foodInfo.columnCarbs + " INTEGER" +
                    foodInfo.columnFat + "INTEGER)";

    public static final String sqlDeleteTable =
            "DROP TABLE IF EXISTS " + foodInfo.tableName;
}
