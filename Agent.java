public class Agent{
	String name;
	int id;
	
	public Agent(String _name , Integer _id){
		name = _name;
		id = _id;		
	}
	
	
	
	public Integer getIdbyName(String name){
		return id;
	}
	
	public String getNamebyId(Integer id){
		return name;
	}
	
	public String getName(){
		return name;
	}
	
	public Integer getId(){
		return id;
	}
}