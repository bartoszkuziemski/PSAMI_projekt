package com.example.psami_projekt.Model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MealDatabaseHelper extends SQLiteOpenHelper {

    private static SQLiteDatabase mealDatabase;
    private final Context context;
    private static final String DATABASE_NAME = "food.db";
    public static final String DATABASE_PATH = "/data/user/0/com.example.psami_projekt/databases/";
    public static final int DATABASE_VERSION = 1;
    public static final String FOOD_TABLE_NAME = "meal";
    private static ArrayList<Meal> meals;

    public MealDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    /**
     * Check if database already exist or not
     *
     * @return true if exist
     */
    private boolean checkDataBase() {
        boolean checkDB;
        try {
            final String myPath = DATABASE_PATH + DATABASE_NAME;
            final File dbFile = new File(myPath);
            checkDB = dbFile.exists();
        } catch (SQLiteException e) {
            e.printStackTrace();
            return false;
        }
        return checkDB;
    }

    /**
     * Copy database from local assets-folder to the just created empty database in the system folder
     *
     * @throws IOException e
     */
    private void copyDataBase() throws IOException {
        try {
            InputStream mInput = context.getAssets().open(DATABASE_NAME);
            String outFileName = DATABASE_PATH + DATABASE_NAME;
            OutputStream mOutput = new FileOutputStream(outFileName);
            byte[] mBuffer = new byte[1024];
            int mLength;
            while ((mLength = mInput.read(mBuffer)) > 0) {
                mOutput.write(mBuffer, 0, mLength);
            }
            mOutput.flush();
            mOutput.close();
            mInput.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create database by copying one from local assets folder if none exist
     *
     * @throws IOException e
     */
    private void createDatabase() throws IOException {
        boolean dbExist = checkDataBase();
        if (!dbExist) {
            this.getReadableDatabase();
            this.close();
            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            } finally {
                this.close();
            }
        }
    }

    public ArrayList<Integer> getProductsFromMealByDate(String date, String meal) {

        ArrayList<Integer> searchedProducts = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select DayId from meal where DayId=? AND Meal=?",new String[]{date, meal});
        while (cursor.moveToNext()) {
            searchedProducts.add(cursor.getColumnIndexOrThrow("DayId"));
        }
        cursor.close();
        return searchedProducts;
    }

    public static void initMeals() {
        if (meals == null) {
            meals = new ArrayList<>();
            meals.add(new Meal("Breakfast"));
            meals.add(new Meal("Lunch"));
            meals.add(new Meal("Dinner"));
            meals.add(new Meal("Snack"));
            meals.add(new Meal("Supper"));
        }
    }

    public static ArrayList<Meal> getMeals() {
        return meals;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
