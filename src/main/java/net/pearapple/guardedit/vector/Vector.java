package net.pearapple.guardedit.vector;

import javax.annotation.Nullable;

/**
 * @author derya
 * 三维坐标
 */
public class Vector implements Comparable<Vector> {
	
	public static final Vector ZERO = new Vector(0, 0, 0);
	public static final Vector UNIT_X = new Vector(1, 0, 0);
	public static final Vector UNIT_Y = new Vector(0, 1, 0);
	public static final Vector UNIT_Z = new Vector(0, 0, 1);
	public static final Vector ONE = new Vector(1, 1, 1);
	
	protected final double x, y, z;
	
	//构造函数们
	public Vector(double x, double y, double z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector(int x, int y, int z){
		this.x = (double)x;
		this.y = (double)y;
		this.z = (double)z;
	}
	
	public Vector(float x, float y, float z){
		this.x = (double)x;
		this.y = (double)y;
		this.z = (double)z;
	}
	
	//复制其他的Vector
	public Vector(Vector other){
		this.x = other.x;
		this.y = other.y;
		this.z = other.z;
	}
	
	//构建一个新的实例，默认x,y,z都为0
	public Vector(){
		this.x = 0;
		this.y = 0;
		this.z = 0;
	}
	
	//获取x坐标
	public double getX(){
		return x;
	}
	
	//获取四舍五入后取整的x坐标
	public int getBlockX(){
		return (int) Math.round(x);
	}
	
	//设置x坐标
	public Vector setX(double x){
		return new Vector(x, y, z);
	}
	
	public Vector setX(int x){
		return new Vector(x, y, z);
	}
	
	//获取y坐标
	public double getY(){
		return y;
	}
	
	//获取四舍五入后取整的y坐标
	public int getBlockY(){
		return (int) Math.round(y);
	}
	
	//设置y坐标
	public Vector setY(double y){
		return new Vector(x, y, z);
	}
	
	public Vector setY(int y){
		return new Vector(x, y, z);
	}
	
	//获取z坐标
	public double getZ(){
		return x;
	}
	
	//获取四舍五入后取整的z坐标
	public int getBlockZ(){
		return (int) Math.round(z);
	}
	
	//设置z坐标
	public Vector setZ(double z){
		return new Vector(x, y, z);
	}
	
	public Vector setZ(int z){
		return new Vector(x, y, z);
	}
	
	//在原有Vector上加上另一个，参数是Vector对象
	public Vector add(Vector other){
		return new Vector(x + other.x, y + other.y, z + other.z);
	}
	
	//在原有Vector上加上另一个，参数是double的x,y,z
	public Vector add(double x, double y, double z){
		return new Vector(this.x + x, this.y + y, this.z + z);
	}
	
	//在原有Vector上加上另一个，参数是整型的x,y,z
	public Vector add(int x, int y, int z){
		return new Vector(this.x + x, this.y + y, this.z + z);
	}
	
	//在原有Vector上加上一组
	public Vector add(Vector... others){
		double newX = x, newY = y, newZ = z;
		
		for (Vector other : others){
			newX += other.x;
			newY += other.y;
			newZ += other.z;
		}
		
		return new Vector(newX, newY, newZ);
	}
	
	//在原有Vector上减去另一个，参数是Vector对象
	public Vector subtract(Vector other){
		return new Vector(x - other.x, y - other.y, z - other.z);
	}
	
	//在原有Vector上减去另一个，参数是double的x,y,z
	public Vector subtract(double x, double y, double z){
		return new Vector(this.x - x, this.y - y, this.z - z);
	}
	
	//在原有Vector上减去另一个，参数是整型的x,y,z
	public Vector subtract(int x, int y, int z){
		return new Vector(this.x - x, this.y - y, this.z - z);
	}
	
	//在原有Vector上减去一组
	public Vector subtract(Vector... others){
		double newX = x, newY = y, newZ = z;
		
		for (Vector other : others){
			newX -= other.x;
			newY -= other.y;
			newZ -= other.z;
		}
		
		return new Vector(newX, newY, newZ);
	}
	
	//在原有Vector上乘上另一个，参数是Vector对象
	public Vector multiply(Vector other){
		return new Vector(x * other.x, y * other.y, z * other.z);
	}
	
	//在原有Vector上乘上另一个，参数是double的x,y,z
	public Vector multiply(double x, double y, double z){
		return new Vector(this.x * x, this.y * y, this.z * z);
	}
	
	//在原有Vector上乘上另一个，参数是整型的x,y,z
	public Vector multiply(int x, int y, int z){
		return new Vector(this.x * x, this.y * y, this.z * z);
	}
	
	//在原有Vector上乘上一组
	public Vector multiply(Vector... others){
		double newX = x, newY = y, newZ = z;
		
		for (Vector other : others){
			newX *= other.x;
			newY *= other.y;
			newZ *= other.z;
		}
		
		return new Vector(newX, newY, newZ);
	}
	
	//乘以n倍，double型
	public Vector multiply(double n){
		return new Vector(this.x * n, this.y * n, this.z * n);
	}
	
	//乘以n倍，float型
	public Vector multiply(float n){
		return new Vector(this.x * n, this.y * n, this.z * n);
	}
	
	//乘以n倍，int型
	public Vector multiply(int n){
		return new Vector(this.x * n, this.y * n, this.z * n);
	}
	
	//在原有Vector上除以另一个，参数是Vector对象
	public Vector divide(Vector other){
		return new Vector(x / other.x, y / other.y, z / other.z);
	}
	
	//在原有Vector上除以另一个，参数是double的x,y,z
	public Vector divide(double x, double y, double z){
		return new Vector(this.x / x, this.y / y, this.z / z);
	}
	
	//在原有Vector上除以另一个，参数是整型的x,y,z
	public Vector divide(int x, int y, int z){
		return new Vector(this.x / x, this.y / y, this.z / z);
	}
	
	//除以n倍，double型
	public Vector divide(double n){
		return new Vector(this.x / n, this.y /n, this.z / n);
	}
	
	//除以n倍，float型
	public Vector divide(float n){
		return new Vector(this.x / n, this.y / n, this.z / n);
	}
	
	//除以n倍，int型
	public Vector divide(int n){
		return new Vector(this.x / n, this.y / n, this.z / n);
	}
	
	//获取向量长度
	public double length(){
		return Math.sqrt(x * x + y * y + z * z);
	}
	
	//获取向量长度的平方
	public double lengthSq(){
		return x * x + y * y + z * z;
	}
	
	//获取两向量间距离
	public double distance(Vector other){
		return Math.sqrt(Math.pow(other.x - x, 2) +
				Math.pow(other.y - y, 2) + 
				Math.pow(other.z - z, 2));
	}
	
	//获取两向量间距离的平方
	public double distanceSq(Vector other){
		return Math.pow(other.x - x, 2) +
				Math.pow(other.y - y, 2) +
				Math.pow(other.z - z, 2);
	}
	
	//获取单位向量（标准化）
	public Vector normalize(){
		return divide(length());
	}
	
	//向量的数量积（点乘）
	public double dot(Vector other){
		return x * other.x + y * other.y + z * other.z;
	}
	
	//向量积（叉乘）
	public Vector cross(Vector other){
		return new Vector(
				y * other.z - z * other.y,
				z * other.x - x * other.z,
				x * other.y - y * other.x);
	}
	
	//判断一个向量是否包含另一个
	public boolean containedWithin(Vector min, Vector max){
		return x >= min.x && x <= max.x && y >= min.y && y <= max.y && z >= min.z && z <= max.z;
	}
	
	//判断向量是否包含另一个，用在内离散的比较
	public boolean containedWithinBlock(Vector min, Vector max){
		return getBlockX() >= min.getBlockX() && getBlockX() <= max.getBlockX()
				&& getBlockY() >= min.getBlockY() && getBlockY() <= max.getBlockY()
				&& getBlockZ() >= min.getBlockZ() && getBlockZ() <= max.getBlockZ();
	}
	
	//TODO: clamp y，具体用途暂时不知，y为高度
	public Vector clampY(int min, int max){
		return new Vector(x, Math.max(min, Math.min(max, y)), z);
	}
	
	//求向量的最大向下取整
	public Vector floor(){
		return new Vector(Math.floor(x), Math.floor(y), Math.floor(z));
	}
	
	//求向量的最小向上取整
	public Vector ceil(){
		return new Vector(Math.ceil(x), Math.ceil(y), Math.ceil(z));
	}
	
	//各坐标模糊向下取整
	public Vector round(){
		return new Vector(Math.floor(x + 0.5), Math.floor(y + 0.5), Math.floor(z + 0.5));
	}
	
	//各坐标取绝对值
	public Vector positive(){
		return new Vector(Math.abs(x), Math.abs(y), Math.abs(z));
	}
	
	//TODO:向量的2维转换？具体不知道干啥的
	public Vector transform2D(double angle, double aboutX, double aboutZ, double translateX, double translateZ){
		angle = Math.toRadians(angle);
		double x = this.x - aboutX;
		double y = this.z - aboutZ;
		double x2 = x * Math.cos(angle) - z * Math.sin(angle);
		double z2 = x * Math.sin(angle) + z * Math.cos(angle);
		
		return new Vector(
				x2 + aboutX + translateX,
				y,
				z2 + aboutZ + translateZ);
	}
	
	//判断向量平行
	public boolean isCollinearWith(Vector other){
		if (x == 0 && y == 0 && z == 0){
			//向量为0向量
			return true;
		}
		
		final double otherX = other.x;
		final double otherY = other.y;
		final double otherZ = other.z;
		
		if (otherX == 0 && otherY == 0 && otherZ == 0){
			//other为0向量
			return true;
		}
		
		if ((x == 0) != (otherX == 0)) return false;
		if ((y == 0) != (otherY == 0)) return false;
		if ((z == 0) != (otherZ == 0)) return false;
		
		final double quotientX = otherX / x;
		if (!Double.isNaN(quotientX)){
			return other.equals(multiply(quotientX));
		}
		
		final double quotientY = otherY / y;
		if (!Double.isNaN(quotientY)){
			return other.equals(multiply(quotientY));
		}
		
		final double quotientZ = otherZ / z;
		if (!Double.isNaN(quotientZ)){
			return other.equals(multiply(quotientZ));
		}
		
		throw new RuntimeException("This should not happen!");
	}
	
	//获取向量使用的pitch
	public float toPitch(){
		double x = getX();
		double z = getZ();
		
		if (x == 0 && z == 0){
			return getY() > 0 ? -90 : 90;
		}else{
			double x2 = x * x;
			double z2 = z * z;
			double xz = Math.sqrt(x2 + z2);
			return (float)Math.toDegrees(Math.atan(-getY() / xz));
		}
	}
	
	//获取向量的yaw
	public float toYaw(){
		double x = getX();
		double z = getZ();
		
		double t = Math.atan2(-x, z);
		double _2pi = 2 * Math.PI;
		
		return (float)Math.toDegrees(((t + _2pi) % _2pi));
	}
	
	//根据坐标创建Block向量
	public static BlockVector toBlockPoint(double x, double y, double z){
		return new BlockVector(
				Math.floor(x),
				Math.floor(y),
				Math.floor(z));
	}
	
	public BlockVector toBlockPoint() {
        return new BlockVector(
            Math.floor(x),
            Math.floor(y),
            Math.floor(z)
        );
	}
	
	public BlockVector toBlockVector() {
        return new BlockVector(this);
	}
	
	//创建二维向量
	public Vector2D toVector2D() {
        return new Vector2D(x, z);
	}
	
	@Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Vector)) {
            return false;
        }

        Vector other = (Vector) obj;
        return other.x == this.x && other.y == this.y && other.z == this.z;
	}

	@Override
	public int compareTo(@Nullable Vector other) {
		if (other == null) {
            throw new IllegalArgumentException("null not supported");
        }
        if (y != other.y) return Double.compare(y, other.y);
        if (z != other.z) return Double.compare(z, other.z);
        if (x != other.x) return Double.compare(x, other.x);
        return 0;
	}
	
	@Override
    public int hashCode() {
        int hash = 7;

        hash = 79 * hash + (int) (Double.doubleToLongBits(this.x) ^ (Double.doubleToLongBits(this.x) >>> 32));
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.y) ^ (Double.doubleToLongBits(this.y) >>> 32));
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.z) ^ (Double.doubleToLongBits(this.z) >>> 32));
        return hash;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }
    
    //两向量取大
    public static Vector getMinimum(Vector v1, Vector v2) {
        return new Vector(
                Math.min(v1.x, v2.x),
                Math.min(v1.y, v2.y),
                Math.min(v1.z, v2.z)
        );
    }
    
    //两向量取小
    public static Vector getMaximum(Vector v1, Vector v2) {
        return new Vector(
                Math.max(v1.x, v2.x),
                Math.max(v1.y, v2.y),
                Math.max(v1.z, v2.z)
        );
    }
    
    //两向量取中点
    public static Vector getMidpoint(Vector v1, Vector v2) {
        return new Vector(
                (v1.x + v2.x) / 2,
                (v1.y + v2.y) / 2,
                (v1.z + v2.z) / 2
        );
    }


}