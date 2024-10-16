import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.text.Style;
import javax.swing.text.StyledDocument;

public class Archivo {
	
	public Archivo() {
	}
	
	public void abrir(JTextPane texto) {
		try {
			JFileChooser chooser=new JFileChooser();
			chooser.setDialogTitle("Selecciona el Archivo a Abrir");
			chooser.showOpenDialog(null);
			FileReader fr;
			BufferedReader br;
			File seleccion=chooser.getSelectedFile();
			String contenido="";
			
			if(!seleccion.exists()) {
				JOptionPane.showMessageDialog(null, "Error al Abrir el Archivo","Archivo",0);
				return;
			}
			
			if(seleccion.getPath().contains(".txt")) {
				try {
					fr=new FileReader(seleccion);
					br=new BufferedReader(fr);
					
					String linea=br.readLine();
					
					while(linea!=null) {
						contenido+=linea;
						contenido+="\n";
						linea=br.readLine();
					}
					
					texto.setText(contenido);
					JOptionPane.showMessageDialog(null,"Se cargo el documento con exito.","Archivo",1);
					EditorDeTextoGUI.title="Editor de Texto Simple - "+chooser.getSelectedFile().getName();
					EditorDeTextoGUI.archivoAbierto=seleccion;
					EditorDeTextoGUI.doc=texto.getStyledDocument();
					EditorDeTextoGUI.estilo=texto.addStyle("MiEstilo", null);
					EditorDeTextoGUI.abierto=true;
					fr.close();
					return;
				}catch(IOException e) {
					JOptionPane.showMessageDialog(null, "ERROR", "Archivo", 0);
				}
			}
			
			if(seleccion.getPath().contains(".obj")) {
				try {
					ObjectInputStream ois=null;
					try{
						seleccion=null;
						seleccion=new File(chooser.getSelectedFile().getPath());
						FileInputStream fis=new FileInputStream(seleccion);
						ois=new ObjectInputStream(fis);
						
						Documento p=(Documento)ois.readObject();
						JOptionPane.showMessageDialog(null,"Se cargo el documento con exito.","Archivo",1);
						EditorDeTextoGUI.title="Editor de Texto Simple - "+chooser.getSelectedFile().getName();
						EditorDeTextoGUI.archivoAbierto=seleccion;
						EditorDeTextoGUI.abierto=true;
						texto.setText("");
						texto.setText(((Documento)p).getPanel().getText());
						texto.setDocument(((Documento)p).getDoc());
						EditorDeTextoGUI.estilo=p.getEstilo();
						EditorDeTextoGUI.doc=p.getDoc();
					}catch(IOException io){
						JOptionPane.showMessageDialog(null, "ERROR", "Archivo", 0);
					}
					finally{			
						ois .close();
					}				
				}catch(ClassNotFoundException e) {
					JOptionPane.showMessageDialog(null, "ERROR", "Archivo", 0);
				}catch(IOException e) {
					JOptionPane.showMessageDialog(null, "ERROR", "Archivo", 0);
				}
			}else {
				JOptionPane.showMessageDialog(null, "Formato No Admitido","Archivo",0);
				return;
			}
		}catch(NullPointerException e) {
			JOptionPane.showMessageDialog(null, "No Se Selecciono Archivo.", "Archivo", 2);
		}
	}
	
	public void guardar(JTextPane texto,StyledDocument doc, Style estilo) {
		if(EditorDeTextoGUI.abierto) {
			if(EditorDeTextoGUI.archivoAbierto.getPath().contains(".txt")) {
				try {
					File archivo=new File(EditorDeTextoGUI.archivoAbierto.getPath());
					FileWriter fw=null;
					PrintWriter pr=null;
					fw=new FileWriter(archivo);
					pr=new PrintWriter(fw);
					pr.println(texto.getText());
					pr.close();
					JOptionPane.showMessageDialog(null, "Se guardaron los cambios.", "Archivo", 1);
				}catch(IOException e) {
					JOptionPane.showMessageDialog(null, "ERROR", "Archivo", 0);
				}
			}
			if(EditorDeTextoGUI.archivoAbierto.getPath().contains(".obj")) {
				try {
					File fichero=null;
					ObjectOutputStream oos=null;
					fichero=new File(EditorDeTextoGUI.archivoAbierto.getPath());
					try
					{
						FileOutputStream fos=new FileOutputStream(fichero);
						oos=new ObjectOutputStream(fos); 
						Documento documento=new Documento(texto,doc,estilo);
						oos.writeObject(documento);
						JOptionPane.showMessageDialog(null,"Se guardaron los cambios.","Archivo",1);
					}
					catch(IOException error)
					{
						JOptionPane.showMessageDialog(null,"ERROR","Archivo",0);
					}
					finally{
						try {
							oos.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}catch(NullPointerException e) {
					JOptionPane.showMessageDialog(null, "No Se Selecciono Archivo.", "Archivo", 1);
				}
			}
		}
	}
	
	public void guardarComo(JTextPane texto, StyledDocument doc, Style estilo) {
		try {
			JFileChooser chooser=new JFileChooser();
			chooser.setDialogTitle("Selecciona el Archivo a Abrir");
			chooser.showOpenDialog(null);
			File fichero=null;
			ObjectOutputStream oos=null;
			fichero=new File(chooser.getSelectedFile().getPath()+".obj");

			try
			{
				FileOutputStream fos=new FileOutputStream(fichero);
				oos=new ObjectOutputStream(fos); 
				Documento documento=new Documento(texto,doc,estilo);
				oos.writeObject(documento);
				EditorDeTextoGUI.title="Editor de Texto Simple - "+chooser.getSelectedFile().getName()+".obj";
				EditorDeTextoGUI.abierto=true;
				JOptionPane.showMessageDialog(null,"Se guardo el documento con exito.","Archivo",1);
			}
			catch(IOException error)
			{
				JOptionPane.showMessageDialog(null,"Error " + error.getMessage(),"Registro Estudiante",0);
			}
			finally{
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}catch(NullPointerException e) {
			JOptionPane.showMessageDialog(null, "No Se Selecciono Ruta De Guardado.", "Archivo", 1);
		}
	}
	
	public void guardarComoText(JTextPane texto) {
		try {
			JFileChooser chooser=new JFileChooser();
			chooser.setDialogTitle("Selecciona la Ruta de Guardado");
			chooser.showOpenDialog(null);
			File fichero=null;
			fichero=new File(chooser.getSelectedFile().getPath()+".txt");
			FileWriter fw=null;
			PrintWriter fp=null;
			try {
				fw=new FileWriter(fichero);
				fp=new PrintWriter(fw);
				
				fp.print(texto.getText());
				
				fw.close();
				fp.close();
				EditorDeTextoGUI.title="Editor de Texto Simple - "+chooser.getSelectedFile().getName()+".txt";
				EditorDeTextoGUI.abierto=true;
				JOptionPane.showMessageDialog(null,"Se guardo el documento con exito.","Archivo",1);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}catch(NullPointerException e) {
			JOptionPane.showMessageDialog(null, "No Se Selecciono Ruta De Guardado.", "Archivo", 1);
		}
	}
}
