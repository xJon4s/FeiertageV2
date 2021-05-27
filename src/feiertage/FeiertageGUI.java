package feiertage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import javax.swing.*;

import org.json.JSONObject;

import java.io.*;

public class FeiertageGUI extends JFrame {
	
	private String tat = "";
	private JLabel l1 = new JLabel();
	private JLabel l2 = new JLabel();
	private JLabel l3 = new JLabel();
	private JLabel l4 = new JLabel();

	private String[] cbs1 = { "DE", "AT", "CH", "IT-BZ" };
	private JComboBox<String> cb1 = new JComboBox<String>(cbs1);
	private String[] cbs2 = { "DE-BW", "DE-BY", "DE-BE", "DE-BB", "DE-HB", "DE-HH", "DE-HE", "DE-MV", "DE-NI", "DE-NW", "DE-RP", "DE-SL", "DE-SN",
			"DE-ST", "DE-SH", "DE-TH" };
	private JComboBox<String> cb2 = new JComboBox<String>(cbs2);
	private String[] cbs3 = { "2020", "2021", "2022", "2023", "2024", "2025" };
	private JComboBox<String> cb3 = new JComboBox<String>(cbs3);
	private String[] cbs4 = { "2020", "2021", "2022", "2023", "2024", "2025" };
	private JComboBox<String> cb4 = new JComboBox<String>(cbs4);

	private JButton b1 = new JButton();
	
	private JTextArea ta1 = new JTextArea(5,20);
	private JScrollPane sp1 = new JScrollPane(ta1);
	
	
	private JButton b2 = new JButton();
	private JButton b3 = new JButton();
	private JButton b4 = new JButton();

	public FeiertageGUI() {
		this.setSize(720, 480);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		this.setTitle("FeiertageExporter");
		this.setResizable(false);
		this.setLocation(
				(int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2) - (int) (this.getWidth() / 2),
				(int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2) - (int) (this.getHeight() / 2));

		// Komponenten
		
		//NichtGUI
		
		try {
		      File myObj = new File("C:\\Users\\PP_divus2\\Documents\\Feiertage\\Daten\\Suedtirol.txt");
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        System.out.println(data);
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
		ArrayList<Ort> anfang = new ArrayList<Ort>();
		Ort BW = new Ort("DE-BW");
		anfang.add(BW);
		Ort BY = new Ort("DE-BY");
		anfang.add(BY);
		Ort BE = new Ort("DE-BE");
		anfang.add(BE);
		Ort BB = new Ort("DE-BB");
		anfang.add(BB);
		Ort HB = new Ort("DE-HB");
		anfang.add(HB);
		Ort HH = new Ort("DE-HH");
		anfang.add(HH);
		Ort HE = new Ort("DE-HE");
		anfang.add(HE);
		Ort MV = new Ort("DE-MV");
		anfang.add(MV);
		Ort NI = new Ort("DE-NI");
		anfang.add(NI);
		Ort NW = new Ort("DE-NW");
		anfang.add(NW);
		Ort RP = new Ort("DE-RP");
		anfang.add(RP);
		Ort SL = new Ort("DE-SL");
		anfang.add(SL);
		Ort SN = new Ort("DE-SN");
		anfang.add(SN);
		Ort ST = new Ort("DE-ST");
		anfang.add(ST);
		Ort SH = new Ort("DE-SH");
		anfang.add(SH);
		Ort TH = new Ort("DE-TH");
		anfang.add(TH);
		Ort CH = new Ort("CH");
		anfang.add(CH);
		Ort BZ = new Ort("IT-BZ");
		anfang.add(BZ);
		Ort AT = new Ort("AT");
		anfang.add(AT);
		ArrayList<Ort> ende = new ArrayList<Ort>();
		
		//GUI
		
		//Auswahl
		Font f = new Font("Comic Sans MS", Font.PLAIN, 15);

		l1.setBounds(10, 10, 120, 30);
		l1.setText("Land:");
		l1.setFont(f);
		getContentPane().add(l1);

		l2.setBounds(10, 50, 120, 30);
		l2.setText("Bundesland:");
		l2.setFont(f);
		l2.setVisible(true);
		getContentPane().add(l2);

		cb1.setBounds(130, 10, 120, 30);
		cb1.setFont(f);
		cb1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (cb1.getSelectedItem() == "DE") {
					l2.setVisible(true);
					cb2.setVisible(true);
					cb2.setEnabled(true);
				} else {
					l2.setVisible(false);
					cb2.setVisible(false);
					cb2.setEnabled(false);
				}
			}
		});
		getContentPane().add(cb1);

		cb2.setBounds(130, 50, 120, 30);
		cb2.setFont(f);
		cb2.setVisible(true);
		cb2.setEnabled(true);
		getContentPane().add(cb2);
		
		b1.setBounds(260, 10, 110, 70);
		b1.setText("ADD");
		b1.setFont(f);
		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Deutschland
				if (cb1.getSelectedItem() == "DE") {
					int inanfang = -1;
					for (int i = 0; i < anfang.size(); i++) {
						if(anfang.get(i).getBeschreibung() == cb2.getSelectedItem()) {
							inanfang = i;
						}
					}
					if(inanfang >= 0) {
						Ort temp = anfang.get(inanfang);
						ende.add(temp);
						anfang.remove(temp);
						tat = tat + "Ort hinzugefuegt:" + temp.getBeschreibung() + "\n";
						ta1.setText(tat);
						ta1.append("Mommentan beinhaltet die Liste:\n");
						for (int i = 0; i < ende.size(); i++) {
							ta1.append(ende.get(i).getBeschreibung() + " ");
							if(ende.size()>10 && i == (int)(ende.size()/2)) {
								ta1.append("\n");
							}
						}
					}else {
						ta1.setText(tat);
						ta1.append("Dieser Ort befindet sich bereits in der Liste!\n");
						ta1.append("Mommentan beinhaltet die Liste:\n");
						for (int i = 0; i < ende.size(); i++) {
							ta1.append(ende.get(i).getBeschreibung() + " ");
							if(ende.size()>10 && i == (int)(ende.size()/2)) {
								ta1.append("\n");
							}
						}
					}
					//else Oesterreich/Schweiz/Suedtirol
				}else {
					int inanfang = -1;
					for (int i = 0; i < anfang.size(); i++) {
						if(anfang.get(i).getBeschreibung() == cb1.getSelectedItem()) {
							inanfang = i;
						}
					}
					if(inanfang >= 0) {
						Ort temp = anfang.get(inanfang);
						ende.add(temp);
						anfang.remove(temp);
						tat = tat + "Ort hinzugefuegt:" + temp.getBeschreibung() + "\n";
						ta1.setText(tat);
						ta1.append("Mommentan beinhaltet die Liste:\n");
						for (int i = 0; i < ende.size(); i++) {
							ta1.append(ende.get(i).getBeschreibung() + " ");
							if(ende.size()>10 && i == (int)(ende.size()/2)) {
								ta1.append("\n");
							}
						}
					}else {
						ta1.setText(tat);
						ta1.append("Dieser ort befindet sich bereits in der Liste!");
						ta1.append("Mommentan beinhaltet die Liste:\n");
						for (int i = 0; i < ende.size(); i++) {
							ta1.append(ende.get(i).getBeschreibung() + " ");
							if(ende.size()>10 && i == (int)(ende.size()/2)) {
								ta1.append("\n");
							}
						}
					}
				}
			}
		});
		getContentPane().add(b1);
		
		b2.setBounds(380, 10, 110, 70);
		b2.setText("RESET");
		b2.setFont(f);
		b2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < ende.size(); i++) {
					anfang.add(ende.get(i));
				}
				ende.removeAll(ende);
				tat = "Removed all\n";
				ta1.setText(tat);
			}
		});
		getContentPane().add(b2);
		
		b3.setBounds(500, 10, 200, 70);
		b3.setText("ADD EVERYTHING");
		b3.setFont(f);
		b3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < anfang.size(); i++) {
					ende.add(anfang.get(i));
				}
				anfang.removeAll(anfang);
				tat = tat + "Added all\n";
				ta1.setText(tat);
				ta1.append("Mommentan beinhaltet die Liste:\n");
				for (int i = 0; i < ende.size(); i++) {
					ta1.append(ende.get(i).getBeschreibung() + " ");
					if(ende.size()>10 && i == (int)(ende.size()/2)) {
						ta1.append("\n");
					}
				}
				
			}
		});
		getContentPane().add(b3);
		
		
		//Ausgabe
		
		ta1.setEditable(false);
		sp1.setBounds(10, 100, 690, 200);
		getContentPane().add(sp1);
		
		
		//Operationen
		
		l3.setBounds(10, 365, 120, 30);
		l3.setText("Beginn:");
		l3.setFont(f);
		l3.setBackground(Color.BLACK);
		getContentPane().add(l3);

		l4.setBounds(10, 405, 120, 30);
		l4.setText("Ende:");
		l4.setFont(f);
		l4.setVisible(true);
		getContentPane().add(l4);

		cb3.setBounds(140, 365, 120, 30);
		cb3.setFont(f);
		cb3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Integer.parseInt((String) cb3.getSelectedItem()) > Integer.parseInt((String) cb4.getSelectedItem())) {
					cb3.setSelectedItem(cb4.getSelectedItem());
				}
			}
		});
		getContentPane().add(cb3);

		cb4.setBounds(140, 405, 120, 30);
		cb4.setFont(f);
		cb4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Integer.parseInt((String) cb3.getSelectedItem()) > Integer.parseInt((String) cb4.getSelectedItem())) {
					cb4.setSelectedItem(cb3.getSelectedItem());
				}
			}
		});
		getContentPane().add(cb4);
		
		b4.setBounds(580, 365, 120, 70);
		b4.setText("EXPORT");
		b4.setFont(f);
		getContentPane().add(b4);
	}

}
