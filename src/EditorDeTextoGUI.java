import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.Style;
import javax.swing.text.StyledDocument;
import javax.swing.undo.UndoManager;
import javax.swing.JComboBox;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import java.awt.BorderLayout;
import javax.swing.JToolBar;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

public class EditorDeTextoGUI {

	public JFrame frame;
	UndoManager um=new UndoManager();
	public Archivo archivo=new Archivo();
	JTextPane texto = new JTextPane();
	public Operaciones op=new Operaciones(texto);
	static public File archivoAbierto=null;
	static Style estilo;
	static StyledDocument doc;
	static String title="Editor de Texto Simple";
	static boolean abierto=false;

	public EditorDeTextoGUI() {
		initialize();
		paneles();
		texto.requestFocus();
	}

	public void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(EditorDeTextoGUI.class.getResource("/imagenes/accessories_text_editor_16842.png")));
		frame.setBounds(100, 100, 876, 680);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle(title);
		
		doc=texto.getStyledDocument();
		texto.getDocument().addUndoableEditListener(new UndoableEditListener() {
			public void undoableEditHappened(UndoableEditEvent e) {
				um.addEdit(e.getEdit());
			}
		});
		estilo=texto.addStyle("MiEstilo", null);
	}
	
	public void paneles() {
	    
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Archivo");
		menuBar.add(mnNewMenu);
		
		JMenuItem abrir = new JMenuItem("Abrir");
		abrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				archivo.abrir(texto);
				frame.setTitle(title);
				texto.requestFocus();
			}
		});
		mnNewMenu.add(abrir);
		
		JMenuItem guardar = new JMenuItem("Guardar");
		guardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				archivo.guardar(texto,doc,estilo);
				texto.requestFocus();
			}
		});
		mnNewMenu.add(guardar);
		
		JMenu guardarComo = new JMenu("Guardar como...");
		mnNewMenu.add(guardarComo);
		
		JMenuItem archivoTexto = new JMenuItem("Archivo de Texto (*.txt)");
		archivoTexto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				archivo.guardarComoText(texto);
				frame.setTitle(title);
				texto.requestFocus();
			}
		});
		guardarComo.add(archivoTexto);
		
		JMenuItem archivoObj = new JMenuItem("Archivo ETS (*.obj)");
		archivoObj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				archivo.guardarComo(texto, doc, estilo);
				frame.setTitle(title);
				texto.requestFocus();
			}
		});
		guardarComo.add(archivoObj);
		
		JMenuItem acerca = new JMenuItem("Acerca de...");
		acerca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Jesus Eduardo Hernandez Bravo-202039944\n"
						+ "Francisco Neftali Fernandez Castillo-202035369\n"
						+ "Yahir Yael Romero Becerril-202055210","Editor De Texto Simple - EQUIPO 6",1);
			}
		});
		mnNewMenu.add(acerca);
		
		JMenu edicionMenu = new JMenu("Edicion");
		menuBar.add(edicionMenu);
		
		JMenuItem deshacer = new JMenuItem("Deshacer");
		deshacer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				op.edit(um,e);
				texto.requestFocus();
			}
		});
		deshacer.setActionCommand("deshacer");
		edicionMenu.add(deshacer);
		
		JMenuItem rehacer = new JMenuItem("Rehacer");
		rehacer.setActionCommand("rehacer");
		rehacer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				op.edit(um,e);
				texto.requestFocus();
			}
		});
		edicionMenu.add(rehacer);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		
		texto.setFont(new Font("Arial", Font.PLAIN, 14));
		texto.setFocusTraversalPolicyProvider(true);
		scrollPane.setViewportView(texto);
		
		JToolBar menu = new JToolBar();
		frame.getContentPane().add(menu, BorderLayout.NORTH);
		
		JComboBox<String> fuentes = new JComboBox<String>();
		fuentes.setMaximumRowCount(20);
		fuentes.setFont(new Font("Arial", Font.PLAIN, 12));
		fuentes.setFocusTraversalKeysEnabled(false);
		fuentes.setFocusable(false);
		menu.add(fuentes);
		DefaultComboBoxModel<String> modelo=(DefaultComboBoxModel<String>)fuentes.getModel();
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    String fontNames[] = ge.getAvailableFontFamilyNames();
	    for (int i = 0; i < fontNames.length; i++) {
	    	modelo.addElement(fontNames[i]);
	    }
	    fuentes.setModel(modelo);
		fuentes.setActionCommand("fuentes");
		fuentes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("fuentes"))
					op.tipoDeFuente(estilo, doc, texto, fuentes.getSelectedItem().toString());
				texto.requestFocus();
			}
		});
		
		JComboBox<String> tamanio = new JComboBox<String>();
		tamanio.setMaximumRowCount(12);
		tamanio.setActionCommand("tamanio");
		tamanio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("tamanio"))
					op.tamanio(estilo, doc, texto, tamanio.getSelectedItem().toString());
				texto.requestFocus();
			}
		});
		
		JSeparator separator_2 = new JSeparator();
		menu.add(separator_2);
		tamanio.setFocusTraversalKeysEnabled(false);
		tamanio.setFocusable(false);
		tamanio.setFont(new Font("Arial", Font.PLAIN, 12));
		menu.add(tamanio);
		tamanio.setModel(new DefaultComboBoxModel<>(new String[] {"8","12","16","20","24","28","32","36","40","44","48","52","56","60"}));
		
		JSeparator separator_1 = new JSeparator();
		menu.add(separator_1);
		
		JButton negritas = new JButton("");
		negritas.setRolloverEnabled(false);
		negritas.setRequestFocusEnabled(false);
		negritas.setPressedIcon(new ImageIcon(EditorDeTextoGUI.class.getResource("/imagenes/n1.png")));
		negritas.setFocusTraversalKeysEnabled(false);
		negritas.setFocusable(false);
		negritas.setFocusPainted(false);
		negritas.setOpaque(false);
		negritas.setIcon(new ImageIcon(EditorDeTextoGUI.class.getResource("/imagenes/n1.png")));
		negritas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				op.negritas(estilo, doc, texto);
				texto.requestFocus();
			}
		});
		menu.add(negritas);
		
		JButton cursivo = new JButton("");
		cursivo.setRequestFocusEnabled(false);
		cursivo.setRolloverEnabled(false);
		cursivo.setFocusPainted(false);
		cursivo.setFocusTraversalKeysEnabled(false);
		cursivo.setFocusable(false);
		cursivo.setIcon(new ImageIcon(EditorDeTextoGUI.class.getResource("/imagenes/c1.png")));
		cursivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				op.cursiva(estilo, doc, texto);
				texto.requestFocus();
			}
		});
		menu.add(cursivo);
		
		JButton underline = new JButton("");
		underline.setRolloverEnabled(false);
		underline.setRequestFocusEnabled(false);
		underline.setFocusPainted(false);
		underline.setFocusTraversalKeysEnabled(false);
		underline.setFocusable(false);
		underline.setOpaque(false);
		underline.setIcon(new ImageIcon(EditorDeTextoGUI.class.getResource("/imagenes/s1.png")));
		underline.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				op.underline(estilo, doc, texto);
				texto.requestFocus();
			}
		});
		menu.add(underline);
		
		JSeparator separator = new JSeparator();
		menu.add(separator);
		
		JButton fondo = new JButton("");
		fondo.setRolloverEnabled(false);
		fondo.setRequestFocusEnabled(false);
		fondo.setOpaque(false);
		fondo.setIcon(new ImageIcon(EditorDeTextoGUI.class.getResource("/imagenes/fondo4.png")));
		fondo.setFocusPainted(false);
		fondo.setFocusTraversalKeysEnabled(false);
		fondo.setFocusable(false);
		fondo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				op.fondoFont(estilo, doc, texto);
				texto.requestFocus();
			}
		});
		menu.add(fondo);
		
		JButton colorF = new JButton("");
		colorF.setOpaque(false);
		colorF.setIcon(new ImageIcon(EditorDeTextoGUI.class.getResource("/imagenes/colorf1.png")));
		colorF.setFocusPainted(false);
		colorF.setFocusTraversalKeysEnabled(false);
		colorF.setFocusable(false);
		colorF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				op.colorFont(estilo, doc, texto);
				texto.requestFocus();
			}
		});
		menu.add(colorF);
		
		JLabel palabras = new JLabel("Palabras : 0     Caracteres : 0 ");
		
		palabras.setRequestFocusEnabled(false);
		palabras.setFocusTraversalKeysEnabled(false);
		palabras.setFocusable(false);
		palabras.setAlignmentX(Component.CENTER_ALIGNMENT);
		palabras.setFont(new Font("Arial", Font.PLAIN, 12));
		palabras.setHorizontalAlignment(SwingConstants.TRAILING);
		frame.getContentPane().add(palabras, BorderLayout.SOUTH);
		
		texto.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				Palabras p=new Palabras();
				p.lineas(texto.getText());
				palabras.setText(p.npalabras());
			}
		});
		
		texto.setText("");
		texto.setSelectedTextColor(Color.BLACK);
		texto.setSelectionColor(Color.LIGHT_GRAY);
		texto.setFont(new Font("Arial", Font.PLAIN, 12));
		texto.requestFocus();
	}
}
