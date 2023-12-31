
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Character.*;

import Location.LocationName;
import Location.Location;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import Item.*;


public class CharacterTest {
    private static List<WhoFight> characters;
    private static Hero h;

    // Initializing variables before all tests
    @BeforeClass
    public static void setup() {        
        Location l = new Location(LocationName.GARDEN);

        h = new Hero(l);
        characters = new ArrayList<WhoFight>();
        characters.add(new Knight());
        characters.add(new Dragon());
        characters.add(new Gargolyle());
        characters.add(new Guard());

    }

    
    @Before
    public void shuffleList() {
        System.out.println("Shuffle list...");
        // before each test, we shuffle the list to be sure that each test work with all WhoFight characters
        Collections.shuffle(characters); 
    }

    
    @Test
    public void testAttack() {
        WhoFight c1 = characters.get(0);
        c1.fight(h);

        assertEquals(h.getHp(), (Hero.DEFAULT_HP + h.getArmor()) - c1.getDamage());
    }

    @Test
    public void testAddItem() {
        Treasure t = new Treasure();
        WhoFight c1 = characters.get(0);
        
        int size_before_add = c1.getItems().size();
        c1.addItem(t);
        int size_after_add = c1.getItems().size();

        assertEquals(size_before_add+1, size_after_add);
        assertTrue(c1.getItems().contains(t));

    }

    @Test
    public void testRemoveItem() {
        Treasure t = new Treasure();
        WhoFight c1 = characters.get(0);
        c1.addItem(t);
        
        int size_before_remove = c1.getItems().size();
        c1.remove(t);
        int size_after_remove = c1.getItems().size();

        assertEquals(size_before_remove-1, size_after_remove);
        assertFalse(c1.getItems().contains(t));
    }

    @Test
    public void testHaveItem() {
        Treasure t = new Treasure();
        WhoFight c1 = characters.get(0);
        c1.addItem(t);

        assertTrue(c1.haveItem(t));
    }

}
