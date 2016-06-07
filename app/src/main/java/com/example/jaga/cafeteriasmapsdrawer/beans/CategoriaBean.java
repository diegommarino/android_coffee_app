package com.example.jaga.cafeteriasmapsdrawer.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jaga on 12/05/16.
 */
public class CategoriaBean {
    private int id;
    private String nome;
    private String descricao;

    public CategoriaBean(){}

    public CategoriaBean(String nome, String descricao){
        this.nome = nome;
        this.descricao = descricao;
    }

    public CategoriaBean(Parcel in){
        readFromParcelable(in);
    }

    private void readFromParcelable (Parcel in){
        nome = in.readString();
        descricao = in.readString();
    }
    public void writeToParcel(Parcel out, int flags){
        out.writeString(nome);
        out.writeString(descricao);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){

        public CategoriaBean createFromParcel(Parcel in){
            return new CategoriaBean(in);
        }

        public CategoriaBean [] newArray(int size){
            return new CategoriaBean[size];
        }
    };

    public String toString(){
        return this.nome + ": " +this.descricao;
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
}
