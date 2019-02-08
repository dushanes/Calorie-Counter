package dushanes.caloriecounterub.Database;

public class diariesContract {

    public diariesContract(){}

    private static class diaryInfo{
        private static final String tableName = "Diaries";
        private static final String userId = "User ID";
        private static final String columnFoodName = "Name";
        private static final String columnBrand = "Brand";
        private static final String columnMeal = "Meal";
        private static final String columnCalories = "Calories";
        private static final String columnProtein = "Protein";
        private static final String columnCarbs = "Carbs";
        private static final String columnFat = "Fat";
        private static final String columnDate = "Date";
    }

    public static final String sqlCreateEntry =
            "CREATE TABLE " + diaryInfo.tableName + " (" +
                    diaryInfo.userId + "INTEGER PRIMARY KEY NOT NULL," +
                    diaryInfo.columnFoodName + "TEXT," +
                    diaryInfo.columnBrand + "TEXT," +
                    diaryInfo.columnMeal + "TEXT," +
                    diaryInfo.columnCalories + " INTEGER," +
                    diaryInfo.columnProtein + " INTEGER," +
                    diaryInfo.columnCarbs + " INTEGER," +
                    diaryInfo.columnFat + "INTEGER," +
                    diaryInfo.columnDate + "TEXT)";

    public static final String sqlDeleteTable =
            "DROP TABLE IF EXISTS " + diaryInfo.tableName;
}
