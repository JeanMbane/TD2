package com.dev.devoir1;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

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
import android.widget.Toast;

public class EnregistrementActivity extends ActionBarActivity implements OnClickListener {

	EditText nom;
	EditText pass;
	EditText conf;
	Button env;
	Button ret;
	Text resl;
	
    OutputStream fos;
	String filname="lefichier";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.enregistrement);
		
		nom=(EditText)findViewById(R.id.non);
		pass=(EditText)findViewById(R.id.pass);
		env=(Button)findViewById(R.id.enreg);
		ret=(Button)findViewById(R.id.ret);
		
		env.setOnClickListener(this);
		ret.setOnClickListener(this);
		
		
		
	}
	




	@Override
	public void onClick(View v) {
		
		switch(v.getId())
		{
		case R.id.enreg :
			
			EditText N=(EditText)findViewById(R.id.non);
			EditText p=(EditText)findViewById(R.id.pass);
			EditText conf=(EditText)findViewById(R.id.conf);
			String No= N.getText().toString() ;
			String Pa=p.getText().toString() ;
			String cof=conf.getText().toString() ;
			
						
			
			if(cof.contentEquals(Pa)) //on compare les les 2 chaine de carractaire (pass et conf)...
			{ 
							
			 if(No.contentEquals("") && Pa.contentEquals("")) // au cas ou il ya les espace vide...
			{			 
		   Toast.makeText(EnregistrementActivity.this,"il ya rien",Toast.LENGTH_LONG).show();	
			}
			else
			{
				Write(getBaseContext(),No,Pa); // on appelle la metode write	
			}					
				Intent f= new Intent(this,MainActivity.class); //premet de revenir a la page de login...
				startActivity(f);
				Toast.makeText(EnregistrementActivity.this,"merci de bien vouloir vous loge : "+No,Toast.LENGTH_LONG).show();	
			
								
			}else
			{
			Toast.makeText(EnregistrementActivity.this,"le password est incorrecte veiller reconfirmer merci",Toast.LENGTH_LONG).show();	
			}
			

			break;
					
			
		case R.id.ret:
		
			Intent r= new Intent(this,MainActivity.class);
			startActivity(r);
			
			break;
		
		
		}
		
	}
	
	 public void Write(Context context, String data,String pa){ 
		          
  String b= cryptage(pa) ; // la fction du criptage
   FileOutputStream fOut = null; 
   OutputStreamWriter osw = null; 
	   		        
	   		            
  try{ 
	 fOut = context.openFileOutput("mfiles.txt",MODE_APPEND);       
	  osw = new OutputStreamWriter(fOut);
	   		           
	  osw.write(data+"\n");
	  osw.write(b+"\n");// c'est a le problm.......  		            
	  osw.flush(); 
	   		           //popup surgissant pour le résultat
	  Toast.makeText(context, "vous avez ete enregistre",Toast.LENGTH_SHORT).show(); 
	   		            
	   		           }
	   		        
	   		            catch (Exception e) {       
	   		                    Toast.makeText(context, "vous n'avez pas ete enregistre",Toast.LENGTH_SHORT).show(); 
	   		            } 
	   		            finally { 
	   		               try { 
	   		                     osw.close();
	   		                      osw.close();
	   		                      fOut.close(); 
	   		                      } catch (IOException e) { 
	   		                               Toast.makeText(context, "vous n'avez pas ete enregistre",Toast.LENGTH_SHORT).show(); 
	   		                      }
	   		            } 
	   		       }
	   					 		
		/* 
		 FileInputStream entre=null;
		// InputStreamReader lire=null;
		
		// char[] recuppas=new char[255];
		 String lnon=null;
		 
		
		 
		try {
			
			entre=context.openFileInput("mfiles.txt");
			//lire= new InputStreamReader(entre);
			 byte[] recupnon= new byte[entre.available()];
			 while(entre.read(recupnon)!=-1)
			 {
				 lnon=new String(recupnon);;//on le transforme en string
					
			 }
				
			Toast.makeText(context, " "+lnon, Toast.LENGTH_LONG).show();
	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Toast.makeText(context, "sa ne lie pas villagoie.. ", Toast.LENGTH_LONG).show();

		}
		finally{
		try {
			entre.close();
			return lnon;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Toast.makeText(context, "revoir ", Toast.LENGTH_LONG).show();

		}
		
		}
		return null;
		 
	 }*/
	 //}
	 public  String cryptage(String pa)
	 {
	 		
			int []element=new int[pa.length()];
			char []dechiffre=new char[pa.length()];
			int valeur;
			String n="";
			
			int cle=3;
			char lettre[]={'a','b','c','d','e','f','g','h','i','j','k','l',
					'm','n','o','p','q','r','s','t','u','v','w','x','y','z'};
			
			
			
	   //	if(pa.length() != lettre.length) //facultatif
		//	{
				
				
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
			 
	// }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.enregistrement, menu);
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




