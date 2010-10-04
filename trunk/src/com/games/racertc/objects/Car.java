package com.games.racertc.objects;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import com.games.racertc.Presentation;
import com.games.racertc.messages.Message;
import com.games.racertc.other.Vec2D;

public class Car extends GameObject {

	/**	Przechowuje numer pojazdu gracza na liscie pojazdow. */
	public final static int CAR_PLAYER = 0;
	
	public Car( float radius, Drawable drawable ) {
		super( radius, drawable );
		// TODO Auto-generated constructor stub
	}

	public void acceptPresentation( Presentation presentation, Canvas canvas, Vec2D screenPos ) {
		presentation.drawGameObject( this, canvas, screenPos );
	}	
	
/*-----------------------------------------*/
/*-          Parametry jazdy:             -*/
/*-----------------------------------------*/		
	
	/**
	 * Jednostkowy wektor okreslajacy zwrot i kierunek wektora predkosci.
	 */
	protected Vec2D velocity = new Vec2D( 0f );
	
	/**
	 * Jednostkowy wektor okreslajacy gdzie znajduje sie przod samochodu.
	 */
	protected Vec2D direction = new Vec2D( 0f );
	
	/** Obecna szybkosd samochodu. */
	public float speed = 0f;
	
	/** Maksyma szybkosc samochodu. */
	public float maxSpeed = 1f;
	
	public float requestedSpeed = 0f;
	
	/** Maksymalny kat skretu samochodu. */
	public float maxTurningAngle = (float) Math.toRadians( 6f );
	
	/** Ile stopni w kazdej klatce samochod bedzie skrecal bardziej niz w poprzedniej. */
	public float turningAngleStep = (float) Math.toRadians( 0.4f );
	
	/** Obecny kat skretu samochodu. */
	public float currentTurningAngle = 0f;
	
	public float requestedTurningAngle = 0f;
	
	/** Przyczepnosc. */
	public float adhesion = 1f;
	
	public float adhesionSide = 1f;
	
	/** Przyspieszenie samochodu. */
	public float acceleration = 0.0018f;
	
	/** Przyspieszenie hamulcow */
	public float accelerationBreaks = -0.0025f;
	
	/** Przyspieszenie hamulca recznego. */
	public float accelerationHandbreak = -0.005f;
	
	public Vec2D getVelocity() { return velocity; };
	
	public Vec2D getDirection() { return direction; };
	
	/**
	 * Flagi okreslajace aktualne zachowanie samochodu.
	 */
	private int behaviourFlags;

	public void updateBehaviour( Message m ) {
		behaviourFlags &= m.getMask(); //usuwa stare flagi
		behaviourFlags |= m.getFlags(); //aplikuje nowe flagi
		this.requestedSpeed = m.yAxisUsage;
		this.requestedTurningAngle = m.xAxisUsage;
	}
	
	public int getBehaviourFlags() {
		return behaviourFlags;
	}
	
	public float getRotation() {
		return direction.getAngle();
	}
	
}