package entity;

import java.util.Random;

import main.GamePanel;

public class NPC_Prince extends Entity {

	public NPC_Prince(GamePanel gp) {
		super(gp);
		
		direction = "left";
		speed = 1;
		
		getImage();
		setDialogue();
	}
	public void getImage() {

		up1 = setup("/npc/prince_down",gp.tileSize,gp.tileSize);
		up2 = setup("/npc/prince_down",gp.tileSize,gp.tileSize);
        down1 = setup("/npc/prince_down",gp.tileSize,gp.tileSize);
        down2 = setup("/npc/prince_down",gp.tileSize,gp.tileSize);
        left1 = setup("/npc/prince_left1",gp.tileSize,gp.tileSize);
        left2 = setup("/npc/prince_left2",gp.tileSize,gp.tileSize);
        right1 = setup("/npc/prince_right1",gp.tileSize,gp.tileSize);
        right2 = setup("/npc/prince_right2",gp.tileSize,gp.tileSize);
    }
	public void setDialogue() {
		
		dialogues[0] = "Hello, Are you Peter right?";
		dialogues[1] = "I called you because I need your help.";
		dialogues[2] = "The princess was kidnapped to the \nDemon Lord's castle.";
		dialogues[3] = "And Peter, you are descendant of the Hero.";
		dialogues[4] = "So may I ask you to help the princess \ncome back?";
	}
	public void setAction() {
		
		actionLockCounter ++;
		
        if(actionLockCounter == 120) {
        	
        	Random random = new Random();
			int i = random.nextInt(50)+1; //pick up a number from 1 to 100
			
			if(i <= 25) {
				direction = "right";
			}
			if(i > 25 && i<=50) {
				direction = "left";
			}
			actionLockCounter = 0;
        }
	}
	public void speak() {
		
		super.speak();
	}
}
