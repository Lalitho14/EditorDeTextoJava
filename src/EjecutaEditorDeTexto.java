import java.awt.EventQueue;
import javax.swing.UIManager;

public class EjecutaEditorDeTexto {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					EditorDeTextoGUI window = new EditorDeTextoGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}