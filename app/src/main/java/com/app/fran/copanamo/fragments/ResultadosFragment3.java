package com.app.fran.copanamo.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.app.fran.copanamo.R;
import com.app.fran.copanamo.adapters.ResultadoRecyclerAdapter3;
import com.app.fran.copanamo.entidades.Resultado;
import com.app.fran.copanamo.service.RetrofitService;
import com.app.fran.copanamo.service.RetrofitServiceGenerator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultadosFragment3 extends Fragment {
    private View viewRoot;
    List<Resultado> resultados3;
    private RecyclerView recycler_fragment3;
    private int cont;
    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewRoot =  inflater.inflate(R.layout.fragment_resultados_tabbed3, container, false);

        recycler_fragment3 = viewRoot.findViewById(R.id.rv_result3);
        recycler_fragment3.setLayoutManager(new LinearLayoutManager(getContext()));

        retornaPlacarFase3();
        progressBar = viewRoot.findViewById(R.id.progress_result3);
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setIndeterminate(true);

        return viewRoot;
    }

    private void retornaPlacarFase3(){
        RetrofitService service = RetrofitServiceGenerator.createService(RetrofitService.class);
        //chamada do metodo que retorna os placares da fase 3 de jogos
        Call<List<Resultado>> call = service.getPlacarTree();

        call.enqueue(new Callback<List<Resultado>>() {
            @Override
            public void onResponse(Call<List<Resultado>> call, Response<List<Resultado>> response) {
                if (response.isSuccessful()){

                    resultados3 = response.body();
                    ResultadoRecyclerAdapter3 adapter = new ResultadoRecyclerAdapter3(resultados3);
                    recycler_fragment3.setAdapter(adapter);
                    progressBar.setVisibility(View.GONE);

                }
            }

            @Override
            public void onFailure(Call<List<Resultado>> call, Throwable t) {

            }
        });

    }

}
