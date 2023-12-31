package Character;

import java.util.ArrayList;
import java.util.List;

import Game_pack.IsMe;
import Game_pack.Lookable;
import Item.Item;

public abstract class Character implements Lookable, IsMe{
    private List<Item> items;
    
    /**
     * creates a character with items
     */
    public Character(List<Item> items){
        this.items = items;
    }
    /**
     * creates a character with no items
     */
    public Character(){
        this(null);
    }

    public List<Item> getItems() {
        return items;
    }
    public Item getItem(String s) throws NullPointerException{
        for (Item i : this.getItems()) {
            if (i.isMe(s)){
                return i;
            }
        }
        throw new NullPointerException();
    }

    public void addItem(Item item){
        if (this.items == null){
            this.items = new ArrayList<Item>();
        }
        this.items.add(item);
    }
    public void remove(Item item) {
    	if (this.items == null) {return;}
    	this.items.remove(item);
    }
    public boolean haveItem(Item i) {
    	int pos = this.items.indexOf(i);
    	if(pos == -1) {return false;}
    	else {return true;}
    }

    public void printItems(){
        String description = "the "+ this +" have : \n";
        if (this.items == null){
            description += "\tno item";
        }
        else{
            for(Item item : this.items){
                description += "\t"+item;
            }
        }
        System.out.println(description);
    }
    public abstract String getDescription();
    @Override
    public void look() {
        String dest = this.getDescription() + "\n\t";
        if (this.items != null) {
        	for (Item i : this.items) {dest += "with "+ i+ "\n\t";}
        }
        System.out.println(dest);
    }
}
