import java.io.Serializable;

import javax.swing.JTextPane;
import javax.swing.text.Style;
import javax.swing.text.StyledDocument;

public class Documento implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7804134627651766671L;
	private JTextPane panel;
	private StyledDocument doc;
	private Style estilo;
	
	public Documento() {
	}
	
	public Documento(JTextPane panel, StyledDocument doc, Style estilo) {
		this.panel=panel;
		this.doc=doc;
		this.estilo=estilo;
	}

	public JTextPane getPanel() {
		return panel;
	}

	public void setPanel(JTextPane panel) {
		this.panel = panel;
	}

	public StyledDocument getDoc() {
		return doc;
	}

	public void setDoc(StyledDocument doc) {
		this.doc = doc;
	}

	public Style getEstilo() {
		return estilo;
	}

	public void setEstilo(Style estilo) {
		this.estilo = estilo;
	}
}
