public class Mastermind{
	public static void main (String[]args){
		Deductions d = new Deductions (4, 6);
		
		// 1 : noir, 2 : blanc, 3 : rouge, 4 : bleu, 5 : jaune, 6 : vert
		//	solution : 1 4 5 2
		String[] couleurs = {"", "noir", "blanc", "rouge", "bleu", "jaune", "vert"};
		
		int[] l1 = {0, 2, 1, 4, 5};	int t1[] = {0,1,1,1,1};
		int[] l2 = {0, 3, 6, 3, 3};	int t2[] = {0,0,0,0,0};
		int[] l3 = {0, 6, 1, 3, 3};	int t3[] = {0,1,0,0,0};
		int[] l4 = {0, 5, 3, 4, 6};	int t4[] = {0,1,1,0,0};
		
		Raisonnement.tirerConclusion (l1, t1, d);
		Raisonnement.tirerConclusion (l2, t2, d);
		Raisonnement.tirerConclusion (l3, t3, d);
		Raisonnement.tirerConclusion (l4, t4, d);
		
		d.afficher (couleurs);
	}
}
