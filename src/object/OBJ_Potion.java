package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Potion extends Entity {
	
	GamePanel gp;
	int value = 5;
	
	public OBJ_Potion(GamePanel gp) {
		super(gp);
		
		this.gp = gp;

		type = type_consumable;
		name = "Potion";
		down1 = setup("/objects/potion", gp.tileSize, gp.tileSize);
		description = "[" + name + "]\nHeals your life by" + value + ".";
	}
	public boolean use(Entity entity) {
		
		gp.gameState = gp.dialogueState;
		gp.ui.currentDialogue = "You drink the " + name + "!\n"
				+ "Your life has been recovered by " + value + ".";
		entity.life += value;
		if(gp.player.life > gp.player.maxLife) {
			gp.player.life = gp.player.maxLife;
		}
		gp.playSE(8);
		return true;
	}
}
