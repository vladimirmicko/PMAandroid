package data.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.randjelovic.vladimir.myapplication.common.MyApplication;

import java.util.ArrayList;
import java.util.List;

import data.database.DbHelper;
import data.models.Slide;
import data.models.Test;

/**
 * Created by Vladimir on 1/6/2017.
 */

public class TestDao {

    public static final String KEY_ID = "id";
    public static final String KEY_TEST_NAME = "test_name";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_PROMO_IMAGE = "promo_image";
    public static final String KEY_CREATION_DATE = "creation_date";

    public static final String TABLE_TESTS = "tests";

    public static final String CREATE_TABLE_TESTS = "CREATE TABLE " + TABLE_TESTS + "("
            + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_TEST_NAME + " TEXT,"
            + KEY_DESCRIPTION + " TEXT,"
            + KEY_PROMO_IMAGE + " BLOB,"
            + KEY_CREATION_DATE + " DATETIME" + ")";

    private static final String LOG = TestDao.class.getName();
    private DbHelper dbHelper;
    private SlideDao slideDao;

    public TestDao() {
        dbHelper = new DbHelper(MyApplication.getAppContext());
        slideDao = new SlideDao();
    }

    public Test mapper(Cursor c) {
        Test test = new Test();
        test.setId(c.getLong(c.getColumnIndex(KEY_ID)));
        test.setTestName(c.getString(c.getColumnIndex(KEY_TEST_NAME)));
        test.setCreationDate(c.getString(c.getColumnIndex(KEY_DESCRIPTION)));
        test.setTestPromoImage(c.getBlob(c.getColumnIndex(KEY_PROMO_IMAGE)));
        test.setCreationDate(c.getString(c.getColumnIndex(KEY_CREATION_DATE)));
        return test;
    }

    public ContentValues mapper(Test test) {
        ContentValues values = new ContentValues();
        values.put(KEY_ID, test.getId());
        values.put(KEY_TEST_NAME, test.getTestName());
        values.put(KEY_DESCRIPTION, test.getTestName());
        values.put(KEY_PROMO_IMAGE, test.getTestPromoImage());
        values.put(KEY_CREATION_DATE, dbHelper.getDateTime());
        return values;
    }

    public Long insert(Test test) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = mapper(test);
        Long test_id = db.insert(TABLE_TESTS, null, values);
        return test_id;
    }

    public Test getTestById(Long id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_TESTS + " WHERE " + KEY_ID + " = ?";
        Log.e(LOG, selectQuery);
        Cursor c = db.rawQuery(selectQuery, new String[]{String.valueOf(id)});
        if (c != null) c.moveToFirst();
        Test test = mapper(c);
        List<Slide> slideList = slideDao.getAllSlidesByTest(test.getId());
        test.setSlideList(slideList);
        return test;
    }

    public List<Test> getAll() {
        List<Test> testList = new ArrayList<Test>();
        String selectQuery = "SELECT  * FROM " + TABLE_TESTS;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                Test test = mapper(c);
                List<Slide> slideList = slideDao.getAllSlidesByTest(test.getId());
                test.setSlideList(slideList);
                testList.add(test);
            } while (c.moveToNext());
        }
        return testList;
    }

    public int updateTest(Test test) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = mapper(test);
        return db.update(TABLE_TESTS, values, KEY_ID + " = ?", new String[]{String.valueOf(test.getId())});
    }

    public void delete(Long id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        SlideDao slideDao = new SlideDao();
        List<Slide> slideList = slideDao.getAllSlidesByTest(id);
        for (Slide slide : slideList) {
            slideDao.delete(slide.getId());
        }
        db.delete(TABLE_TESTS, KEY_ID + " = ?", new String[]{String.valueOf(id)});
    }

    public DbHelper getDbHelper() {
        return dbHelper;
    }

    public void setDbHelper(DbHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public SQLiteDatabase getDb() {
        return dbHelper.getReadableDatabase();
    }

}
