package entity;

import main.GamePanel;

public class NPC_DemonLord  extends Entity {

	public NPC_DemonLord(GamePanel gp) {
		super(gp);
		
		direction = "left";
		speed = 0;
		
		getImage();
		setDialogue();
	}
	
	public void getImage() {

        up1 = setup("/npc/demon_lord",gp.tileSize,gp.tileSize);
        up2 = setup("/npc/demon_lord",gp.tileSize,gp.tileSize);
        down1 = setup("/npc/demon_lord",gp.tileSize,gp.tileSize);
        down2 = setup("/npc/demon_lord",gp.tileSize,gp.tileSize);
        left1 = setup("/npc/demon_lord",gp.tileSize,gp.tileSize);
        left2 = setup("/npc/demon_lord",gp.tileSize,gp.tileSize);
        right1 = setup("/npc/demon_lord",gp.tileSize,gp.tileSize);
        right2 = setup("/npc/demon_lord",gp.tileSize,gp.tileSize);
        
    }
	
	public void setDialogue() {
		
		dialogues[0] = "Hello adventurer...\nWhy you come here?";
		dialogues[1] = "Come to save the princess from me?!";
		dialogues[2] = "I'm sure there must be a misunderstanding.";
		dialogues[3] = "Come to save the princess?";
		dialogues[4] = "I didn't kidnap the princess.";
		dialogues[5] = "If you don't believe it, ask the princess.";
	}
	
	public void speak() {
		
		super.speak();
	}
}
