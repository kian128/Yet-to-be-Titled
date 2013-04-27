package entity;

import org.lwjgl.util.vector.Vector3f;

import core.World;

public class Entity {
	
	protected float x, y, z;
	protected float xWidth, height, zWidth;
	protected boolean isCollidable;
	
	protected Entity(float x, float y, float z, float xWidth, float height, float zWidth, boolean isCollidable) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.xWidth = xWidth;
		this.height = height;
		this.zWidth = zWidth;
		this.isCollidable = isCollidable;
		
		if(isCollidable) {
			World.collidableList.add(this);
		}
	}
	
	public void moveX(float speed) {
		float newX = x;
		newX += speed;
		
		boolean isColliding = false;
		for(int n = 0; n < World.collidableList.size(); n++) {
			if(isPositionCollidingWithEntity(new Vector3f(newX, y, z), World.collidableList.get(n))) {
				isColliding = true;
			}
		}
		
		if(!isColliding) {
			setX(newX);
		}
	}
	public void moveY(float speed) {
		float newY = y;
		newY += speed;
		
		boolean isColliding = false;
		for(int n = 0; n < World.collidableList.size(); n++) {
			if(isPositionCollidingWithEntity(new Vector3f(x, newY, z), World.collidableList.get(n))) {
				isColliding = true;
			}
		}
		
		if(!isColliding) {
			setY(newY);
		}
	}
	public void moveZ(float speed) {
		float newZ = z;
		newZ += speed;
		
		boolean isColliding = false;
		for(int n = 0; n < World.collidableList.size(); n++) {
			if(isPositionCollidingWithEntity(new Vector3f(x, y, newZ), World.collidableList.get(n))) {
				isColliding = true;
			}
		}
		
		if(!isColliding) {
			setZ(newZ);
		}
	}
	
	public boolean isPositionCollidingWithEntity(Vector3f position, Entity entity) {
		if(position.x + xWidth > entity.getX() && position.x < entity.getX() + entity.getXWidth()) {
    		if(position.y + height > entity.getY() && position.y < entity.getY() + entity.getHeight()) {
    			if(position.z + zWidth > entity.getZ() && position.z < entity.getZ() + entity.getZWidth()) {
    				if(entity != this) {
    					return true;
    				}
            	}
        	}
    	}
		return false;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	public void setY(float y) {
		this.y = y;
	}
	public void setZ(float z) {
		this.z = z;
	}
	
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	public float getZ() {
		return z;
	}
	
	public float getXWidth() {
		return xWidth;
	}
	public float getHeight() {
		return height;
	}
	public float getZWidth() {
		return zWidth;
	}
}
