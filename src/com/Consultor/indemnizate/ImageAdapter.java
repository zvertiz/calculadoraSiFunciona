package com.Consultor.indemnizate;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter  {

	 private Context mContext;

	    public ImageAdapter(Context c) {
	        mContext = c;
	    }

	    public int getCount() {
	        return pantallaInicial.length;
	    }

	    public Object getItem(int position) {
	        return null;
	    }

	    public long getItemId(int position) {
	        return 0;
	   }

	    // reate a new ImageView for each item referenced by the Adapter
	    public View getView(int position, View convertView, ViewGroup parent) {
	        ImageView imageView;
	        if (convertView == null) {  // if it's not recycled, initialize some attributes
	            imageView = new ImageView(mContext);
	            imageView.setLayoutParams(new GridView.LayoutParams(100, 100)); //Tamaño de los íconos
	            imageView.setScaleType(ImageView.ScaleType.FIT_END);
	            imageView.setPadding(1, 1, 1, 1);//Distancia entre imágenes
	        } else {
	            imageView = (ImageView) convertView;
	        }
	         imageView.setImageResource(pantallaInicial[position]);
	        return imageView;
	    }

	    // references to our images
	    private Integer[] pantallaInicial = {
	            R.drawable.il, R.drawable.pa,
	     //     R.drawable.botonimagenissste, R.drawable.botonimagenissste
	            
	    };
}
