package org.jicker.mp3.data;

public class TestGetMp3Files {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GetMp3Files gmf = new GetMp3Files();
		gmf.setDelDB(true);
		gmf.setMusicBase("f:/musik/cd/");
		gmf.getDataNew();
		
		new ReadMp3Db();
	}

}
