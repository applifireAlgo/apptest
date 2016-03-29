package com.app.config;
import java.util.UUID;
import java.util.Vector;

import org.eclipse.persistence.config.SessionCustomizer;
import org.eclipse.persistence.internal.databaseaccess.Accessor;
import org.eclipse.persistence.internal.sessions.AbstractSession;
import org.eclipse.persistence.sequencing.Sequence;
import org.eclipse.persistence.sessions.Session;

/**
 * Version 4 (random)
 * 
 * Version 4 UUIDs use a scheme relying only on random numbers. This algorithm
 * sets the version number (4 bits) as well as two reserved bits. All other bits
 * (the remaining 122 bits) are set using a random or pseudorandom data source.
 * Version 4 UUIDs have the form xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx where x is
 * any hexadecimal digit and y is one of 8, 9, A, or B (e.g.,
 * f47ac10b-58cc-4372-a567-0e02b2c3d479).
 * **/
public class UUIDGenerator extends Sequence implements SessionCustomizer {

	public UUIDGenerator() {
		super();
	}

	public UUIDGenerator(String name) {
		super(name);
	}

	@Override
	public Object getGeneratedValue(Accessor accessor, AbstractSession writeSession, String seqName) {
		return UUID.randomUUID().toString().toUpperCase();
	}

	@Override
	public Vector getGeneratedVector(Accessor accessor, AbstractSession writeSession, String seqName, int size) {
		return null;
	}

	@Override
	public boolean shouldAcquireValueAfterInsert() {
		return false;
	}

	@Override
	public boolean shouldUseTransaction() {
		return false;
	}

	@Override
	public boolean shouldUsePreallocation() {
		return false;
	}

	public void customize(Session session) throws Exception {
		UUIDGenerator sequence = new UUIDGenerator("UUIDGenerator");
		session.getLogin().addSequence(sequence);
	}

	@Override
	public void onConnect() {

	}

	@Override
	public void onDisconnect() {

	}

}
