package pe.com.toyota.productividad.a_FunActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;
import org.json.JSONException;

import pe.com.toyota.productividad.a_DataObject.Categoria;
import pe.com.toyota.productividad.a_DataObject.Login;

/**
 * Created by jlino on 10/04/2018.
 */

public class MySQLLogin {


    private static final String DATA_INSERT_URL="http://wamp.toyotaperu.com.pe/AlmacenApp/conexion2.php";
    // private static final String DATA_RETRIEVE_URL="http://10.0.2.2/ndroid3/index.php";

    //INSTANCE FIELDS
    private final Context c;


    public  MySQLLogin(Context c) {
        this.c = c;

    }
    /*
   SAVE/INSERT
    */
    public void addpausa(final Login s, final View...inputViews) {
        if (s == null) {
            Toast.makeText(c, "Por favor, Complete el formulario", Toast.LENGTH_SHORT).show();
        } else {

            //  Toast.makeText(c, "Ingresando datos...", Toast.LENGTH_SHORT).show();
                       final ProgressDialog ringProgressDialog = ProgressDialog.show(c, "Estado", "Validando usuario...", true);

            ringProgressDialog.setCancelable(false);

            AndroidNetworking.post(DATA_INSERT_URL)
                    .addBodyParameter("user_name",s.getUsuario())
                    .addBodyParameter("password",s.getPassword())
                    .setTag("TAG_ADD")
                    .build()
                    .getAsJSONArray(new JSONArrayRequestListener() {
                        @Override
                        public void onResponse(JSONArray response) {

                            if(response != null)
                                try {
                                    //SHOW RESPONSE FROM SERVER
                                    String responseString = response.get(0).toString();

                                    String responseString1 = response.get(1).toString();
                                    //   Toast.makeText(c, "PHP SERVER RESPONSE : " + responseString, Toast.LENGTH_SHORT).show();

                                    if (responseString.equalsIgnoreCase("Success")) {
                                        //RESET VIEWS
                                        ringProgressDialog.dismiss();
                                        Toast.makeText(c, "Login corrrecto ", Toast.LENGTH_SHORT).show();

                                        Intent i=new Intent(c, categoria.class);
                                        i.putExtra("USURIOCLAS_KEY",s.getUsuario());

                                        i.putExtra("codusu",responseString1);
                                        c.startActivity(i);

                                        EditText etName = (EditText) inputViews[0];
                                        EditText etName2 =(EditText) inputViews[1];

                                        etName.setText("");
                                        etName2.setText("");


                                    }else
                                    {

                                        EditText etName = (EditText) inputViews[0];
                                        EditText etName2 =(EditText) inputViews[1];

                                        etName.setText("");
                                        etName.setSelection(0);
                                        etName2.setText("");

                                        ringProgressDialog.dismiss();
                                        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(c);
                                        dialogo1.setTitle("Login Incorrecto");
                                        dialogo1.setMessage(responseString);
                                        dialogo1.setCancelable(false);
                                        dialogo1.setPositiveButton("Salir", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialogo1, int id) {

                                            }
                                        });
                                        dialogo1.show();


                                        //     Toast.makeText(c, "Usuario y/o contraseña incorrecta", Toast.LENGTH_LONG).show();

                                    }


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    ringProgressDialog.dismiss();
                                    Toast.makeText(c, "GOOD RESPONSE BUT JAVA CAN'T PARSE JSON IT RECEIVED : "+e.getMessage(), Toast.LENGTH_SHORT).show();
                                }

                        }

                        //ERROR
                        @Override
                        public void onError(ANError anError) {
                            ringProgressDialog.dismiss();
                            Toast.makeText(c, "UNSUCCESSFUL :  ERROR IS : "+anError.getMessage(), Toast.LENGTH_SHORT).show();
                        }


                    });




        }

    }

}
