import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import javax.swing.JFrame;

public class FrameJeu extends JFrame implements ActionListener
{
	private JLabel score;
	
	private JButton[] tabButton;
	private JPanel panel;
	
	private Jeu jeu;
	
	public FrameJeu()
	{
		this.jeu = new Jeu();
		this.jeu.creerTab();
		
		this.score = new JLabel("score : 0");
		
		this.setTitle   ("Une Fenetre");
		this.setSize    (285,275);
		this.setLocation( 20,200);
		this.setLayout( new BorderLayout());
		
		this.panel = new JPanel();
		this.panel.setLayout( new GridLayout(this.jeu.getSize(), this.jeu.getSize() ));
		
		this.tabButton = new JButton[this.jeu.getSize()*this.jeu.getSize()];
		
		int cptBtn = 0;
		//ImageIcon icon = new ImageIcon("./image/vide.gif");
		for(int i =0; i < this.jeu.getSize(); i++)
		{
			for(int j =0; j < this.jeu.getSize(); j++)
			{
				/*switch( this.jeu.get(i, j) )
				{
					case 0 -> icon = new ImageIcon("./image/fl_gauche.gif");
					case 1 -> icon = new ImageIcon("./image/jaune.gif");
					case 2 -> icon = new ImageIcon("./image/rouge.gif");
					case 3 -> icon = new ImageIcon("./image/vert.gif");
				}*/
				if(this.jeu.get(i,j) == 0)
					this.tabButton[cptBtn] = new JButton("");
				else
					this.tabButton[cptBtn] = new JButton(this.jeu.get(i,j) + "");
				
				this.tabButton[cptBtn].addActionListener(this);
				this.panel.add(this.tabButton[cptBtn++]);
			}
		}
		this.add(this.score, BorderLayout.NORTH);
		this.add(this.panel, BorderLayout.CENTER);
		this.revalidate();
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void maj()
	{
		this.setSize(55 * this.jeu.getSize() + 10, 55 * this.jeu.getSize());

		this.panel.setLayout(new GridLayout(this.jeu.getSize(), this.jeu.getSize()));

		int cptBtn = 0;

		for (int i = 0; i < this.jeu.getSize(); i++) {
			for (int j = 0; j < this.jeu.getSize(); j++) {
				String newText ="";
				
				if(this.jeu.get(i,j) != 0)
					newText += this.jeu.get(i, j) + "";
				
				this.tabButton[cptBtn].setText(newText);
				cptBtn++;
			}
		}
		this.score.setText("score : " + (this.jeu.getNiveau() - 1) );
		
		this.panel.revalidate();
		this.panel.repaint();
	}

	public void cacher()
	{
		int cptBtn = 0;
		
		for (int i = 0; i < this.jeu.getSize(); i++) {
			for (int j = 0; j < this.jeu.getSize(); j++) {
				this.tabButton[cptBtn].setText("");
				cptBtn++;
			}
		}

		// Revalidation et rafraîchissement du JPanel
		this.panel.revalidate();
		this.panel.repaint();
	}
	
	
	public void actionPerformed ( ActionEvent e)
	{
		for(int cpt=0; cpt < this.tabButton.length; cpt++)
		{
			if( e.getSource().equals(this.tabButton[cpt]) )
			{
				if( !this.jeu.verif(cpt) )
				{
					this.jeu.reset();
					maj();
				}
				else
				{
					if( this.jeu.vide() )
					{
						this.jeu.niveauSuivant();
						this.jeu.creerTab();
						maj();
					}
					else
					{
						cacher();
					}
				}
			}
		}
	}
	
	public static void main(String[] args)
	{
		FrameJeu f = new FrameJeu();
	}
}
