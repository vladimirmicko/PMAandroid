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

public class SlideDao {

    public static final String KEY_ID = "id";
    public static final String KEY_SLIDE_NAME = "slide_name";
    public static final String KEY_DELAY = "delay";
    public static final String KEY_PRIMING_IMAGE = "priming_image";
    public static final String KEY_TEST_IMAGE = "test_image";
    public static final String KEY_TEST_ID = "test_id";

    public static final String TABLE_SLIDES = "slides";

    public static final String CREATE_TABLE_SLIDES = "CREATE TABLE " + TABLE_SLIDES
            + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_SLIDE_NAME + " TEXT,"
            + KEY_DELAY + " INTEGER,"
            + KEY_PRIMING_IMAGE + " BLOB,"
            + KEY_TEST_IMAGE + " BLOB,"
            + KEY_TEST_ID + " INTEGER" + ")";


    private static final String LOG = SlideDao.class.getName();
    private DbHelper dbHelper;

    public SlideDao() {
        dbHelper = new DbHelper(MyApplication.getAppContext());
    }

    public Slide mapper(Cursor c){
        Slide slide = new Slide();
        slide.setId(c.getLong((c.getColumnIndex(KEY_ID))));
        slide.setSlideName(c.getString(c.getColumnIndex(KEY_SLIDE_NAME)));
        slide.setDelay(c.getInt(c.getColumnIndex(KEY_DELAY)));
        slide.setPrimingImage(c.getBlob(c.getColumnIndex(KEY_PRIMING_IMAGE)));
        slide.setTestImage(c.getBlob(c.getColumnIndex(KEY_TEST_IMAGE)));
        slide.setTestId(c.getLong(c.getColumnIndex(KEY_TEST_ID)));
        return slide;
    }

    public ContentValues mapper(Slide slide){
        ContentValues values = new ContentValues();
        values.put(KEY_ID, slide.getId());
        values.put(KEY_SLIDE_NAME, slide.getSlideName());
        values.put(KEY_DELAY, slide.getDelay());
        values.put(KEY_PRIMING_IMAGE, slide.getPrimingImage());
        values.put(KEY_TEST_IMAGE, slide.getTestImage());
        values.put(KEY_TEST_ID, slide.getTestId());
        return values;
    }


    public Long insert(Slide slide) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = mapper(slide);
        Long slideId = db.insert(TABLE_SLIDES, null, values);
        return slideId;
    }


    public Long insert(Slide slide, SQLiteDatabase db) {
        ContentValues values = mapper(slide);
        Long slideId = db.insert(TABLE_SLIDES, null, values);
        return slideId;
    }


    public List<Slide> getAllSlidesByTest(Long id) {
        List<Slide> slideList = new ArrayList<Slide>();
        String selectQuery = "SELECT  * FROM " + TABLE_SLIDES + " WHERE " + KEY_TEST_ID + " = ?";

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, new String[] {String.valueOf(id)});

        if (c.moveToFirst()) {
            do {
                Slide slide = mapper(c);
                slideList.add(slide);
            } while (c.moveToNext());
        }
        return slideList;
    }


    public int update(Slide slide) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = mapper(slide);
        return db.update(TABLE_SLIDES, values, KEY_ID + " = ?", new String[] { String.valueOf(slide.getId()) });
    }


    public void delete(Long id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TABLE_SLIDES, KEY_ID + " = ?", new String[] { String.valueOf(id) });
    }


    public SQLiteDatabase getDb() {
        return dbHelper.getReadableDatabase();
    }


    public DbHelper getDbHelper() {
        return dbHelper;
    }

    public void setDbHelper(DbHelper dbHelper) {
        this.dbHelper = dbHelper;
    }
}
