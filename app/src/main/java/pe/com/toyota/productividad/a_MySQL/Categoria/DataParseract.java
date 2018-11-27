package pe.com.toyota.productividad.a_MySQL.Categoria;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import pe.com.toyota.productividad.a_DataObject.Categoria;

/**
 * Created by jlino on 10/04/2018.
 */

public class DataParseract extends AsyncTask<Void,Void,Boolean> {
    Context c;
    String jsonData;
    ListView lvppend;
    String usuariodd;
    String codusu;


    ProgressDialog pd;
    ArrayList<Categoria> clientes=new ArrayList<>();

    public DataParseract(Context c, String jsonData, ListView lvppend,String usuariodd,String codusu) {
        this.c = c;
        this.jsonData = jsonData;
        this.lvppend = lvppend;
        this.usuariodd=usuariodd;
        this.codusu=codusu;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //  Toast.makeText(c,"on preexecute",Toast.LENGTH_SHORT).show();
        pd = new ProgressDialog(c);
        pd.setTitle("Cargando");
        pd.setMessage("Espere un momento, por favor");
        pd.show();


    }

    @Override
    protected Boolean doInBackground(Void... params) {

        return this.parseData();
    }


    @Override
    protected void onPostExecute(Boolean pared) {
        super.onPostExecute(pared);

        pd.dismiss();
        if (pared)
        {
            // Toast.makeText(c,"ingreso en DataParser,onPostExecute",Toast.LENGTH_SHORT).show();

            CustomAdapteract adapter=new CustomAdapteract(c,clientes,usuariodd,codusu);
            lvppend.setAdapter(adapter);



        }else {

         /*   AlertDialog.Builder dialogo1 = new AlertDialog.Builder(c);
            dialogo1.setTitle("Aviso");
            dialogo1.setMessage("No hay datos para mostrar ");
            dialogo1.setCancelable(false);
            dialogo1.setPositiveButton("Cerrar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogo1, int id) {


                }
            });
            dialogo1.show();
*/

            Toast.makeText(c,"Sin resultado",Toast.LENGTH_SHORT).show();

        }
        pd.dismiss();

    }



    private Boolean parseData() {

        try {

            JSONArray ja=new JSONArray(jsonData);
            JSONObject jo;
            clientes.clear();
            Categoria categoria ;
            for (int i=0;i<ja.length();i++){
                jo=ja.getJSONObject(i);
                int code=jo.getInt("cod");
                String codactividad=jo.getString("cod");
                String descripcionactividad=jo.getString("des");

                categoria =new Categoria();
                categoria.setCodcateg(code);
                categoria.setCodcat(codactividad);
                categoria.setDescat(descripcionactividad);
                clientes.add(categoria);


            }
            return true;

        } catch (JSONException e) {

            e.printStackTrace();
        }

        return false;
    }

}

