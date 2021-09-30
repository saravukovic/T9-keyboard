package tastatura;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;

@SuppressWarnings("serial")
public class Stavka extends Panel {
	public Label naslov = new Label(), tekst = new Label();

	public Stavka(String n, String t) {
		setLayout(new GridLayout(2,1));
		naslov.setText(n);
		naslov.setFont(new Font(null, Font.BOLD, 0));
		tekst.setText(t);
		add(naslov);
		add(tekst);
	}

	public void postaviNaslov(String naslov) {
		this.naslov.setText(naslov);
	}

	public void postaviTekst(String tekst) {
		this.tekst.setText(tekst);
	}

}
