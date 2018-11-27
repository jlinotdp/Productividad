package pe.com.toyota.productividad.a_FunActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;

import pe.com.toyota.productividad.MainActivity;
import pe.com.toyota.productividad.R;
import pe.com.toyota.productividad.a_MySQL.Categoria.Downloaderact;

public class categoria extends AppCompatActivity {


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.terminar, menu);
        return super.onCreateOptionsMenu(menu);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria);


        Intent i=this.getIntent();
        String usuario=i.getExtras().getString("USUARIOCLAS_KEY");
        String codusuario=i.getExtras().getString("codusu");


        final ListView lvmat=(ListView) findViewById(R.id.lvcategoria);
        final  String urlAddress="http://wamp.toyotaperu.com.pe/AlmacenApp/asistencia.php";
        new Downloaderact(categoria.this,urlAddress,lvmat,usuario,codusuario).execute();



    }


    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_finalizar:

                Intent i=this.getIntent();
                String usuario=i.getExtras().getString("USUARIOCLAS_KEY");
                String codusuario=i.getExtras().getString("codusu");

                final ProgressDialog ringProgressDialog = ProgressDialog.show(categoria.this, "Estado", "Validando usuario...", true);

                ringProgressDialog.setCancelable(false);


                final String DATA_CHK_URL_MYSQL_SAP="http://wamp.toyotaperu.com.pe/AlmacenApp/terminar_actividad2.php";


                AndroidNetworking.post(DATA_CHK_URL_MYSQL_SAP)
                        .addBodyParameter("codper",codusuario)
                        // .addBodyParameter("FECFAC",)
                        .setTag("TAG_CHEQUEO_SAP")
                        .build()
                        .getAsJSONArray(new JSONArrayRequestListener() {
                            @Override
                            public void onResponse(JSONArray response1) {

                                try {
                                    ringProgressDialog.dismiss();
                                    //    Toast.makeText(Marca_Guardado.this, "Proceso Guardado Creado idusuario: "+usuario+"mensaje: "+mensaje+" proceso: "+proceso+" numref: "+numref, Toast.LENGTH_SHORT).show();

                                    ringProgressDialog.dismiss();
                                    AlertDialog.Builder dialogo1 = new AlertDialog.Builder(categoria.this);
                                    dialogo1.setTitle("Estado");
                                    dialogo1.setMessage("Actividad finalizada");
                                    dialogo1.setCancelable(false);
                                    dialogo1.setPositiveButton("Salir", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialogo1, int id) {

                                            //     Toast.makeText(c,saptxt.getText().toString()+"Proceso Seleccionado"+codigotxt.getText().toString(),Toast.LENGTH_SHORT).show();

                                        }
                                    });
                                    dialogo1.show();




                                }catch (Exception e){
                                    Toast.makeText(categoria.this, "Error"+e, Toast.LENGTH_SHORT).show();

                                }

                            }

                            @Override
                            public void onError(ANError anError) {
                                ringProgressDialog.dismiss();
                                Toast.makeText(categoria.this, "Error:"+anError, Toast.LENGTH_SHORT).show();

                            }
                        });









                return false;
            case R.id.action_home:


                Intent intent=new Intent(categoria.this,MainActivity.class);

                startActivity(intent);

                finish();

                startActivity(intent);
                return false;
            default:
                return super.onOptionsItemSelected(item);
        }


    }





}
