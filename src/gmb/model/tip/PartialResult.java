package gmb.model.tip;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PartialResult 
{
	@Id @GeneratedValue (strategy=GenerationType.IDENTITY)
	protected int partialResultId;
	
	protected String clubname;
	protected int score;
	
	@Deprecated
	protected PartialResult(){}
	
	public PartialResult(String clubname, int score)
	{
		this.clubname = clubname;
		this.score = score;
	}
	
	public String getClubName(){ return clubname; }
	public int getScore(){ return score; }
}
