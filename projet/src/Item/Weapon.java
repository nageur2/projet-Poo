package Item;

import Character.Hero;

public abstract class Weapon extends Item{
	private final int DAMAGE;
    
    public Weapon(int dmg) {
    	this.DAMAGE = dmg;
    }
    public int getDamage() {
    	return this.DAMAGE;
    }
	@Override
	public void use(Hero h) {
		System.out.println("the hero equip " + this + "\n");
		h.setWeapon(this);
		h.remove(this);
	}
}
