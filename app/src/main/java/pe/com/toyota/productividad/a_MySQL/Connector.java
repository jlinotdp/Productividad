package pe.com.toyota.productividad.a_MySQL;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by jlino on 10/04/2018.
 */

public class Connector {

    public static HttpURLConnection connect(String urlAddress){
        try {

            URL url=new URL(urlAddress);
            HttpURLConnection con=(HttpURLConnection) url.openConnection();


            con.setRequestMethod("GET");
            con.setConnectTimeout(10000);
            con.setReadTimeout(10000);
            con.setDoInput(true);
            con.setDoOutput(true);

            return con;

        }catch(MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e){

            e.printStackTrace();

        }
        return  null;
    }


}
