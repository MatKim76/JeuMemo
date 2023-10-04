public class JeuColor
{
	private int niveau;
	private int[][] tableau;
	
	private int taille;
	
	private int[] ordre;
	private int ind;
	
	public JeuColor()
	{
		this.niveau = 1;
		this.taille = 2; //mettre taille progressive
		
		this.ind = 0;
		creerOrdre();
		
		this.tableau = new int[taille][taille];
		
		int num = 0;
		for(int i =0; i < taille; i++)
			for(int j =0; j < taille; j++)
				tableau[i][j] = num++;
	}
	
	public void creerOrdre()
	{
		this.ordre = new int[this.niveau];
		for(int i = 0; i < this.ordre.length; i++)
			this.ordre[i] = (int)(Math.random() * (taille*taille) );
		
	}
	
	public void reset()
	{
		this.niveau = 1;
		this.ind = 0;
		creerOrdre();
	}
	
	public void niveauSuivant()
	{
		this.niveau++;
		this.ind = 0;
		creerOrdre();
	}
	
	public String toString()
	{
		String s = "";
		
		for(int i = 0; i < this.ordre.length; i++)
			s+= this.ordre[i] + "";
		
		return s;
	}
	
	public int[][] getTableau()
	{
		return this.tableau;
	}
	
	public int[] getOrdre()
	{
		return this.ordre;
	}
	
	public int getSize()
	{
		return this.taille;
	}
	
	public int getNiveau()
	{
		return this.niveau;
	}
	
	public int get(int x, int y)
	{
		return this.tableau[x][y];
	}
	
	public boolean verif(int nb)
	{
		int lig = nb / taille;
		int col = nb % taille;
		
		//System.out.println(this.tableau[lig][col]);
		//System.out.println(choix);
		
		if( this.tableau[lig][col] == this.ordre[this.ind++] )
			return true;
		
		return false;
	}
	
	public boolean vide()
	{
		if(this.ordre.length == this.ind )
			return true;
		
		return false;
	}
	
	public static void main(String[] args)
	{
		JeuColor j = new JeuColor();
		
		System.out.println(j.toString());
		
		j.niveauSuivant();
		System.out.println(j.toString());
	}
}
