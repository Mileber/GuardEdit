package net.pearapple.guardedit.regions;

import java.util.List;
import java.util.Set;

import javax.annotation.Nullable;

import org.bukkit.World;

import net.pearapple.guardedit.vector.BlockVector;
import net.pearapple.guardedit.vector.BlockVector2D;
import net.pearapple.guardedit.vector.Vector;
import net.pearapple.guardedit.vector.Vector2D;

public interface Region extends Iterable<BlockVector>, Cloneable {

	public Vector getMinimumPoint();
	
	public Vector getMaximumPoint();
	
	//获取中心点
	public Vector getCenter();
	
	//获取区域中方块数量
	public int getArea();
	
	//获取x
	public int getWidth();
	
	//获取y
	public int getHeight();
	
	//获取z
	public int getLength();
	
	/*
	public void expand(Vector... changes) throws RegionOperationException;
	
	public void contract(Vector... changes) throws RegionOperationException;
	
	public void shift(Vector... changes) throws RegionOperationException;
	*/
	
	//区域包含点返回true
	public boolean contains(Vector position);
	
	//返回一系列区块
	public Set<Vector2D> getChunks();
	
	//返回一系列16*16*16的区块
	public Set<Vector> getChunkCubes();
	
	//获取选择所在世界
	@Nullable
	public World getWorld();
	
	//设置选择所在世界
	public void setWorld(@Nullable World world);
	
	//复制区域
	public Region clone();
	
	public List<BlockVector2D> polygonize(int maxPoints);
	
}
