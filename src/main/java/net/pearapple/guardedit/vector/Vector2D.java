package net.pearapple.guardedit.vector;

public class Vector2D {
	public static final Vector2D ZERO = new Vector2D(0, 0);
	public static final Vector2D UNIT_X = new Vector2D(1, 0);
	public static final Vector2D UNIT_Z = new Vector2D(0, 1);
	public static final Vector2D ONE = new Vector2D(1, 1);
	
	protected final double x, z;
	
	//构造函数
	public Vector2D(double x, double z){
		this.x = x;
		this.z = z;
	}
	
	public Vector2D(int x, int z){
		this.x = (double) x;
		this.z = (double) z;
	}
	
	public Vector2D(float x, float z){
		this.x = (double) x;
		this.z = (double) z;
	}
	
	public Vector2D(Vector2D other){
		this.x = other.x;
		this.z = other.z;
	}
	
	public Vector2D(){
		this.x = 0;
		this.z = 0;
	}
	
	//获取x坐标
	public double getX(){
		return x;
	}
	
	public int getBlockX(){
		return (int) Math.round(x);
	}
	
	public Vector2D setX(double x){
		return new Vector2D(x, z);
	}
	
	public Vector2D setX(int x){
		return new Vector2D(x, z);
	}
	
	//获取z坐标
	public double getZ(){
		return z;
	}
	
	public int getBlockZ(){
		return (int)Math.round(z);
	}
	
	public Vector2D setZ(double z){
		return new Vector2D(x, z);
	}
	
	public Vector2D setZ(int z){
		return new Vector2D(x, z);
	}
	
	//向量加法
	public Vector2D add(Vector2D other){
		return new Vector2D(x + other.x, z + other.z);
	}
	
	public Vector2D add(double x, double z){
		return new Vector2D(this.x + x, this.z + z);
	}
	
	public Vector2D add(int x, int z){
		return new Vector2D(this.x + x, this.z + z);
	}
	
	public Vector2D add(Vector2D... others){
		double newX = x, newZ = z;
		
		for(Vector2D other : others){
			newX += other.x;
			newZ += other.z;
		}
		
		return new Vector2D(newX, newZ);
	}
	
	//向量减法
	public Vector2D subtract(Vector2D other){
		return new Vector2D(x - other.x, z - other.z);
	}
	
	public Vector2D subtract(double x, double z){
		return new Vector2D(this.x - x, this.z - z);
	}
	
	public Vector2D subtract(int x, int z){
		return new Vector2D(this.x - x, this.z - z);
	}
	
	public Vector2D subtract(Vector2D... others){
		double newX = x, newZ = z;
		
		for(Vector2D other : others){
			newX -= other.x;
			newZ -= other.z;
		}
		
		return new Vector2D(newX, newZ);
	}
	
	//向量乘法
	public Vector2D multiply(Vector2D other){
		return new Vector2D(x * other.x, z * other.z);
	}
	
	public Vector2D multiply(double x, double z){
		return new Vector2D(this.x * x, this.z * z);
	}
	
	public Vector2D multiply(int x, int z){
		return new Vector2D(this.x * x, this.z * z);
	}
	
	public Vector2D multiply(Vector2D... others){
		double newX = x, newZ = z;
		
		for(Vector2D other : others){
			newX *= other.x;
			newZ *= other.z;
		}
		
		return new Vector2D(newX, newZ);
	}
	
	public Vector2D multiply(double n){
		return new Vector2D(this.x * n, this.z * n);
	}
	
	public Vector2D multiply(float n){
		return new Vector2D(this.x * n, this.z * n);
	}
	
	public Vector2D multiply(int n){
		return new Vector2D(this.x * n, this.z * n);
	}
	
	//向量除法
	public Vector2D divide(Vector2D other){
		return new Vector2D(x / other.x, z / other.z);
	}
	
	public Vector2D divide(double x, double z){
		return new Vector2D(this.x / x, this.z / z);
	}
	
	public Vector2D divide(int x, int z){
		return new Vector2D(this.x / x, this.z / z);
	}
	
	public Vector2D divide(double n){
		return new Vector2D(this.x / n, this.z / n);
	}
	
	public Vector2D divide(float n){
		return new Vector2D(this.x / n, this.z / n);
	}
	
	public Vector2D divide(int n){
		return new Vector2D(this.x / n, this.z / n);
	}
	
	//向量的模
	public double length(){
		return Math.sqrt(x * x + z * z);
	}
	
	//向量的模的平方
	public double lengthSq(){
		return x * x + z * z;
	}
	
	//两向量间距离
	public double distance(Vector2D other){
		return Math.sqrt(Math.pow(other.x - x, 2) + Math.pow(other.z - z, 2));
	}
	
	//两向量间距离的平方
	public double distanceSq(Vector2D other){
		return Math.pow(other.x - x, 2) +
				Math.pow(other.z - z, 2);
	}
	
	//单位向量
	public Vector2D normalize(){
		return divide(length());
	}
	
	//向量点乘
	public double dot(Vector2D other){
		return x * other.x + z * other.z;
	}
	
	//向量包含
	public boolean containedWithin(Vector2D min, Vector2D max){
		return x >= min.x && x <= max.x
				&& z >= min.z && z <= max.z;
	}
	
	public boolean containedWithinBlock(Vector2D min, Vector2D max){
		return getBlockX() >= min.getBlockX() && getBlockX() <= max.getBlockX()
				&& getBlockZ() >= min.getBlockZ() && getBlockZ() <= max.getBlockZ();
	}
	
	//最大向下取整
	public Vector2D floor(){
		return new Vector2D(Math.floor(x), Math.floor(z));
	}
	
	//最小向上取整
	public Vector2D ceil(){
		return new Vector2D(Math.ceil(x), Math.ceil(z));
	}
	
	//模糊最大向下取整
	public Vector2D round(){
		return new Vector2D(Math.floor(x + 0.5), Math.floor(z + 0.5));
	}
	
	//向量取绝对值
	public Vector2D positive(){
		return new Vector2D(Math.abs(x), Math.abs(z));
	}
	
	public Vector2D transform2D(double angle, double aboutX, double aboutZ, double translateX, double translateZ) {
        angle = Math.toRadians(angle);
        double x = this.x - aboutX;
        double z = this.z - aboutZ;
        double x2 = x * Math.cos(angle) - z * Math.sin(angle);
        double z2 = x * Math.sin(angle) + z * Math.cos(angle);
        return new Vector2D(
            x2 + aboutX + translateX,
            z2 + aboutZ + translateZ
        );
	}
	
	//向量平行
	public boolean isCollinearWith(Vector2D other) {
        if (x == 0 && z == 0) {
            // this is a zero vector
            return true;
        }

        final double otherX = other.x;
        final double otherZ = other.z;

        if (otherX == 0 && otherZ == 0) {
            // other is a zero vector
            return true;
        }

        if ((x == 0) != (otherX == 0)) return false;
        if ((z == 0) != (otherZ == 0)) return false;

        final double quotientX = otherX / x;
        if (!Double.isNaN(quotientX)) {
            return other.equals(multiply(quotientX));
        }

        final double quotientZ = otherZ / z;
        if (!Double.isNaN(quotientZ)) {
            return other.equals(multiply(quotientZ));
        }

        throw new RuntimeException("This should not happen");
    }

	public BlockVector2D toBlockVector2D() {
        return new BlockVector2D(this);
	}
	
	public Vector toVector() {
        return new Vector(x, 0, z);

	}
	
	public Vector toVector(double y) {
        return new Vector(x, y, z);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Vector2D)) {
            return false;
        }

        Vector2D other = (Vector2D) obj;
        return other.x == this.x && other.z == this.z;

    }

    @Override
    public int hashCode() {
        return ((new Double(x)).hashCode() >> 13) ^
                (new Double(z)).hashCode();
    }

    @Override
    public String toString() {
        return "(" + x + ", " + z + ")";
    }
    
    public static Vector2D getMinimum(Vector2D v1, Vector2D v2) {
        return new Vector2D(
            Math.min(v1.x, v2.x),
            Math.min(v1.z, v2.z)
        );
    }

    public static Vector2D getMaximum(Vector2D v1, Vector2D v2) {
        return new Vector2D(
            Math.max(v1.x, v2.x),
            Math.max(v1.z, v2.z)
        );
    }

}
