
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
	public static InvestigatorToken[] listDetectiveConstructor(String name,String nameTwo,String nameThree){
		InvestigatorToken[] detectiveListe= {
				new InvestigatorToken(name),
				new InvestigatorToken(nameTwo),
				new InvestigatorToken(nameThree),
		};
		return detectiveListe;
	}
}
