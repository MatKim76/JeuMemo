public class Jeu
{
	private int niveau;
	private int[][] tableau;
	
	private int taille;
	private int choix;
	
	public Jeu()
	{
		this.niveau = 1;
		this.taille = 5; //mettre taille progressive
		this.choix = 1;
	}
	
	public void creerTab()
	{
		int[][] tab = new int [taille][taille];
		
		for(int i =0; i < taille; i++)
			for(int j =0; j < taille; j++)
				tab[i][j] = 0;
		
		//this.niveau = 1;
		int i = 1;
		
		while(i < niveau + 1)
		{
			int x = (int)(Math.random() * taille);
			int y = (int)(Math.random() * taille);
			
			if( tab[x][y] == 0 )
			{
				tab[x][y] = i;
				i++;
			}
		}
		
		this.tableau = tab;
	}
	
	public void reset()
	{
		this.niveau = 1;
		this.choix = 1;
		creerTab();
	}
	
	public void niveauSuivant()
	{
		this.niveau++;
		this.choix = 1;
	}
	
	public String toString()
	{
		String s = "";
		for(int i =0; i < taille; i++)
		{
			for(int j =0; j < taille; j++)
				s += tableau[i][j] + "";
			
			s += "\n";
		}
		return s;
	}
	
	public int[][] getTableau()
	{
		return this.tableau;
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
		
		if( this.tableau[lig][col] == this.choix )
		{
			this.tableau[lig][col] = 0;
			this.choix++;
			return true;
		}
		return false;
	}
	
	public boolean vide()
	{
		int test = 0;
		for(int i =0; i < taille; i++)
			for(int j =0; j < taille; j++)
				test += this.tableau[i][j];
		
		if(test == 0) return true;
		return false;
	}
	
	public static void main(String[] args)
	{
		Jeu j = new Jeu();
		j.creerTab();
		
		System.out.println(j.toString());
		
		j.niveauSuivant();
		System.out.println(j.toString());
	}
}
