package scripts;

import org.tribot.api.General;
import org.tribot.api2007.Skills;
import org.tribot.api2007.Skills.SKILLS;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSCharacter;
import org.tribot.api2007.types.RSItem;
import org.tribot.api2007.types.RSPlayer;
import org.tribot.api2007.types.RSTile;
import org.tribot.script.Script;
import org.tribot.script.ScriptManifest;
import org.tribot.script.interfaces.Painting;

import java.awt.Color;
import java.awt.Graphics;
import java.util.*;
@ScriptManifest(authors = { "Kingboy" }, category = "Quests", name = "KingboyNMZ")

public class KingboyNMZ extends Script implements Painting{

	
	//declare/initialze variables	
	private boolean onOff = true;  //the on/off switch for script
	private int sleepTime;
	private static final RSArea nmzArea = new RSArea(new RSTile(2633,3083,0), new RSTile(2596,3123,0));
	private int prayerLevel = Skills.getActualLevel(SKILLS.PRAYER);
	private int strLevel = Skills.getActualLevel(SKILLS.STRENGTH);
	private static final long startTime = System.currentTimeMillis();
	private int startStrXP = Skills.getXP(SKILLS.STRENGTH);
	private int startDefXP = Skills.getXP(SKILLS.DEFENCE);
	private int startAttackXP = Skills.getXP(SKILLS.ATTACK);
	private int startRangeXP = Skills.getXP(SKILLS.RANGED);
	private int startMagicXP = Skills.getXP(SKILLS.MAGIC);
	private int startHPXP = Skills.getXP(SKILLS.HITPOINTS);
	
	
	
	
	@Override
	public void run() { //main method
		System.out.println("KingboyNMZ script starting...");
		General.useAntiBanCompliance(true);
		
		while(onOff == true){ //while loop that keeps going if acc is inside NMZ
			Random rand = new Random(); //Random # Generator
			System.out.println("Script continues...");
			if(onOff == true){ //sets sleepTime to a different # each loop
				sleepTime = rand.nextInt(10000) + 5000;
			}
			
			General.sleep(sleepTime); //dynamic sleep
			inNMZ();
			
		}
		
	}

	public void inNMZ(){ //checks to see if inside NMZ
		General.sleep(sleepTime);
		if(nmzArea.contains(Player.getPosition()) == false){ //if the acc is in nmz
			prayer();
		}else{ //turn off the script
			onOff = false;
			System.out.println("Your character is not in NMZ! Ending Script...");
		}
		if(nmzArea.contains(Player.getPosition()) == false){ //if the acc is in nmz
			overload();
		}else{ //turn off the script
			onOff = false;
			System.out.println("Your character is not in NMZ! Ending Script...");
		}
	}

	
	public void overload(){ //tests to see if need to drink overload potion
		General.sleep(sleepTime);
		
		int currentStrLevel = Skills.getCurrentLevel(SKILLS.STRENGTH); //gets current Str lvl
			
			System.out.println("Checking if Overload Potion is needed...");
		
		if(currentStrLevel <= strLevel + 15){ //if strLvl is low enough
			
				drinkOverload();
				
				System.out.println("You have drank a dose of Overload Potion.");
			
		}else{ //if Strength level is not low enough
			
				System.out.println("Character does not currently need Overload...");
			}
		}
	

	public void drinkOverload(){ //drinks overload potions
		RSItem[] overload1 = Inventory.find("Overload (1)");
		RSItem[] overload2 = Inventory.find("Overload (2)");
		RSItem[] overload3 = Inventory.find("Overload (3)");
		RSItem[] overload4 = Inventory.find("Overload (4)");
		System.out.println("Drinking Overload Potion...");
		if(overload1.length>0){		
			overload1[0].click("Drink");		
		}else if(overload2.length>0){		
			overload2[0].click("Drink");		
		}else if(overload3.length>0){		
				overload3[0].click("Drink");		
		}else if(overload4.length>0){
			overload4[0].click("Drink");
		}else{ //prints error to alert person that they are out of overloads
			System.out.println("Could not find Overload Potion in inventory! Finishing Inventory without them...");
		}
	}
	
	

	public void prayer(){ //tests to see if need to drink prayer potion
		
		General.sleep(sleepTime);
		
		int currentPrayerLevel = Skills.getCurrentLevel(SKILLS.PRAYER); //gets players current prayer lvl
		
			System.out.println("Checking if Prayer Potion is needed...");
		
		if(currentPrayerLevel <= prayerLevel * .6){ //if prayLvl is low enough
				drinkPrayerPot();
				System.out.println("You have drank a dose of Prayer Potion.");
			
		}else{ //if prayer level is not low enough
				System.out.println("Prayer is at a high level...");
			}
		}
	
	public void drinkPrayerPot(){ //method to drink prayer pots
		RSItem[] prayer1 = Inventory.find("Prayer potion(1)");
		RSItem[] prayer2 = Inventory.find("Prayer potion(2)");
		RSItem[] prayer3 = Inventory.find("Prayer potion(3)");
		RSItem[] prayer4 = Inventory.find("Prayer potion(4)");
		System.out.println("Drinking Prayer Potion...");
		if(prayer1.length>0){		
			prayer1[0].click("Drink");		
		}else if(prayer2.length>0){		
			prayer2[0].click("Drink");		
		}else if(prayer3.length>0){		
				prayer3[0].click("Drink");		
		}else if(prayer4.length>0){
			prayer4[0].click("Drink");
		}else{ //prints error and ends script
			System.out.println("Could not find Prayer Potion in inventory! Ending Script...");
			onOff = false;
		}
	}

	public void onPaint(Graphics g) {//PAINT / GFX
		//variable for time program is running
		long timeRan = System.currentTimeMillis() - startTime;
		//variables for Current XP
		int currentStrXP = Skills.getXP(SKILLS.STRENGTH);
		int currentDefXP = Skills.getXP(SKILLS.DEFENCE);
		int currentAttackXP = Skills.getXP(SKILLS.ATTACK);
		int currentRangeXP = Skills.getXP(SKILLS.RANGED);
		int currentMagicXP = Skills.getXP(SKILLS.MAGIC);
		int currentHPXP = Skills.getXP(SKILLS.HITPOINTS);
		//variables for displayed XP Trackers
		int totalXPGained = (currentStrXP - startStrXP) + (currentDefXP - startDefXP) + (currentAttackXP - startAttackXP) + (currentRangeXP - startRangeXP) + (currentMagicXP - startMagicXP);
		int totalHPGained = (currentHPXP - startHPXP);
		long totalXPGained2 = java.lang.Math.abs(totalXPGained);
		long totalHPGained2 = java.lang.Math.abs(totalHPGained);
		
		g.setColor(Color.GREEN);
		g.drawString("KingboyNMZ V0.l", 20, 225);
		g.drawString("Combat XP Gained: " + totalXPGained , 20, 250);
		g.drawString("HP XP Gained: " + totalHPGained , 20, 275);
		g.drawString("Running: " + timeRan / 1000 / 60 + " Minutes " , 20, 300);
		g.drawString("Combat XP/Hour: " + (totalXPGained2 * 3600000 / timeRan) + "   HP XP/Hour: " + (long)(totalHPGained2 * 360000 / timeRan), 20, 325);
	} 

	}
	


