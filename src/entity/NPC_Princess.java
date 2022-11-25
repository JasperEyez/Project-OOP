package entity;

import main.GamePanel;

public class NPC_Princess extends Entity {

	public NPC_Princess(GamePanel gp) {
		super(gp);
		
		direction = "left";
		speed = 0;
		
		getImage();
		setDialogue();
	}

	public void getImage() {

        up1 = setup("/npc/princess",gp.tileSize,gp.tileSize);
        up2 = setup("/npc/princess",gp.tileSize,gp.tileSize);
        down1 = setup("/npc/princess",gp.tileSize,gp.tileSize);
        down2 = setup("/npc/princess",gp.tileSize,gp.tileSize);
        left1 = setup("/npc/princess",gp.tileSize,gp.tileSize);
        left2 = setup("/npc/princess",gp.tileSize,gp.tileSize);
        right1 = setup("/npc/princess",gp.tileSize,gp.tileSize);
        right2 = setup("/npc/princess",gp.tileSize,gp.tileSize);
    }
	
	public void setDialogue() {
		
		dialogues[0] = "...\nWho are you?!\nAre you an adventurer?";
		dialogues[1] = "Come to save me, Help from whom?";
		dialogues[2] = "Demon Lord didn't kidnap me.";
		dialogues[3] = "He saved me from demonic beasts.";
		dialogues[4] = "and stay here until the carriage is repaired.";
		dialogues[5] = "Today the carriage was completely repaired.\nThen I can go back with you.";
	}
	
	public void speak() {
		
		super.speak();
	}
}
