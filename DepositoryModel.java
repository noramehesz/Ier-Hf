import jason.asSyntax.*;
import jason.environment.*;
import java.util.logging.*;
import jason.environment.grid.*;
import java.util.*;


public class DepositoryModel extends GridWorldModel {
	public DepositoryEnvironment env;
	public DepositoryModel model;
	private Logger logger = Logger.getLogger("Intelligent-Deporsitory.mas2j."+DepositoryEnvironment.class.getName());
	
	public DepositoryModel(DepositoryEnvironment e){
		
		super(12, 12, 13);
		env = e;
		env.ags = new ArrayList<Agent>();
		String name;
		//cars 
		int empty= 1;
		int full = 1;
		for(int i = 0;  i <6; i++){
		setAgPos(i, i, 0);    //is, x, y
		if(i % 2 == 0){
			 name = "fullTruck" + full++;
		} else {
			name = "emptyTruck" + empty++;
		}
		Agent a = new Agent(name, i);
		env.ags.add(a);
		
		}
		
		//gate
		setAgPos(6, 8, 2);
		name = "gate";
		env.ags.add(new Agent(name, 7));
		
		//selectors
		setAgPos(7, 2, 5);
		env.ags.add(new Agent("selector_full", 8));
		
		setAgPos(8, 9, 5);
		env.ags.add(new Agent("selector", 9));
		
		//terminals
		setAgPos(9, 4, 11);
		env.ags.add(new Agent("terminal1", 10));
		
		setAgPos(10, 0, 9);
		env.ags.add(new Agent("terminal2", 11));
		
		setAgPos(11, 7, 11);
		env.ags.add(new Agent("terminal3", 12));
		
		setAgPos(12, 11, 9);
		env.ags.add(new Agent("terminal4", 13));
	}
	
	public boolean isFree(int x, int y){
			Location loc = new Location(x,y ) ;
			for (int i = 0; i< env.ags.size(); i ++){
				if(env.ags.get(i) != null && loc.equals(getAgPos(i)) ){
					return false;
				}
			}
					return true;
	}

	
	public void move(int id, int x, int y) throws Exception {
		Location pos = getAgPos(id);
		if(pos == null){
			logger.info("nem jó");
		}
		
		synchronized(this){
			
			if(pos.x < x && isFree(pos.x+1, pos.y)){
				pos.x += 1;
			} else if(pos.x > x && isFree(pos.x-1, pos.y)){	
				pos.x -= 1;
			}
			
			else if (pos.y < y && isFree(pos.x, pos.y+1)){
				pos.y += 1;
			}else if(pos.y > y && isFree(pos.x, pos.y-1)){
				pos.y -= 1;
			}
		}
		
		setAgPos(id, pos);
	}
	
	public int at_gate(){
		for(int i = 0; i < env.ags.size(); i++){
			if(getAgPos(i).x ==7   && getAgPos(i).y == 2 ){
			   return i;
			}
		}
		return -1;
	}
	
	public int at_selector(){
	   for(int i = 0; i < env.ags.size(); i++){
			if(  (  getAgPos(i).x == 8   && getAgPos(i).y == 5  ) || (  getAgPos(i).x ==3   && getAgPos(i).y == 5  ) ){
			   return i;
			}
		}
		return -1;
	}
	
}
