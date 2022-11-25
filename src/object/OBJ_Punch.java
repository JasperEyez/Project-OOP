package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Punch extends Entity {
	public OBJ_Punch(GamePanel gp) {
		super(gp);
		
		name = "Punch";
		down1 = setup("/objects/punch", gp.tileSize, gp.tileSize);
		attackValue = 1;
		solidArea.width = 10;
		solidArea.height = 10;
		description = "[" + name + "]\nYour own hand";
	}
}
