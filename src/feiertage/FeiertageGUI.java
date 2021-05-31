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

import org.json.JSONException;
import org.json.JSONObject;

public class FeiertageGUI extends JFrame {

	private String tat = "";
	private JLabel l1 = new JLabel();
	private JLabel l2 = new JLabel();
	private JLabel l3 = new JLabel();
	private JLabel l4 = new JLabel();

	private String[] cbs1 = { "DE", "AT", "CH", "IT-BZ" };
	private JComboBox<String> cb1 = new JComboBox<String>(cbs1);
	private String[] cbs2 = { "DE-BW", "DE-BY", "DE-BE", "DE-BB", "DE-HB", "DE-HH", "DE-HE", "DE-MV", "DE-NI", "DE-NW",
			"DE-RP", "DE-SL", "DE-SN", "DE-ST", "DE-SH", "DE-TH" };
	private JComboBox<String> cb2 = new JComboBox<String>(cbs2);
	private String[] cbs3 = { "2020", "2021", "2022", "2023", "2024", "2025" };
	private JComboBox<String> cb3 = new JComboBox<String>(cbs3);
	private String[] cbs4 = { "2020", "2021", "2022", "2023", "2024", "2025" };
	private JComboBox<String> cb4 = new JComboBox<String>(cbs4);

	private JButton b1 = new JButton();

	private JTextArea ta1 = new JTextArea(5, 20);
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

//		 Komponenten

//		 NichtGUI

//		try {
//			File myObj = new File("C:\\Users\\PP_divus2\\Documents\\Feiertage\\Daten\\Suedtirol\\Suedtirol.txt");
//			Scanner myReader = new Scanner(myObj);
//			while (myReader.hasNextLine()) {
//				String data = myReader.nextLine();
//				System.out.println(data);
//			}
//			myReader.close();
//		} catch (FileNotFoundException e) {
//			System.out.println("An error occurred.");
//			e.printStackTrace();
//		}

		File[] schweiz = { new File("C:\\Users\\PP_divus2\\Documents\\Feiertage\\Daten\\Schweiz\\2020.txt"),
				new File("C:\\Users\\PP_divus2\\Documents\\Feiertage\\Daten\\Schweiz\\2021.txt"),
				new File("C:\\Users\\PP_divus2\\Documents\\Feiertage\\Daten\\Schweiz\\2022.txt"),
				new File("C:\\Users\\PP_divus2\\Documents\\Feiertage\\Daten\\Schweiz\\2023.txt"),
				new File("C:\\Users\\PP_divus2\\Documents\\Feiertage\\Daten\\Schweiz\\2024.txt"),
				new File("C:\\Users\\PP_divus2\\Documents\\Feiertage\\Daten\\Schweiz\\2025.txt"),
				new File("C:\\Users\\PP_divus2\\Documents\\Feiertage\\Daten\\Schweiz\\2026.txt"),
				new File("C:\\Users\\PP_divus2\\Documents\\Feiertage\\Daten\\Schweiz\\2027.txt"),
				new File("C:\\Users\\PP_divus2\\Documents\\Feiertage\\Daten\\Schweiz\\2028.txt"),
				new File("C:\\Users\\PP_divus2\\Documents\\Feiertage\\Daten\\Schweiz\\2029.txt"),
				new File("C:\\Users\\PP_divus2\\Documents\\Feiertage\\Daten\\Schweiz\\2030.txt") };

		File[] suedtirol = { new File("C:\\Users\\PP_divus2\\Documents\\Feiertage\\Daten\\Suedtirol\\2020.txt"),
				new File("C:\\Users\\PP_divus2\\Documents\\Feiertage\\Daten\\Suedtirol\\2021.txt"),
				new File("C:\\Users\\PP_divus2\\Documents\\Feiertage\\Daten\\Suedtirol\\2022.txt"),
				new File("C:\\Users\\PP_divus2\\Documents\\Feiertage\\Daten\\Suedtirol\\2023.txt"),
				new File("C:\\Users\\PP_divus2\\Documents\\Feiertage\\Daten\\Suedtirol\\2024.txt"),
				new File("C:\\Users\\PP_divus2\\Documents\\Feiertage\\Daten\\Suedtirol\\2025.txt"),
				new File("C:\\Users\\PP_divus2\\Documents\\Feiertage\\Daten\\Suedtirol\\2026.txt"),
				new File("C:\\Users\\PP_divus2\\Documents\\Feiertage\\Daten\\Suedtirol\\2027.txt"),
				new File("C:\\Users\\PP_divus2\\Documents\\Feiertage\\Daten\\Suedtirol\\2028.txt"),
				new File("C:\\Users\\PP_divus2\\Documents\\Feiertage\\Daten\\Suedtirol\\2029.txt"),
				new File("C:\\Users\\PP_divus2\\Documents\\Feiertage\\Daten\\Suedtirol\\2030.txt"), };

		File[] oesterreich = { new File("C:\\Users\\PP_divus2\\Documents\\Feiertage\\Daten\\Oesterreich\\2020.txt"),
				new File("C:\\Users\\PP_divus2\\Documents\\Feiertage\\Daten\\Oesterreich\\2021.txt"),
				new File("C:\\Users\\PP_divus2\\Documents\\Feiertage\\Daten\\Oesterreich\\2022.txt"),
				new File("C:\\Users\\PP_divus2\\Documents\\Feiertage\\Daten\\Oesterreich\\2023.txt"),
				new File("C:\\Users\\PP_divus2\\Documents\\Feiertage\\Daten\\Oesterreich\\2024.txt"),
				new File("C:\\Users\\PP_divus2\\Documents\\Feiertage\\Daten\\Oesterreich\\2025.txt"),
				new File("C:\\Users\\PP_divus2\\Documents\\Feiertage\\Daten\\Oesterreich\\2026.txt") };

		ArrayList<Ort> anfang = new ArrayList<Ort>();
		Ort CH = new Ort("CH", aFabrik.produceFromString(schweiz));
		anfang.add(CH);

		Ort BZ = new Ort("IT-BZ", aFabrik.produceFromString(suedtirol));
		anfang.add(BZ);

		Ort AT = new Ort("AT", aFabrik.produceFromString(oesterreich));
		anfang.add(AT);

		ArrayList<ArrayList<feiertag>> bw = new ArrayList<ArrayList<feiertag>>();
		ArrayList<ArrayList<feiertag>> by = new ArrayList<ArrayList<feiertag>>();
		ArrayList<ArrayList<feiertag>> be = new ArrayList<ArrayList<feiertag>>();
		ArrayList<ArrayList<feiertag>> bb = new ArrayList<ArrayList<feiertag>>();
		ArrayList<ArrayList<feiertag>> hb = new ArrayList<ArrayList<feiertag>>();
		ArrayList<ArrayList<feiertag>> hh = new ArrayList<ArrayList<feiertag>>();
		ArrayList<ArrayList<feiertag>> he = new ArrayList<ArrayList<feiertag>>();
		ArrayList<ArrayList<feiertag>> mv = new ArrayList<ArrayList<feiertag>>();
		ArrayList<ArrayList<feiertag>> ni = new ArrayList<ArrayList<feiertag>>();
		ArrayList<ArrayList<feiertag>> nw = new ArrayList<ArrayList<feiertag>>();
		ArrayList<ArrayList<feiertag>> rp = new ArrayList<ArrayList<feiertag>>();
		ArrayList<ArrayList<feiertag>> sl = new ArrayList<ArrayList<feiertag>>();
		ArrayList<ArrayList<feiertag>> sn = new ArrayList<ArrayList<feiertag>>();
		ArrayList<ArrayList<feiertag>> st = new ArrayList<ArrayList<feiertag>>();
		ArrayList<ArrayList<feiertag>> sh = new ArrayList<ArrayList<feiertag>>();
		ArrayList<ArrayList<feiertag>> th = new ArrayList<ArrayList<feiertag>>();

		bw = aFabrik.fill("BW");
		by = aFabrik.fill("BY");
		be = aFabrik.fill("BE");
		bb = aFabrik.fill("BB");
		hb = aFabrik.fill("HB");
		hh = aFabrik.fill("HH");
		he = aFabrik.fill("HE");
		mv = aFabrik.fill("MV");
		ni = aFabrik.fill("NI");
		nw = aFabrik.fill("NW");
		rp = aFabrik.fill("RP");
		sl = aFabrik.fill("SL");
		sn = aFabrik.fill("SN");
		st = aFabrik.fill("ST");
		sh = aFabrik.fill("SH");
		th = aFabrik.fill("TH");

		Ort BW = new Ort("DE-BW", bw);
		anfang.add(BW);

		Ort BY = new Ort("DE-BY", by);
		anfang.add(BY);

		Ort BE = new Ort("DE-BE", be);
		anfang.add(BE);

		Ort BB = new Ort("DE-BB", bb);
		anfang.add(BB);

		Ort HB = new Ort("DE-HB", hb);
		anfang.add(HB);

		Ort HH = new Ort("DE-HH", hh);
		anfang.add(HH);

		Ort HE = new Ort("DE-HE", he);
		anfang.add(HE);

		Ort MV = new Ort("DE-MV", mv);
		anfang.add(MV);

		Ort NI = new Ort("DE-NI", ni);
		anfang.add(NI);

		Ort NW = new Ort("DE-NW", nw);
		anfang.add(NW);

		Ort RP = new Ort("DE-RP", rp);
		anfang.add(RP);

		Ort SL = new Ort("DE-SL", sl);
		anfang.add(SL);

		Ort SN = new Ort("DE-SN", sn);
		anfang.add(SN);

		Ort ST = new Ort("DE-ST", st);
		anfang.add(ST);

		Ort SH = new Ort("DE-SH", sh);
		anfang.add(SH);

		Ort TH = new Ort("DE-TH", th);
		anfang.add(TH);

		ArrayList<Ort> ende = new ArrayList<Ort>();

		// GUI

		// Auswahl
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
				// Deutschland
				if (cb1.getSelectedItem() == "DE") {
					int inanfang = -1;
					for (int i = 0; i < anfang.size(); i++) {
						if (anfang.get(i).getBeschreibung() == cb2.getSelectedItem()) {
							inanfang = i;
						}
					}
					if (inanfang >= 0) {
						Ort temp = anfang.get(inanfang);
						ende.add(temp);
						anfang.remove(temp);
						tat = tat + "Ort hinzugefuegt:" + temp.getBeschreibung() + "\n";
						ta1.setText(tat);
						ta1.append("Mommentan beinhaltet die Liste:\n");
						for (int i = 0; i < ende.size(); i++) {
							ta1.append(ende.get(i).getBeschreibung() + " ");
							if (ende.size() > 10 && i == (int) (ende.size() / 2)) {
								ta1.append("\n");
							}
						}
					} else {
						ta1.setText(tat);
						ta1.append("Dieser Ort befindet sich bereits in der Liste!\n");
						ta1.append("Mommentan beinhaltet die Liste:\n");
						for (int i = 0; i < ende.size(); i++) {
							ta1.append(ende.get(i).getBeschreibung() + " ");
							if (ende.size() > 10 && i == (int) (ende.size() / 2)) {
								ta1.append("\n");
							}
						}
					}
					// else Oesterreich/Schweiz/Suedtirol
				} else {
					int inanfang = -1;
					for (int i = 0; i < anfang.size(); i++) {
						if (anfang.get(i).getBeschreibung() == cb1.getSelectedItem()) {
							inanfang = i;
						}
					}
					if (inanfang >= 0) {
						Ort temp = anfang.get(inanfang);
						ende.add(temp);
						anfang.remove(temp);
						tat = tat + "Ort hinzugefuegt:" + temp.getBeschreibung() + "\n";
						ta1.setText(tat);
						ta1.append("Mommentan beinhaltet die Liste:\n");
						for (int i = 0; i < ende.size(); i++) {
							ta1.append(ende.get(i).getBeschreibung() + " ");
							if (ende.size() > 10 && i == (int) (ende.size() / 2)) {
								ta1.append("\n");
							}
						}
					} else {
						ta1.setText(tat);
						ta1.append("Dieser ort befindet sich bereits in der Liste!");
						ta1.append("Mommentan beinhaltet die Liste:\n");
						for (int i = 0; i < ende.size(); i++) {
							ta1.append(ende.get(i).getBeschreibung() + " ");
							if (ende.size() > 10 && i == (int) (ende.size() / 2)) {
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
					if (ende.size() > 10 && i == (int) (ende.size() / 2)) {
						ta1.append("\n");
					}
				}

			}
		});
		getContentPane().add(b3);

		// Ausgabe

		ta1.setEditable(false);
		sp1.setBounds(10, 100, 690, 200);
		getContentPane().add(sp1);

		// Operationen

		l3.setBounds(10, 365, 120, 30);
		l3.setText("Beginn:");
		l3.setFont(f);
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
				if (Integer.parseInt((String) cb3.getSelectedItem()) > Integer
						.parseInt((String) cb4.getSelectedItem())) {
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
				if (Integer.parseInt((String) cb3.getSelectedItem()) > Integer
						.parseInt((String) cb4.getSelectedItem())) {
					cb4.setSelectedItem(cb3.getSelectedItem());
				}
			}
		});
		getContentPane().add(cb4);

		// nicht fertig
		b4.setBounds(580, 365, 120, 70);
		b4.setText("EXPORT");
		b4.setFont(f);
		b4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Schritt1:Feiertage getten
				int cb3i = Integer.parseInt((String) cb3.getSelectedItem());
				int cb4i = Integer.parseInt((String) cb4.getSelectedItem());
				ArrayList<ArrayList<feiertag>> finale = new ArrayList<ArrayList<feiertag>>();
				// Jahr
				for (int i = cb3i - 2020; i <= (cb4i - cb3i + (cb3i - 2020)); i++) {
					ArrayList<feiertag> jahr = new ArrayList<feiertag>();
					// Orte in ende
					for (int j = 0; j < ende.size(); j++) {
						// Feiertage in einem Ort j im Jahr i
						for (int k = 0; k < ende.get(j).getJahr20().get(i).size(); k++) {
							feiertag temp = new feiertag(ende.get(j).getJahr20().get(i).get(k).getDatum(),
									ende.get(j).getJahr20().get(i).get(k).getName());
							// Kontrolle ob hinzuzufuegender Feiertag bereits existiert
							// Kontrolle ob Array leer => wichtig dass anfaengt zu schreiben
							if (jahr.size() == 0 && ende.size() > 0) {
								temp.addOrt(ende.get(j).getBeschreibung());
								jahr.add(temp);
							} else {
								for (int l = 0; l < jahr.size(); l++) {
									if (jahr.get(l).getDatum().equals(temp.getDatum())) {
										jahr.get(l).addOrt(ende.get(j).getBeschreibung());
										break;
									}
									if (l == (jahr.size() - 1)) {
										temp.addOrt(ende.get(j).getBeschreibung());
										jahr.add(temp);
										break;
									}

								}
							}
						}
					}
					finale.add(jahr);
				}

				// Schritt2: gutes Format & export????
				// File
//				try {
//					File export = new File("C:\\Users\\PP_divus2\\Documents\\Feiertage\\Daten\\export.txt");
//					if (export.createNewFile()) {
//						System.out.println("File created: " + export.getName());
//					} else {
//						System.out.println("File already exists.");
//					}
//				} catch (IOException e1) {
//					e1.printStackTrace();
//				}

				ArrayList<String[]> finalfinal = new ArrayList<String[]>();

				for (int i = 0; i < finale.size(); i++) {
					for (int j = 0; j < finale.get(i).size(); j++) {
						String[] write = { finale.get(i).get(j).getDatum(),
								finale.get(i).get(j).getBeschreibungAsString() };
						finalfinal.add(write);
					}
				}

				try (PrintWriter writer = new PrintWriter(
						new File("C:\\Users\\PP_divus2\\Documents\\Feiertage\\Daten\\export.csv"))) {

					StringBuilder sb = new StringBuilder();
					for (int i = 0; i < finalfinal.size(); i++) {
						sb.append(finalfinal.get(i)[0]);
						sb.append(';');
						sb.append(finalfinal.get(i)[1]);
						sb.append('\n');
					}

					writer.write(sb.toString());


				} catch (FileNotFoundException e3) {
					e3.printStackTrace();
				}

			}
		});
		getContentPane().add(b4);
	}

}
