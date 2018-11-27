package pe.com.toyota.productividad;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import pe.com.toyota.productividad.a_DataObject.Login;
import pe.com.toyota.productividad.a_FunActivity.MySQLLogin;

public class MainActivity extends AppCompatActivity {

    EditText txtusuario;
    EditText txtpass;
    Context c;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtusuario = (EditText) findViewById(R.id.txtusu);
        txtpass = (EditText) findViewById(R.id.txtpass);
    }

    public  void OnLogin(View view){
        final   String username= txtusuario.getText().toString();
        final  String password=txtpass.getText().toString();


        Login s=new Login();
        s.setUsuario(username);
        s.setPassword(password);


        if (username.equalsIgnoreCase("")){

            Toast.makeText(MainActivity.this, "Complete el campo usuario ", Toast.LENGTH_SHORT).show();


        }else
        {
            if (password.equalsIgnoreCase("")){


                Toast.makeText(MainActivity.this, "Complete el campo contraseña ", Toast.LENGTH_SHORT).show();

            }else
            {




                new MySQLLogin(MainActivity.this).addpausa(s,txtusuario,txtpass);





            }


        }



        //  String type="login";


//        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        //      backgroundWorker.execute(type, username, password);

//    intent.putExtra("USU_KEY", txtusuario.getText().toString());


    }



    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_setting:

                int pq = android.os.Process.myPid();
                android.os.Process.killProcess(pq);
                return true;
            case R.id.action_salir:


                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                finish();
                startActivity(intent);
                System.exit(0);
                finish();

                return false;

            default:
                return super.onOptionsItemSelected(item);
        }


    }













}
