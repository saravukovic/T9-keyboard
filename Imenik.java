package tastatura;

@SuppressWarnings("serial")
public class Imenik extends ListaStavki {
	
	@Override
	public void dodajStavku(Stavka s) {
		// TODO Auto-generated method stub
		if(s instanceof Kontakt) super.dodajStavku(s);
	}

	public String dohvatiImeKorisnika(Broj b) throws GNePostoji {
		for(Stavka s:lista) {
			if(s.tekst.getText().equals(b.toString())) return s.naslov.getText();
		}
		throw new GNePostoji();
	}
	
	public Broj dohvatiBrojKorisnika(String b) throws GNePostoji {
		for(Stavka s:lista) {
			if(s.naslov.getText().equals(b.toString())) {
				String str = s.tekst.getText();
				str.replace(" ", "");
				return new Broj(str);
			}
		}
		throw new GNePostoji();
	}
}
