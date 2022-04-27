package com.example.psami_projekt.Model;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private SQLiteDatabase myDataBase;
    private final Context myContext;
    private static final String DATABASE_NAME = "food.db";
    // public static final String DATABASE_PATH = "/data/data/com.example.sqltesting/databases/";
    public static final String DATABASE_PATH = "/data/user/0/com.example.psami_projekt/databases/";
    public static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.myContext = context;
    }

    //Check if database already exist or not
    private boolean checkDataBase() {
        boolean checkDB = false;
        try {
            final String myPath = DATABASE_PATH + DATABASE_NAME;
            final File dbfile = new File(myPath);
            checkDB = dbfile.exists();
        } catch (SQLiteException e) {
            e.printStackTrace();
            return false;
        }
        return checkDB;
    }

    //Copies your database from your local assets-folder to the just created empty database in the system folder
    private void copyDataBase() throws IOException {
        try {
            InputStream mInput = myContext.getAssets().open(DATABASE_NAME);
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

    public void createDatabase() throws IOException {
        boolean dbExist1 = checkDataBase();
        if (!dbExist1) {
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

    public void db_delete() {
        File file = new File(DATABASE_PATH + DATABASE_NAME);
        if (file.exists()) {
            file.delete();
            System.out.println("delete database file");
        }
    }

    public void openDatabase() throws SQLException {
        String myPath = DATABASE_PATH + DATABASE_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public synchronized void closeDataBase() throws SQLException {
        if (myDataBase != null)
            myDataBase.close();
        SQLiteDatabase.releaseMemory();
        super.close();
    }

    public ArrayList<Product> loadHandler() {
        try {
            createDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<Product> products = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from food limit 5;", null);
        while (cursor.moveToNext()) {
            Product product = new Product();
            product.setId(cursor.getInt(cursor.getColumnIndexOrThrow("Id")));
            product.setName(cursor.getString(cursor.getColumnIndexOrThrow("Category")));
            product.setDescription(cursor.getString(cursor.getColumnIndexOrThrow("Description")));

            double protein = cursor.getDouble(cursor.getColumnIndexOrThrow("DataProtein"));
            double fat = cursor.getDouble(cursor.getColumnIndexOrThrow("DataFatTotalLipid"));
            double carbs = cursor.getDouble(cursor.getColumnIndexOrThrow("DataCarbohydrate"));
            product.setProtein(protein);
            product.setFat(fat);
            product.setCarbs(carbs);

            // calculate kcal because there is none in DB
            int kcal = (int) ((protein + carbs) * 4 + fat * 9);
            product.setKcal(kcal);

            products.add(product);
        }
        cursor.close();
        db.close();
        return products;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

