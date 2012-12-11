package gmb.model.financial.container;

import gmb.model.CDecimal;
import gmb.model.financial.FinancialManagement;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class LotteryCredits
{
	@Id @GeneratedValue (strategy=GenerationType.IDENTITY)
	protected int lotteryCreditId;
	
	@OneToOne
	protected FinancialManagement financialManagementId;
	
	protected CDecimal treasuryCedit = new CDecimal(0);
	protected CDecimal lotteryTaxCedit = new CDecimal(0);
	protected CDecimal managementCedit = new CDecimal(0);
	
	public LotteryCredits(){}
	
	public void setTreasuryDue(CDecimal treasuryCedit){ this.treasuryCedit = treasuryCedit; }
	public void setLotteryTaxDue(CDecimal lotteryTaxCedit){ this.lotteryTaxCedit = lotteryTaxCedit; }
	public void setManagementDue(CDecimal managementCedit){ this.managementCedit = managementCedit; }	
	
	public CDecimal getTreasuryCedit(){ return treasuryCedit; }
	public CDecimal getLotteryTaxCedit(){ return lotteryTaxCedit; }
	public CDecimal getManagementCedit(){ return managementCedit; }
	
	public void update(ReceiptsDistributionResult receipts)
	{
		treasuryCedit = treasuryCedit.add(receipts.getTreasuryDue());
		lotteryTaxCedit = lotteryTaxCedit.add(receipts.getLotteryTaxDue());
		managementCedit = managementCedit.add(receipts.getManagementDue());
	}
}
