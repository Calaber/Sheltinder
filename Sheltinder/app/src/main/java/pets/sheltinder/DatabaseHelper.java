package pets.sheltinder;

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;

class DataBaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contacts.db";

    private static final String TABLE_NAME = "contacts";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_ACCESS_TYPE = "isShelter";

    private SQLiteDatabase db;

    private static final String TABLE_CREATE = "create table contacts " +
            "(id integer primary key not null, username text no null, " +
            "password text not null, isShelter boolean false)";

    DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db =db;
    }

    void insertContact(Contact c) {
        db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        String query = "select * from contacts";
        Cursor cursor = db.rawQuery(query, null);

        int count = cursor.getCount();
        cursor.close();

        values.put(COLUMN_ID, count);
        values.put(COLUMN_USERNAME, c.getUsername());
        values.put(COLUMN_PASSWORD, c.getPassword());
        values.put(COLUMN_ACCESS_TYPE, c.getAccessType());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    String searchPassword(String username) {
        db = this.getReadableDatabase();

        String query = "select username, password from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        String a, b;
        b = "not found";

        if (cursor.moveToFirst()) {
            do {
                a = cursor.getString(0);
                b = cursor.getString(1);

                if (a.equals(username)) {
                    b = cursor.getString(1);
                    break;
                }

            } while(cursor.moveToNext());
        }

        cursor.close();

        return b;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IP EXISTS" + TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }
}

