package com.example.jaga.cafeteriasmapsdrawer.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.jaga.cafeteriasmapsdrawer.beans.CafeteriaBean;
import com.example.jaga.cafeteriasmapsdrawer.beans.CategoriaBean;
import com.example.jaga.cafeteriasmapsdrawer.beans.ProdutoBean;

import java.util.ArrayList;

/**
 * Created by jaga on 15/05/16.
 */
public class DataAccessObject extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "cafeterias.db";
    private static final String TABLE_CATEGORIA = "categoria";
    private static final String TABLE_PRODUTO = "produto";
    private static final String TABLE_CAFETERIA = "cafeteria";
    private static final String TABLE_PRODUTO_CAFETERIA = "produto_cafeteria";
    private static final int DATABASE_VERSION = 1;

    public DataAccessObject(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_CATEGORIA
                + "(id INTEGER PRIMARY KEY, "
                + "nome TEXT, "
                + "descricao TEXT);";
        db.execSQL(sql);

        sql = "CREATE TABLE " + TABLE_PRODUTO
                + "(id INTEGER PRIMARY KEY, "
                + "nome TEXT, "
                + "descricao TEXT,"
                + "preco DECIMAL,"
                + "categoria_id,"
                + "cafeteria_id);";
        db.execSQL(sql);

        sql = "CREATE TABLE " + TABLE_CAFETERIA
                + "(id INTEGER PRIMARY KEY, "
                + "nome TEXT, "
                + "descricao TEXT,"
                + "endereco TEXT,"
                + "telefone TEXT,"
                + "latitude DECIMAL,"
                + "longitude DECIMAL);";
        db.execSQL(sql);

        sql = "CREATE TABLE " + TABLE_PRODUTO_CAFETERIA
                + "(id INTEGER PRIMARY KEY, "
                + "produto_id INTEGER, "
                + "cafeteria_id INTEGER);";
        db.execSQL(sql);

        seedTableCategoria();
        seedTableProduto();
        seedTableCafeteria();
    }

    public void seedTableProduto() {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "DELETE FROM " + TABLE_CATEGORIA;
        db.execSQL(sql);

        ContentValues contentValues = new ContentValues();

        // Bebidas Quentes
        contentValues.put("nome", "Café Carioca");
        contentValues.put("descricao", "Cafézinho filtrado na meia.");
        contentValues.put("preco", "2.99");
        contentValues.put("categoria_id", "1");
        contentValues.put("cafeteria_id", "1");
        db.insert(TABLE_PRODUTO, null, contentValues);

        contentValues.put("nome", "Café Expresso");
        contentValues.put("descricao", "Café mais encorpado, com sabor mais acentuado.");
        contentValues.put("preco", "3.99");
        contentValues.put("categoria_id", "1");
        contentValues.put("cafeteria_id", "2");
        db.insert(TABLE_PRODUTO, null, contentValues);

        contentValues.put("nome", "Café Submarino");
        contentValues.put("descricao", "Café mais com bordas de chocolate e um chocolate alpino dentro.");
        contentValues.put("preco", "3.99");
        contentValues.put("categoria_id", "1");
        contentValues.put("cafeteria_id", "3");
        db.insert(TABLE_PRODUTO, null, contentValues);

        // Bebidas Geladas
        contentValues.put("nome", "Suco de Frutas");
        contentValues.put("descricao", "Suco fresquinho da fruta.");
        contentValues.put("preco", "3.00");
        contentValues.put("categoria_id", "2");
        contentValues.put("cafeteria_id", "1");
        db.insert(TABLE_PRODUTO, null, contentValues);

        contentValues.put("nome", "Chá Mate Gelado com Limão");
        contentValues.put("descricao", "O próprio nome já define o frescor.");
        contentValues.put("preco", "5.00");
        contentValues.put("categoria_id", "2");
        contentValues.put("cafeteria_id", "2");
        db.insert(TABLE_PRODUTO, null, contentValues);

        contentValues.put("nome", "Frapuccino");
        contentValues.put("descricao", "Delicioso frapê de capuccino com sorvete de creme e chantily.");
        contentValues.put("preco", "5.00");
        contentValues.put("categoria_id", "2");
        contentValues.put("cafeteria_id", "3");
        db.insert(TABLE_PRODUTO, null, contentValues);

        // Outros
        contentValues.put("nome", "Empada de Frango");
        contentValues.put("descricao", "Empada com massa sequinha e um suculento recheio de frango.");
        contentValues.put("preco", "5.00");
        contentValues.put("categoria_id", "3");
        contentValues.put("cafeteria_id", "1");
        db.insert(TABLE_PRODUTO, null, contentValues);

        contentValues.put("nome", "Croissant");
        contentValues.put("descricao", "Especialidade da casa.");
        contentValues.put("preco", "6.00");
        contentValues.put("categoria_id", "3");
        contentValues.put("cafeteria_id", "2");
        db.insert(TABLE_PRODUTO, null, contentValues);

        contentValues.put("nome", "Waffles Canadense");
        contentValues.put("descricao", "Waffles cobertos com o delicioso maple syrup.");
        contentValues.put("preco", "6.00");
        contentValues.put("categoria_id", "3");
        contentValues.put("cafeteria_id", "3");
        db.insert(TABLE_PRODUTO, null, contentValues);
    }

    public void seedTableCategoria() {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "DELETE FROM " + TABLE_CATEGORIA;
        db.execSQL(sql);

        ContentValues contentValues = new ContentValues();

        contentValues.put("nome", "Bebidas Quentes");
        contentValues.put("descricao", "Bebidas para alegrar a alma e deixar seu dia mais feliz.");
        db.insert(TABLE_CATEGORIA, null, contentValues);

        contentValues.put("nome", "Bebidas Geladas");
        contentValues.put("descricao", "Bebidas para dar aquela refrescada em dias quentes.");
        db.insert(TABLE_CATEGORIA, null, contentValues);

        contentValues.put("nome", "Outros");
        contentValues.put("descricao", "Outras opções deliciosas.");
        db.insert(TABLE_CATEGORIA, null, contentValues);
    }

    public void seedTableCafeteria() {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "DELETE FROM " + TABLE_CAFETERIA;
        db.execSQL(sql);

        ContentValues contentValues = new ContentValues();

        contentValues.put("nome", "Amika");
        contentValues.put("descricao", "Aproveite os melhores cafés das melhores origens, com toda delicadeza na seleção e preparo em um ambiente único no coração da capital cearense. Amika, o café do amigos.");
        contentValues.put("endereco", "Rua Ana Bilhar, 1136B - Meireles - Fortaleza");
        contentValues.put("telefone", "(85)3031-0351");
        contentValues.put("latitude", "-3.7312642");
        contentValues.put("longitude", "-38.490107");
        db.insert(TABLE_CAFETERIA, null, contentValues);

        contentValues.put("nome", "Café Santa Clara (Dragão do Mar)");
        contentValues.put("descricao", "Já com muitas conquistas na bagagem, em 2002 o Café Santa Clara foi eleito o Café nº 1 do Norte e Nordeste do Brasil, segundo a Nielsen. Um prêmio especial, que atesta a qualidade do produto.");
        contentValues.put("endereco", "Rua Dragão do Mar, 81 - Centro Dragão do Mar");
        contentValues.put("telefone", "(85)3219-6900");
        contentValues.put("latitude", "-3.722207");
        contentValues.put("longitude", "-38.5229737");
        db.insert(TABLE_CAFETERIA, null, contentValues);

        contentValues.put("nome", "L'Café");
        contentValues.put("descricao", "Culinária descontraída de salgados, lanches caseiros e doces de confeiteiro acompanhados de cafés especiais.");
        contentValues.put("endereco", "Av. Eng. Leal Lima Verde, 88 - Edson Queiroz -Fortaleza ");
        contentValues.put("telefone", "(85)3472-6106");
        contentValues.put("latitude", "-3.7884717");
        contentValues.put("longitude", "-38.4786781");
        db.insert(TABLE_CAFETERIA, null, contentValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_CATEGORIA;
        db.execSQL(sql);
        sql = "DROP TABLE IF EXISTS " + TABLE_PRODUTO;
        db.execSQL(sql);
        sql = "DROP TABLE IF EXISTS " + TABLE_CAFETERIA;
        db.execSQL(sql);
        onCreate(db);
    }

    public ArrayList selectInstances(String tipo) {
        checkTableCategoria();
        checkTableCafeteria();
        checkTableProdutos();

        String s = tipo.toLowerCase();
        if (s.equals("categoria")) {
            return selectCategorias();

        } else if (s.equals("produto")) {
            return selectProdutos();
        } else if (s.equals("cafeteria")) {
            return selectCafeterias();
        } else {
            return null;
        }
    }

    public ArrayList<CategoriaBean> selectCategorias() {
        checkTableCategoria();
        ArrayList<CategoriaBean> listaCategoria = new ArrayList<CategoriaBean>();
        String sql = "SELECT * FROM " + TABLE_CATEGORIA;
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);

        while (cursor.moveToNext()) {
            CategoriaBean categoriaBean = new CategoriaBean();

            categoriaBean.setId(cursor.getInt(0));
            categoriaBean.setNome(cursor.getString(1));
            categoriaBean.setDescricao(cursor.getString(2));

            listaCategoria.add(categoriaBean);
        }
        return listaCategoria;
    }

    public ArrayList<CafeteriaBean> selectCafeterias() {
        checkTableCafeteria();
        ArrayList<CafeteriaBean> listaCafeteria= new ArrayList<CafeteriaBean>();
        String sql = "SELECT * FROM " + TABLE_CAFETERIA;
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);

        while (cursor.moveToNext()) {
            CafeteriaBean cafeteriaBean = new CafeteriaBean();

            cafeteriaBean.setId(cursor.getInt(0));
            cafeteriaBean.setNome(cursor.getString(1));
            cafeteriaBean.setDescricao(cursor.getString(2));
            cafeteriaBean.setEndereco(cursor.getString(3));
            cafeteriaBean.setTelefone(cursor.getString(4));
            cafeteriaBean.setLatitude(cursor.getDouble(5));
            cafeteriaBean.setLongitude(cursor.getDouble(6));

            listaCafeteria.add(cafeteriaBean);
        }
        return listaCafeteria;
    }

    public ArrayList<ProdutoBean> selectProdutos() {
        checkTableProdutos();
        ArrayList<ProdutoBean> listaProduto = new ArrayList<ProdutoBean>();
        String sql = "SELECT * FROM " + TABLE_PRODUTO;
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);

        while (cursor.moveToNext()) {
            ProdutoBean produtoBean = new ProdutoBean();

            produtoBean.setId(cursor.getInt(0));
            produtoBean.setNome(cursor.getString(1));
            produtoBean.setDescricao(cursor.getString(2));

            listaProduto.add(produtoBean);
        }
        return listaProduto;
    }

    public CategoriaBean selectCategoriaByName(String name) {
        checkTableCategoria();
        
        CategoriaBean categoria = new CategoriaBean();
        Cursor cursor = getReadableDatabase().query(
                TABLE_CATEGORIA,
                null,
                "nome = ?",
                new String[]{name}, null, null, "1");
        //Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + TABLE_CATEGORIA + " WHERE nome = '"+ name +"'  LIMIT 1;", null);
        cursor.moveToNext();
        
        categoria.setId(cursor.getInt(0));
        categoria.setNome(cursor.getString(1));
        categoria.setDescricao(cursor.getString(2));
        return categoria;
    }

    public ArrayList<ProdutoBean> productListByCategoryId(Integer categoryId){
        checkTableCategoria();
        checkTableProdutos();
        ArrayList<ProdutoBean> listaProduto = new ArrayList<ProdutoBean>();
        Cursor cursor = getReadableDatabase().query(
            TABLE_PRODUTO,
            null,
            "categoria_id = ?",
            new String[]{categoryId.toString()}, null, null, null);

        while (cursor.moveToNext()) {
            ProdutoBean produtoBean = new ProdutoBean();

            produtoBean.setId(cursor.getInt(0));
            produtoBean.setNome(cursor.getString(1));
            produtoBean.setDescricao(cursor.getString(2));
            produtoBean.setPreco(cursor.getInt(3));

            listaProduto.add(produtoBean);
            //Log.i("Produto******* ", cursor.getString(1));
        }
        return listaProduto;
    }

    public ArrayList<ProdutoBean> productListByCategoriaCafeteriaId(Integer categoryId, Integer cafeteriaId){
        checkTableCafeteria();
        checkTableProdutos();
        ArrayList<ProdutoBean> listaProduto = new ArrayList<ProdutoBean>();
        Cursor cursor = getReadableDatabase().query(
                TABLE_PRODUTO,
                null,
                "categoria_id = ? AND cafeteria_id =?",
                new String[]{categoryId.toString(), cafeteriaId.toString()}, null, null, null);

        while (cursor.moveToNext()) {
            ProdutoBean produtoBean = new ProdutoBean();

            produtoBean.setId(cursor.getInt(0));
            produtoBean.setNome(cursor.getString(1));
            produtoBean.setDescricao(cursor.getString(2));
            produtoBean.setPreco(cursor.getInt(3));

            listaProduto.add(produtoBean);
            //Log.i("Produto******* ", cursor.getString(1));
        }
        return listaProduto;
    }
    
    private void checkTableCategoria(){
        if (DatabaseUtils.queryNumEntries(getReadableDatabase(), TABLE_CATEGORIA) == 0)
            seedTableCategoria();
    }

    private void checkTableCafeteria(){
        if (DatabaseUtils.queryNumEntries(getReadableDatabase(), TABLE_CAFETERIA) == 0)
            seedTableCafeteria();
    }

    private void checkTableProdutos(){
        if (DatabaseUtils.queryNumEntries(getReadableDatabase(), TABLE_PRODUTO) == 0)
            seedTableProduto();
    }

}