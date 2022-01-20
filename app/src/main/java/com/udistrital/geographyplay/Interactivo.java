package com.udistrital.geographyplay;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

public class Interactivo extends AppCompatActivity {


    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ImageView cerrarSesion;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interactivo);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        cerrarSesion = findViewById(R.id.cerrarSesion);
        cerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("","Cerro sesion");
                FirebaseAuth.getInstance().signOut();
                Intent login = new Intent(Interactivo.this, login.class);
                startActivity(login);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_interactivo, menu);
        return true;
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position){
                case 0:
                    fragment = new pais1();
                    break;
                case 1:
                    fragment = new pais2();
                    break;
                case 2:
                    fragment = new pais3();
                    break;
                case 3:
                    fragment = new pais4();
                    break;
                case 4:
                    fragment = new pais5();
                    break;
                case 5:
                    fragment = new pais6();
                    break;
                case 6:
                    fragment = new pais7();
                    break;
                case 7:
                    fragment = new pais8();
                    break;
                case 8:
                    fragment = new pais9();
                    break;
                case 9:
                    fragment = new pais10();
                    break;
                case 10:
                    fragment = new pais11();
                    break;
                case 11:
                    fragment = new pais12();
                    break;
                case 12:
                    fragment = new pais13();
                    break;
                case 13:
                    fragment = new pais14();
                    break;
                case 14:
                    fragment = new pais15();
                    break;
                case 15:
                    fragment = new pais16();
                    break;
                case 16:
                    fragment = new pais17();
                    break;
                case 17:
                    fragment = new pais18();
                    break;
                case 18:
                    fragment = new pais19();
                    break;
                case 19:
                    fragment = new pais20();
                    break;
                case 20:
                    fragment = new pais21();
                    break;
                case 21:
                    fragment = new pais22();
                    break;
                case 22:
                    fragment = new pais23();
                    break;
                case 23:
                    fragment = new pais24();
                    break;
                case 24:
                    fragment = new pais25();
                    break;
                case 25:
                    fragment = new pais26();
                    break;
                case 26:
                    fragment = new pais27();
                    break;
                case 27:
                    fragment = new pais28();
                    break;
                case 28:
                    fragment = new pais29();
                    break;
                case 29:
                    fragment = new pais30();
                    break;
                case 30:
                    fragment = new pais31();
                    break;
                case 31:
                    fragment = new pais32();
                    break;
                case 32:
                    fragment = new pais33();
                    break;
                case 33:
                    fragment = new pais34();
                    break;
                case 34:
                    fragment = new pais35();
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 35;
        }
    }
}