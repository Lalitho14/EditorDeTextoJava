import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.undo.UndoManager;

public class Operaciones extends Formato{
	
	static boolean n=false;
	static boolean c=false;
	static boolean u=false;
	static boolean f=false;
	static boolean cf=false;
	static boolean t=false;
	static boolean tf=false;
	
	public Operaciones() {
	}
	
	public Operaciones(JTextPane texto) {
		super(texto);
	}
	
	public void negritas(Style estilo, StyledDocument doc, JTextPane texto) {
		if(!n) {
			n=true;
			try {
				if(texto.getSelectedText().startsWith(" ")) {
					if(estilo.containsAttributes(negrita)) {
						estilo.removeAttributes(negrita);
						estilo.addAttributes(noNegrita);
						texto.getStyledDocument().setCharacterAttributes(texto.getSelectionStart(), texto.getSelectionEnd()-texto.getSelectionStart(), texto.getStyle("MiEstilo"), true);
					}
					else {
						estilo.addAttributes(negrita);
						doc.setCharacterAttributes(texto.getSelectionStart(), texto.getSelectionEnd()-texto.getSelectionStart(), texto.getStyle("MiEstilo"), true);
					}
				}
				else {
					if(estilo.containsAttributes(negrita)) {
						estilo.removeAttributes(negrita);
						estilo.addAttributes(noNegrita);
						doc.setCharacterAttributes(texto.getSelectionStart(), texto.getSelectionEnd()-texto.getSelectionStart(), texto.getStyle("MiEstilo"), true);
					}
					else {
						estilo.addAttributes(negrita);
						doc.setCharacterAttributes(texto.getSelectionStart(), texto.getSelectionEnd()-texto.getSelectionStart(), texto.getStyle("MiEstilo"), true);
					}
				}
			}catch(NullPointerException e) {
				estilo.addAttributes(negrita);
				texto.setCharacterAttributes(estilo, true);
			}
			
		}else {
			n=false;
			estilo.addAttributes(noNegrita);
			texto.setCharacterAttributes(estilo, true);
		}	
	}
	
	public void cursiva(Style estilo, StyledDocument doc, JTextPane texto) {
		if(!c) {
			c=true;
			
			try {
				if(texto.getSelectedText().startsWith(" ")) {
					if(estilo.containsAttributes(cursiva)) {
						estilo.removeAttributes(cursiva);
						estilo.addAttributes(noCursiva);
						texto.getStyledDocument().setCharacterAttributes(texto.getSelectionStart(), texto.getSelectionEnd()-texto.getSelectionStart(), texto.getStyle("MiEstilo"), true);
					}
					else {
						estilo.addAttributes(cursiva);
						doc.setCharacterAttributes(texto.getSelectionStart(), texto.getSelectionEnd()-texto.getSelectionStart(), texto.getStyle("MiEstilo"), true);
					}
				}
				else {
					if(estilo.containsAttributes(cursiva)) {
						estilo.removeAttributes(cursiva);
						estilo.addAttributes(noCursiva);
						doc.setCharacterAttributes(texto.getSelectionStart(), texto.getSelectionEnd()-texto.getSelectionStart(), texto.getStyle("MiEstilo"), true);
					}
					else {
						estilo.addAttributes(cursiva);
						doc.setCharacterAttributes(texto.getSelectionStart(), texto.getSelectionEnd()-texto.getSelectionStart(), texto.getStyle("MiEstilo"), true);
					}
				}
			}catch(NullPointerException e) {
				estilo.addAttributes(cursiva);
				texto.setCharacterAttributes(estilo, true);
			}
			
		}else {
			c=false;
			estilo.addAttributes(noCursiva);
			texto.setCharacterAttributes(estilo, true);
		}	
	}
	
	public void underline(Style estilo, StyledDocument doc, JTextPane texto) {
		if(!u) {
			u=true;
			
			try {
				if(texto.getSelectedText().startsWith(" ")) {
					if(estilo.containsAttributes(underline)) {
						estilo.removeAttributes(underline);
						estilo.addAttributes(noUnderline);
						texto.getStyledDocument().setCharacterAttributes(texto.getSelectionStart(), texto.getSelectionEnd()-texto.getSelectionStart(), texto.getStyle("MiEstilo"), true);
					}
					else {
						estilo.addAttributes(underline);
						doc.setCharacterAttributes(texto.getSelectionStart(), texto.getSelectionEnd()-texto.getSelectionStart(), texto.getStyle("MiEstilo"), true);
					}
				}
				else {
					if(estilo.containsAttributes(underline)) {
						estilo.removeAttributes(underline);
						estilo.addAttributes(noUnderline);
						doc.setCharacterAttributes(texto.getSelectionStart(), texto.getSelectionEnd()-texto.getSelectionStart(), texto.getStyle("MiEstilo"), true);
					}
					else {
						estilo.addAttributes(underline);
						doc.setCharacterAttributes(texto.getSelectionStart(), texto.getSelectionEnd()-texto.getSelectionStart(), texto.getStyle("MiEstilo"), true);
					}
				}
			}catch(NullPointerException e) {
				estilo.addAttributes(underline);
				texto.setCharacterAttributes(estilo, true);
			}
			
		}else {
			u=false;
			estilo.addAttributes(noUnderline);
			texto.setCharacterAttributes(estilo, true);
		}	
	}

	public void fondoFont(Style estilo, StyledDocument doc, JTextPane texto) {
		if(!f) {
			f=true;
			try {
				if(texto.getSelectedText().equals(" ")) {
					try {
						StyleConstants.setBackground(fondo, JColorChooser.showDialog(texto, "Seleccione Color", Color.red));
					}catch(NullPointerException e) {
						JOptionPane.showMessageDialog(null, "No se selecciono ningun color.","Seleecione Color",1);
					}
					estilo.addAttributes(fondo);
					texto.setCharacterAttributes(estilo, true);
				}else {
					try {
						StyleConstants.setBackground(fondo, JColorChooser.showDialog(texto, "Seleccione Color", Color.red));
					}catch(NullPointerException e) {
						JOptionPane.showMessageDialog(null, "No se selecciono ningun color.","Seleecione Color",1);
					}
					estilo.addAttributes(fondo);
					doc.setCharacterAttributes(texto.getSelectionStart(), texto.getSelectionEnd()-texto.getSelectionStart(), texto.getStyle("MiEstilo"), true);
				}
			}catch(NullPointerException e) {
				try {
					StyleConstants.setBackground(fondo, JColorChooser.showDialog(texto, "Seleccione Color", Color.red));
				}catch(NullPointerException e2) {
					JOptionPane.showMessageDialog(null, "No se selecciono ningun color.","Seleecione Color",1);
				}
				estilo.addAttributes(fondo);
				texto.setCharacterAttributes(estilo, true);
			}
		}else {
			try {
				if(texto.getSelectedText().equals(" ")) {
					estilo.removeAttributes(fondo);
					try {
						StyleConstants.setBackground(fondo, JColorChooser.showDialog(texto, "Seleccione Color", Color.red));
					}catch(NullPointerException e) {
						JOptionPane.showMessageDialog(null, "No se selecciono ningun color.","Seleecione Color",1);
					}
					estilo.addAttributes(fondo);
					texto.setCharacterAttributes(estilo, true);
				}else {
					estilo.removeAttributes(fondo);
					try {
						StyleConstants.setBackground(fondo, JColorChooser.showDialog(texto, "Seleccione Color", Color.red));
					}catch(NullPointerException e) {
						JOptionPane.showMessageDialog(null, "No se selecciono ningun color.","Seleecione Color",1);
					}
					estilo.addAttributes(fondo);
					doc.setCharacterAttributes(texto.getSelectionStart(), texto.getSelectionEnd()-texto.getSelectionStart(), texto.getStyle("MiEstilo"), true);
				}
			}catch(NullPointerException e) {
				estilo.removeAttributes(fondo);
				try {
					StyleConstants.setBackground(fondo, JColorChooser.showDialog(texto, "Seleccione Color", Color.red));
				}catch(NullPointerException e2) {
					JOptionPane.showMessageDialog(null, "No se selecciono ningun color.","Seleecione Color",1);
				}
				estilo.addAttributes(fondo);
				texto.setCharacterAttributes(estilo, true);
			}
		}
	}
	
	public void colorFont(Style estilo, StyledDocument doc, JTextPane texto ) {
		if(!cf) {
			cf=true;
			try {
				if(texto.getSelectedText().equals(" ")) {
					try {
						StyleConstants.setForeground(colorF, JColorChooser.showDialog(texto, "Selecciones Color", Color.red));
					}catch(NullPointerException e) {
						JOptionPane.showMessageDialog(null, "No se selecciono ningun color.","Seleecione Color",1);
					}
					estilo.addAttributes(colorF);
					texto.setCharacterAttributes(estilo, true);
				}else {
					try {
						StyleConstants.setForeground(colorF, JColorChooser.showDialog(texto, "Selecciones Color", Color.red));
					}catch(NullPointerException e) {
						JOptionPane.showMessageDialog(null, "No se selecciono ningun color.","Seleecione Color",1);
					}
					estilo.addAttributes(colorF);
					doc.setCharacterAttributes(texto.getSelectionStart(), texto.getSelectionEnd()-texto.getSelectionStart(), texto.getStyle("MiEstilo"), true);
				}
			}catch(NullPointerException e) {
				try {
					StyleConstants.setForeground(colorF, JColorChooser.showDialog(texto, "Selecciones Color", Color.red));
				}catch(NullPointerException e2) {
					JOptionPane.showMessageDialog(null, "No se selecciono ningun color.","Seleecione Color",1);
				}
				estilo.addAttributes(colorF);
				texto.setCharacterAttributes(estilo, true);
			}
		}else {
			try {
				if(texto.getSelectedText().equals(" ")) {
					estilo.removeAttributes(colorF);
					try {
						StyleConstants.setForeground(colorF, JColorChooser.showDialog(texto, "Selecciones Color", Color.red));
					}catch(NullPointerException e) {
						JOptionPane.showMessageDialog(null, "No se selecciono ningun color.","Seleecione Color",1);
					}
					estilo.addAttributes(colorF);
					texto.setCharacterAttributes(estilo, true);
				}else {
					estilo.removeAttributes(colorF);
					try {
						StyleConstants.setForeground(colorF, JColorChooser.showDialog(texto, "Selecciones Color", Color.red));
					}catch(NullPointerException e) {
						JOptionPane.showMessageDialog(null, "No se selecciono ningun color.","Seleecione Color",1);
					}
					estilo.addAttributes(colorF);
					doc.setCharacterAttributes(texto.getSelectionStart(), texto.getSelectionEnd()-texto.getSelectionStart(), texto.getStyle("MiEstilo"), true);
				}
			}catch(NullPointerException e) {
				estilo.removeAttributes(colorF);
				try {
					StyleConstants.setForeground(colorF, JColorChooser.showDialog(texto, "Selecciones Color", Color.red));
				}catch(NullPointerException e2) {
					JOptionPane.showMessageDialog(null, "No se selecciono ningun color.","Seleecione Color",1);
				}
				estilo.addAttributes(colorF);
				texto.setCharacterAttributes(estilo, true);
			}
		}
	}

	public void tamanio(Style estilo, StyledDocument doc, JTextPane texto, String s) {
		if(!t) {
			t=true;
			try {
				if(texto.getSelectedText().equals(" ")) {
					StyleConstants.setFontSize(size, Integer.parseInt(s));
					estilo.addAttributes(size);
					texto.setCharacterAttributes(estilo, true);
				}else {
					StyleConstants.setFontSize(size, Integer.parseInt(s));
					estilo.addAttributes(size);
					doc.setCharacterAttributes(texto.getSelectionStart(), texto.getSelectionEnd()-texto.getSelectionStart(), texto.getStyle("MiEstilo"), true);
				}
			}catch(NullPointerException e) {
				StyleConstants.setFontSize(size, Integer.parseInt(s));
				estilo.addAttributes(size);
				texto.setCharacterAttributes(estilo, true);
			}
		}else {
			try {
				if(texto.getSelectedText().equals(" ")) {
					estilo.removeAttributes(size);
					StyleConstants.setFontSize(size, Integer.parseInt(s));
					estilo.addAttributes(size);
					texto.setCharacterAttributes(estilo, true);
				}else {
					estilo.removeAttributes(size);
					StyleConstants.setFontSize(size, Integer.parseInt(s));
					estilo.addAttributes(size);
					doc.setCharacterAttributes(texto.getSelectionStart(), texto.getSelectionEnd()-texto.getSelectionStart(), texto.getStyle("MiEstilo"), true);
				}
			}catch(NullPointerException e) {
				estilo.removeAttributes(size);
				StyleConstants.setFontSize(size, Integer.parseInt(s));
				estilo.addAttributes(size);
				texto.setCharacterAttributes(estilo, true);
			}
		}
	}

	public void tipoDeFuente(Style estilo, StyledDocument doc, JTextPane texto, String s) {
		if(!tf) {
			tf=true;
			try {
				if(texto.getSelectedText().equals(" ")) {
					StyleConstants.setFontFamily(tipoFuente, s);
					estilo.addAttributes(tipoFuente);
					texto.setCharacterAttributes(estilo, true);
				}else {
					StyleConstants.setFontFamily(tipoFuente, s);
					estilo.addAttributes(tipoFuente);
					doc.setCharacterAttributes(texto.getSelectionStart(), texto.getSelectionEnd()-texto.getSelectionStart(), texto.getStyle("MiEstilo"), true);
				}
			}catch(NullPointerException e) {
				StyleConstants.setFontFamily(tipoFuente, s);
				estilo.addAttributes(tipoFuente);
				texto.setCharacterAttributes(estilo, true);
			}
		}else {
			try {
				if(texto.getSelectedText().equals(" ")) {
					estilo.removeAttributes(tipoFuente);
					StyleConstants.setFontFamily(tipoFuente, s);
					estilo.addAttributes(tipoFuente);
					texto.setCharacterAttributes(estilo, true);
				}else {
					estilo.removeAttributes(tipoFuente);
					StyleConstants.setFontFamily(tipoFuente, s);
					estilo.addAttributes(tipoFuente);
					doc.setCharacterAttributes(texto.getSelectionStart(), texto.getSelectionEnd()-texto.getSelectionStart(), texto.getStyle("MiEstilo"), true);
				}
			}catch(NullPointerException e) {
				estilo.removeAttributes(tipoFuente);
				StyleConstants.setFontFamily(tipoFuente, s);
				estilo.addAttributes(tipoFuente);
				texto.setCharacterAttributes(estilo, true);
			}
		}
	}
	
	public void edit(UndoManager um, ActionEvent e) {
		if(e.getActionCommand().equals("deshacer")) {
			try {
				um.undo();
			}catch(Exception e2) {
				
			}
		}
		if(e.getActionCommand().equals("rehacer")) {
			try {
				um.redo();
			}catch(Exception e2) {
				
			}
		}
	}

}
