class Deductions {
	// On représente une couleur par un nombre >= 1, 0 représente aucune information
	// Placement : une certaine couleur à une certaine position
	private int [] tfp; // Tableau final de placement
	private boolean [][] ti; // Tableau des impossibles, true si c'est impossible
	private int nf;	//	Nombres de fiches
	private int nc;	//	Nombre de couleurs
	
	public Deductions (int f, int c) {
		this.nc = c;
		this.nf = f;
		this.tfp = new int [f + 1];
		this.ti = new boolean [f + 1][c + 1];
		for (int i = 1; i <= f; i++) {
			this.tfp[i] = 0;
			for (int j = 1; j <= c; j++)
				this.ti[i][j] = false;
		}
	}
	
	public int getNF () {
		return nf;
	}
	
	public int getNC () {
		return nc;
	}
	
	public boolean estFini (){
		for (int i = 1; i <= this.nf; i++)
			if (this.tfp[i] == 0)
				return false;
		
		return true;
	}
	
	public void setPlacement (int f, int c) {
		this.tfp[f] = c;
		
		for (int i = 1; i <= this.nc; i++){
			if (i != c)
				this.ti [f][i] = true;
		}
	}
	
	public int getPlacement (int f) {
		return this.tfp[f];
	}
	
	public void setImpossible (int f, int c) {
		this.ti[f][c] = true;
		
		//	S'il ne reste plus qu'une couleur possible (si le tableau ti[f] possède nc - 1 couleurs éliminées) alors la couleur restante est la bonne
		if (getNbPossibilites (f) == 1){
			for (int i = 1; i <= this.nc; i++)
				if (!this.ti[f][i])
					setPlacement (f, c);
		}
	}
	
	public void setImpossible (int c){
		for (int i = 1; i <= this.nf; i++)
			setImpossible (i, c);
	}
	
	public boolean getImpossible (int f, int c){
		return this.ti[f][c];
	}

	public void afficher (){
		String[] couleurs = new String[this.nc + 1];
		this.afficher (couleurs);
	}

	public void afficher (String[] couleurs){
		System.out.print ("Solution partielle : ");
		for (int i = 1; i <= this.nf; i++){
			if (this.tfp[i] != 0)
				System.out.print (couleurs [this.tfp [i]]);
			else
				System.out.print ("?");
			
			System.out.print(i == this.nf ? "\n" : "-");
		}
		
		System.out.println ("Couleurs impossibles pour chaque fiche : ");
		for (int i = 1; i <= this.nf; i++){
			System.out.print ("\tFiche " + i + "\t:\t");
			boolean b = false;
			for (int j = 1; j <= this.nc; j++){
				if (this.ti [i][j]){
					System.out.print ((b ? ", " : "") + couleurs[j]);
					b = true;
				}
			}
			System.out.println();
			if (!b)
				System.out.println ("aucune couleur impossible");
		}
		
		int p = getNbPossibilites ();
		System.out.println (p + " possibilité" + (p > 1 ? "s" : ""));
	}
	
	public int getNbPossibilites (int f){
		int c = 0;
		for (int i = 1; i <= this.nc; i++)
			 if (!this.ti[f][i])
			 	c++;
		return c;
	}
	
	public int getNbPossibilites (){
		int n = 1;
		
		for (int i = 1; i <= this.nf; i++)
			n *= getNbPossibilites (i);
		
		return n;
	}
	
	public int getEval (){
		int n = 1;
		
		for (int i = 1; i <= this.nf; i++)
			n += this.nc - getNbPossibilites (i);
		
		return n;
	}
}
