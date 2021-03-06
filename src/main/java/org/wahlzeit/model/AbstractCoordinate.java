package org.wahlzeit.model;

import org.wahlzeit.utils.DesignPattern;

@DesignPattern(
		name = "Bridge",
		participants = {"AbstractCoordinate"}
		)


public abstract class AbstractCoordinate implements Coordinate{


	@Override
	public double getCartesianDistance(Coordinate c) {
		
		assertClassInvariants();
		
		//precondition
		assertIsNotNull(c);
		
		double d = this.asCartesianCoordinate().getDistance(c);
		
		//postcondition
		assertIsPositive(d);
		
		assertClassInvariants();
		return d;
	}

	@Override
	public double getSphericDistance(Coordinate c) {
		
		assertClassInvariants();
		
		//precondition
		assertIsNotNull(c);
		
		double d = this.asSphericCoordinate().getDistance(c);
		
		//postcondition
		assertIsPositive(d);
		
		assertClassInvariants();
		
		return d;
	}

	@Override
	public boolean isEqual(Coordinate c) {
		
		CartesianCoordinate other = c.asCartesianCoordinate();
		
		assertIsNotNull(other);
		
		if(Double.doubleToLongBits(other.getX()) != Double.doubleToLongBits(this.asCartesianCoordinate().getX()))
			return false;
		if(Double.doubleToLongBits(other.getY()) != Double.doubleToLongBits(this.asCartesianCoordinate().getX()))
			return false;
		if(Double.doubleToLongBits(other.getZ()) != Double.doubleToLongBits(this.asCartesianCoordinate().getX()))
			return false;
		
		return true;
	}
	
	
	@Override
	public int hashCode() {
		return generateHashCode(this.asCartesianCoordinate().getX(),
				this.asCartesianCoordinate().getY(),this.asCartesianCoordinate().getZ());
	}
	
	private static int generateHashCode(double x, double y, double z) {
		
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(z);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinate other = (Coordinate) obj;
		
		return isEqual(other);
	}
	
	protected abstract void assertClassInvariants();
	
	protected void assertIsNotNull(Coordinate c) {
		if(c == null) {
			throw new IllegalArgumentException("Coordinate must not be null.");
		}
	}
	
	protected void assertNotOverflowed(double d) {
		if( d == Double.POSITIVE_INFINITY) {
			throw new ArithmeticException("Double overflowed!");
		}
		if( d == Double.NEGATIVE_INFINITY) {
			throw new ArithmeticException("Double overflowed!");
		}
	}
	
	protected void assertIsPositive( double d ) {
		assert d >= 0;
	}
}
