package org.jicker.util.db.db4o;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class First {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		//Testverzeichnisse generieren
		for (int n = 1;n<10;n++){
			String verzName = "e:/bilder/test" + n + "/";
			boolean status = new File(verzName).mkdir();
			for (int m=1;m<10;m++){
				String fileName = "test" + m + ".jpg";
				boolean statusFile = new File(verzName + fileName).createNewFile();
			}
		}
		//Datenbank löschen
		new File("first.db4o").delete();
		//Neue Datenbank anlegen
		ObjectContainer db = Db4o.openFile("first.db4o");
		//Datenbank befüllen
		try {
			for (int n = 1;n<10;n++){
				String verzName = "E:/bilder/test" + n + "/";
				Verzeichnis verzeichnis = new Verzeichnis(verzName);
				db.set(verzeichnis);
				for (int m =1;m<10;m++){
					Datei datei = new Datei(verzName + "test" + m + ".jpg", verzeichnis);
					db.set(datei);
					//System.out.println((verzName + "test" + m + ".jpg").substring((verzName + "test" + m + ".jpg").lastIndexOf("/")+1));
				}
			}
			//Suche nach allen Dateien
/*			ObjectSet<Datei> result = db.get(Datei.class);
			System.out.println(result.size());
			while (result.hasNext()) {
				System.out.println(result.next());
			}
			System.out.println("---");*/
			//Suche nach allen Verzeichnissen
/*			ObjectSet<Verzeichnis> resultVerzeichnis = db
					.get(Verzeichnis.class);

			System.out.println(resultVerzeichnis.size());
			while (resultVerzeichnis.hasNext()) {
				System.out.println(resultVerzeichnis.next());
			}
			System.out.println("---");
*/			
			Verzeichnis verzeichnisProto = new Verzeichnis("E:/bilder/test3/");
			Datei dateiProto = new Datei(null,verzeichnisProto);
			dateiProto.setVerzeichnis(verzeichnisProto);
			ObjectSet<Object> result3 = db.get(dateiProto);
			
	System.out.println(result3.size());
	while (result3.hasNext()) {
		System.out.println(result3.next());
	}
	System.out.println("---");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}

	}

}
