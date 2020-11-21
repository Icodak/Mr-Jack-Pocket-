
public enum DetectiveName {
	Waston,
	Sherlock,
	Tobby;
	
	
	
	public static String[] getName() {
		String[] listeName = new String[3];
		listeName[0]=Waston.name();
		listeName[1]=Sherlock.name();
		listeName[2]=Tobby.name();
		return listeName;
	}
	
}
