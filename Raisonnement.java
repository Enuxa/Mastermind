public class Raisonnement {
	//	l	:	la ligne à analyser
	//	t	:	les NF témoins (0 : rien, 1 : blanc, 2 : noir)
	//	d	:	objet de déductions
	public static void tirerConclusion (int[] l, int[] t, Deductions d){
		int nf = d.getNF ();
		int nc = d.getNC ();
		
		int b = 0;
		int n = 0;
		for (int i = 1; i <= nf; i++) {
			if (t[i] == 1)
				b++;
			if (t[i] == 2)
				n++;
		} 
		
		//	NF témoins : les couleurs non utilisées appartiennent au TI
		if (n + b == nf) {
			for (int i = 1; i <= nc; i++) {
				boolean flag = false;
				for (int j = 1; j <= nf; j++) {
					if (l[j] == i)
						flag = true;
				}
				if (!flag)
					d.setImpossible (i);
			}
		}
		
		//	Aucun noir : les placements sont mauvais ; placer les couleurs dans le TI à ces places-ci
		if (n == 0) {
			for (int i = 1; i <= nf; i++)
				d.setImpossible (i, l[i]);
		}
		
		//	Aucun témoin : placer les couleurs dans le TI
		if (n == 0 && b == 0) {
			for (int i = 1; i <= nf; i++)
				d.setImpossible (l[i]);
		}
	}
	
	public static void tirerConclusions (int[] lA, int[] lB, int[] tA, int[] tB, Deductions d){
		int nc = d.getNC();
		for (int i = 1; i < nc; i++){
			if (!estDansLigne (i, lA))
				continue;
			int n = compter (i, lB);
			int nT = compterTemoins (tB) - compterTemoins (tA);
			
		}
	}
	
	public boolean estDansLigne (int c, int[] l){
		for (int i = 1; i <= l.length - 1; i++){
			if (l[i] == c)
				return true;
		}
		
		return false;
	}
	
	public int compter (int c, int[] l){
		int n = 0;
	
		for (int i = 1; i <= l.length - 1; i++){
			if (l[i] == c)
				n++;
		}
		
		return n;
	}
	
	public int compterTemoins (int[] t){
		int n = 0;
		
		for (int i = 1; i < t.length; i++){
			if (t[i]!=0)
				n++;
		}
		
		return n;
	}
}
