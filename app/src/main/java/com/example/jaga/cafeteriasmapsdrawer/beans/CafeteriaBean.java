package com.example.jaga.cafeteriasmapsdrawer.beans;

/**
 * Created by jaga on 03/06/16.
 */

import android.os.Parcel;
import android.os.Parcelable;

public class CafeteriaBean {
    private int id;
    private String nome;
    private String descricao;
    private String endereco;
    private String telefone;
    private Double latitude;
    private Double longitude;

    public CafeteriaBean(){}

    public CafeteriaBean(String nome, String descricao, String endereco, String telefone, Double latitude, Double longitude){
        this.nome = nome;
        this.descricao = descricao;
        this.endereco = endereco;
        this.telefone = telefone;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public CafeteriaBean(Parcel in){
        readFromParcelable(in);
    }

    private void readFromParcelable (Parcel in){
        nome = in.readString();
        descricao = in.readString();
        endereco = in.readString();
        telefone = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
    }
    public void writeToParcel(Parcel out, int flags){
        out.writeString(nome);
        out.writeString(descricao);
        out.writeString(endereco);
        out.writeString(telefone);
        out.writeDouble(latitude);
        out.writeDouble(longitude);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){

        public CafeteriaBean createFromParcel(Parcel in){
            return new CafeteriaBean(in);
        }

        public CafeteriaBean [] newArray(int size){
            return new CafeteriaBean[size];
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

    public String getEndereco() {
        return endereco;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
