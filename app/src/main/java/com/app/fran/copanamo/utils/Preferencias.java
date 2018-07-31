package com.app.fran.copanamo.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferencias {

    private Context contexto;
    private SharedPreferences preferences;
    public final String NOME_ARQUIVO = "copa_na_mao.pref";
    public final String VIU_INFO = "visualizou_info";
    public final String VIU_INFO_FASE_FINAL = "visualizou_info_finais";
    public final int MODE = 0;
    private SharedPreferences.Editor editor;

    public static final String NOME_USER = "nome_user";

    public Preferencias( Context contextoParametro){

        contexto = contextoParametro;
        preferences = contexto.getSharedPreferences(NOME_ARQUIVO, MODE );
        editor = preferences.edit();

    }

    public void salvaNomeUsuario(String nome){
        editor.putString(NOME_USER,nome);
        editor.commit();
    }

    public String recuperaNomeUsuario(){
        String nome = preferences.getString(NOME_USER,null);
        return nome;
    }

    public void salvaStatusInfo(boolean status){
       editor.putBoolean(VIU_INFO, status);
        editor.commit();
    }

    public boolean getStatusInfo(){
        return preferences.getBoolean(VIU_INFO, false);
    }

    public void salvaStatusInfoFinais(boolean status){
        editor.putBoolean(VIU_INFO_FASE_FINAL, status);
        editor.commit();
    }

    public boolean getStatusInfoFaseFinal(){
        return preferences.getBoolean(VIU_INFO_FASE_FINAL, false);
    }

}
