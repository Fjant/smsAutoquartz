package cn.zhuoqin.platform.ibatis;

import java.io.Serializable;
import java.util.List;

import cn.zhuoqin.platform.page.Page;

public interface EntityDao<T, PK extends Serializable> {
	 public  T get(Serializable paramSerializable);

	  public  List<T> getAll();

	  public  void save(Object paramObject);

	  public  void removeById(Serializable paramSerializable);
	  
	  public  Page<?> pagedQuery(Object paramObject, Page<?> paramPage);

	  public  List<T> find(Object paramObject);
}

