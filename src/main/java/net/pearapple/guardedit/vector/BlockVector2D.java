package net.pearapple.guardedit.vector;

public class BlockVector2D extends Vector2D{
	
	public static final BlockVector2D ZERO = new BlockVector2D(0, 0);
	public static final BlockVector2D UNIT_X = new BlockVector2D(1, 0);
	public static final BlockVector2D UNIT_Z = new BlockVector2D(0, 1);
	public static final BlockVector2D ONE = new BlockVector2D(1, 1);
	
	//构造函数
	public BlockVector2D(Vector2D position){
		super(position);
	}
	
	public BlockVector2D(int x, int z){
		super(x, z);
	}
	
	public BlockVector2D(float x, float z){
		super(x, z);
	}
	
	public BlockVector2D(double x, double z){
		super(x, z);
	}
	
	public boolean equals(Object obj){
		if(!(obj instanceof Vector2D)){
			return false;
		}
		
		Vector2D other = (Vector2D) obj;
		return (int)other.x == (int)this.x && (int)other.z == (int)this.z;
	}
	
	public int hashCode(){
		return (Integer.valueOf((int) x).hashCode() >> 13) ^
				Integer.valueOf((int) z).hashCode();
	}
	
	public BlockVector2D toBlockVector2D(){
		return this;
	}

}
