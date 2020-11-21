
public class Players {
	private Card[] alibiCard ;
	private TimeToken[] turnToken;
	
	public Players(Card[] alibiCard, TimeToken[] turnToken) {
		super();
		this.alibiCard = alibiCard;
		this.turnToken = turnToken;
	}
	
	
	
	public Card[] getAlibiCard() {
		return alibiCard;
	}
	public void setAlibiCard(Card[] alibiCard) {
		this.alibiCard = alibiCard;
	}
	public TimeToken[] getTurnToken() {
		return turnToken;
	}
	public void setTurnToken(TimeToken[] turnToken) {
		this.turnToken = turnToken;
	}
	
	
	
	

	
}