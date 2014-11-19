package com.dev.devoir1;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.w3c.dom.Text;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements OnClickListener {

	 EditText nom;
     EditText pass;
	 Button login;
	 Button rest;
	 Button enrg;
	 TextView q;
	 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		   q=(TextView)findViewById(R.id.nonlog);
		  nom=(EditText)findViewById(R.id.non);
	      pass=(EditText)findViewById(R.id.pass);
		  login=(Button)findViewById(R.id.log);
		  rest=(Button)findViewById(R.id.rest);
		  enrg=(Button)findViewById(R.id.enreg);
		  
		  
		 login.setOnClickListener(this);
		 rest.setOnClickListener(this);
		 enrg.setOnClickListener(this);
		 
	}
	// mise en marche des buttons....
	@Override
	public void onClick(View v) {
		
		String No = nom.getText().toString();
		String pas= pass.getText().toString();
		
		
		switch(v.getId())
		
		{
		case R.id.log:
			EditText N=(EditText)findViewById(R.id.non);
			EditText p=(EditText)findViewById(R.id.pass);
		
			String z= N.getText().toString() ;
			String w=p.getText().toString() ;
			
			// pett condition au cas il les non et pass et vid...
			if(z.equals("")||w.equals(""))
			{
	Toast.makeText(MainActivity.this, "Tu nous prend pour qui villageoi...",Toast.LENGTH_SHORT).show();

			}else
			{
			read(getBaseContext(),z,w);// on appelle la methode read()..
			
			}
			
			break;
			
		case R.id.rest:
			Intent r= new Intent(this,MainActivity.class);
			startActivity(r);
			// nom.getText().clear();
			//pass.getText().clear();
		
			break;
		
		case R.id.enreg:
			//au cas on a pas le nom et le pass on peus s'enregistrer 
			if(No.equals("") || pas.equals(""))
			{
				// on se dirige vers la page enregistremt...
				Intent en= new Intent(this,EnregistrementActivity.class);
				startActivity(en);
				
			} else 
				Toast.makeText(MainActivity.this, "vous etez deja enregistrer.. :)",Toast.LENGTH_SHORT).show();

			break;
		
		
		}
		
	}
	// la methode read.....
	 public  void read( Context context,String lo,String Pa) 
	 {
	
		 String h=null;
		 String j=null;
		 q=(TextView)findViewById(R.id.nonlog);// c'est le text login pas tres important
			 lo= nom.getText().toString() ;
		     Pa=pass.getText().toString() ;
		     String y=cryptage(Pa);
		  // on vas cherche a ouvri  mfiles.txt
		  try {
		    //ouvrire le fichier pour lire 
		    InputStream fichier = openFileInput("mfiles.txt");
		 
		    // if file the available for reading
		    if (fichier!=null) {
		      // on prepare le document que on dois lire
		      InputStreamReader inputreader = new InputStreamReader(fichier);
		      BufferedReader buffreader = new BufferedReader(inputreader);
	           
		      String line;
		 
		      // on vas lire chaque ligne pendant un abs de temps
		      try {
				while((line = buffreader.readLine()) != null) {
				    // on compare le non intraduit avc celle existant dans le fiche.txt
					//Toast.makeText(context, "loading", Toast.LENGTH_LONG).show();
					
					if(line.equals(lo)){
					h=new String(line);
				
					}
					// on compare le pass intraduit avc celle existant dans le fiche.txt
					else if(line.equals(y)){
						j=new String(line);
				    }}
				    	
				    	
					
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
		    }  
		    // ferme le fich..       
		    try {
		    	fichier.close();
				
				if(h!=null && j!=null)
				{
					q.setText(h);
					Toast.makeText(MainActivity.this, "welcom: "+""+h,Toast.LENGTH_LONG).show();	
						 
					
				} else 
				{ Toast.makeText(MainActivity.this, "error verifier le ID ou PASSWORD  ",Toast.LENGTH_LONG).show();
	          
				}
				
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  } catch (java.io.FileNotFoundException e) {
		    // do something if the myfilename.txt does not exits
		  }
		//return null;
	 }
	 // le crytage...
	 public  String cryptage(String pa)
	 {
	 		
			int []element=new int[pa.length()];
			char []dechiffre=new char[pa.length()];
			int valeur;
			String n="";
			
			int cle=3;
			char lettre[]={'a','b','c','d','e','f','g','h','i','j','k','l',
					'm','n','o','p','q','r','s','t','u','v','w','x','y','z'};
				
	   		for (int i = 0; i < pa.length(); i++) {
				for (int j = 0; j < lettre.length; j++) {
					if (pa.charAt(i) == lettre[j]) {
						
					valeur=j-cle;
					
					if(valeur<0)
					{
						valeur+=26;
					}else {valeur=valeur;}		
					
					if(valeur>=26)
	                {
					element[i]=valeur%26;	
					}else
					{element[i]=valeur;}
					}
					
					
					}

					
				}
	   		
	   		for(int i=0;i<pa.length();i++)
	   		{
	   			
	   		 /*  if(pa.charAt(i)== ' ')  // les espaces
	   		   {
	   			   dechiffre[i]=pa.charAt(i);
	   			   
	   		   }*/
	   		   
	   			
	   		   for(int j=0;j<lettre.length;j++)
	   		   {
	   			   if(element[i]==j)
	   			   {
	   				 dechiffre[i]=lettre[j];
	   				 n+=dechiffre[i];
	   			   }
	   			

	   		   }
	   		     
	   		}return n;
	   		
			}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
