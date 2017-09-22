package juanjo.googlesamples.mytodoapp.data.source.local;

import android.provider.BaseColumns;

/**
 * Created by juanjoberenguer on 22/9/17.
 */

public class TasksPersistenceContract {

    public TasksPersistenceContract() {
    }

    public static abstract class TaskEntry implements BaseColumns {
        public static final String TABLE_NAME = "tasks";
        public static final String COLUMN_NAME_ENTRY_ID = "entryid";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_COMPLETED = "completed";
    }
}
