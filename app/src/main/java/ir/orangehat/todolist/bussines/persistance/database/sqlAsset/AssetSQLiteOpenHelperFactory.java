package ir.orangehat.todolist.bussines.persistance.database.sqlAsset;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;

/**
 * AssetSQLiteOpenHelperFactory
 */

public class AssetSQLiteOpenHelperFactory implements SupportSQLiteOpenHelper.Factory {
    @Override
    public SupportSQLiteOpenHelper create(SupportSQLiteOpenHelper.Configuration configuration) {
        return new AssetSQLiteOpenHelper(configuration.context, configuration.name, null,
                configuration.callback.version, new DatabaseErrorHandler() {
            @Override
            public void onCorruption(SQLiteDatabase sqLiteDatabase) {
                // TODO
            }
        }, configuration.callback);
    }
}
