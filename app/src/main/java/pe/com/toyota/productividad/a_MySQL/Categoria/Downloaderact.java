package pe.com.toyota.productividad.a_MySQL.Categoria;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import pe.com.toyota.productividad.a_MySQL.Connector;

/**
 * Created by jlino on 10/04/2018.
 */

public class Downloaderact  extends AsyncTask<Void,Void,String> {
    Context c;
    String urlAddress;
    ListView lvppend;
    String usuariod;
    String codusu;

    ProgressDialog pd;

    public Downloaderact(Context c, String urlAddress, ListView lvppend, String usuariod,String codusu) {
        this.c = c;
        this.urlAddress = urlAddress;
        this.lvppend = lvppend;
        this.usuariod = usuariod;
        this.codusu=codusu;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd=new ProgressDialog(c);
        pd.setTitle("Buscando");
        pd.setMessage("Espere un momento...");
        pd.show();
    }

    @Override
    protected String doInBackground(Void... params) {
        return this.downloadData();
    }



    @Override
    protected void onPostExecute(String jsonData) {
        super.onPostExecute(jsonData);



        pd.dismiss();

        if (jsonData==null)
        {
            Toast.makeText(c,"Error: Cargue de nuevo"+jsonData,Toast.LENGTH_SHORT).show();

        }else
        {
            //parseSSSS

            //  Toast.makeText(c,"eeeeeeeeeeee",Toast.LENGTH_SHORT).show();
            new DataParseract(c,jsonData,lvppend,usuariod,codusu).execute();


        }





    }



    private  String downloadData(){

        HttpURLConnection con= Connector.connect(urlAddress);

        if (con==null){
            return null;

        }
        try {

            InputStream is=new BufferedInputStream(con.getInputStream());
            BufferedReader br=new BufferedReader(new InputStreamReader(is));

            String line;
            StringBuffer jsonData=new StringBuffer();



            while ((line=br.readLine()) !=null ){

                jsonData.append(line+'\n');


            }

            br.close();
            is.close();

            return jsonData.toString();


        }catch (IOException e){
            e.printStackTrace();

        }


        return null;

    }


}

