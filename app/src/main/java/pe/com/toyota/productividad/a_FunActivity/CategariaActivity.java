package pe.com.toyota.productividad.a_FunActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import pe.com.toyota.productividad.R;
import pe.com.toyota.productividad.a_MySQL.Actividad.Downloaderactm;

public class CategariaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categaria);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent i=this.getIntent();


        int actv=i.getExtras().getInt("CODCAT_KEY");
        String usuariocat=i.getExtras().getString("USUCAT_KEY");
        String codusu=i.getExtras().getString("codusu");

        final ListView lvmat=(ListView) findViewById(R.id.lvactividad);
        final  String urlAddress="http://wamp.toyotaperu.com.pe/AlmacenApp/asistencia_actividad.php?activi="+actv;
        new Downloaderactm(CategariaActivity.this,urlAddress,lvmat,usuariocat,codusu,actv).execute();


    }


    @Override
    public boolean onSupportNavigateUp() {
        // onBackPressed();
        Intent i=this.getIntent();

        int actv=i.getExtras().getInt("CODCAT_KEY");
        String usuariocat=i.getExtras().getString("USUCAT_KEY");
        String codusu=i.getExtras().getString("codusu");;

        Intent intent=new Intent(CategariaActivity.this,categoria.class);

        intent.putExtra("USUARIOCLAS_KEY",usuariocat);
        intent.putExtra("codusu",codusu);

        startActivity(intent);

        return true;
    }

}
