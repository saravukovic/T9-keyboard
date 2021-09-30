package tastatura;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import java.util.List;
@SuppressWarnings("serial")
public class ListaStavki extends Panel {
	protected List<Stavka> lista = new ArrayList<>();
	public ListaStavki() {
		setLayout(new GridLayout(1,0));
	}
	public void dodajStavku(Stavka s) {
		lista.add(s);
		if(lista.size()>5) setLayout(new GridLayout(5,0));
		add(s);
		revalidate();
	}

}
