package com.fedoraapps.www.appguarda;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.fedoraapps.www.appguarda.Api.ViajeApi;
import com.fedoraapps.www.appguarda.Model.Viaje;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main extends AppCompatActivity {
    ListView listTrip;
    ArrayAdapter<Viaje> adapter;
    List<Viaje> ListResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Asociar Elementos XML
        listTrip =(ListView)findViewById(R.id.listTrip);

        Call<List<Viaje>> call = ViajeApi.createService().getAll();
        call.enqueue(new Callback<List<Viaje>>() {
            @Override
            public void onResponse(Call<List<Viaje>> call, Response<List<Viaje>> response) {
                ListResponse = response.body();
                adapter = new InteractiveArrayAdapterTerminales(Main.this,getModel());
                listTrip.setAdapter(adapter);
                for (Viaje t : ListResponse) {
                    adapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<List<Viaje>> call, Throwable t) {
                System.out.println("onFailure");}
        });



        //CALL LOGIN
       // Intent i = new Intent(Main.this,Login.class);
      //  startActivity(i);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private List<Viaje> getModel() {
        List<Viaje> list = new ArrayList<Viaje>();
        for (Viaje t : ListResponse) {
            list.add(t);
        }return list;
    }
}
