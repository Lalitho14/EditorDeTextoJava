
public class Palabras {
	int[] palabras;
	int n;
	
	public Palabras() {
	}
	
	public void lineas(String texto) {
		if(!texto.isEmpty()) {
			String texto1=texto.replace("\n", " ");
			String[] cad=texto1.split(" ");
			for(int j=0;j<cad.length;j++) {
				if(!cad[j].isBlank())
					n++;
			}
			palabras=new int[n];
	
			int i=0;
			for(int j=0;j<cad.length;j++) {
				if(!cad[j].isBlank() && !cad[j].contains("\n")) {
					palabras[i]=cad[j].length();
					i++;
				}
			}
		}else {
			palabras=null;
		}
	}
	
	public String npalabras() {
		if(palabras!=null)
			return ("Palabras : "+n+"    Caracteres : "+suma(palabras,n-1)+" ");
		else
			return ("Palabras : 0    Caracteres : 0 ");
	}
	
	public int suma(int[]A, int n) {
		if(n==0) {
			return A[n];
		} 
		else {
			return A[n] + suma(A, n-1);
		}
	}
}
