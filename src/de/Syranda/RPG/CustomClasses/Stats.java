package de.Syranda.RPG.CustomClasses;

public class Stats {
	
	private int maxHealth;
	private int currentHealth;
	private int maxStamina;
	private int currentStamina;
	
	private int luk;
	private int vit;
	private int str;
	private int armor;
	
	private int level;
	private int exp;
	private int expToLvl;
	
	public Stats(int maxHealth, int currentHealth, int maxStamina, int currentStamina, int luk, int vit, int str, int armor, int level, int exp, int expToLvl) {

		this.maxHealth = maxHealth;
		this.currentHealth = currentHealth;
		this.maxStamina = maxStamina;
		this.currentStamina = currentStamina;
		this.luk = luk;
		this.vit = vit;
		this.str = str;
		this.armor = armor;
		this.level = level;
		this.exp = exp;
		this.expToLvl = expToLvl;
		
	}
	
	public int getMaxHealth() {
		
		return this.maxHealth;
		
	}
	
	public int getCurrenHealth() {
		
		return this.currentHealth;
		
	}
	
	public int getMaxStamina() {
		
		return this.maxStamina;
		
	}
	
	public int getCurrentStamina() {
		
		return this.currentStamina;
		
	}
	
	public int getLuk() {
		
		return this.luk;
		
	}
	
	public int getVit() {
		
		return this.vit;
		
	}
	
	public int getStr() {
		
		return this.str;
		
	}
	
	public int getArmor() {
		
		return this.armor;
		
	}
	
	public int getLevel() {
		
		return this.level;
		
	}
	
	public int getExp() {
		
		return this.exp;
		
	}
	
	public void setMaxHealth(int health) {
		
		this.maxHealth = health;
		
	}
	
	public void setCurrentHealth(int health) {
		
		this.currentHealth = health;
		
	}
	
	public void setMaxStamina(int stamina) {
		
		this.maxStamina = stamina;
		
	}
	
	public void setCurrentStamina(int stamina) {
		
		this.currentStamina = stamina;
		
	}
	
	public void setLuk(int luk) {
		
		this.luk = luk;
		
	}
	
	public void setVit(int vit) {
		
		this.vit = vit;
		
	}
	
	public void setStr(int str) {
		
		this.str = str;
		
	}
	
	public void setArmor(int armor) {
		
		this.armor = armor;
		
	}
	
	public void setLevel(int level) {
		
		this.level = level;
		
	}
	
	public void setExp(int exp) {
		
		this.exp = exp;
		
	}
	
	public int getExpToLvl() {
		
		return this.expToLvl;
		
	}
	
	public void setExpToLvl(int expToLvl) {
		
		this.expToLvl = expToLvl;
		
	}
	
}
