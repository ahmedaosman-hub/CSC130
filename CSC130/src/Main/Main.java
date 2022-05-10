// Name: 				Ahmed Osman
// Assignment:		 	Final Project
// Course: 				CSC 130 Spring 2022



package Main;

import java.awt.Color;
import java.util.ArrayList;

import Data.Vector2D;
import Data.boundingBox;
import Data.spriteInfo;


import logic.Control;


public class Main{

	// Static Fields
	public static int frameCounter = 0; // Holds reference to current sprite
	public static Vector2D currentVec = new Vector2D(100, 296); // Holds current 2d Vector
	public static Vector2D lastVec = new Vector2D(0, 0);
	public static spriteInfo playerSprite = new spriteInfo(currentVec, "stickR"+frameCounter); // Holds info of sprites
	public static spriteInfo lastPos = new spriteInfo (lastVec, playerSprite.getTag());
	public static spriteInfo boxSprite = new spriteInfo(new Vector2D(800, 296), "testBox");
	public static boundingBox testBox = new boundingBox(boxSprite);
	public static boundingBox playerBox;
	public static ArrayList<boundingBox> boxes = new ArrayList<>();

	public static boundingBox book = new boundingBox(770, 815, 155, 270);
	public static boundingBox door = new boundingBox(960, 1020, 75, 145);
	public static boundingBox chairGreen = new boundingBox(780, 945, 520, 720);
	public static boundingBox chairRed = new boundingBox(120, 290, 540, 720);
	public static boundingBox chairPink = new boundingBox(450, 620, 530, 720);
	public static String trigger = "";
	public static String textTrigger = "empty";

	public static void main(String[] args) {
		Control ctrl = new Control();				/* Do NOT remove! */
		ctrl.gameLoop();							/* Do NOT remove! */
	}

	/* Starting Conditions before game*/
	public static void start() {

		/* Defining all bounding boxes for static objects */
		boxes.add(new boundingBox(-128, 1408, 0, 128));  	// Top boundary
		boxes.add(new boundingBox(-128, 1408, 700, 848));	// Bottom boundary
		boxes.add(new boundingBox(-128, 50, -128, 848));	// Left Boundary
		boxes.add(new boundingBox(1280, 1408, -128, 848));	// Right Boundary
		boxes.add(new boundingBox(200, 370, 128, 420)); // top left shelf
		boxes.add(new boundingBox(610, 780, 128, 430)); // top right shelf
	}



	/* The game loop */
	public static void update(Control ctrl) {

		ctrl.addSpriteToFrontBuffer(0, 0, "background");

		/* Player bounding box updated by current playerSprite position and adjusted bounds relative to origin (top left) */
		playerBox = new boundingBox(playerSprite, 20, 108, 108, 120);

		/* Check collision between player and any rigid body stored in the array */
		for (int i = 0; i < boxes.size(); i++)
			if (checkCollision(playerBox, boxes.get(i)))
				bouncePlayer();

		/* Player Sprite */
		ctrl.addSpriteToFrontBuffer(playerSprite.getCoords().getX(), playerSprite.getCoords().getY(), playerSprite.getTag());

		/* Dialog Prompts */
		ctrl.addSpriteToFrontBuffer(410, 500, textTrigger);
		ctrl.drawString(415, 600, trigger, Color.WHITE);
	}


	/* Additional Static methods below...(if needed) */

	/* Method for checking a collision. */
	public static boolean checkCollision(boundingBox box1, boundingBox box2){
		if (((box1.getX1() > box2.getX2()) || (box1.getX2() < box2.getX1()) || (box1.getY1() > box2.getY2()) || (box1.getY2() < box2.getY1())))
			return false;
		else
			return true;
	}

	/* Method for bouncing the player back to the last position after a collisionn */
	public static void bouncePlayer(){
		if ((playerSprite.getCoords().getX() - lastPos.getCoords().getX()) != 0){
			if ((playerSprite.getCoords().getX() - lastPos.getCoords().getX()) > 0)		// Moved from left to right
				playerSprite.getCoords().adjustX(-16);
			if ((playerSprite.getCoords().getX() - lastPos.getCoords().getX()) < 0)  	// Moved from right to left
				playerSprite.getCoords().adjustX(+16);
		}
		if ((playerSprite.getCoords().getY() - lastPos.getCoords().getY()) != 0){
			if ((playerSprite.getCoords().getY() - lastPos.getCoords().getY()) > 0)		// Moved from up to bottom
				playerSprite.getCoords().adjustY(-16);
			if ((playerSprite.getCoords().getY() - lastPos.getCoords().getY()) < 0)		// Moved from bottom to up
				playerSprite.getCoords().adjustY(+16);
		}
	}
}
