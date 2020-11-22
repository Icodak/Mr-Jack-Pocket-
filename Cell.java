public class Cell {
	private InvestigatorToken[] detective ;
	private Tile discrict;
		
	
	public Cell(InvestigatorToken[] investigatorToken, Tile discrict) {
		this.detective = investigatorToken;
		this.discrict = discrict;
	}
	
	public InvestigatorToken[] getDetective() {
		return detective;
	}
	public void setDetective(InvestigatorToken[] detective) {
		this.detective = detective;
	}
	public Tile getDiscrict() {
		return discrict;
	}
	public void setDiscrict(Tile discrict) {
		this.discrict = discrict;
	}
	
	public String getDetectiveToString() {
		return (detective[0].getName()+""+detective[1].getName()+""+detective[2].getName());
	}
	
	
	
	
}
