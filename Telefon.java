package tastatura;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.border.Border;

@SuppressWarnings("serial")
public class Telefon extends Panel {
	
	private Broj mojBrojTelefona;
	private Color boja;
	private Imenik imenik = new Imenik();
	private Label brojLabela = new Label();
	private Label imeLabela = new Label();
	private Tastatura tastatura;
	private Label mojBroj = new Label();
	private Button dodajKontakt = new Button("Dodaj kontakt");
	private Button iskljuciTelefon = new Button("Iskljuci telefon");
	private boolean ukljucen = true;
	private Broj trenutniBroj;
	private String trenutnoIme;
	private int cnt = 0;
	
	
	public Telefon(Broj brojTelefona, Color boja) {
		this.mojBrojTelefona = brojTelefona;
		this.boja = boja;
		brojLabela.setBackground(boja);
		brojLabela.setText("");
		tastatura = new Tastatura(brojLabela);
		adjustPanel();
	}
	
	private void adjustPanel() {		
		setLayout(new BorderLayout());
		
		Panel pomocni1 = new Panel(new GridLayout(0,1));
		
		pomocni1.add(brojLabela);
		
		imeLabela.setBackground(boja);
		imeLabela.setText("");
		pomocni1.add(imeLabela);
		add(pomocni1,BorderLayout.NORTH);
		
		Panel pomocni4 = new Panel(new GridLayout(0,1));
		pomocni4.add(tastatura);
		
		imenik.setBackground(boja);
		pomocni4.add(imenik);
		
		add(pomocni4, BorderLayout.CENTER);

		dodajKontakt.setBackground(Color.LIGHT_GRAY);
		dodajKontakt.addActionListener(ae->{
			if(cnt==0) {
				trenutniBroj = new Broj(brojLabela.getText());
				tastatura.promeniLabelu(imeLabela);
				tastatura.promeniRezimRada();
				cnt++;
			}else {
				trenutnoIme = imeLabela.getText();
				if(trenutnoIme!="") dodajNoviBroj(trenutniBroj, trenutnoIme);
				imeLabela.setText("");
				brojLabela.setText("");
				tastatura.promeniLabelu(brojLabela);
				tastatura.promeniRezimRada();
				cnt--;
			}
		});
		iskljuciTelefon.setBackground(Color.GRAY);
		iskljuciTelefon.addActionListener(ae->{
			if(ukljucen) {
				iskljuciTelefon.setLabel("Ukljuci telefon");
				iskljuciTelefon.setBackground(Color.RED);
				ukljucen = false;
			} else {
				iskljuciTelefon.setLabel("Iskljuci telefon");
				iskljuciTelefon.setBackground(Color.GRAY);
				ukljucen = true;
			}
		});
		Panel pomocni2 = new Panel(new GridLayout(1,2));
		pomocni2.add(dodajKontakt);
		pomocni2.add(iskljuciTelefon);
		
		mojBroj.setBackground(boja);
		mojBroj.setAlignment(Label.CENTER);
		mojBroj.setFont(new Font(null, Font.BOLD, 0));
		mojBroj.setText(mojBrojTelefona.toString());
		
		Panel pomocni3 = new Panel(new GridLayout(2,1));
		pomocni3.add(pomocni2);
		pomocni3.add(mojBroj);
		add(pomocni3, BorderLayout.SOUTH);
	}

	public Broj dohvatiBrojTelefona() {
		return mojBrojTelefona;
	}
	
	public void dodajNoviBroj(Broj b, String ime) {
		Kontakt k = new Kontakt(ime, b);
		imenik.dodajStavku(k);
	}
	
	public static void main(String[] args) {
		Telefon t1 = new Telefon(new Broj("+381658831055"), Color.GREEN);
		Telefon t2 = new Telefon(new Broj("+381658831055"), Color.YELLOW);
		Frame f = new Frame();
		f.setLayout(new GridLayout(1,0));
		f.setBounds(700, 200, 500, 500);
		f.setResizable(true);
		f.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				f.dispose();
			}
		});
		f.add(t1);
		f.add(t2);
		f.setVisible(true);
	}

}
