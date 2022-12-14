package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Armor extends Entity{
	
	public OBJ_Armor(GamePanel gp) {
		super(gp);

		type = type_armor;
		name = "Iron Armor";
		down1 = setup("/objects/armor", gp.tileSize, gp.tileSize);
		defenseValue = 2;
		description = "[" + name + "]\nAn old iron armor";
	}

}
