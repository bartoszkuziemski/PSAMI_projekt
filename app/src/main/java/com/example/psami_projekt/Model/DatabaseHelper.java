package com.example.psami_projekt.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static SQLiteDatabase myDataBase;
    private final Context myContext;
    private static final String DATABASE_NAME = "food.db";
    public static final String DATABASE_PATH = "/data/user/0/com.example.psami_projekt/databases/";
    public static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.myContext = context;
    }

    /**
     * Check if database already exist or not
     * @return true if exist
     */
    private boolean checkDataBase() {
        boolean checkDB;
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

    /**
     * Copies database from local assets-folder to the just created empty database in the system folder
     * @throws IOException e
     */
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

    /**
     * Init some products at the beginning
     * @return Array of products
     */
    public ArrayList<Product> initStartingProducts() {
        try {
            createDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<Product> products = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("select rowid, Category, Description, DataProtein, DataFatTotalLipid, DataCarbohydrate from food limit 20;", null);
        while (cursor.moveToNext()) {
            Product product = new Product();
            product.setId(cursor.getInt(cursor.getColumnIndexOrThrow("rowid")));
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

    public ArrayList<Product> getProductsByName(String name) {
        try {
            createDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<Product> products = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query("food", new String[] {"rowid", "Category", "Description", "DataProtein", "DataFatTotalLipid", "DataCarbohydrate"}, "Category LIKE ?", new String[] {"%" + name + "%"}, null, null, null, "20");
        while (cursor.moveToNext()) {
            Product product = new Product();
            product.setId(cursor.getInt(cursor.getColumnIndexOrThrow("rowid")));
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

    public Product getProductById(int id) {
        try {
            createDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select rowid, Category, Description, DataProtein, DataFatTotalLipid, DataCarbohydrate from food where rowid = ?" , new String[] {String.valueOf(id)});
        Product product = new Product();

        if (cursor.moveToNext()) {
            product.setId(cursor.getInt(cursor.getColumnIndexOrThrow("rowid")));
            product.setName(cursor.getString(cursor.getColumnIndexOrThrow("Category")));
            product.setDescription(cursor.getString(cursor.getColumnIndexOrThrow("Description")));

            double protein = cursor.getDouble(cursor.getColumnIndexOrThrow("DataProtein"));
            double fat = cursor.getDouble(cursor.getColumnIndexOrThrow("DataFatTotalLipid"));
            double carbs = cursor.getDouble(cursor.getColumnIndexOrThrow("DataCarbohydrate"));
            product.setProtein(protein);
            product.setFat(fat);
            product.setCarbs(carbs);

            int kcal = (int) ((protein + carbs) * 4 + fat * 9);
            product.setKcal(kcal);
        }
        cursor.close();
        db.close();
        return product;
    }

    /**
     * Checks if product exist in database before adding new one
     * @param product p
     * @return true if exist
     */
    public boolean checkIfExistInDB(Product product) {
        try {
            createDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select Category, Description from food where Category = ? AND Description = ?", new String[] {product.getName(), product.getDescription()});

        if (cursor.getCount()>0) {
            cursor.close();
            db.close();
            return true;
        } else {
            cursor.close();
            db.close();
            return false;
        }
    }

    public boolean addProduct(Product product) {
        if (checkIfExistInDB(product)){
            return false;
        }

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        String name = product.getName();
        String description = product.getDescription();
        double protein = product.getProtein();
        double fat = product.getFat();
        double carbs = product.getCarbs();
        values.put("Category", name);
        values.put("Description", description);
        values.put("DataProtein", protein);
        values.put("DataFatTotalLipid", fat);
        values.put("DataCarbohydrate", carbs);
        long result = db.insert("food", null, values);
        db.close();
        if (result<0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

