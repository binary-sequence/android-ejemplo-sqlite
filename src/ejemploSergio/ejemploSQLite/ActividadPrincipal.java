package ejemploSergio.ejemploSQLite;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;

/** Clase de actividad principal */
public class ActividadPrincipal extends ListActivity {
	/** Propiedades a partir de aqu�. */

	// Variable para manejar la base de datos.
	DbAdapter db;

	/** Constructores y m�todos heredados a partir de aqu�. */

	// M�todo ejecutado al iniciar la aplicaci�n.
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Establece main.xml como vista actual.
		setContentView(R.layout.main);

		// Obtiene el elemento btnInsertar de main.xml
		Button btnInsertar = (Button) this.findViewById(R.id.btnInsertar);

		// Le asigna un evento de clase BtnInsertarClick.
		btnInsertar.setOnClickListener(new BtnInsertarClick(this));

		// Obtiene el elemento btnCancelar de main.xml
		Button btnCancelar = (Button) this.findViewById(R.id.btnCancelar);

		// Le asigna un evento de clase BtnCancelarClick.
		btnCancelar.setOnClickListener(new BtnCancelarClick(this));

		// Crea un objeto manipulador de base de datos y abre una conexi�n.
		db = new DbAdapter(this);
		db.open();

		// Rellena el elemento ListView con los registros de la tabla notas.
		this.rellenarListView();
	}

	/** M�todos adicionales a partir de aqu�. */

	/**
	 * M�todo para rellenar el elemento ListView con los registros de la base de
	 * datos.
	 */
	void rellenarListView() {
		// Variable que contiene los registros.
		Cursor c = db.obtenerNotas();

		// Prepara el cursor para su uso.
		startManagingCursor(c);

		// Indica los campos a proyectar.
		String[] desde = new String[] { SqLiteHelper.KEY_ID,
				SqLiteHelper.KEY_TITULO, SqLiteHelper.KEY_CUERPO };

		// Indica d�nde se mostrar�n los campos.
		int[] hasta = new int[] { R.id.tvId, R.id.tvTitulo, R.id.tvCuerpo };

		// Crea un adaptador para poder mostrar los datos en el ListView.
		SimpleCursorAdapter notes = new SimpleCursorAdapter(this,
				R.layout.listview_item, c, desde, hasta);

		// Asigna el adaptador al ListView.
		setListAdapter(notes);
	}
}

/** Clases de eventos de elementos gr�ficos a partir de aqu�. */

class BtnInsertarClick implements OnClickListener {
	/** Propiedades a partir de aqu�. */

	// Variable para acceder a los elementos de actividad principal.
	ActividadPrincipal actividadPrincipal;

	/** Constructores y m�todos heredados a partir de aqu�. */

	/**
	 * Constructor de la clase BtnInsertarClick.
	 * 
	 * @param actividadPrincipal
	 *            Referencia a la actividad principal para acceder a sus
	 *            elementos desde esta clase.
	 */
	BtnInsertarClick(ActividadPrincipal actividadPrincipal) {
		this.actividadPrincipal = actividadPrincipal;
	}

	// M�todo ejecutado en el evento click.
	@Override
	public void onClick(View v) {
		// Obtiene el elemento etTitulo de main.xml
		EditText etTitulo = (EditText) this.actividadPrincipal
				.findViewById(R.id.etTitulo);

		// Obtiene el elemento etCuerpo de main.xml
		EditText etCuerpo = (EditText) this.actividadPrincipal
				.findViewById(R.id.etCuerpo);

		// Inserta los valores de las cajas de texto en la tabla notas.
		this.actividadPrincipal.db.insertarNota(etTitulo.getText().toString(),
				etCuerpo.getText().toString());

		// Actualiza los datos del elemento ListView.
		this.actividadPrincipal.rellenarListView();

		// Vac�a las cajas de texto.
		etTitulo.setText("");
		etCuerpo.setText("");
	}
}

class BtnCancelarClick implements OnClickListener {
	/** Propiedades a partir de aqu�. */

	// Variable para acceder a los elementos de actividad principal.
	ActividadPrincipal actividadPrincipal;

	/** Constructores y m�todos heredados a partir de aqu�. */

	/**
	 * Constructor de la clase BtnCancelarClick.
	 * 
	 * @param actividadPrincipal
	 *            Referencia a la actividad principal para acceder a sus
	 *            elementos desde esta clase.
	 */
	BtnCancelarClick(ActividadPrincipal actividadPrincipal) {
		this.actividadPrincipal = actividadPrincipal;
	}

	// M�todo ejecutado en el evento click.
	@Override
	public void onClick(View v) {
		// Obtiene el elemento etTitulo de main.xml
		EditText etTitulo = (EditText) this.actividadPrincipal
				.findViewById(R.id.etTitulo);

		// Obtiene el elemento etCuerpo de main.xml
		EditText etCuerpo = (EditText) this.actividadPrincipal
				.findViewById(R.id.etCuerpo);

		// Vac�a las cajas de texto.
		etTitulo.setText("");
		etCuerpo.setText("");
	}
}