package com.Consultor.indemnizate;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        GridView gv = (GridView)findViewById(R.id.gvMenu2X2);
           gv.setAdapter(new ImageAdapter(this));
           
           gv.setOnItemClickListener(new OnItemClickListener() {
               public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
            	   if (position == 0) {
            		   Toast.makeText(MainActivity.this, "Indemnizacion", Toast.LENGTH_SHORT).show();
            		   //Toast.makeText(MainActivity.this, "Bienvenido a Indemnízate" + position, Toast.LENGTH_SHORT).show();
                  		   
            		   startActivity(new Intent(MainActivity.this, Indemnizacion.class));

            	   }
            	   else if (position == 1 ){
                   Toast.makeText(MainActivity.this, "Prima de Antigüedad" , Toast.LENGTH_SHORT).show();
                   		
                   		startActivity(new Intent(MainActivity.this, Prima.class));

            	   }
               }
           });
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}
