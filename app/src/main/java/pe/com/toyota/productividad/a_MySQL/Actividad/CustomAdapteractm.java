package pe.com.toyota.productividad.a_MySQL.Actividad;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import pe.com.toyota.productividad.R;
import pe.com.toyota.productividad.a_DataObject.Actividad;
import pe.com.toyota.productividad.a_DataObject.RegistroActividad;
import pe.com.toyota.productividad.a_FunActivity.categoria;
import pe.com.toyota.productividad.a_MySQL.Ingresar_actividad;

/**
 * Created by jlino on 10/04/2018.
 */

public class CustomAdapteractm extends BaseAdapter {
    Context c;
    ArrayList<Actividad> actividads;
    String usuario2;
    String codusu;
    Integer actv;

    public CustomAdapteractm(Context c, ArrayList<Actividad> actividads,String usuario2,String codusu,Integer actv) {
        this.c = c;
        this.actividads = actividads;
        this.usuario2=usuario2;
        this.codusu=codusu;
        this.actv=actv;
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

            convertView= LayoutInflater.from(c).inflate(R.layout.model_actividad,parent,false);



        }

        final TextView categoria2= (TextView) convertView.findViewById(R.id.aname);




        final Actividad pp=(Actividad) this.getItem(i);

        categoria2.setText(pp.getDesact());


        convertView.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View view) {
                //   Toast.makeText(c,"Seleccionando categoriaf "+pp.getDesact()+" codigo "+pp.getCodact(),Toast.LENGTH_SHORT).show();
                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(c);
                dialogo1.setTitle("Aviso");
                dialogo1.setMessage("¿Está seguro de realizar el cambio? ");
                dialogo1.setCancelable(false);

                dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogo1, int id) {

                                //codusu  ,  categoria  ,  actividad
                                //
                                //   Toast.makeText(c,"Datos "+codusu+" codigo "+actv+"  "+pp.getCodact(),Toast.LENGTH_SHORT).show();


                                RegistroActividad rd= new RegistroActividad();
                                rd.setCodusuario(codusu);
                                rd.setActcategoria(actv);
                                rd.setActividad(pp.getCodact());
                                new Ingresar_actividad(c).matest(rd,codusu,actv,pp.getCodact());

                                Intent intt=new Intent(c, categoria.class);
                                intt.putExtra("USUARIOCLAS_KEY",usuario2);
                                intt.putExtra("codusu",codusu);
                                c.startActivity(intt);
                                Toast.makeText(c,"Actividad iniciada",Toast.LENGTH_SHORT).show();
                            }
                        }


                );
                dialogo1.setNegativeButton("Cerrar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        Intent intt=new Intent(c, categoria.class);
                        intt.putExtra("USUARIOCLAS_KEY",usuario2);
                        intt.putExtra("codusu",codusu);
                        c.startActivity(intt);
                        Toast.makeText(c,"No se actualizó estado",Toast.LENGTH_SHORT).show();
                        // User cancelled the dialog
                    }
                });

                dialogo1.show();

            }
        });


        return convertView;
    }

}
