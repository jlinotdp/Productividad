package pe.com.toyota.productividad.a_MySQL.Categoria;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import pe.com.toyota.productividad.R;
import pe.com.toyota.productividad.a_DataObject.Categoria;
import pe.com.toyota.productividad.a_FunActivity.CategariaActivity;

/**
 * Created by jlino on 10/04/2018.
 */

public class CustomAdapteract extends BaseAdapter {

    Context c;
    ArrayList<Categoria> actividads;
    String usuarioddd;
    String codusu;

    public CustomAdapteract(Context c, ArrayList<Categoria> actividads,String usuarioddd,String codusu) {
        this.c = c;
        this.actividads = actividads;
        this.usuarioddd=usuarioddd;
        this.codusu=codusu;
    }

    @Override
    public int getCount() {
        return actividads.size();
    }

    @Override
    public Object getItem(int i) {
        return actividads.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        if (convertView==null){

            convertView= LayoutInflater.from(c).inflate(R.layout.model_categoria,parent,false);



        }

        final TextView categoria= (TextView) convertView.findViewById(R.id.categorianame);




        final Categoria pp=(Categoria) this.getItem(i);

        categoria.setText(pp.getDescat());



        convertView.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View view) {
                //    Toast.makeText(c,"Seleccionando categoriaf "+pp.getDescat()+" codigo "+pp.getCodcat()+" usuario "+usuarioddd+"  "+codusu,Toast.LENGTH_SHORT).show();

                openDetailActivity(pp.getCodcateg(),usuarioddd,codusu);

            }
        });


        return convertView;
    }

    private void openDetailActivity(int codigo,String usuario,String codusu)
    {
        Intent i=new Intent(c,CategariaActivity.class);

        i.putExtra("CODCAT_KEY",codigo);
        i.putExtra("USUCAT_KEY",usuario);
        i.putExtra("codusu",codusu);

        c.startActivity(i);



    }





}
