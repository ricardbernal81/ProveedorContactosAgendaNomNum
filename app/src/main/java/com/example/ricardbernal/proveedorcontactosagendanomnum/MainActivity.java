package com.example.ricardbernal.proveedorcontactosagendanomnum;


import android.content.ContentResolver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.database.Cursor;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Data;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.support.v4.widget.CursorAdapter;



public class MainActivity extends AppCompatActivity {

    //ListView lvContactos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //lvContactos = (ListView)findViewById(lvContactos);
        cargarContactos();
    }

    public void cargarContactos(){
        ContentResolver resolver=this.getContentResolver();

        Cursor c=resolver.query(Data.CONTENT_URI,
                null,
                Data.MIMETYPE+"='"+Phone.CONTENT_ITEM_TYPE+"",
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
