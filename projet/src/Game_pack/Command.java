package Game_pack;

import java.util.ArrayList;
import java.lang.NullPointerException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import Location.Location;
import Location.LocationName;
import Location.DecorObjet;

import Character.Character;
import Character.WhoFight;
import Item.Item;

public class Command {
	public final Game GAME;
	
	public Command(Game game){
		this.GAME = game;
	}
   
	public boolean read(Scanner scanner){
		String command = "";
		List<String> argv = new ArrayList<String>();
        try{
            
            System.out.print( "put your command: " );
            if (scanner.hasNext()) {command = scanner.next("GO|HELP|LOOK|ATTACK|TAKE|USE|QUIT|");}            
            
            switch(command) {
            	case "GO" :
					if(scanner.hasNext()){argv.add(scanner.next());}
            		LocationName location = LocationName.stringToLocationName(argv.get(0)); 
					this.go(location);
					break;
				case "HELP":
					this.help();
					break;
				case "LOOK":
					argv.add(scanner.nextLine());
					this.look(argv.get(0));
					break;
				case "ATTACK":
					if(scanner.hasNext()){argv.add(scanner.next());}
					this.attack(argv.get(0));
					break;
				case "TAKE":
					if(scanner.hasNext()){argv.add(scanner.next());}
					this.take(argv.get(0));
					break;
				case "USE":
					if(scanner.hasNext()){argv.add(scanner.next());}
					this.use(argv.get(0));
					break;
				case "QUIT":
					return false;
			}
        }catch(NoSuchElementException e){
			System.out.println("List of command");
			this.help();
			if(scanner.hasNext())scanner.next(); //avoid infinity loop
		}
		catch(Exception e){System.out.println("An error was occured : " + e+ "\n\t"+ e.getMessage());}
        return true;
    }
	
	public void go(LocationName location) {
		Location locationH = this.GAME.HERO.getLocation();
		Location lOut = locationH.getNeighbor(location);
		this.GAME.HERO.goLocation(lOut);
	}

	public void help() {
		System.out.println("Command :\n\tGO arg1\n\tLOOK [arg1]\n\tATTACK arg1\n\tTAKE arg1 \n\tUSE arg1 \n\tQUIT");
	}
	public void look(String s){
		if (s.equals("")) {this.look(); return;}
		String[]argv = s.split("\\s+"); //delete whitespace character
		s = argv[1];
		if(s.equals("HERO")) {this.GAME.HERO.look();}
		Location locationH = this.GAME.HERO.getLocation();
		try {
			List<DecorObjet> decorObjects = locationH.getDecorObject();
			for(DecorObjet obj : decorObjects) {
				if (obj.isMe(s)) {
					obj.look();
				}
			}
		}catch(NullPointerException e) {}
		try {
			List<Character> characters = locationH.getCharacters();
			for(Character c : characters) {
				if (c instanceof Lookable) {
					if ( c.isMe(s)) {
						((Lookable) c).look();
					}
				}
			}
		}catch(NullPointerException e) {}
	}
	public void look(){
		Location locationH = this.GAME.HERO.getLocation();
		locationH.look();
	}
	
	public void attack(String s) {
		Location locationH = this.GAME.HERO.getLocation();
		List<Character> characters = locationH.getCharacters();
		try {
			for(Character c : characters) {
				if ( c.isMe(s)) {
					if (c instanceof WhoFight) {
						this.GAME.HERO.fight((WhoFight)c);
					}
				}
			}
		}
		catch(NullPointerException e) {}
	}
	public void take(String s) {
		Location locationH = this.GAME.HERO.getLocation();
		try {
			List<DecorObjet> decorObjects = locationH.getDecorObject();
			DecorObjet objFound = null;
			for(DecorObjet obj : decorObjects) {
				if (obj.isMe(s)) {
					Item i = obj.take();
					this.GAME.HERO.addItem(i);
					System.out.println("The HERO take the "+ i);
					objFound = obj;
				}
			}
			locationH.removeDecorObjet(objFound);
		}catch(NullPointerException e) {}
	}
	public void use(String s) {
		for (Item i : this.GAME.HERO.getItems()) {
			if (i.isMe(s) && i instanceof Useable) {
					i.use(this.GAME.HERO);
			}
		}
	}
}
