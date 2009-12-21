package org.jicker.mp3.data;

import org.jicker.mp3.JickerMp3Globals;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.jicker.mp3.dbobject.*;


public class ReadMp3Db {
public ReadMp3Db(){
	ODB odb = ODBFactory.open(JickerMp3Globals.dbName);
	Objects<Mp3File> mp3Files = odb.getObjects(Mp3File.class);
	System.out.println(mp3Files.size());
	// Close ODB
	odb.close();

	
}
}
