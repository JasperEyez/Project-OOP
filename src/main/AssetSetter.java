package main;

import entity.NPC_DemonLord;
import entity.NPC_Prince;
import entity.NPC_Princess;
import monster.MON_GreenSlime;
import object.OBJ_Door;
import object.OBJ_Key;
import object.OBJ_Potion;
import object.OBJ_Shield_Iron;

public class AssetSetter {

	GamePanel gp;
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		
		int i = 0;
		
		gp.obj[i] = new OBJ_Key(gp);
		gp.obj[i].worldX = gp.tileSize*37;
		gp.obj[i].worldY = gp.tileSize*43;
		i++;
		gp.obj[i] = new OBJ_Key(gp);
		gp.obj[i].worldX = gp.tileSize*33;
		gp.obj[i].worldY = gp.tileSize*7;
		i++;
		gp.obj[i] = new OBJ_Key(gp);
		gp.obj[i].worldX = gp.tileSize*20;
		gp.obj[i].worldY = gp.tileSize*42;
		i++;
		gp.obj[i] = new OBJ_Shield_Iron(gp);
		gp.obj[i].worldX = gp.tileSize*33;
		gp.obj[i].worldY = gp.tileSize*21;
		i++;
		gp.obj[i] = new OBJ_Potion(gp);
		gp.obj[i].worldX = gp.tileSize*23;
		gp.obj[i].worldY = gp.tileSize*7;
		i++;
		gp.obj[i] = new OBJ_Potion(gp);
		gp.obj[i].worldX = gp.tileSize*14;
		gp.obj[i].worldY = gp.tileSize*25;
		i++;
		gp.obj[i] = new OBJ_Door(gp);
		gp.obj[i].worldX = gp.tileSize*14;
		gp.obj[i].worldY = gp.tileSize*28;
		i++;
		gp.obj[i] = new OBJ_Door(gp);
		gp.obj[i].worldX = gp.tileSize*10;
		gp.obj[i].worldY = gp.tileSize*13;
		i++;
		gp.obj[i] = new OBJ_Door(gp);
		gp.obj[i].worldX = gp.tileSize*12;
		gp.obj[i].worldY = gp.tileSize*23;
		i++;
		gp.obj[i] = new OBJ_Door(gp);
		gp.obj[i].worldX = gp.tileSize*29;
		gp.obj[i].worldY = gp.tileSize*40;
		i++;
	}
	public void setNPC() {
		
		int i = 0;
		
		gp.npc[i] = new NPC_Prince(gp);
		gp.npc[i].worldX = gp.tileSize*21;
		gp.npc[i].worldY = gp.tileSize*21;
		i++;
		gp.npc[i] = new NPC_Princess(gp);
		gp.npc[i].worldX = gp.tileSize*12;
		gp.npc[i].worldY = gp.tileSize*10;
		i++;
		gp.npc[i] = new NPC_DemonLord(gp);
		gp.npc[i].worldX = gp.tileSize*12;
		gp.npc[i].worldY = gp.tileSize*12;
		i++;
	}
	public void setMonster() {

		int i = 0;
		
		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize*23;
		gp.monster[i].worldY = gp.tileSize*36;
		i++;
		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize*23;
		gp.monster[i].worldY = gp.tileSize*37;
		i++;
		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize*35;
		gp.monster[i].worldY = gp.tileSize*36;
		i++;
		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize*35;
		gp.monster[i].worldY = gp.tileSize*37;
		i++;
		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize*35;
		gp.monster[i].worldY = gp.tileSize*10;
		i++;
		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize*37;
		gp.monster[i].worldY = gp.tileSize*12;
		i++;
		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize*10;
		gp.monster[i].worldY = gp.tileSize*32;
		i++;
		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize*13;
		gp.monster[i].worldY = gp.tileSize*33;
		i++;
	}
}
