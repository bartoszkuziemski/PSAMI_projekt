package com.example.psami_projekt.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;


import com.example.psami_projekt.View.Adapter.ProductInMealAdapter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private SQLiteDatabase myDatabase;
    private final Context context;
    private static final String DATABASE_NAME = "food.db";
    public static final String DATABASE_PATH = "/data/user/0/com.example.psami_projekt/databases/";
    public static final int DATABASE_VERSION = 1;
    public static final String FOOD_TABLE_NAME = "food";
    public static final String MEAL_TABLE_NAME = "meal";
    public static final int START_PRODUCTS_LIMIT = 30;
    public static final int SEARCH_PRODUCTS_LIMIT = 20;

    public DatabaseHelper(Context context) {
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

    /**
     * Init some products at the beginning
     *
     * @return Array of products
     */
    public ArrayList<Product> initStartingProducts() {
        try {
            createDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("food", new String[]{"rowid", "Category", "Description", "DataProtein", "DataFatTotalLipid", "DataCarbohydrate"}, null, null, null, null, null, String.valueOf(START_PRODUCTS_LIMIT));

        ArrayList<Product> products = setProductFields(cursor);
        db.close();
        return products;
    }

    /**
     * Get products in searchActivity after typing in search bar
     *
     * @param name search bar product name
     * @return products that contain name
     */
    public ArrayList<Product> getProductsByName(String name) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("food", new String[]{"rowid", "Category", "Description", "DataProtein", "DataFatTotalLipid", "DataCarbohydrate"}, "Category LIKE ?", new String[]{"%" + name + "%"}, null, null, null, String.valueOf(SEARCH_PRODUCTS_LIMIT));

        ArrayList<Product> products = setProductFields(cursor);
        db.close();
        return products;
    }

    /**
     * Get product which was clicked by id to show in ProductActivity, used in Adapter
     *
     * @param id row id
     * @return product
     */
    public Product getProductById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select rowid, Category, Description, DataProtein, DataFatTotalLipid, DataCarbohydrate from food where rowid = ?", new String[]{String.valueOf(id)});

        Product product = setProductFields(cursor).get(0);
        db.close();
        return product;
    }

    /**
     * Sets product fields by sql query and return Array of products
     * @param cursor contains sql query
     * @return Array of products that match cursor query
     */
    private ArrayList<Product> setProductFields(Cursor cursor) {
        ArrayList<Product> products = new ArrayList<>();
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
        return products;
    }

    /**
     * Checks if product exist in database before adding new one
     *
     * @param product p
     * @return true if exist
     */
    private boolean checkIfExistInDB(Product product) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select Category, Description from food where Category = ? AND Description = ?", new String[]{product.getName(), product.getDescription()});

        if (cursor.getCount() > 0) {
            cursor.close();
            db.close();
            return true;
        } else {
            cursor.close();
            db.close();
            return false;
        }
    }

    /**
     * Add product to database, first check if already exist
     *
     * @param product p
     * @return true if added successfully
     */
    public boolean addProduct(Product product) {
        if (checkIfExistInDB(product)) {
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
        if (result < 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Delete product from database
     *
     * @param id row id
     * @return true if number of deleted rows is greater than 0
     */
    public boolean deleteProduct(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Cursor cursor = db.rawQuery("DELETE FROM food WHERE rowid = ?", new String[] {String.valueOf(id)});
        int deletedRows = db.delete(FOOD_TABLE_NAME, "rowid = ?", new String[]{String.valueOf(id)});
        db.close();
        return deletedRows > 0;
    }

    private void checkMealTableExist() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='meal'",null);
        if (cursor.getCount() == 0) {
            try {
                db.execSQL("CREATE TABLE meal (DateId TEXT, Meal TEXT, ProductId INTEGER, Grams INTEGER);");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean addProductToMeal(String date, String meal, int productId, int grams) {

        SQLiteDatabase db = this.getWritableDatabase();

        checkMealTableExist();

        ContentValues values = new ContentValues();
        values.put("DateId", date);
        values.put("Meal", meal);
        values.put("ProductId", productId);
        values.put("Grams", grams);
        long result = db.insert(MEAL_TABLE_NAME, null, values);
        db.close();
        if (result < 0) {
            return false;
        } else {
            return true;
        }
    }

    public ArrayList<ProductInMeal> getProductsFromMeal(String date, String meal) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select meal.rowid, Category, Description, DataProtein, DataFatTotalLipid, DataCarbohydrate, Grams from food inner join meal on food.rowid=meal.ProductId where DateId = ? and Meal = ?;", new String[]{date, meal});
        ArrayList<ProductInMeal> products = setProductInMealFields(cursor);
        db.close();
        return products;
    }

    private ArrayList<ProductInMeal> setProductInMealFields(Cursor cursor) {
        ArrayList<ProductInMeal> products = new ArrayList<>();
        while (cursor.moveToNext()) {
            ProductInMeal product = new ProductInMeal();
            product.setId(cursor.getInt(cursor.getColumnIndexOrThrow("rowid")));
            product.setName(cursor.getString(cursor.getColumnIndexOrThrow("Category")));
            product.setDescription(cursor.getString(cursor.getColumnIndexOrThrow("Description")));
            int grams = cursor.getInt(cursor.getColumnIndexOrThrow("Grams"));
            double protein = cursor.getDouble(cursor.getColumnIndexOrThrow("DataProtein")) * grams / 100;
            double fat = cursor.getDouble(cursor.getColumnIndexOrThrow("DataFatTotalLipid")) * grams / 100;
            double carbs = cursor.getDouble(cursor.getColumnIndexOrThrow("DataCarbohydrate")) * grams / 100;
            int kcal = (int) ((protein + carbs) * 4 + fat * 9);
            product.setProtein(protein);
            product.setFat(fat);
            product.setCarbs(carbs);
            product.setGrams(grams);
            product.setKcal(kcal);

            products.add(product);
        }
        cursor.close();
        return products;
    }

    public boolean deleteProductFromMeal(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int deletedRows = db.delete(MEAL_TABLE_NAME, "rowid = ?", new String[]{String.valueOf(id)});
        db.close();
        return deletedRows > 0;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


}

