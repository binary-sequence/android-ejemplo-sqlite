package ejemploSergio.ejemploSQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

// Clase que act�a de DBA para creaci�n y actualizaci�n de una base de datos SQLite.
class SqLiteHelper extends SQLiteOpenHelper {
	/** Propiedades a partir de aqu�. */
	// Nombre de la base de datos y del fichero.
	public static final String DATABASE_NAME = "datos.db";
	// N�mero de versi�n de la base de datos.
	public static final int DATABASE_VERSION = 1;

	// Nombre de la �nica tabla que va a contener la base de datos.
	public static final String DATABASE_TABLE = "notas";
	// Nombre del campo clave primaria.
	public static final String KEY_ID = "_id";
	// Nombres de los dem�s campos.
	public static final String KEY_TITULO = "titulo";
	public static final String KEY_CUERPO = "cuerpo";

	// Sentencia de creaci�n de la tabla notas.
	public static final String TABLE_CREATE = "create table " + DATABASE_TABLE
			+ " (" + KEY_ID + " integer primary key autoincrement, "
			+ KEY_TITULO + " text not null, " + KEY_CUERPO + " text not null);";

	/** Constructores y m�todos heredados a partir de aqu�. */

	/**
	 * Constructor de la clase SqLiteHelper.
	 * 
	 * @param contexto
	 *            Referencia a la actividad que usar� esta clase.
	 */
	SqLiteHelper(Context contexto) {
		super(contexto, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Es ejecutado autom�ticamente al conectar con la base de datos (Si no
	// existe).
	@Override
	public void onCreate(SQLiteDatabase db) {
		// Se ejecuta la sentencia de creaci�n de la tabla notas.
		db.execSQL(TABLE_CREATE);
	}

	// Es ejecutado autom�ticamente al conectar con la base de datos (Si existe
	// pero es de version inferior).
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Se env�a un mensaje al LOG (Acci�n por defecto.).
		Log.w("SqLiteHelper", "Upgrading database from version " + oldVersion
				+ " to " + newVersion + ", which will destroy all old data");

		// Se borra la tabla notas.
		db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);

		// Se llama al m�todo donde hemos definido la creaci�n de las tablas.
		onCreate(db);
	}
}