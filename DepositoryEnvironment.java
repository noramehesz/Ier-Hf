// Environment code for project Intelligent-Deporsitory.mas2j



import jason.asSyntax.*;

import jason.environment.*;

import java.util.logging.*;

import java.util.*;



public class DepositoryEnvironment extends Environment {

	DepositoryModel model;
	DepositoryView view;
	boolean closed = true;
	int sensor = 0;
	List<Agent> trucksatgate = new ArrayList<Agent>();
	boolean isin = false;	
	//boolean hasGUI   = true;
	
	public List<Agent> ags = new ArrayList<Agent>();


    private Logger logger = Logger.getLogger("Intelligent-Deporsitory.mas2j."+DepositoryEnvironment.class.getName());

	
	public DepositoryEnvironment(){
	     
	}
	
	
    /** Called before the MAS execution with the args informed in .mas2j */

    @Override

    public void init(String[] args) {

		 super.init(args);
		 //hasGUI = true;
		model = new DepositoryModel(this);
		view = new DepositoryView(model, this);
		model.setView(view);
       
        //addPercept(ASSyntax.parseLiteral("percept(demo)"));

    }
	
    @Override
    public boolean executeAction(String agName, Structure action) {
		
		String actId = action.getFunctor();
		
		//logger.info(agName+"="+actId);
		
		int agId = -1;
		
		for(int i = 0; i < ags.size(); i++){
			if(ags.get(i).getName().equals(agName)){			
					 agId = ags.get(i).getIdbyName(agName);
			}
		}
		
		if(agId < 0){
		    logger.info("cant find agent");
		}

		try{
			Thread.sleep(800);
		
			if (actId.equals("move")) { 
				synchronized(this){
					int x = (int)((NumberTerm)action.getTerm(0)).solve();
					int y = (int)((NumberTerm)action.getTerm(1)).solve();
					model.move(agId, x, y);
					
					if(agName.substring(0, 4).equals("empt")){
					logger.info("executing: "+ action+ " " + agName);
					}
					}
					
					
					if(model.at_gate() >= 0){
						isin = false;
						trucksatgate.add(new Agent("haha", 333));
						for(int k= 0; k<trucksatgate.size(); k++ ){
							if(trucksatgate.get(k).getName().equals( ags.get(model.at_gate()).getName()   )){
									isin = true;
							}
						}
						
						if(isin == false){
							trucksatgate.add(ags.get(model.at_gate()));
							sensor++;
						}
						
						if(sensor == 6 ){ 
							
							closed = false;
							addPercept("terminal1", Literal.parseLiteral("open"));
							addPercept("terminal3", Literal.parseLiteral("open"));
							view.redraw();
						}
						
						addPercept(ags.get(model.at_gate()).getName(), Literal.parseLiteral( "at_gate(gate)" ));
						//addPercept("gate", Literal.parseLiteral("at_gate("+ ags.get(model.at_gate()).getName() + ")"));
						
					}else {
						
					}
					                                           
					if(model.at_selector() >= 0){
					
						clearPercepts(ags.get(model.at_selector()).getName());
						if(ags.get(model.at_selector()).getName().substring(0,5).equals("empty")){
							logger.info(ags.get(model.at_selector()).getName());
							addPercept(ags.get(model.at_selector()).getName(), Literal.parseLiteral( "at_selector(selector)"  ));
						}
						
						if(ags.get(model.at_selector()).getName().substring(0,4).equals("full")){
							logger.info("ful reach seceltor");
							addPercept(ags.get(model.at_selector()).getName() , Literal.parseLiteral( "at_selector(selector_full)"));
						}
						
					}
					
					
			}
		} catch (Exception e) {
				e.printStackTrace();
			}
			
			if (true) { 
				informAgsEnvironmentChanged();
			}
			
		return true;
	}

	
	
    /** Called before the end of MAS execution */

    @Override

    public void stop() {

        super.stop();

    }
	
	public void refresh(){
		view.redraw();
	}

}


