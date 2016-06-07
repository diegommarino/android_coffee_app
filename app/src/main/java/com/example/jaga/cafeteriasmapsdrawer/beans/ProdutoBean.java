package com.example.jaga.cafeteriasmapsdrawer.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jaga on 12/05/16.
 */
public class ProdutoBean {
    private int id;
    private String nome;
    private String descricao;
    private Integer preco;

    public ProdutoBean(){
        // TODO
    }

    public ProdutoBean(Parcel in){
        readFromParcelable(in);
    }

    private void readFromParcelable (Parcel in){
        nome = in.readString();
        descricao = in.readString();
        preco = in.readInt();
    }
    public void writeToParcel(Parcel out, int flags){
        out.writeString(nome);
        out.writeString(descricao);
        out.writeString(preco.toString());
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){

        public ProdutoBean createFromParcel(Parcel in){
            return new ProdutoBean(in);
        }

        public ProdutoBean [] newArray(int size){
            return new ProdutoBean[size];
        }
    };

    public String toString(){
        return this.nome + ": " +this.descricao + "(R$ " + this.preco.toString() +")";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getPreco(){
        return  preco;
    }

    public void setPreco(Integer preco){
        this.preco = preco;
    }
}
