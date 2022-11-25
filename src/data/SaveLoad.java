package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Armor;
import object.OBJ_Door;
import object.OBJ_Heart;
import object.OBJ_Key;
import object.OBJ_Potion;
import object.OBJ_Shield_Iron;
import object.OBJ_Shield_Wood;
import object.OBJ_Sword_Normal;

public class SaveLoad {

	GamePanel gp;
	
	public SaveLoad(GamePanel gp) {
		
		this.gp = gp;
	}
	public Entity getObject(String itemName) {
		
		Entity obj = null;
		
		switch(itemName) {
		case "Iron Shield": obj = new OBJ_Shield_Iron(gp); break;
		case "Key": obj = new OBJ_Key(gp); break;
		case "Potion": obj = new OBJ_Potion(gp); break;
		case "Door": obj = new OBJ_Door(gp); break;
		case "Heart": obj = new OBJ_Heart(gp); break;
		case "Wood Shield":  obj = new OBJ_Shield_Wood(gp); break;
		case "Normal Sword":  obj = new OBJ_Sword_Normal(gp); break;
		case "Iron Armor":  obj = new OBJ_Armor(gp); break;
		}
		return obj;
	}
	public void save() {
		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));
			
			DataStorage ds = new DataStorage();
			
			ds.maxLife = gp.player.maxLife;
			ds.life = gp.player.life;
			ds.coin = gp.player.coin;
			
			//PLAYER INVENTORY
			for(int i=0;i<gp.player.inventory.size();i++) {
				ds.itemNames.add(gp.player.inventory.get(i).name);
			}
			//PLAYER EQUIPMENT
			ds.currentWeaponSlot = gp.player.getCurrentWeaponSlot();
			ds.currentShieldSlot = gp.player.getCurrentShieldSlot();
			ds.currentArmorSlot = gp.player.getCurrentArmorSlot();
			
			//OBJECT ON MAP
			ds.mapObjectNames = new String[gp.obj.length];
			ds.mapObjectWorldX = new int[gp.obj.length];
			ds.mapObjectWorldY = new int[gp.obj.length];
			
			for(int i = 0;i < gp.obj.length; i++) {
				
				if(gp.obj[i] == null) {
					ds.mapObjectNames[i] = "NA";
				}
				else {
					ds.mapObjectNames[i] = gp.obj[i].name;
					ds.mapObjectWorldX[i] = gp.obj[i].worldX;
					ds.mapObjectWorldY[i] = gp.obj[i].worldY;
				}
			}
			
			//Write the DataStorage object
			oos.writeObject(ds);
		}
		catch(Exception e){
			System.out.println("Save Exception!");
		}
	}
	public void load() {
		
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.dat")));
			
			//Read the DataStorage object
			DataStorage ds = (DataStorage)ois.readObject();
			
			gp.player.maxLife = ds.maxLife;
			gp.player.life = ds.life;
			gp.player.coin = ds.coin;
			
			//PLAYER INVENTORY
			gp.player.inventory.clear();
			for(int i=0;i<ds.itemNames.size();i++) {
				gp.player.inventory.add(getObject(ds.itemNames.get(i)));
			}
			//PLAYER REQUIPMENT
			gp.player.currentWeapon = gp.player.inventory.get(ds.currentWeaponSlot);
			gp.player.currentShield = gp.player.inventory.get(ds.currentShieldSlot);
			gp.player.currentArmor = gp.player.inventory.get(ds.currentArmorSlot);
			gp.player.getAttack();
			gp.player.getDefense();
			gp.player.getPlayerAttackImage();
			
			//OBJECT ON MAP
			for(int i = 0;i < gp.obj.length; i++) {
				
				if(ds.mapObjectNames[i].equals("NA")) {
					gp.obj[i] = null;
				}
				else {
					gp.obj[i] = getObject(ds.mapObjectNames[i]);
					gp.obj[i].worldX = ds.mapObjectWorldX[i];
					gp.obj[i].worldY = ds.mapObjectWorldY[i];
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Load Exception!");
		}
	}
}
