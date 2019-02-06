package dushanes.caloriecounterub.Database;

public final class healthInfoContract{

    public healthInfoContract() {}

    public static final class healthInfo{
        public static final String tableName = "healthInfo";
        public static final String columnId = "id";
        public static final String columnName = "name";
        public static final String columnGender = "gender";
        public static final String columnAge = "age";
        public static final String columnWeight = "weight";
        public static final String columnHeight = "height";
        public static final String columnActivity = "activity";
        public static final String columnCalories = "calorie";
        public static final String columnBmi = "BMI";
    }

    public static final String sqlCreateEntry =
            "CREATE TABLE " + healthInfo.tableName + " (" +
                    healthInfo.columnId + " INTEGER," +
                    healthInfo.columnName + " TEXT," +
                    healthInfo.columnGender + " TEXT," +
                    healthInfo.columnAge + " TEXT," +
                    healthInfo.columnWeight + " TEXT," +
                    healthInfo.columnHeight + " TEXT," +
                    healthInfo.columnActivity + " TEXT," +
                    healthInfo.columnCalories + " INTEGER," +
                    healthInfo.columnBmi + " TEXT)";

    public static final String sqlDeleteTable =
            "DROP TABLE IF EXISTS " + healthInfo.tableName;

}
