package data;

import java.io.Serializable;
import java.util.ArrayList;

public class DataStorage implements Serializable {

	//PLAYER START
	int maxLife;
	int life;
	int coin;
	
	// PLAYER INVENTORY
	ArrayList<String> itemNames = new ArrayList<>();
	int currentWeaponSlot;
	int currentShieldSlot;
	int currentArmorSlot;
	
	//OBJECT ON MAP
	String mapObjectNames[];
	int mapObjectWorldX[];
	int mapObjectWorldY[];
}
