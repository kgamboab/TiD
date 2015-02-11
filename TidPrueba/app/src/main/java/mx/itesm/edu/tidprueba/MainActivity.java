package mx.itesm.edu.tidprueba;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


public class MainActivity extends ActionBarActivity {

    private String[] itemsTitulos;
    private DrawerLayout drawerLayout;
    private ListView listViewDrawer;
    private ActionBarDrawerToggle toggle;
    private CharSequence tituloDrawer, titulo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       titulo = tituloDrawer = getTitle();
       itemsTitulos = getResources().getStringArray(R.array.opciones);
       drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
       listViewDrawer = (ListView)findViewById(R.id.left_drawer);

        Objeto[] opciones = new Objeto[7];
        opciones[0] = new Objeto(R.drawable.ic_action_augmented, itemsTitulos[0]);
        opciones[1] = new Objeto(R.drawable.ic_action_facebook,itemsTitulos[1]);
        opciones[2] = new Objeto(R.drawable.ic_launcher, itemsTitulos[2]);
        opciones[3] = new Objeto(R.drawable.ic_launcher, itemsTitulos[3]);
        opciones[4] = new Objeto(R.drawable.ic_launcher, itemsTitulos[4]);
        opciones[5] = new Objeto(R.drawable.ic_launcher, itemsTitulos[5]);
        opciones[6] = new Objeto(R.drawable.ic_launcher, itemsTitulos[6]);

        DrawerAdaptador adaptador = new DrawerAdaptador(this, R.layout.renglon, opciones);
        listViewDrawer.setAdapter(adaptador);

        listViewDrawer.setOnItemClickListener(new DrawerListListener());

        if (savedInstanceState == null){
            seleccionaAccion(0);
        }

        toggle = new ActionBarDrawerToggle(
                this,
                     drawerLayout,
                    R.string.abrir,
                    R.string.cerrar
                ){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(tituloDrawer);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getSupportActionBar().setTitle(titulo);
            }
        };

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();
        drawerLayout.setDrawerListener(toggle);




    }

    private class DrawerListListener implements ListView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id){
            seleccionaAccion(position);
        }

    }

    public void seleccionaAccion(int p){
        Fragment fragment = null;
        switch (p){
            case 0:
                fragment = new AugmentedRealityFragment();
                break;

            case 1:
                fragment = new FacebookFragment();
                break;

            case 2:
                Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
                startActivity(it);
                break;

            case 3:
                fragment = new iTunesFragment();
                break;

            case 4:
                fragment = new LineClimateFragment();
                break;

            case 5:
                fragment = new BarClimateFragment();
                break;

            case 6:
                fragment = new PieClimateFragment();
                break;

        }
        if(  fragment != null  ){
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
            listViewDrawer.setItemChecked(p, true);
            listViewDrawer.setSelection(p);
            setTitle(itemsTitulos[p]);
            drawerLayout.closeDrawer(listViewDrawer);
        }else{
            listViewDrawer.setItemChecked(p,true);
            listViewDrawer.setSelection(p);
            setTitle(itemsTitulos[p]);
            drawerLayout.closeDrawer(listViewDrawer);
        }

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


        //noinspection SimplifiableIfStatement
        if (toggle. onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
