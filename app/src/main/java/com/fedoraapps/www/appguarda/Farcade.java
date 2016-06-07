package com.fedoraapps.www.appguarda;

import com.fedoraapps.www.appguarda.Api.PrecioApi;
import com.fedoraapps.www.appguarda.Model.Precio;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by maxi on 06/06/2016.
 */
public class Farcade {

    private static int monto = 0;
    private static int idPrecio = 0;

      public static int retornarPrecioViaje(final int codV){

          Call<List<Precio>> call = PrecioApi.createService().getAll();
          call.enqueue(new Callback<List<Precio>>() {
              @Override
              public void onResponse(Call<List<Precio>> call, Response<List<Precio>> response) {
                  List<Precio> listaPrecios = response.body();
                  for(Precio precio: listaPrecios){
                      if(codV == precio.getIdViaje()){
                          monto = precio.getMonto();}
                  }
              }
              @Override
              public void onFailure(Call<List<Precio>> call, Throwable t) {
                  System.out.println("onFailure");}
          });
            return monto;
      }

    public static int retornarCodigoPrecio(final int codV){

        Call<List<Precio>> call = PrecioApi.createService().getAll();
        call.enqueue(new Callback<List<Precio>>() {
            @Override
            public void onResponse(Call<List<Precio>> call, Response<List<Precio>> response) {
                List<Precio> listaPrecios = response.body();
                for(Precio precio: listaPrecios){
                    if(codV == precio.getIdViaje()){
                        idPrecio = precio.getId();}
                }
            }
            @Override
            public void onFailure(Call<List<Precio>> call, Throwable t) {
                System.out.println("onFailure");}
        });
        return idPrecio;
    }


}
