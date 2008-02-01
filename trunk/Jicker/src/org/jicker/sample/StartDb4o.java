package org.jicker.sample;

import java.io.File;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class StartDb4o {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new File("test.db4o").delete();
		ObjectContainer db=Db4o.openFile("test.db4o");
        try {
            storeFirstPilot(db);
            storeSecondPilot(db);
            retrieveAllPilots(db);
        }
        finally {
            db.close();
        }


	}
	public static void storeFirstPilot(ObjectContainer db) {
        Inventory inv=new Inventory(1,"c:/bilder");
        db.set(inv);
        System.out.println("Stored " + inv);
    }
	public static void storeSecondPilot(ObjectContainer db) {
        Inventory inv=new Inventory(1,"c:/bilder/test");
        db.set(inv);
        System.out.println("Stored " + inv);
    }
    public static void retrieveAllPilots(ObjectContainer db) {
        ObjectSet result=db.get(Inventory.class);
        listResult(result);
    }
    public static void listResult(java.util.List result){
    	System.out.println(result.size());
    	for(int x = 0; x < result.size(); x++)
    		System.out.println(result.get(x));
    }

}
