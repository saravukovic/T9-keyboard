package tastatura;

import java.awt.Button;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;

/**/
@SuppressWarnings("serial")
public class Tastatura extends Panel implements Runnable {
	private Label mojaLabela;
	private Button[] dugmici = new Button[12];
	private boolean rezimRada;
	private Thread mojaNit;
	private String trenutnaSlova;
	private String trenutniBroj;
	private String trenutnoIme;
	private String natpis;
	private boolean pisanje;
	private boolean nitCeka = true;
	private boolean istaSlova = true;

	public Tastatura(Label l) {
		mojaLabela = l;
		setLayout(new GridLayout(4, 3));
		for (int i = 0; i < 12; i++) {
			dugmici[i] = new Button();
			dugmici[i].setBackground(Color.LIGHT_GRAY);
		}
		adjustPanel();
	}
	
	public void promeniLabelu(Label l) {
		mojaLabela = l;
	}

	private void adjustPanel() {
		postaviLabele();
		for (int i = 0; i < 12; i++) {
			Button d = dugmici[i];
			d.addActionListener(ae -> {
				if (!rezimRada) {
					mojaLabela.setText(mojaLabela.getText() + d.getLabel());
				} else {
					trenutnaSlova = d.getLabel();
					if (mojaNit == null) {
						pisanje = true;
						mojaNit = new Thread(this);
						mojaNit.start();
					} else {
						if(nitCeka) {
							budiNit();
						}
						else mojaNit.interrupt();
					}
				}
			});
			add(dugmici[i]);
		}
	}
	
	private synchronized void budiNit() {
		notifyAll();
	}

	public void promeniRezimRada() {
		rezimRada = !rezimRada;
		postaviLabele();
	}

	private void postaviLabele() {
		if (!rezimRada) {
			natpis = "";
			trenutnoIme = "";
			for (int i = 0; i < 9; i++) {
				dugmici[i].setLabel("" + (i + 1));
			}
			dugmici[9].setLabel("*");
			dugmici[10].setLabel("0");
			dugmici[11].setLabel("+");
			zavrsiNit();
		} else {
			trenutniBroj = mojaLabela.getText();
			natpis = "";
			dugmici[0].setLabel("");
			dugmici[1].setLabel("ABC");
			dugmici[2].setLabel("DEF");
			dugmici[3].setLabel("GHI");
			dugmici[4].setLabel("JKL");
			dugmici[5].setLabel("MNO");
			dugmici[6].setLabel("PQRS");
			dugmici[7].setLabel("TUV");
			dugmici[8].setLabel("WXYZ");
			dugmici[9].setLabel("");
			dugmici[10].setLabel("_");
			dugmici[11].setLabel("");
		}

	}

	private void zavrsiNit() {
		if(mojaNit!=null) {
			pisanje = false;
			nitCeka = true;
			istaSlova = true;
			mojaNit.interrupt();
		}
		while (mojaNit != null) {
			try {
				wait();
			} catch (Exception e) {
			}
		}
	}

	private void azurirajNatpis(char c) {
		if (trenutnoIme == null)
			trenutnoIme = new String();
		trenutnoIme = trenutnoIme + c;
		natpis = trenutnoIme;
	}

	@Override
	public void run() {
		try {
			while (!mojaNit.isInterrupted()) {
				synchronized (this) {
					while (!pisanje)
						wait();
				}
				int cnt = 0;
				synchronized (this) {
					while (pisanje) {
						if(!istaSlova) {
							nitCeka = true;
							wait();
						}
						nitCeka = false;
						String slova = trenutnaSlova;
						try {
							mojaLabela.setText(natpis + slova.charAt(cnt % slova.length()));
							Thread.sleep(1000);
							azurirajNatpis(slova.charAt(cnt % slova.length()));
							istaSlova = false;
							cnt = 0;
						} catch (InterruptedException e) {
							if (slova.equals(trenutnaSlova)) {
								istaSlova = true;
								cnt++;
							} else {
								azurirajNatpis(slova.charAt(cnt % slova.length()));
								istaSlova = true;
								cnt = 0;
								mojaLabela.setText(natpis + trenutnaSlova.charAt(cnt % trenutnaSlova.length()));
							}
						}
					}
				}
			}
		} catch (InterruptedException e) {
		}
		synchronized (this) {
			mojaNit = null;
			notifyAll();
		}

	}

}
