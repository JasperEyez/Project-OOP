package entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.JobAttributes.DefaultSelectionType;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.GamePanel;
import main.KeyHandler;
import object.OBJ_Armor;
import object.OBJ_Key;
import object.OBJ_Shield_Wood;
import object.OBJ_Sword_Normal;

public final class Player extends Entity{

    KeyHandler keyH;
    
    public final int screenX;
    public final int screenY;
    public boolean attackCanceled = false;
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int maxinventorySize = 20;
    int count = 0;
    
    public Player(GamePanel gp, KeyHandler keyH) {
    	
    	super(gp);
        this.keyH = keyH;
        
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);
        
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        
        //ATTACK AREA
//        attackArea.width = 36;
//        attackArea.height = 25;
        
        setDefaultValues();
    }
    public void setDefaultValues() {
    	
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";
        
        //PLAYER STATUS
        maxLife = 6;
        life = maxLife;
        coin = 0;
        currentWeapon = new OBJ_Sword_Normal(gp);
        currentShield = new OBJ_Shield_Wood(gp);
        currentArmor = new OBJ_Armor(gp);
        attack = getAttack();
        defense = getDefense();
        
        getPlayerImage();
        getPlayerAttackImage();
        setItem();
    }
    public void setDefaultPosition() {
    	
    	worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        direction = "down";
    }
    public void restoreStatus() {
    	life = maxLife;
    	invincible = false;
    	attacking = false;
    }
    public void setItem() {
    	
    	inventory.clear();
    	inventory.add(currentWeapon);
    	inventory.add(currentShield);
    	inventory.add(currentArmor);
    	inventory.add(new OBJ_Key(gp));
    }
    public int getAttack() {
    	attackArea = currentWeapon.attackArea;
    	return attack = currentWeapon.attackValue;
    }
	public int getDefense() {
	    return defense = currentShield.defenseValue + currentArmor.defenseValue;
	}
	public int getCurrentWeaponSlot() {
		int currentWeaponSlot = 0;
		for(int i=0;i<inventory.size();i++) {
			if(inventory.get(i) == currentWeapon) {
				currentWeaponSlot = i;
			}
		}
		return currentWeaponSlot;
	}
	public int getCurrentShieldSlot() {
		int currentShieldSlot = 0;
		for(int i=0;i<inventory.size();i++) {
			if(inventory.get(i) == currentShield) {
				currentShieldSlot = i;
			}
		}
		return currentShieldSlot;
	}
	public int getCurrentArmorSlot() {
		int currentArmorSlot = 0;
		for(int i=0;i<inventory.size();i++) {
			if(inventory.get(i) == currentArmor) {
				currentArmorSlot = i;
			}
		}
		return currentArmorSlot;
	}
    public void getPlayerImage() {
            
        up1 = setup("/player/up1",gp.tileSize,gp.tileSize);
        up2 = setup("/player/up2",gp.tileSize,gp.tileSize);
        down1 = setup("/player/down1",gp.tileSize,gp.tileSize);
        down2 = setup("/player/down2",gp.tileSize,gp.tileSize);
        left1 = setup("/player/left1",gp.tileSize,gp.tileSize);
        left2 = setup("/player/left2",gp.tileSize,gp.tileSize);
        right1 = setup("/player/right1",gp.tileSize,gp.tileSize);
        right2 = setup("/player/right2",gp.tileSize,gp.tileSize);
    }
    public void getPlayerAttackImage() {
    	
    	attackUp1 = setup("/player/attack_up1",gp.tileSize,gp.tileSize*2);
    	attackUp2 = setup("/player/attack_up2",gp.tileSize,gp.tileSize*2);
    	attackDown1 = setup("/player/attack_down1",gp.tileSize,gp.tileSize*2);
    	attackDown2 = setup("/player/attack_down2",gp.tileSize,gp.tileSize*2);
    	attackLeft1 = setup("/player/attack_left1",gp.tileSize*2,gp.tileSize);
    	attackLeft2 = setup("/player/attack_left2",gp.tileSize*2,gp.tileSize);
    	attackRight1 = setup("/player/attack_right1",gp.tileSize*2,gp.tileSize);
    	attackRight2 = setup("/player/attack_right2",gp.tileSize*2,gp.tileSize);
    }
    public void update() {
    	
    	if(attacking == true) {
    		attacking();
    	} 	
    	else if(keyH.upPressed == true || keyH.downPressed == true ||
    			keyH.leftPressed == true || keyH.rightPressed == true || keyH.enterPressed == true) {
	        if(keyH.upPressed == true) {
	            direction = "up";
	        }
	        else if(keyH.downPressed == true) {
	            direction = "down";
	        }
	        else if(keyH.leftPressed == true) {
	            direction = "left";
	        }
	        else if(keyH.rightPressed == true) {
	            direction = "right";
	        }
	        
	        //CHECK TILE COLLISION
	        collisionOn = false;
	        gp.cChecker.checkTile(this);
	        
	        //CHECK OBJECT COLLISION
	        int objIndex = gp.cChecker.checkObject(this, true);
	        pickUpObject(objIndex);
	        
	        //CHECK NPC COLLISION
	        int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
	        interactNPC(npcIndex);
	        
	        //CHECK MONSTER COLLISION
	        int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
	        contactMonster(monsterIndex);
	        
	        //CHECK EVENT
	        gp.eHandler.checkEvent();
	        
	        //IF COLLISION IS FALSE, PLAYER CAN MOVE
	        if(collisionOn == false && keyH.enterPressed == false) {
	        	switch(direction) {
	        	case "up": worldY -= speed; break;
	        	case "down": worldY += speed; break;
	        	case "left": worldX -= speed; break;
	        	case "right": worldX += speed; break;
	        	}
	        }
	        
	        if(keyH.enterPressed == true && attackCanceled == false) {
	        	gp.playSE(7);
	        	attacking = true;
	        	spriteCounter = 0;
	        }
	        
	        attackCanceled = false;
	        gp.keyH.enterPressed = false;
	        
	        spriteCounter++;
	        if(spriteCounter > 10) {
	        	if(spriteNum == 1) {
	        		spriteNum = 2;
	        	}
	        	else if(spriteNum == 2) {
	        		spriteNum = 1;
	        	}
	        	spriteCounter = 0;
	        }
    	}
    	
    	// This needs to be outside of key if statement!
    	if(invincible == true) {
    		invincibleCounter++;
    		if(invincibleCounter > 60) {
    			invincible = false;
    			invincibleCounter = 0;
    		}
    	}
    	if(life <= 0) {
    		gp.gameState = gp.gameOverState;
    		gp.ui.commandNum = -1;
    		gp.stopMusic();
    		gp.playSE(10);
    	}
    }
    public void attacking() {
    	
    	spriteCounter++;
    	
    	if(spriteCounter <= 5) {
    		spriteNum = 1;
    	}
    	if(spriteCounter > 5 && spriteCounter <= 25) {
    		spriteNum = 2;
    		
    		//Save the current worldX, worldY, solidArea
    		int currentWorldX = worldX;
    		int currentWorldY = worldY;
    		int solidAreaWidth = solidArea.width;
    		int solidAreaHeight = solidArea.height;
    		
    		//Adjust player's worldX/Y for the attackArea
    		switch(direction) {
    		case "up": worldY -= attackArea.height; break;
    		case "down": worldY += attackArea.height; break;
    		case "left": worldX -= attackArea.width; break;
    		case "right": worldX += attackArea.width; break;
    		}
    		//attackArea becomes solidArea
    		solidArea.width = attackArea.width;
    		solidArea.height = attackArea.height;
    		//Check monster collision with the update worldX, worldY and solidArea
    		int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
    		damageMonster(monsterIndex);
    		
    		
    		//After checking collision, resort the original data
    		worldX = currentWorldX;
    		worldY = currentWorldY;
    		solidArea.width = solidAreaWidth;
    		solidArea.height = solidAreaHeight;
    		
    	}
    	if(spriteCounter > 25) {
    		spriteNum = 1;
    		spriteCounter = 0;
    		attacking = false;
    	}
    }
    public void pickUpObject(int i) {
    	
    	if(i != 999) {
    		//PICKUP ONLY ITEM
    		if(gp.obj[i].type == type_pickupOnly) {
    			gp.obj[i].use(this);
    			gp.obj[i] = null;
    		}
    		//OBSTACLE
    		else if(gp.obj[i].type == type_obstacle) {
    			if(keyH.enterPressed == true) {
    				attackCanceled = true;
    				gp.obj[i].interact();
    			}
    		}
    		//INVENTORY ITEM
    		else {
    			String text;
    		
	    		if(inventory.size() != maxinventorySize) {
	    			inventory.add(gp.obj[i]);
	    			gp.playSE(2);
	    			text = "Got a " + gp.obj[i].name + "!";
	    		}
	    		else
	    		{
	    			text = "Your cannot carry any more!";
	    		}
	    		gp.ui.addMessage(text);
	    		gp.obj[i] = null;
    		}
    	}
    }
    public void interactNPC(int i) {
    	
    	if(gp.keyH.enterPressed == true) {
    		if(i != 999) {
    			attackCanceled = true;
	    		gp.gameState = gp.dialogueState;
	    		if(gp.npc[i] != gp.npc[1]) {
	    			gp.npc[i].speak();
	    		}
	    		if(gp.npc[i] == gp.npc[1]) {
	    			if(count < 6) {
	    				gp.npc[i].speak();
	    				count++;
	    			}
	    			else {
	    				gp.gameState = gp.winState;
		    			gp.stopMusic();
		    			gp.playSE(4);
	    			}
	    		}
	    	}
    	}
    	
    	
    }
    public void contactMonster(int i) {
    	
    	if(i != 999) {
    		if(invincible == false && gp.monster[i].dying == false) {
    			
    			gp.playSE(6);
    			
    			int damage = gp.monster[i].attack - defense;
    			if(damage < 0) {
    				damage = 0;
    			}
    			life -= damage;
    			invincible = true;
    		}
    	}
    }
    public void damageMonster(int i) {
    	
    	if(i != 999) {
    		if(gp.monster[i].invincible == false) {
    			
    			gp.playSE(5);
    			
    			int damage = attack - gp.monster[i].defense;
    			if(damage < 0) {
    				damage = 0;
    			}
    			gp.monster[i].life -= damage;
    			gp.ui.addMessage(damage + " damage!");
    			
    			gp.monster[i].invincible = true;
    			gp.monster[i].damageReaction();
    			
    			if(gp.monster[i].life <= 0) {
    				gp.monster[i].dying = true;
    				gp.ui.addMessage("killed the " + gp.monster[i].name + "!");
    				gp.ui.addMessage("Coin + " + gp.monster[i].coin + "!");
    				coin += gp.monster[i].coin;
    			}
    		}
    	}
    }
    public void selectItem() {
    	int itemIndex = gp.ui.getItemIndexOnSlot();
    	
    	if(itemIndex < inventory.size()) {
    		
    		Entity selectedItem = inventory.get(itemIndex);
    		
    		if(selectedItem.type == type_sword) {
    			
    			currentWeapon = selectedItem;
    			attack = getAttack();
    		}
    		if(selectedItem.type == type_shield) {
    			
    			currentShield = selectedItem;
    			defense = getDefense();
    		}
    		if(selectedItem.type == type_armor) {
    			
    			defense = getDefense();
    		}
    		if(selectedItem.type == type_consumable) {
    			
    			if(selectedItem.use(this) == true) {
    				inventory.remove(itemIndex);
    			}
    		}
    	}
    }
    public void draw(Graphics2D g2) {
        
          BufferedImage image = null;
          int tempScreenX = screenX;
          int tempScreenY = screenY;
          
          switch(direction) {
              case "up":
            	  if(attacking == false) {
            		  if(spriteNum == 1) {image = up1;}
            		  if(spriteNum == 2) {image = up2;}
            	  }
            	  if(attacking == true) {
            		  tempScreenY = screenY - gp.tileSize;
            		  if(spriteNum == 1) {image = attackUp1;}
            		  if(spriteNum == 2) {image = attackUp2;}
            	  }
                  break;
              case "down":
            	  if(attacking == false) {
            		  if(spriteNum == 1) {image = down1;}
            		  if(spriteNum == 2) {image = down2;}
            	  }
            	  if(attacking == true) {
            		  if(spriteNum == 1) {image = attackDown1;}
            		  if(spriteNum == 2) {image = attackDown2;}
            	  }
                  break;
              case "left":
            	  if(attacking == false) {
            		  if(spriteNum == 1) {image = left1;}
            		  if(spriteNum == 2) {image = left2;}
            	  }
            	  if(attacking == true) {
            		  tempScreenX = screenX - gp.tileSize;
            		  if(spriteNum == 1) {image = attackLeft1;}
            		  if(spriteNum == 2) {image = attackLeft2;}
            	  }
                  break;
              case "right":
            	  if(attacking == false) {
            		  if(spriteNum == 1) {image = right1;}
            		  if(spriteNum == 2) {image = right2;}
            	  }
            	  if(attacking == true) {
            		  if(spriteNum == 1) {image = attackRight1;}
            		  if(spriteNum == 2) {image = attackRight2;}
            	  }
                  break;
          }
          
          if(invincible == true) {
        	  g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));	//จางๆ
          }
          g2.drawImage(image, tempScreenX, tempScreenY, null);
          
          //Reset alpha
          g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
          
          //DEBUG
//          g2.setFont(new Font("Arial", Font.PLAIN, 26));
//          g2.setColor(Color.white);
//          g2.drawString("Invincible:"+invincibleCounter, 10, 400);
    }
}
