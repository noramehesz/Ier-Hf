import jason.asSyntax.*;
import jason.environment.*;
import java.util.logging.*;
import jason.environment.grid.*;
import java.awt.event.*;
import java.util.Random;
import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.*;

public class DepositoryView extends GridWorldView{
	DepositoryEnvironment env;
	DepositoryModel model;
	 private Logger logger = Logger.getLogger("Intelligent-Deporsitory.mas2j."+DepositoryEnvironment.class.getName());
	
	public DepositoryView(DepositoryModel m, DepositoryEnvironment e){
		super(m, "Depo", 800);
		setVisible(true);
		env = e;
		model = m;	
		repaint();
	}
	
	public void redraw(){
		update(4, 11);
		update(7, 11);
	}
	
	/*@Override
	public void draw(Graphics g, int x, int y, int object) {
		for(int i = 0; i < env.ags.size(); i++){
		 super.drawAgent(g, x, y, Color.RED, i);
			
		}
	
	}*/
	
	@Override
	public void drawAgent(Graphics g, int x, int y, Color c, int id){
		String name = new String();

		if(env.ags.get(id) != null){
		
			if(env.closed && env.ags.get(id).getName().equals("terminal1")){
				g.setColor(Color.RED);
				name = "closed";
			}  
			
			if(env.closed && env.ags.get(id).getName().equals("terminal3")){
				g.setColor(Color.RED);
				name = "closed";
			} 
			
			 if(env.closed && (env.ags.get(id).getName().equals("terminal2") || env.ags.get(id).getName().equals("terminal4"))){
				g.setColor(Color.BLUE);
				name = "open";
			} 
			
			if( env.closed == false &&  env.ags.get(id).getName().startsWith("terminal") ){  //env.ags.get(id).getName().substring(0,8).equals("terminal")){
				
					g.setColor(Color.BLUE);
				name = "open";
				//repaint();
			}
			
			if(env.ags.get(id).getName().equals("selector") ||env.ags.get(id).getName().equals("selector_full") ){
				g.setColor(Color.GRAY);
				name = "S";
			}
		                                                                  
			if(env.ags.get(id).getName().equals("gate")){
				g.setColor(Color.BLACK);
				name = "gate";
			}
			
			if(env.ags.get(id).getName().substring(0,4).equals("empt")){
				g.setColor(Color.LIGHT_GRAY);
				name = "empty";
			}
			
			if(env.ags.get(id).getName().substring(0,4).equals("full")){
				g.setColor(Color.DARK_GRAY);
				name = "full";
			}
		}
		g.fillRect(x * cellSizeW + 1, y * cellSizeH+1, cellSizeW-1, cellSizeH-1);
		g.setColor(Color.WHITE);
		drawString(g, x, y, defaultFont, name); 
		
	
		}
				
	
}
