import javax.swing.JTextPane;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;

public class Formato {
	protected Style negrita, noNegrita;
	protected Style cursiva, noCursiva;
	protected Style underline, noUnderline;
	protected Style fondo, colorF;
	protected Style size, tipoFuente;
	
	public Formato() {
	}
	
	public Formato(JTextPane texto) {
		negrita=texto.addStyle("Negrita", null);
		StyleConstants.setBold(negrita, true);
		noNegrita=texto.addStyle("NoNegrita", null);
		StyleConstants.setBold(noNegrita, false);
		cursiva=texto.addStyle("Cursiva", null);
		StyleConstants.setItalic(cursiva,true);
		noCursiva=texto.addStyle("NoCursiva", null);
		StyleConstants.setItalic(noCursiva,false);
		underline=texto.addStyle("Underline", null);
		StyleConstants.setUnderline(underline, true);
		noUnderline=texto.addStyle("NoUnderline", null);
		StyleConstants.setUnderline(noUnderline, false);
		fondo=texto.addStyle("Fondo", null);
		colorF=texto.addStyle("ColorF", null);
		size=texto.addStyle("Size", null);
		tipoFuente=texto.addStyle("TipoFuente", null);
	}
}