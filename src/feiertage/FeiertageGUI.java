package feiertage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.*;

import java.io.*;

//Does not get serialized
@SuppressWarnings("serial")
public class FeiertageGUI extends JFrame {

	private JMenu menu = new JMenu("");
	private JMenuBar mb = new JMenuBar();
	JMenuItem i1 = new JMenuItem();

	private String tat = "";
	private JLabel l1 = new JLabel();
	private JLabel l2 = new JLabel();
	private JLabel l3 = new JLabel();
	private JLabel l4 = new JLabel();

	private String[] cbs1 = { "DE", "AT", "CH", "IT-BZ" };
	private JComboBox<String> cb1 = new JComboBox<String>(cbs1);
	private String[] cbs2 = { "Alle", "DE-BW", "DE-BY", "DE-BE", "DE-BB", "DE-HB", "DE-HH", "DE-HE", "DE-MV", "DE-NI",
			"DE-NW", "DE-RP", "DE-SL", "DE-SN", "DE-ST", "DE-SH", "DE-TH" };
	private String[] bundeslaender = { "BW", "BY", "BE", "BB", "HB", "HH", "HE", "MV", "NI", "NW", "RP", "SL", "SN",
			"ST", "SH", "TH" };
	private JComboBox<String> cb2 = new JComboBox<String>(cbs2);
	private String[] cbs3 = { "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030" };
	private JComboBox<String> cb3 = new JComboBox<String>(cbs3);
	private String[] cbs4 = { "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030" };
	private JComboBox<String> cb4 = new JComboBox<String>(cbs4);

	private JButton b1 = new JButton();

	private JTextArea ta1 = new JTextArea(5, 20);
	private JScrollPane sp1 = new JScrollPane(ta1);

	private JButton b2 = new JButton();
	private JButton b3 = new JButton();
	private JButton b4 = new JButton();
	private JButton b5 = null;

	String exportpath = null;
	String pfad = System.getProperty("user.dir") + "\\" + "Daten" + "\\" + "Deutschland";
	String pfadmd = System.getProperty("user.dir");

	public FeiertageGUI() {
		//GUI
		this.setSize(720, 480);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		this.setTitle("FeiertageExporter");
		this.setResizable(false);
		this.setLocation(
				(int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2) - (int) (this.getWidth() / 2),
				(int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2) - (int) (this.getHeight() / 2));

		File[] schweiz = null;
		File[] suedtirol = null;
		File[] oesterreich = null;
		boolean dataerror = false;

		//programmstart
		do {
			//if an error during filereading occured
			if (dataerror) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int rw = chooser.showOpenDialog(null);
				File file = chooser.getSelectedFile();
				if (rw == 0) {
					String s = file.getAbsolutePath();
					int a = s.indexOf("Daten");
					s = s.substring(0, (a - 1));
					pfadmd = s;
					pfad = s + "\\" + "Daten" + "\\" + "Deutschland";
				}
			}
			//try reading files
			dataerror = false;
			schweiz = new File[] { new File(pfadmd + "\\Daten\\Schweiz\\2020.txt"),
					new File(pfadmd + "\\Daten\\Schweiz\\2021.txt"), new File(pfadmd + "\\Daten\\Schweiz\\2022.txt"),
					new File(pfadmd + "\\Daten\\Schweiz\\2023.txt"), new File(pfadmd + "\\Daten\\Schweiz\\2024.txt"),
					new File(pfadmd + "\\Daten\\Schweiz\\2025.txt"), new File(pfadmd + "\\Daten\\Schweiz\\2026.txt"),
					new File(pfadmd + "\\Daten\\Schweiz\\2027.txt"), new File(pfadmd + "\\Daten\\Schweiz\\2028.txt"),
					new File(pfadmd + "\\Daten\\Schweiz\\2029.txt"), new File(pfadmd + "\\Daten\\Schweiz\\2030.txt") };

			suedtirol = new File[] { new File(pfadmd + "\\Daten\\Suedtirol\\2020.txt"),
					new File(pfadmd + "\\Daten\\Suedtirol\\2021.txt"),
					new File(pfadmd + "\\Daten\\Suedtirol\\2022.txt"),
					new File(pfadmd + "\\Daten\\Suedtirol\\2023.txt"),
					new File(pfadmd + "\\Daten\\Suedtirol\\2024.txt"),
					new File(pfadmd + "\\Daten\\Suedtirol\\2025.txt"),
					new File(pfadmd + "\\Daten\\Suedtirol\\2026.txt"),
					new File(pfadmd + "\\Daten\\Suedtirol\\2027.txt"),
					new File(pfadmd + "\\Daten\\Suedtirol\\2028.txt"),
					new File(pfadmd + "\\Daten\\Suedtirol\\2029.txt"),
					new File(pfadmd + "\\Daten\\Suedtirol\\2030.txt"), };

			oesterreich = new File[] { new File(pfadmd + "\\Daten\\Oesterreich\\2020.txt"),
					new File(pfadmd + "\\Daten\\Oesterreich\\2021.txt"),
					new File(pfadmd + "\\Daten\\Oesterreich\\2022.txt"),
					new File(pfadmd + "\\Daten\\Oesterreich\\2023.txt"),
					new File(pfadmd + "\\Daten\\Oesterreich\\2024.txt"),
					new File(pfadmd + "\\Daten\\Oesterreich\\2025.txt"),
					new File(pfadmd + "\\Daten\\Oesterreich\\2026.txt") };
			//check if files are there
			try {

				for (int i = 0; i < schweiz.length; i++) {
					if (schweiz[i].exists()) {
					} else {
						throw new FileNotFoundException("Schweiz an stelle" + i + " konnte nicht gefunden werden");
					}
				}

				for (int i = 0; i < suedtirol.length; i++) {
					if (suedtirol[i].exists()) {
					} else {
						throw new FileNotFoundException("Suedtirol an stelle" + i + " konnte nicht gefunden werden");
					}
				}

				for (int i = 0; i < oesterreich.length; i++) {
					if (oesterreich[i].exists()) {
					} else {
						throw new FileNotFoundException("Oesterreich an stelle" + i + " konnte nicht gefunden werden");
					}
				}
			} catch (FileNotFoundException e33) {
				e33.printStackTrace();
				dataerror = true;
			}
			//reapeat if error occured
		} while (dataerror);

		ArrayList<Ort> possiblePlaces = new ArrayList<Ort>();
		Ort CH = new Ort("CH", FeiertageFactory.produceFromString(schweiz));
		possiblePlaces.add(CH);

		Ort BZ = new Ort("IT-BZ", FeiertageFactory.produceFromString(suedtirol));
		possiblePlaces.add(BZ);

		Ort AT = new Ort("AT", FeiertageFactory.produceFromString(oesterreich));
		possiblePlaces.add(AT);

		ArrayList<ArrayList<Feiertag>> bw = new ArrayList<ArrayList<Feiertag>>();
		ArrayList<ArrayList<Feiertag>> by = new ArrayList<ArrayList<Feiertag>>();
		ArrayList<ArrayList<Feiertag>> be = new ArrayList<ArrayList<Feiertag>>();
		ArrayList<ArrayList<Feiertag>> bb = new ArrayList<ArrayList<Feiertag>>();
		ArrayList<ArrayList<Feiertag>> hb = new ArrayList<ArrayList<Feiertag>>();
		ArrayList<ArrayList<Feiertag>> hh = new ArrayList<ArrayList<Feiertag>>();
		ArrayList<ArrayList<Feiertag>> he = new ArrayList<ArrayList<Feiertag>>();
		ArrayList<ArrayList<Feiertag>> mv = new ArrayList<ArrayList<Feiertag>>();
		ArrayList<ArrayList<Feiertag>> ni = new ArrayList<ArrayList<Feiertag>>();
		ArrayList<ArrayList<Feiertag>> nw = new ArrayList<ArrayList<Feiertag>>();
		ArrayList<ArrayList<Feiertag>> rp = new ArrayList<ArrayList<Feiertag>>();
		ArrayList<ArrayList<Feiertag>> sl = new ArrayList<ArrayList<Feiertag>>();
		ArrayList<ArrayList<Feiertag>> sn = new ArrayList<ArrayList<Feiertag>>();
		ArrayList<ArrayList<Feiertag>> st = new ArrayList<ArrayList<Feiertag>>();
		ArrayList<ArrayList<Feiertag>> sh = new ArrayList<ArrayList<Feiertag>>();
		ArrayList<ArrayList<Feiertag>> th = new ArrayList<ArrayList<Feiertag>>();

		try {
		bw = FeiertageFactory.fill("BW", pfad);
		by = FeiertageFactory.fill("BY", pfad);
		be = FeiertageFactory.fill("BE", pfad);
		bb = FeiertageFactory.fill("BB", pfad);
		hb = FeiertageFactory.fill("HB", pfad);
		hh = FeiertageFactory.fill("HH", pfad);
		he = FeiertageFactory.fill("HE", pfad);
		mv = FeiertageFactory.fill("MV", pfad);
		ni = FeiertageFactory.fill("NI", pfad);
		nw = FeiertageFactory.fill("NW", pfad);
		rp = FeiertageFactory.fill("RP", pfad);
		sl = FeiertageFactory.fill("SL", pfad);
		sn = FeiertageFactory.fill("SN", pfad);
		st = FeiertageFactory.fill("ST", pfad);
		sh = FeiertageFactory.fill("SH", pfad);
		th = FeiertageFactory.fill("TH", pfad);
		}catch(NoConnectionException e) {
			JOptionPane.showMessageDialog(FeiertageGUI.this,
					"You have to be connected to start this programm without complete data", "Error",
					JOptionPane.ERROR_MESSAGE);
			FeiertageGUI.this.dispatchEvent(new WindowEvent(FeiertageGUI.this, WindowEvent.WINDOW_CLOSING));
		}
		Ort BW = new Ort("DE-BW", bw);
		possiblePlaces.add(BW);

		Ort BY = new Ort("DE-BY", by);
		possiblePlaces.add(BY);

		Ort BE = new Ort("DE-BE", be);
		possiblePlaces.add(BE);

		Ort BB = new Ort("DE-BB", bb);
		possiblePlaces.add(BB);

		Ort HB = new Ort("DE-HB", hb);
		possiblePlaces.add(HB);

		Ort HH = new Ort("DE-HH", hh);
		possiblePlaces.add(HH);

		Ort HE = new Ort("DE-HE", he);
		possiblePlaces.add(HE);

		Ort MV = new Ort("DE-MV", mv);
		possiblePlaces.add(MV);

		Ort NI = new Ort("DE-NI", ni);
		possiblePlaces.add(NI);

		Ort NW = new Ort("DE-NW", nw);
		possiblePlaces.add(NW);

		Ort RP = new Ort("DE-RP", rp);
		possiblePlaces.add(RP);

		Ort SL = new Ort("DE-SL", sl);
		possiblePlaces.add(SL);

		Ort SN = new Ort("DE-SN", sn);
		possiblePlaces.add(SN);

		Ort ST = new Ort("DE-ST", st);
		possiblePlaces.add(ST);

		Ort SH = new Ort("DE-SH", sh);
		possiblePlaces.add(SH);

		Ort TH = new Ort("DE-TH", th);
		possiblePlaces.add(TH);

		ArrayList<Ort> effectivePlaces = new ArrayList<Ort>();

		// GUI-Components
		// Select
		Font f = new Font("Comic Sans MS", Font.PLAIN, 15);

		//refresh Json Data from API in MenuBar
		i1.setText("refresh from API");
		i1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
				for (int i = 0; i < bundeslaender.length; i++) {
					FeiertageFactory.refresh(bundeslaender[i], pfad);
				}
				}catch(NoConnectionException e2) {
					JOptionPane.showMessageDialog(FeiertageGUI.this,
							"You have to be connected to the internet to perform this action", "Error",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		menu.add(i1);
		menu.setText("Aktionen");
		mb.add(menu);
		this.setJMenuBar(mb);

		l1.setBounds(10, 10, 120, 30);
		l1.setText("Land:");
		l1.setFont(f);
		getContentPane().add(l1);

		l2.setBounds(10, 50, 120, 30);
		l2.setText("Bundesland:");
		l2.setFont(f);
		l2.setVisible(true);
		getContentPane().add(l2);

		//Select place
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

		//Select bundesland if deutschland is choosen
		cb2.setBounds(130, 50, 120, 30);
		cb2.setFont(f);
		cb2.setVisible(true);
		cb2.setEnabled(true);
		getContentPane().add(cb2);

		//Adding button
		b1.setBounds(260, 10, 110, 70);
		b1.setText("ADD");
		b1.setFont(f);
		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Only one Bundesland
				if (cb1.getSelectedItem().equals("DE") && !cb2.getSelectedItem().equals("Alle")) {
					//check if Bundesland is added
					int inanfang = -1;
					for (int i = 0; i < possiblePlaces.size(); i++) {
						if (possiblePlaces.get(i).getBeschreibung() == cb2.getSelectedItem()) {
							inanfang = i;
						}
					}
					//add Bundesland
					if (inanfang >= 0) {
						Ort temp = possiblePlaces.get(inanfang);
						effectivePlaces.add(temp);
						possiblePlaces.remove(temp);
						tat = tat + "Ort hinzugefuegt:" + temp.getBeschreibung() + "\n";
						ta1.setText(tat);
						ta1.append("Mommentan beinhaltet die Liste:\n");
						for (int i = 0; i < effectivePlaces.size(); i++) {
							ta1.append(effectivePlaces.get(i).getBeschreibung() + " ");
							if (effectivePlaces.size() > 10 && i == (int) (effectivePlaces.size() / 2)) {
								ta1.append("\n");
							}
						}
						//else dont add Bundesland
					} else {
						ta1.setText(tat);
						ta1.append("Dieser Ort befindet sich bereits in der Liste!\n");
						ta1.append("Mommentan beinhaltet die Liste:\n");
						for (int i = 0; i < effectivePlaces.size(); i++) {
							ta1.append(effectivePlaces.get(i).getBeschreibung() + " ");
							if (effectivePlaces.size() > 10 && i == (int) (effectivePlaces.size() / 2)) {
								ta1.append("\n");
							}
						}
					}
					// else add all Bundeslaender
				} else if (cb1.getSelectedItem().equals("DE") && cb2.getSelectedItem().equals("Alle")) {
					//add all Bundeslaender
					for (int i = 0; i < possiblePlaces.size(); i++) {
						if (possiblePlaces.get(i).getBeschreibung().substring(0, 2).equals("DE")) {
							effectivePlaces.add(possiblePlaces.get(i));
							possiblePlaces.remove(i);
							i = 0;
						}
					}
					ta1.setText(tat);
					ta1.append("Alle deutschen Bundeslaender wurden hinzugefügt!\n");
					ta1.append("Mommentan beinhaltet die Liste:\n");
					for (int i = 0; i < effectivePlaces.size(); i++) {
						ta1.append(effectivePlaces.get(i).getBeschreibung() + " ");
						if (effectivePlaces.size() > 10 && i == (int) (effectivePlaces.size() / 2)) {
							ta1.append("\n");
						}
					}

					// else Oesterreich/Schweiz/Suedtirol
				} else {
					//check if Place is added
					int inanfang = -1;
					for (int i = 0; i < possiblePlaces.size(); i++) {
						if (possiblePlaces.get(i).getBeschreibung() == cb1.getSelectedItem()) {
							inanfang = i;
						}
					}
					//add Place
					if (inanfang >= 0) {
						Ort temp = possiblePlaces.get(inanfang);
						effectivePlaces.add(temp);
						possiblePlaces.remove(temp);
						tat = tat + "Ort hinzugefuegt:" + temp.getBeschreibung() + "\n";
						ta1.setText(tat);
						ta1.append("Mommentan beinhaltet die Liste:\n");
						for (int i = 0; i < effectivePlaces.size(); i++) {
							ta1.append(effectivePlaces.get(i).getBeschreibung() + " ");
							if (effectivePlaces.size() > 10 && i == (int) (effectivePlaces.size() / 2)) {
								ta1.append("\n");
							}
						}
						//else dont add Place
					} else {
						ta1.setText(tat);
						ta1.append("Dieser Ort befindet sich bereits in der Liste!");
						ta1.append("Mommentan beinhaltet die Liste:\n");
						for (int i = 0; i < effectivePlaces.size(); i++) {
							ta1.append(effectivePlaces.get(i).getBeschreibung() + " ");
							if (effectivePlaces.size() > 10 && i == (int) (effectivePlaces.size() / 2)) {
								ta1.append("\n");
							}
						}
					}
				}
			}
		});
		getContentPane().add(b1);

		//removes all Places from list
		b2.setBounds(380, 10, 110, 70);
		b2.setText("RESET");
		b2.setFont(f);
		b2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < effectivePlaces.size(); i++) {
					possiblePlaces.add(effectivePlaces.get(i));
				}
				effectivePlaces.removeAll(effectivePlaces);
				tat = "Removed all\n";
				ta1.setText(tat);
			}
		});
		getContentPane().add(b2);

		//adds every not added Place to the list
		b3.setBounds(500, 10, 200, 70);
		b3.setText("ADD EVERYTHING");
		b3.setFont(f);
		b3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < possiblePlaces.size(); i++) {
					effectivePlaces.add(possiblePlaces.get(i));
				}
				possiblePlaces.removeAll(possiblePlaces);
				tat = tat + "Added all\n";
				ta1.setText(tat);
				ta1.append("Mommentan beinhaltet die Liste:\n");
				for (int i = 0; i < effectivePlaces.size(); i++) {
					ta1.append(effectivePlaces.get(i).getBeschreibung() + " ");
					if (effectivePlaces.size() > 10 && i == (int) (effectivePlaces.size() / 2)) {
						ta1.append("\n");
					}
				}

			}
		});
		getContentPane().add(b3);

		// TextArea
		ta1.setEditable(false);
		sp1.setBounds(10, 100, 690, 200);
		getContentPane().add(sp1);

		l3.setBounds(10, 340, 120, 30);
		l3.setText("Beginn:");
		l3.setFont(f);
		getContentPane().add(l3);

		l4.setBounds(10, 380, 120, 30);
		l4.setText("Ende:");
		l4.setFont(f);
		l4.setVisible(true);
		getContentPane().add(l4);

		//Select beginning year
		cb3.setBounds(140, 340, 120, 30);
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

		//Selcet ending year
		cb4.setBounds(140, 380, 120, 30);
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

		
		/*Export into choosen filepath(exportpath)
		 * Step1: Collect all Feiertage into ArrayList<ArrayList<Feiertag>> finale
		 * Step2: Bring it into a good format and sort it
		 * Step3: Export
		 */
		b4.setBounds(580, 340, 120, 70);
		b4.setText("EXPORT");
		b4.setFont(f);
		b4.setEnabled(false);
		b4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {

					// Step1
					int cb3i = Integer.parseInt((String) cb3.getSelectedItem());
					int cb4i = Integer.parseInt((String) cb4.getSelectedItem());
					ArrayList<ArrayList<Feiertag>> finale = new ArrayList<ArrayList<Feiertag>>();
					// Year i
					for (int i = cb3i - 2020; i <= (cb4i - cb3i + (cb3i - 2020)); i++) {
						ArrayList<Feiertag> year = new ArrayList<Feiertag>();
						// Place j in year i
						for (int j = 0; j < effectivePlaces.size(); j++) {
							// Feiertag k in Place j in year i
							for (int k = 0; k < effectivePlaces.get(j).getJahr20().get(i).size(); k++) {
								Feiertag temp = new Feiertag(effectivePlaces.get(j).getJahr20().get(i).get(k).getDatum(),
										effectivePlaces.get(j).getJahr20().get(i).get(k).getName());
								// Check if ArrayList is empty => else it does not start writing
								if (year.size() == 0 && effectivePlaces.size() > 0) {
									temp.addOrt(effectivePlaces.get(j).getBeschreibung());
									year.add(temp);
								} else {
									//Check if Feiertag allready exists
									for (int l = 0; l < year.size(); l++) {
										//If does not exist => add
										if (year.get(l).getDatum().equals(temp.getDatum())) {
											year.get(l).addOrt(effectivePlaces.get(j).getBeschreibung());
											break;
										}
										if (l == (year.size() - 1)) {
											//If it does exist => add Place to description
											temp.addOrt(effectivePlaces.get(j).getBeschreibung());
											year.add(temp);
											break;
										}
									}
								}
							}
						}
						finale.add(year);
					}

					//Step2
					// sort
					for (int i = 0; i < finale.size(); i++) {
						Collections.sort(finale.get(i));
					}

					// bring it into a workable format
					ArrayList<String[]> finalfinal = new ArrayList<String[]>();

					for (int i = 0; i < finale.size(); i++) {
						for (int j = 0; j < finale.get(i).size(); j++) {
							String[] write = { finale.get(i).get(j).getDatum(),
									finale.get(i).get(j).getBeschreibungAsString() };
							finalfinal.add(write);
						}
					}

					//Step3
					try (PrintWriter writer = new PrintWriter(new File(exportpath + "\\export.csv"))) {

						//build String
						StringBuilder sb = new StringBuilder();
						for (int i = 0; i < finalfinal.size(); i++) {
							sb.append(finalfinal.get(i)[0]);
							sb.append(';');
							sb.append(finalfinal.get(i)[1]);
							sb.append('\n');
						}

						//write
						writer.write(sb.toString());
						ta1.append("exported!\n");

					} catch (FileNotFoundException e3) {
						e3.printStackTrace();
					}

				} catch (IndexOutOfBoundsException e4) {
					JOptionPane.showMessageDialog(FeiertageGUI.this,
							"The data for Österreich only is available until 2026", "Warning",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		getContentPane().add(b4);

		// Choose Export Filepath
		Icon icon = new ImageIcon(pfadmd + "\\Daten\\zIcons\\folder.png");
		Image img = ((ImageIcon) icon).getImage();
		Image newimg = img.getScaledInstance(65, 65, java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(newimg);
		b5 = new JButton(icon);
		b5.setBounds(490, 340, 70, 70);
		b5.setFont(f);
		b5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				int rw = chooser.showOpenDialog(null);
				File file = chooser.getSelectedFile();
				if (rw == 0) {
					String s = file.getAbsolutePath();
					setPfad(file.getAbsolutePath().replaceAll("\\\\", "\\\\\\\\"));
					b4.setEnabled(true);
					tat = tat + "Der Pfad wurde auf \"" + s + "\" gesetzt\n";
					ta1.setText(tat);
					ta1.append("Mommentan beinhaltet die Liste:\n");
					for (int i = 0; i < effectivePlaces.size(); i++) {
						ta1.append(effectivePlaces.get(i).getBeschreibung() + " ");
						if (effectivePlaces.size() > 10 && i == (int) (effectivePlaces.size() / 2)) {
							ta1.append("\n");
						}
					}
				} else {
					b4.setEnabled(false);
				}
			}
		});
		getContentPane().add(b5);

	}

	public void setPfad(String s) {
		exportpath = s;
	}

}
