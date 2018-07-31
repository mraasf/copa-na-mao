package com.app.fran.copanamo.fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
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
import android.widget.Toast;

import com.alespero.expandablecardview.ExpandableCardView;
import com.app.fran.copanamo.R;
import com.app.fran.copanamo.adapters.NoticiaRecyclerAdapter;
import com.app.fran.copanamo.entidades.Grupo;
import com.app.fran.copanamo.entidades.Noticia;
import com.app.fran.copanamo.service.RetrofitService;
import com.app.fran.copanamo.service.RetrofitServiceGenerator;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NoticiasFragment extends Fragment {
    RecyclerView rvNoticias;
    List<Noticia> noticias;
    ProgressBar progressBar;
    AlertDialog alert;
    private static final String KEY_API_ADMOB ="ca-app-pub-7136462619743657~9311752194";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         View view  = inflater.inflate(R.layout.fragment_noticias, container, false);
        MobileAds.initialize(getContext(),KEY_API_ADMOB);


        AdView adView = view.findViewById(R.id.adView);
        AdRequest request = new AdRequest.Builder()
                .build();
        adView.loadAd(request);
         noticias = new ArrayList<>();
         rvNoticias = view.findViewById(R.id.rv_noticias);
         rvNoticias.setLayoutManager(new LinearLayoutManager(getContext()));
        progressBar = view.findViewById(R.id.progress_noticias);
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setIndeterminate(true);

        retornaNoticias();


        return  view;
    }

    private void retornaNoticias(){
        RetrofitService service = RetrofitServiceGenerator.createService(RetrofitService.class);

        Call<List<Noticia>> call = service.getNoticias();
        call.enqueue(new Callback<List<Noticia>>() {
            @Override
            public void onResponse(Call<List<Noticia>> call, Response<List<Noticia>> response) {
                if (response.isSuccessful()){
                    List<Noticia> notics = response.body();
                    if(noticias != null){
                        noticias = notics;
                        NoticiaRecyclerAdapter adapter = new NoticiaRecyclerAdapter(noticias,getContext());
                        rvNoticias.setAdapter(adapter);
                        progressBar.setVisibility(View.GONE);
                    }
                }else{
                    progressBar.setVisibility(View.GONE);
                    final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("Erro");
                    builder.setMessage("Erro ao buscar notícias\n" +
                            "verifique sua conexão e tente novamente");
                    builder.setCancelable(false);
                    builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            retornaNoticias();
                        }
                    });
                    alert = builder.create();
                    alert.show();

                }
            }

            @Override
            public void onFailure(Call<List<Noticia>> call, Throwable t) {
                Toast.makeText(getContext(), "Error "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
