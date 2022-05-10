/* This handles the keys for the game*/

package Main;

import logic.Control;
import timer.stopWatchX;

public class KeyProcessor{
	// Static Fields
	private static char last = ' ';			// For debouncing purposes
	private static stopWatchX sw = new stopWatchX(250); // Debouncing Timer
	private static stopWatchX timer = new stopWatchX(85); // Timer for movement
	private static int i, j;

	// Static Method(s)
	public static void processKey(char key){

		/* TODO: You can modify values below here! */
		switch(key){
			case '%':								// ESC key
				System.exit(0);
				break;

			// Spacebar
			case '$':

				if(key == ' ')				return;
				if(key == last)
					if(sw.isTimeUp() == false)			return;
				last = key;
				sw.resetWatch();

				// Opens the dialog boxes
				if(Main.checkCollision(Main.playerBox, Main.chairGreen)){
					Main.trigger = "This seems comfy.";
					Main.textTrigger = "textbg";
				}
				if(Main.checkCollision(Main.playerBox, Main.chairRed)) {
					Main.trigger = "Kinda looks like a skittle";
					Main.textTrigger = "textbg";
				}
				if(Main.checkCollision(Main.playerBox, Main.chairPink)) {
					Main.trigger = "I should probably get back to work.";
					Main.textTrigger = "textbg";
				}
				if(Main.checkCollision(Main.playerBox, Main.book)){
					Main.trigger = "Seems like a good read.";
					Main.textTrigger = "textbg";
				}

				if(Main.checkCollision(Main.playerBox, Main.door)){
					Main.trigger = "My shift isn't over yet";
					Main.textTrigger = "textbg";
				}

				break;

			// Mouse
			case 'm':

				if(key == ' ')				return;
				if(key == last)
					if(sw.isTimeUp() == false)			return;
				last = key;
				sw.resetWatch();

				Control.isMouseCoordsDisplayed = !Control.isMouseCoordsDisplayed;
				break;

			/* Movement Keys - Each keypress should have its own set of spriteInfo and currentVec adjust and timer */
			// Moves north
			case 'w':
				Main.trigger = "";
				Main.textTrigger = "empty";
				if (timer.isTimeUp()){
					int x = Main.playerSprite.getCoords().getX();
					int y = Main.playerSprite.getCoords().getY();
					Main.lastPos.setCoords(x, y);
					Main.playerSprite.getCoords().adjustY(-16);
					Main.playerSprite.setTag("stickUD"+j);
					j++;
					if (j >= 9){
						j = 0;
					}
					timer.resetWatch();
				}

				break;

			// Moves west
			case 'a':
				Main.trigger = "";
				Main.textTrigger = "empty";
				if (timer.isTimeUp()){
					int x = Main.playerSprite.getCoords().getX();
					int y = Main.playerSprite.getCoords().getY();
					Main.lastPos.setCoords(x, y);
					Main.playerSprite.getCoords().adjustX(-16);
					Main.playerSprite.setTag("stickL"+i);
					i++;
					if (i >= 5){
						i = 0;
					}
					timer.resetWatch();
				}
				break;

			// Moves south
			case 's':
				Main.trigger = "";
				Main.textTrigger = "empty";
				if (timer.isTimeUp()){
					int x = Main.playerSprite.getCoords().getX();
					int y = Main.playerSprite.getCoords().getY();
					Main.lastPos.setCoords(x, y);
					Main.playerSprite.getCoords().adjustY(16);
					Main.playerSprite.setTag("stickUD"+j);
					j++;
					if (j >= 9){
						j = 0;
					}
					timer.resetWatch();
				}
				break;

			// Moves east
			case 'd':
				Main.trigger = "";
				Main.textTrigger = "empty";
				if (timer.isTimeUp()){
					int x = Main.playerSprite.getCoords().getX();
					int y = Main.playerSprite.getCoords().getY();
					Main.lastPos.setCoords(x, y);
					Main.playerSprite.getCoords().adjustX(16);
					Main.playerSprite.setTag("stickR"+i);
					i++;
					if (i >= 5){
						i = 0;
					}
					timer.resetWatch();
				}
				break;
		}
	}
}

