import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

public class FrameJeuColor extends JFrame implements ActionListener
{
	private JLabel score;
	
	private JButton[] tabButton;
	private JPanel panel;
	
	private JeuColor jeu;
	
	public FrameJeuColor()
	{
		this.jeu = new JeuColor();
		
		this.score = new JLabel("score : 0");
		
		this.setTitle   ("Une Fenetre");
		this.setSize    (310,300);
		this.setLocation( 20,200);
		this.setLayout( new BorderLayout());
		
		this.panel = new JPanel();
		this.panel.setLayout( new GridLayout(this.jeu.getSize(), this.jeu.getSize() ));
		
		this.tabButton = new JButton[this.jeu.getSize()*this.jeu.getSize()];
		
		int cptBtn = 0;
		//ImageIcon icon = new ImageIcon("./image/vide.gif");
		Color couleur = Color.WHITE;
		for(int i =0; i < this.jeu.getSize(); i++)
		{
			for(int j =0; j < this.jeu.getSize(); j++)
			{
				/*switch( this.jeu.get(i, j) )
				{
					case 0 -> icon = new ImageIcon("./image/bleu.gif");
					case 1 -> icon = new ImageIcon("./image/jaune.gif");
					case 2 -> icon = new ImageIcon("./image/rouge.gif");
					case 3 -> icon = new ImageIcon("./image/vert.gif");
				}*/
				//this.tabButton[cptBtn] = new JButton(icon);
				/*if(this.jeu.get(i,j) == 0)
					this.tabButton[cptBtn] = new JButton("");
				else
					this.tabButton[cptBtn] = new JButton(this.jeu.get(i,j) + "");
				*/
				
				switch( this.jeu.get(i, j) )
				{
					case 0 -> couleur = Color.BLUE;
					case 1 -> couleur = Color.YELLOW;
					case 2 -> couleur = Color.RED;
					case 3 -> couleur = Color.GREEN;
				}
				this.tabButton[cptBtn] = new JButton();
				this.tabButton[cptBtn].setBackground( couleur );
				this.tabButton[cptBtn].addActionListener(this);
				this.panel.add(this.tabButton[cptBtn++]);
			}
		}
		this.add(this.score, BorderLayout.NORTH);
		this.add(this.panel, BorderLayout.CENTER);
		this.revalidate();
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		
		animation();
	}

	public void animation() {
		for (int cpt = 0; cpt < this.tabButton.length; cpt++)
		    this.tabButton[cpt].setEnabled(false);

		cacher();

		int[] ordre = this.jeu.getOrdre();
		final int[] cpt = {0};

		Timer timer = new Timer(500, null);
		Timer pauseTimer = new Timer(500, null); // Timer pour la pause de 0.5 seconde

		ActionListener timerAction = new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        if (cpt[0] < ordre.length) {
		            colorier(tabButton[ordre[cpt[0]]]);
		            pauseTimer.start(); // Démarrez le timer de pause de 0.5 seconde
		            timer.stop(); // Arrêtez le timer principal
		        } else {
		            timer.stop();
		            for (int i = 0; i < tabButton.length; i++) {
		                colorier(tabButton[i]);
		                tabButton[i].setEnabled(true);
		            }
		            panel.repaint();
		        }
		    }
		};

		ActionListener pauseTimerAction = new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        cacher();
		        cpt[0]++;
		        pauseTimer.stop(); // Arrêtez le timer de pause de 0.5 seconde
		        timer.start(); // Redémarrez le timer principal
		    }
		};

		timer.addActionListener(timerAction);
		timer.setRepeats(false);
		timer.start();

		pauseTimer.addActionListener(pauseTimerAction);
		pauseTimer.setRepeats(false);
	}




	
	public void colorier(JButton btn)
	{
		for(int cpt=0; cpt < this.tabButton.length; cpt++)
		{
			if(btn == this.tabButton[cpt] )
			{
				Color couleur = Color.WHITE;
				switch( cpt )
				{
					case 0 -> couleur = Color.BLUE;
					case 1 -> couleur = Color.YELLOW;
					case 2 -> couleur = Color.RED;
					case 3 -> couleur = Color.GREEN;
				}
				btn.setBackground( couleur );
			}
		}
		this.panel.revalidate();
		this.panel.repaint();
	}

	public void cacher()
	{
		for(int cpt=0; cpt < this.tabButton.length; cpt++)
			this.tabButton[cpt].setBackground( Color.WHITE );

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
					animation();
				}
				else
				{
					if( this.jeu.vide() )
					{
						this.jeu.niveauSuivant();
						animation();
					}
				}
			}
		}
		this.score.setText("score : " + (this.jeu.getNiveau() - 1) );
		
	}
	
	public static void main(String[] args)
	{
		FrameJeuColor f = new FrameJeuColor();
	}
}
