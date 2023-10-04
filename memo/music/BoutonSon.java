import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.sound.sampled.*;

public class BoutonSon {

    public static void main(String[] args) {
        JFrame fenetre = new JFrame("Lecture du son");
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setSize(300, 200);

        JButton bouton = new JButton("Jouer le son");
        fenetre.add(bouton);

        bouton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jouerSon("chemin/vers/le/son.wav");
            }
        });

        fenetre.setVisible(true);
    }

    public static void jouerSon(String cheminDuSon) {
        try {
            File fichierSon = new File(cheminDuSon);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(fichierSon);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

