package com.fedoraapps.www.appguarda;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.fedoraapps.www.appguarda.Api.PasajeApi;
import com.fedoraapps.www.appguarda.Api.PuntosRecorridoApi;
import com.fedoraapps.www.appguarda.Model.Pasaje;
import com.fedoraapps.www.appguarda.Model.PuntosDeRecorrido;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by maxi on 05/06/2016.
 */
public class VentaEfectivo extends AppCompatActivity implements View.OnClickListener {

    Spinner origen;
    Spinner destino;
    Button generar;
    String valOfSpinner;
    String valOfSpinner2;
    PuntosDeRecorrido puntoOrigen;
    PuntosDeRecorrido puntoDestino;
    int codViaje;
    List<PuntosDeRecorrido> lista;
    ArrayAdapter<PuntosDeRecorrido> paradas;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_venta_efectivo);

        codViaje = getIntent().getExtras().getInt("codigo");

        origen = (Spinner)findViewById(R.id.spinner);
        destino = (Spinner)findViewById(R.id.spinner2);
        generar = (Button)findViewById(R.id.button);
        generar.setOnClickListener(this);

        Call<List<PuntosDeRecorrido>> call = PuntosRecorridoApi.createService().getAll();
        call.enqueue(new Callback<List<PuntosDeRecorrido>>() {
            @Override
            public void onResponse(Call<List<PuntosDeRecorrido>> call, Response<List<PuntosDeRecorrido>> response) {
               lista = response.body();

                paradas = new InteractiveArrayAdapterSpinner(VentaEfectivo.this,getModel());
                origen.setAdapter(paradas);
                destino.setAdapter(paradas);
            }

            @Override
            public void onFailure(Call<List<PuntosDeRecorrido>> call, Throwable t) {
                System.out.println("onFailure");
            }
        });

        origen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            //GET SPINNER
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                valOfSpinner = origen.getSelectedItem().toString();

               puntoOrigen= (PuntosDeRecorrido)origen.getSelectedItem();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        destino.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            //GET SPINNER
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                valOfSpinner2 = destino.getSelectedItem().toString();
                puntoDestino = (PuntosDeRecorrido)origen.getSelectedItem();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        generar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(v.getId()==R.id.button) {

                    if (valOfSpinner != null && valOfSpinner2 != null) {

                        if(valOfSpinner.equals(valOfSpinner2)){
                            Toast.makeText(VentaEfectivo.this,"Origen y destino deben ser distintos",Toast.LENGTH_LONG).show();
                        }else{
                            Pasaje pasaje = new Pasaje(codViaje,puntoOrigen.getId(),puntoDestino.getId(),Farcade.retornarCodigoPrecio(codViaje),1,Farcade.retornarPrecioViaje(codViaje));
                            Call<Pasaje> call = PasajeApi.createService().setPasaje(pasaje);
                            call.enqueue(new Callback<Pasaje>() {
                                @Override
                                public void onResponse(Call<Pasaje> call, Response<Pasaje> response) {
                                  Pasaje p = response.body();
                                    System.out.println(p);
                                }
                                @Override
                                public void onFailure(Call<Pasaje> call, Throwable t) {
                                    System.out.println("onFailure");}
                            });
                        }
                    }else{Toast.makeText(VentaEfectivo.this,"Debe seleccionar un Origen / Destino",Toast.LENGTH_LONG).show();}
                }

            }
        });





    }
    private List<PuntosDeRecorrido> getModel() {
        List<PuntosDeRecorrido> list = new ArrayList<PuntosDeRecorrido>();
        for(PuntosDeRecorrido e: lista){
            list.add(e);
            // adapter.notifyDataSetChanged();
        }

        return list;
    }

    @Override
    public void onClick(View v) {


    }
}
