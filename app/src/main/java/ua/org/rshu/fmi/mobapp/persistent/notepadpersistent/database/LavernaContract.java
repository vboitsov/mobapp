package ua.org.rshu.fmi.mobapp.persistent.notepadpersistent.database;

/**
 * @author Vadim Boitsov <vadimboitsov1@gmail.com>
 */

public final class LavernaContract {
    private static final String CREATE_TABLE_IF_NOT_EXISTS = "CREATE TABLE IF NOT EXISTS";
    private static final String DROP_TABLE_IF_EXISTS = "DROP TABLE IF EXISTS";

    private LavernaContract() {}

    /**
     * An abstract class which is extended by all other table classes.
     */
    public static abstract class LavernaBaseTable {
        private LavernaBaseTable(){}

        public static final String COLUMN_ID = "id";
    }

    /**
     * A table of notes.
     */
    public final static class NotesTable extends LavernaBaseTable {
        private NotesTable() {}

        public static final String TABLE_NAME = "notes";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_CREATION_TIME = "creation_time";
        public static final String COLUMN_UPDATE_TIME = "update_time";
        public static final String COLUMN_CONTENT = "content";
        public static final String COLUMN_HTML_CONTENT = "html_content";

        static final String SQL_CREATE_NOTES_TABLE =
                CREATE_TABLE_IF_NOT_EXISTS + " " + TABLE_NAME + " ("
                        + COLUMN_ID + " TEXT PRIMARY KEY,"
                        + COLUMN_TITLE + " TEXT,"
                        + COLUMN_CREATION_TIME + " INTEGER,"
                        + COLUMN_UPDATE_TIME + " INTEGER,"
                        + COLUMN_CONTENT + " TEXT,"
                        + COLUMN_HTML_CONTENT + " TEXT)";

        static final String SQL_DELETE_NOTES_TABLE = DROP_TABLE_IF_EXISTS + " " + TABLE_NAME;
    }

    /**
     * A table of tags.
     */
    public final static class TagsTable extends LavernaBaseTable {
        private TagsTable() {}

        public static final String TABLE_NAME = "tags";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_CREATION_TIME = "creation_time";
        public static final String COLUMN_UPDATE_TIME = "update_time";

        static final String SQL_CREATE_TAGS_TABLE =
                CREATE_TABLE_IF_NOT_EXISTS + " " + TABLE_NAME + " ("
                        + COLUMN_ID + " TEXT PRIMARY KEY,"
                        + COLUMN_NAME + " TEXT unique,"
                        + COLUMN_CREATION_TIME + " INTEGER,"
                        + COLUMN_UPDATE_TIME + " INTEGER)";

        static final String SQL_DELETE_TAGS_TABLE = DROP_TABLE_IF_EXISTS + " " + TABLE_NAME;
    }

    /**
     * A junction table of a many-to-many relationship between the notes table and the tags table.
     */
    public final static class NotesTagsTable {
        private NotesTagsTable() {}

        public static final String TABLE_NAME = "notes_tags";
        public static final String COLUMN_NOTE_ID = "note_id";
        public static final String COLUMN_TAG_ID = "tag_id";

        static final String SQL_CREATE_NOTES_TAGS_TABLE =
                CREATE_TABLE_IF_NOT_EXISTS + " " + TABLE_NAME + " ("
                        + COLUMN_NOTE_ID + " TEXT,"
                        + COLUMN_TAG_ID + " TEXT,"
                        + "FOREIGN KEY (" + COLUMN_NOTE_ID + ") REFERENCES "
                        + NotesTable.TABLE_NAME +"(" + LavernaBaseTable.COLUMN_ID + ") ON DELETE CASCADE,"
                        + "FOREIGN KEY (" + COLUMN_TAG_ID + ") REFERENCES "
                        + TagsTable.TABLE_NAME +"(" + LavernaBaseTable.COLUMN_ID + ") ON DELETE CASCADE)";

        static final String SQL_DELETE_NOTES_TAGS_TABLE = DROP_TABLE_IF_EXISTS + " " + TABLE_NAME;

    }
}
