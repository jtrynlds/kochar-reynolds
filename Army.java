

public class Army {
	
	public static final double STD_DEFENSE_KILL_RATE = 0.6;
	public static final double STD_ATTACK_KILL_RATE = 0.5;

	private int size;
	private int defense;
	
	public Army(int numTroops, double defensiveness){
		size = numTroops;
	}
	
	public void add(int numToAdd){
		size += numToAdd;
	}
	
	public void kill(int numToKill){
		size -= numToKill;
	}
	
	public boolean attack(Army other){
		return false;
	}
	
	public int getDefense(){
		return defense;
	}
	
	public void transfer(Army other, int numToTransfer){
		if(numToTransfer >= size){
			other.add(size);
			size = 0;
		}
		else if(numToTransfer < 1){
			return;
		}
		else{
			other.add(numToTransfer);
			size -= numToTransfer;
		}
	}
	
	public static double genNormalDistribution(double mean, double stdev){
		double z0, z1 = 0;
		double u0, u1;
		
		//Generates uniformly distributed numbers in (0,1]
		u0 = 1.0 - Math.random();
		u1 = 1.0 - Math.random();
		
		//Uses Box-Muller transform to convert to a normal distribution
		z0 = Math.sqrt(-2.0*Math.log(u0)) * Math.cos(2*Math.PI*u1);
		z1 = Math.sqrt(-2.0*Math.log(u0)) * Math.sin(2*Math.PI*u1);
		
		//Uses given values for stdev and mean
		return (z0*stdev+mean);
	}
	
	public static void main(String[] args){
		genNormalDistribution(0,.03);
	}
}
