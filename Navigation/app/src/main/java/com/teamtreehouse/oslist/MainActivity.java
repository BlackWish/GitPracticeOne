package com.teamtreehouse.oslist;

import android.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.view.View;
import android.widget.Toast;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.content.res.Configuration;
import android.app.FragmentManager;
import android.app.FragmentTransaction;


public class MainActivity extends ActionBarActivity {

    private ListView mDrawerList;
    private ArrayAdapter<String> mAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private String mActivityTitle;
    private int selectedPosition;
    private String[] drawerArray= { "Home", "Recipe List", "Recipes", "Timer", "Linux" };
    private boolean objectControl=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Connecting ID for DrawerList
        mDrawerList = (ListView)findViewById(R.id.navList);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

        //returns the title of this activity as a string
        mActivityTitle = getTitle().toString();

        //Drawer Items
        addDrawerItems();

        //Listener
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(MainActivity.this, "Time for an upgrade!", Toast.LENGTH_SHORT).show();
                selectedPosition = position;

                //Replace fragment content
                updateFragment();
                mDrawerLayout.closeDrawer(mDrawerList);

            }
        });

        //Customizes action bar to show up icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        //Drawer setup
        setupDrawer();
    }

    //*** Helper method *** //
    //Items in Drawer
    public void addDrawerItems() {
        String[] osArray = { "Home", "Recipe List", "Recipes", "Timer", "Linux" };
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(mAdapter);
    }


    //Setup drawer
    public void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Menu");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                //getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    //Update the Fragment
    public void updateFragment() {
        //Fragment objFragment= null;

        //Choosing Fragment
        switch (selectedPosition){

            case 0:
                Fragment objFragmentOne= null;
                getSupportActionBar().setTitle("Home");
                objFragmentOne=new Home();
                FragmentManager fragmentManagerOne = getFragmentManager();
                FragmentTransaction ftOne = fragmentManagerOne.beginTransaction();
                ftOne.replace(R.id.mainlayout, objFragmentOne);
                ftOne.commit();
                break;

            case 1:
                Fragment objFragmentTwo= null;
                getSupportActionBar().setTitle("Recipe List");
                objFragmentTwo=new RecipeList();
                FragmentManager fragmentManagerTwo = getFragmentManager();
                FragmentTransaction ftTwo = fragmentManagerTwo.beginTransaction();
                ftTwo.replace(R.id.mainlayout, objFragmentTwo);
                ftTwo.commit();
                break;

            case 2:
                Fragment objFragmentThree= null;
                getSupportActionBar().setTitle("Recipe");
                objFragmentThree=new Recipes();
                FragmentManager fragmentManagerThree = getFragmentManager();
                FragmentTransaction ftThree = fragmentManagerThree.beginTransaction();
                ftThree.replace(R.id.mainlayout, objFragmentThree);
                ftThree.commit();
                break;

            case 3:
                objectControl=true;
                Fragment objFragmentFour= null;
                getSupportActionBar().setTitle("Timer");
                objFragmentFour=new Timer();
                FragmentManager fragmentManagerFour = getFragmentManager();
                FragmentTransaction ftFour = fragmentManagerFour.beginTransaction();
                ftFour.replace(R.id.mainlayout, objFragmentFour);
                ftFour.commit();
                break;

            case 4:
                if(objectControl){
                    Toast.makeText(MainActivity.this, "Timer is running behind", Toast.LENGTH_SHORT).show();
                }else if (!objectControl){
                    Toast.makeText(MainActivity.this, "Timer is not running behind", Toast.LENGTH_SHORT).show();
                }
                break;
        }

        /*FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.mainlayout, objFragment);
        ft.commit();*/

        //WebViewFragment rFragment = new WebViewFragment();

        // Passing selected item information to fragment
        //Bundle data = new Bundle();
        //data.putString("title", drawerArray[selectedPosition]);
        /*data.putString("url", getUrl());
        rFragment.setArguments(data);*/

        //Replace fragment
       /* FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.content_frame, rFragment);
        ft.commit();*/
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

        //handle opening and closing the drawer depending on if it is open or not
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

            return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }


}
