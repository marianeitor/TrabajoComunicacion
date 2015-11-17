package connectionpoint.trabajocomunicacion;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnFarmacia = (Button) findViewById(R.id.btnFarmacias);
        Button btnKiosco = (Button) findViewById(R.id.btnKioscos);
        Button btnComidas = (Button) findViewById(R.id.btnComidas);
        Button btnVarios = (Button) findViewById(R.id.btnOtros);
        Button btnCarneVerdu = (Button) findViewById(R.id.btnCarneVerdu);

        btnFarmacia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMapActivity("farmacias");
            }
        });
        btnKiosco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMapActivity("kioscos");
            }
        });
        btnComidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMapActivity("comidas");
            }
        });
        btnVarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMapActivity("varios");
            }
        });
        btnCarneVerdu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMapActivity("carneverdu");
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void openMapActivity(String categoria)
    {
        Intent mainIntent = new Intent().setClass(MainActivity.this, MapsActivity.class);
        mainIntent.putExtra("categoria",categoria);
        startActivity(mainIntent);
    }
}
