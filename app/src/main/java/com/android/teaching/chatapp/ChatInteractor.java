package com.android.teaching.chatapp;

import java.util.ArrayList;

public class ChatInteractor {
    private static ArrayList<Bandas> bandas;

    public ChatInteractor(){
        if (bandas == null){
            Bandas chrome = new Bandas("Holaa","holaaa");

            Bandas pokemon = new Bandas("Holaaa"," Pokemon");

            bandas = new ArrayList<>();
            bandas.add(chrome);
            bandas.add(pokemon);
        }

    }

    public static ArrayList<Bandas> getBandas() {

        return bandas;
    }

    public Bandas getBandasWithId(int id){
        for (Bandas bandas:bandas){
            if (bandas.getId() == id){
                return bandas;
            }
        }
        return null;
    }

}
