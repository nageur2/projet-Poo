package Location;


import Item.Item;
import Item.key;

public class Cupboard extends DecorObjet{
	public Cupboard() {
		super((Item)new key());
	}
    @Override
    public boolean isMe(String s){
    	switch(s) {
    	case "Cupboard": 
    		return true;
    	default : 
    		return false;
    	}
    }
    public String getDescription() {
    	return "A dusty cupboard, nestled in the corner, bore the weight of time.";
    }
    public String toString() {
    	return "Cupboard";
    }
}
