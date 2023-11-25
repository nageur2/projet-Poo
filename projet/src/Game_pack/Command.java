package Game_pack;

import java.util.ArrayList;
import java.lang.NullPointerException;

import java.util.List;
import java.util.Scanner;

import Location.Location;
import Location.LocationName;
import Location.DecorObjet;

import Character.Character;
import Character.WhoFight;

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
					break;
				case "USE":
					break;
				case "QUIT":
					return false;
			}
        }catch(Exception e){
			System.out.println("List of command\n");
			this.help();
			if(scanner.hasNext())scanner.next();
		}
        return true;
    }
	
	public void go(LocationName location) {
		Location locationH = this.GAME.HERO.getLocation();
		Location lOut = locationH.getNeighbor(location);
		this.GAME.HERO.goLocation(lOut);
	}

	public void help() {
		System.out.println("Command :\n\tGO location\n\tLOOK \n\tATTACK  \n\tTAKE  \n\tUSE  \n\tQUIT");
	}
	public void look(String s){
		if (s == "") {this.look();}
		String[]argv = s.split("\\s+"); //delete whitespace character
		s = argv[0];
		Location locationH = this.GAME.HERO.getLocation();
		try {
			List<DecorObjet> decorObjects = locationH.getDecorObject();
			for(DecorObjet obj : decorObjects) {
				System.out.println(obj);
				if (obj.itMe(s)) {
					obj.look();
				}
			}
		}catch(NullPointerException e) {}
		try {
			List<Character> characters = locationH.getCharacters();
			for(Character c : characters) {
				if (c instanceof Lookable) {
					if ( c.itMe(s)) {
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
				if ( c.itMe(s)) {
					if (c instanceof WhoFight) {
						System.out.println(c);
						this.GAME.HERO.fight((WhoFight)c);
					}
				}
			}
		}
		catch(NullPointerException e) {}
	}
	public void take(String s) {
		
	}
}
