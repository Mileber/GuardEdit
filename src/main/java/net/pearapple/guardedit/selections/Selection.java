package net.pearapple.guardedit.selections;

import javax.annotation.Nullable;

import org.bukkit.Location;
import org.bukkit.World;

import net.pearapple.guardedit.regions.RegionSelector;
import net.pearapple.guardedit.vector.Vector;

public interface Selection {
	
	//获取区域较小的点
	public Location getMinimunPoint();
	
	public Vector getNativeMinimumPoint();
	
	//获取区域较大的点
	public Location getMaximumPoint();
	
	public Vector getNativeMaximumPoint();
	
	//获取区域选择器
	public RegionSelector getRegionSelector();
	
	@Nullable
	public World getWorld();
	
	//获取区域中方块数量
	public int getArea();
	
	//获取x大小
	public int getWidth();
	
	//获取y大小
	public int getHeight();
	
	//获取z大小
	public int getLength();
	
	//如果区域包含该点，返回true
	public boolean contains(Location position);
}
