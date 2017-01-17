package net.pearapple.guardedit.vector;

public class BlockVector extends Vector {
	
	public static final BlockVector ZERO = new BlockVector(0, 0, 0);
	public static final BlockVector UNIT_X = new BlockVector(1, 0, 0);
	public static final BlockVector UNIT_Y = new BlockVector(0, 1, 0);
	public static final BlockVector UNIT_Z = new BlockVector(0, 0, 1);
	public static final BlockVector ONE = new BlockVector(1, 1, 1);
	
	//构造函数，复制原有实例
	public BlockVector(Vector position){
		super(position);
	}
	
	//构造函数，新建实例
	public BlockVector(int x, int y, int z){
		super(x, y, z);
	}
	
	public BlockVector(float x,float y, float z){
		super(x, y, z);
	}
	
	public BlockVector(double x, double y, double z){
		super(x, y, z);
	}
	
	public boolean equals(Object obj){
		if(!(obj instanceof Vector)){
			return false;
		}
		Vector other = (Vector) obj;
		return (int)other.getX() == (int)this.x && (int)other.getY() == (int)this.y
				&& (int)other.getZ() == (int)this.z;
	}
	
	public int hashCode(){
		return ((int) x << 19) ^
				((int) y << 12) ^
				(int) z;
	}
	
	public BlockVector toBlockVector(){
		return this;
	}

}
