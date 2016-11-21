package com.example.ricardbernal.proveedorcontactosagendanomnum;


import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.database.Cursor;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Data;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.support.v4.widget.CursorAdapter;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    //ListView lvContactos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //lvContactos = (ListView)findViewById(lvContactos);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.READ_CONTACTS)
                    != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CONTACTS},
                        100);
                cargarContactos();

            } else {
                cargarContactos();
            }
        }
    }

        //--------------



        public void onRequestPermissionsResult (int requestCode,
        String permissions[],
        int[] grantResults){
            switch (requestCode) {
                case 100: {
                    // If request is cancelled, the result arrays are empty.
                    if (grantResults.length > 0
                            && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        cargarContactos();
                    } else {
                        Toast.makeText(this, "No se pueden mostrar los contactos", Toast.LENGTH_SHORT).show();
                    }
                    return;
                }
            }
        }




    //--------------


    public void cargarContactos(){
        ContentResolver resolver=this.getContentResolver();

        Cursor c=resolver.query(Data.CONTENT_URI,
                null,
                Data.MIMETYPE+"='"+Phone.CONTENT_ITEM_TYPE+"'",
                null,
                null);

        String[] nombres={Phone.DISPLAY_NAME,Phone.NUMBER};

        int[] ids={android.R.id.text1,android.R.id.text2};

        SimpleCursorAdapter adp = new SimpleCursorAdapter(this,android.R.layout.two_line_list_item,
                c,
                nombres,
                ids,
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        ListView lvContactos=(ListView)this.findViewById(R.id.lvContactos);
            lvContactos.setAdapter(adp);
    }
}
