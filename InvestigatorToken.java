
public class InvestigatorToken {
	private String name;

	
	public InvestigatorToken(String name) {
		super();
		this.name = name;

	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public static String[] listDetectiveConstructor(String name,String nameTwo,String nameThree){
		String[] detectiveListe= {
				name,
				nameTwo,
				nameThree,			
		};
		return detectiveListe;
	}
}
