package com.Consultor.indemnizate;

import com.actionbarsherlock.app.SherlockActivity;

import java.text.DecimalFormat;
import java.util.Calendar;
import android.os.Bundle;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;



public class Indemnizacion extends SherlockActivity {
	private EditText etSueldo,etAntiguedad,etResultado1,etResultado2;
	private Button boCalendario;
	private int anopd, mespd, diapd;//A�o pick date
	private int anopdAnt, mespdAnt, diapdAnt;//A�o pick date
	static final int DATE_DIALOG_ID = 0;
	int pp;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.indemnizacion);
   
        etSueldo=(EditText)findViewById(R.id.etSueldo);
        etAntiguedad=(EditText)findViewById(R.id.etAntiguedad);
        etResultado1=(EditText)findViewById(R.id.etResultado1);
        etResultado2=(EditText)findViewById(R.id.etResultado2);
        
        //Lista despegable periodicidad de pago   
        Spinner sppp = (Spinner) findViewById(R.id.sppp);	
        ArrayAdapter<CharSequence> adaptadorSppp = ArrayAdapter.createFromResource(this, R.array.sppp, R.layout.centradospinner);
        adaptadorSppp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sppp.setAdapter(adaptadorSppp);
        
        sppp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        			public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
        				pp = position; 
        				}
        			public void onNothingSelected(AdapterView<?> parent){
        				}
				});

        
        //Bot�n Calendario
        boCalendario=(Button)findViewById(R.id.boCalendario);
        boCalendario.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				showDialog(DATE_DIALOG_ID);
			}
		});
        
        final Calendar c = Calendar.getInstance();
        anopd = c.get(Calendar.YEAR);
        mespd = c.get(Calendar.MONTH);
        diapd = c.get(Calendar.DAY_OF_MONTH);
            
        }
	
	//Si le da establecer o "set"
	private DatePickerDialog.OnDateSetListener mDateSetListener =
    		new DatePickerDialog.OnDateSetListener() {				
				public void onDateSet(DatePicker arg0, int year, int month, int day) {
					anopdAnt = year;
					mespdAnt = month;
					diapdAnt = day;
					diferenciaEntreFechas();
				}
			};

			
		    //Al oprimir el bot�n se inicializa este m�todo
			@Override
	        protected Dialog onCreateDialog(int id) { 
	         	switch (id) {
	        	case DATE_DIALOG_ID:
	        		return new DatePickerDialog(this,mDateSetListener,anopd,mespd,diapd);
	        	}
	        	return null;
	        }

			
			public void crearDialogoError() {

				AlertDialog dialogoError = new AlertDialog.Builder(this).create();
		    	
		    	dialogoError.setTitle("Fecha err�nea");
		    	dialogoError.setMessage("La fecha de ingreso o antig�edad debe ser menor que la fecha de salida");
		    	dialogoError.setButton("Reintentar", new OnClickListener() {
		    		public void onClick(DialogInterface dialog, int which) {
		    			dialog.cancel();
		    		}
		    	});
		    	
		    	dialogoError.show();
				
			}

			

			
public void diferenciaEntreFechas() {
				    
				        // Crear 2 instancias de Calendar
				        Calendar cal1 = Calendar.getInstance();
				        Calendar cal2 = Calendar.getInstance();
				 
				        // Establecer las fechas
				        cal1.set(anopdAnt, mespdAnt, diapdAnt);
				        cal2.set(anopd, mespd, diapd);
				 
				        // Milisegundos
				        long mil1 = cal1.getTimeInMillis();
				        long mil2 = cal2.getTimeInMillis();
				 
				        // Calcular la diferencia en milisengundos
				        long dif = mil2 - mil1;
				    	

				        if(dif <= 0) { 
				        	crearDialogoError(); 
				        	
					        
				        	
				        }
				        
				        // calcular la diferencia en d�as
				        float difDias = dif / (24 * 60 * 60 * 1000);
				        // calcular la diferencia en a�os
				        float difAnos= difDias/365;
				        String sdifAnos = String.valueOf(difAnos);
				        etAntiguedad.setText(sdifAnos);
				
			}
	
public void calculaIL (View view) {
		
			if ((etSueldo.getText().toString().length() == 0)||(etAntiguedad.getText().toString() .length() == 0)) { //Si alguno es vac�o 
				if(etSueldo.getText().toString().length() == 0) etSueldo.setError( "Campo requerido" ); //Si el sueldo es vacio
				if(etAntiguedad.getText().toString().length() == 0) etAntiguedad.setError( "Campo requerido" ); //Si la antig�edad es vacia
			}else{
			
				String sSueldo = etSueldo.getText().toString();//S de string 
				String sAntiguedad    = etAntiguedad.getText().toString();
				
				float nSueldo = Float.parseFloat(sSueldo); //N de numero
				float nAntiguedad = Float.parseFloat(sAntiguedad);
	
				//Pseudo validaciones
				
				if((nSueldo == 0)||(nAntiguedad <= 0)||(nAntiguedad >= 50)) {
					if(nAntiguedad <= 0) etAntiguedad.setError( "La antig�edad debe ser estrictamente mayor a cero" ); //Si la antig�edad <=0
					if(nAntiguedad >= 50) etAntiguedad.setError( "La antig�edad m�xima permitida son 50 a�os" ); //Si la antig�edad >=50
					if(nSueldo <= 0) etSueldo.setError( "El sueldo debe ser estrictamente mayor a cero" ); //Si el sueldo <= cero
				}else{
				
					//Periodicidad de pago
				
					float nSueldoMensual;
					
					switch(pp) {
				    case 0:
				    	nSueldoMensual=nSueldo*1;//Mensual
				        break;
				    case 1:
				    	nSueldoMensual=nSueldo*30;//Diario
				    	break;
				    case 2:
				    	nSueldoMensual=nSueldo/12;//Anual
				    	break;
				    default:
				    	nSueldoMensual=nSueldo*1;
					}
					float nIndemnizacion = (90 + 20 * nAntiguedad) * nSueldoMensual /30;
					
					//Para salario integrado por ley
					
					
					float agu = nSueldoMensual / 30*15;
					
					//float dvac(6,6,6,6,66,6,6,6,6);
					
					float vac = (float) 0.50; //* dvac(nAntiguedad);
					
					float nSueldoMensualI = nSueldoMensual + agu + vac; 
					
					float nIndemnizacionSi = (90 + 20 * nAntiguedad) * nSueldoMensualI/30;
					
					//String sIndemnizacion = String.valueOf(nIndemnizacion);
					//etResultado.setText(sIndemnizacion);
					
					DecimalFormat miles = new DecimalFormat(",000");
					
					etResultado1.setText(miles.format(nIndemnizacion));
					etResultado2.setText(miles.format(nIndemnizacionSi));
				
					//Para formato del sueldo una vez que oprimo el bot�n calcular
					
					//etSueldo.setText(miles.format(nSueldo));
				}	
			}
		}
}
