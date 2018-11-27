package pe.com.toyota.productividad.a_MySQL;

import android.content.Context;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;
import org.json.JSONException;

import pe.com.toyota.productividad.a_DataObject.RegistroActividad;

/**
 * Created by jlino on 10/04/2018.
 */

public class Ingresar_actividad {

    private static final String DATA_INSERT_URL="http://wamp.toyotaperu.com.pe/AlmacenApp/iniciar_actividad2.php";
    // private static final String DATA_RETRIEVE_URL="http://10.0.2.2/ndroid3/index.php";

    //INSTANCE FIELDS
    private final Context c;


    public Ingresar_actividad(Context c) {
        this.c = c;

    }
    /*
   SAVE/INSERT
    */
    public void matest(final RegistroActividad react, String codusu, Integer actv, String actividad) {
        if (react == null) {
            Toast.makeText(c, "Sin data", Toast.LENGTH_SHORT).show();
        } else {

            // Toast.makeText(c, "Ingresando datos...", Toast.LENGTH_SHORT).show();ZfmmCheckPersonal(codact, codcat, codper, "I", "");

            AndroidNetworking.post(DATA_INSERT_URL)
                    .addBodyParameter("codact",react.getActcategoria().toString())
                    .addBodyParameter("codcat",react.getActividad())
                    .addBodyParameter("codper",react.getCodusuario())
                    .setTag("TAG_ADD")
                    .build()
                    .getAsJSONArray(new JSONArrayRequestListener() {
                        @Override
                        public void onResponse(JSONArray response) {

                            if(response != null)
                                try {
                                    //SHOW RESPONSE FROM SERVER
                                    String responseString = response.get(0).toString();
                                    //   Toast.makeText(c, "PHP SERVER RESPONSE : " + responseString, Toast.LENGTH_SHORT).show();

                                    if (responseString.equalsIgnoreCase("Success")) {
                                        //RESET VIEWS
                                        //   Toast.makeText(c, "Proceso de actividad inicado ", Toast.LENGTH_SHORT).show();


                                    }else
                                    {
                                        Toast.makeText(c, "Error Success", Toast.LENGTH_LONG).show();

                                    }


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(c, "GOOD RESPONSE BUT JAVA CAN'T PARSE JSON IT RECEIVED : "+e.getMessage(), Toast.LENGTH_SHORT).show();
                                }

                        }

                        //ERROR
                        @Override
                        public void onError(ANError anError) {
                            Toast.makeText(c, "UNSUCCESSFUL :  ERROR IS : "+anError.getMessage(), Toast.LENGTH_SHORT).show();
                        }


                    });




        }

    }


}
