package com.example.practica_confotos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.practica_confotos.Adaptadores.AdaptadorUsuario;
import com.example.practica_confotos.Modelos.Usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import WebService.Asynchtask;
import WebService.WebService;

public class MainActivity extends AppCompatActivity implements Asynchtask {
    ListView LstOpciones;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LstOpciones = (ListView)findViewById(R.id.iditem);
        View header = getLayoutInflater().inflate(R.layout.lyheader,null);
        LstOpciones.addHeaderView(header);
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("https://reqres.in/api/users",
                datos, MainActivity.this, MainActivity.this);
        ws.execute("GET");
        //get para obtener info post para enviar//
    }

    @Override
    public void processFinish(String result) throws JSONException {
            JSONObject JSONlista = new JSONObject(result);
            JSONArray JSONlistaUsuarios= JSONlista.getJSONArray("data");
            ArrayList<Usuario> lstUsuarios= Usuario.JsonObjectsBuild(JSONlistaUsuarios);
            AdaptadorUsuario adapatorUsuario = new AdaptadorUsuario(this, lstUsuarios);
            LstOpciones.setAdapter(adapatorUsuario);
    }
}