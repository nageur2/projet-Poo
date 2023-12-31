package Character;

import java.util.List;

import Item.Armor;
import Item.Item;
import Item.Shield;
import Item.Weapon;

public abstract class WhoFight extends Character{
    private static final int MAX_HP = 15000;
    private int hp;
    /**
     * some of shield, armor and start_armor
     */
    private int armor_total;
    private int damage;

    private Weapon weapon;
    private Armor armor;
    private Shield shield;

    public WhoFight(int hp, int armor_start, int damage_start, Weapon weapon, Armor armor, Shield shield, List<Item> items){
        super(items);
        this.hp = hp;
        this.armor_total = armor_start;
        this.damage = damage_start;
        this.weapon = weapon;
        this.armor = armor;
        this.shield = shield;
        if(this.armor != null){
            this.armor_total += this.armor.getArmor();
        }
        if(this.shield != null){
            this.armor_total += this.shield.getProtection();
        }
        if(this.weapon != null){
            this.damage += this.weapon.getDamage();
        }
    }
    public WhoFight(int hp, int armor_start, int damage_start){
        this(hp, armor_start, damage_start, null, null, null,null);
    }
    public WhoFight(int hp, int armor_start, int damage_start,List<Item> items){
        this(hp, armor_start, damage_start, null, null, null,items);
    }


    /**
     * @return if the character is dead or not
     */
    public boolean isDead(){
        return this.hp <= 0;
    }
    /**
     * reduces the hp of the enemy in function of the damage
     * @param enemy
     */
    public void fight(WhoFight enemy) {
    	enemy.reduceHp(this.getDamage());
    }

    /**
     * this will reduces hp by damage - enemy.armor_total
     * @param damage is the damage of the character who attack
     */
    public void reduceHp(int damage){
        this.hp +=  -damage + this.armor_total;
    }
    public int getDamage() {
        return this.damage;
    }

     public int getHp() {
        return this.hp;
    }
    public int getArmor() {
        return this.armor_total;
    }
    public void setArmor(Armor armor) {
        try{this.armor_total -= this.armor.getArmor();}
        catch(NullPointerException e){}
    	this.armor_total += armor.getArmor();
    	this.armor = armor;
    }
    public void setShield(Shield shield) {
        try{this.armor_total -= this.shield.getProtection();}
        catch(NullPointerException e){}
    	this.armor_total += shield.getProtection();
    	this.shield = shield;
    }
    public void setWeapon(Weapon weapon) {
        try{this.damage -= this.weapon.getDamage();}
        catch(NullPointerException e){}
    	this.damage += weapon.getDamage();
    	this.weapon = weapon;
    }
    @Override
    public void look() {
    	String dest = this.getDescription()+"\n\t";
    	dest += "hp : " + this.getHp() + "\n\t";
    	dest += "armor : " + this.getArmor() + "\n\t";
    	dest += "damage : " + this.getDamage() + "\n";
    	dest += "the "+ this.toString() + " have equip :\n\t";
    	if (this.armor != null) {
    		dest += this.armor + "\n\t";
    	}
    	if (this.shield != null) {
    		dest += this.shield + "\n\t";
    	}
    	if (this.weapon != null) {
    		dest += this.weapon + "\n\t";
    	}
    	System.out.println(dest);
    	this.printItems();
    	
    }
    /**
     * increases hp from WhoFight by i up to MAX_HP
     * @param i number of hp restores
     */
    public void addHp(int i){
        this.hp += i;
        if (this.hp > WhoFight.MAX_HP){this.hp = WhoFight.MAX_HP;}
    }

}
