import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Graphical extends JFrame {

	private static final long serialVersionUID = 1L;

	public void startInterface() throws IOException {

		JButton bouton = new JButton("JOUER contre un autre joueur !");
		bouton.setPreferredSize(new Dimension(500, 250));

		JButton boutontwo = new JButton("JOUER contre l'IA !");
		boutontwo.setPreferredSize(new Dimension(500, 250));

		JPanel panneautwo = new JPanel();
		panneautwo.setPreferredSize(new Dimension(500, 500));
		panneautwo.setBackground(Color.WHITE);
		panneautwo.add(bouton);
		panneautwo.add(boutontwo);
		panneautwo.setVisible(true);
		panneautwo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));

		JPanel panneau = new JPanel();
		panneau.setPreferredSize(new Dimension(800, 800));
		panneau.setBackground(Color.WHITE);
		panneau.add(panneautwo);
		panneau.setVisible(true);
		panneau.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));

		JFrame cadre = new javax.swing.JFrame("Mr.Jackpocket");
		cadre.setContentPane(panneau);
		cadre.setLocation(200, 200);
		cadre.pack();
		cadre.setVisible(true);
		cadre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void gameInterface() throws IOException {

		JPanel boardGame = new JPanel();
		boardGame.setPreferredSize(new Dimension(500, 500));
		boardGame.setBackground(Color.WHITE);
		boardGame.setVisible(true);
		boardGame.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));

		JPanel panneautwo = new JPanel();
		panneautwo.setPreferredSize(new Dimension(500, 500));
		panneautwo.setBackground(Color.WHITE);
		panneautwo.setVisible(true);
		panneautwo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));

		JPanel panneauthree = new JPanel();
		panneauthree.setPreferredSize(new Dimension(500, 500));
		panneauthree.setBackground(Color.WHITE);
		panneauthree.setVisible(true);
		panneauthree.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));

		JPanel panneaufour = new JPanel();
		panneaufour.setPreferredSize(new Dimension(500, 500));
		panneaufour.setBackground(Color.WHITE);
		panneaufour.setVisible(true);
		panneaufour.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));

		JPanel panneaufive = new JPanel();
		panneaufive.setPreferredSize(new Dimension(500, 500));
		panneaufive.setBackground(Color.WHITE);
		panneaufive.setVisible(true);
		panneaufive.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));

		JFrame cadre = new javax.swing.JFrame("Mr.Jackpocket");

		cadre.setLocation(200, 200);
		cadre.pack();
		cadre.setVisible(true);
		cadre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
};
