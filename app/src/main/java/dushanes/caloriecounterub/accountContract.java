package dushanes.caloriecounterub;

public final class accountContract{

    public accountContract() {};

    public static class accountInfo{

        public static final String tableName = "accounts";
        public static final String columnEmail = "email";
        public static final String columnPassword = "password";
        public static final String columnId = "id";
    }

    public static final String sqlCreateEntry =
            "CREATE TABLE " + accountInfo.tableName + " (" +
                    accountInfo.columnId + " INTEGER PRIMARY KEY," +
                    accountInfo.columnEmail + " TEXT," +
                    accountInfo.columnPassword + " TEXT)";

    public static final String sqlDeleteTable =
            "DROP TABLE IF EXISTS " + accountInfo.tableName;
}
