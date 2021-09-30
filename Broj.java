package tastatura;

public class Broj {

	private int kodDrzave, pozivniBroj, brojPret;

	public Broj(int kodDrzave, int pozivniBroj, int brojPret) {
		this.kodDrzave = kodDrzave;
		this.pozivniBroj = pozivniBroj;
		this.brojPret = brojPret;
	}
	
	public Broj(String s) {
		if (s.length() > 5) {
			kodDrzave = Integer.parseInt(s.substring(1, 4));
			pozivniBroj = Integer.parseInt(s.substring(4, 6));
			brojPret = Integer.parseInt(s.substring(6));
		}
	}

	public boolean jednakKodDrzave(Broj br) {
		return kodDrzave == br.kodDrzave;
	}

	public boolean istaMreza(Broj br) {
		return kodDrzave == br.kodDrzave && pozivniBroj == br.pozivniBroj;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Broj))
			return false;
		Broj b = (Broj) obj;
		return istaMreza(b) && brojPret == b.brojPret;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("+");
		sb.append(kodDrzave);
		sb.append(" ");
		sb.append(pozivniBroj);
		sb.append(" ");
		sb.append(brojPret);
		return sb.toString();
	}

}
