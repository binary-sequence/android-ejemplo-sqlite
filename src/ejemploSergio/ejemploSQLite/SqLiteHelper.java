package ejemploSergio.ejemploSQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

// Clase que actúa de DBA para creación y actualización de una base de datos SQLite.
class SqLiteHelper extends SQLiteOpenHelper {
	/** Propiedades a partir de aquí. */
	// Nombre de la base de datos y del fichero.
	public static final String DATABASE_NAME = "datos.db";
	// Número de versión de la base de datos.
	public static final int DATABASE_VERSION = 1;

	// Nombre de la única tabla que va a contener la base de datos.
	public static final String DATABASE_TABLE = "notas";
	// Nombre del campo clave primaria.
	public static final String KEY_ID = "_id";
	// Nombres de los demás campos.
	public static final String KEY_TITULO = "titulo";
	public static final String KEY_CUERPO = "cuerpo";

	// Sentencia de creación de la tabla notas.
	public static final String TABLE_CREATE = "create table " + DATABASE_TABLE
			+ " (" + KEY_ID + " integer primary key autoincrement, "
			+ KEY_TITULO + " text not null, " + KEY_CUERPO + " text not null);";

	/** Constructores y métodos heredados a partir de aquí. */

	/**
	 * Constructor de la clase SqLiteHelper.
	 * 
	 * @param contexto
	 *            Referencia a la actividad que usará esta clase.
	 */
	SqLiteHelper(Context contexto) {
		super(contexto, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Es ejecutado automáticamente al conectar con la base de datos (Si no
	// existe).
	@Override
	public void onCreate(SQLiteDatabase db) {
		// Se ejecuta la sentencia de creación de la tabla notas.
		db.execSQL(TABLE_CREATE);
	}

	// Es ejecutado automáticamente al conectar con la base de datos (Si existe
	// pero es de version inferior).
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Se envía un mensaje al LOG (Acción por defecto.).
		Log.w("SqLiteHelper", "Upgrading database from version " + oldVersion
				+ " to " + newVersion + ", which will destroy all old data");

		// Se borra la tabla notas.
		db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);

		// Se llama al método donde hemos definido la creación de las tablas.
		onCreate(db);
	}
}