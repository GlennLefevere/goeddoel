package be.vdab.entities;

public class Bedrag {
	private long amount = 0;
	
	public long getAmount(){
		return amount;
	}
	
	public void addAmount(long add){
		this.amount += add;
	}
}
