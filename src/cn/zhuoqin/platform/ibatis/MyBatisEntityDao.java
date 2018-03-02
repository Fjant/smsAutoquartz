package cn.zhuoqin.platform.ibatis;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

import cn.zhuoqin.platform.page.Page;

public class MyBatisEntityDao<T, PK extends Serializable> extends MyBatisGenericDao implements EntityDao<T, PK>{
	@SuppressWarnings("unchecked")
	protected Class<T> entityClass = MyBatisEntityDao.getSuperClassGenricType(getClass(),0);
	protected String primaryKeyName;
	 public List<T> findBy(String paramString, Object paramObject){
	    return findBy(getEntityClass(), paramString, paramObject);
	  }

	  public List<T> findByLike(String paramString1, String paramString2) {
	    return findByLike(getEntityClass(), paramString1, paramString2);
	  }

	  public T findUniqueBy(String paramString, Object paramObject) {
	    return findUniqueBy(getEntityClass(), paramString, paramObject);
	  }

	  public T get(Serializable paramSerializable) {
	    return get(getEntityClass(), paramSerializable);
	  }

	  public List<T> getAll() {
	    return getAll(getEntityClass());
	  }

	  protected Class<T> getEntityClass(){
	    return this.entityClass;
	  }

	  public String getIdName(Class<T> paramClass) {
	    return "id";
	  }

	  public String getPrimaryKeyName(){
	    if (StringUtils.isBlank(this.primaryKeyName))
	      this.primaryKeyName = "id";
	    return this.primaryKeyName;
	  }

	  protected Object getPrimaryKeyValue(Object paramObject)
	    throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException{
	    return PropertyUtils.getProperty(this.entityClass.cast(paramObject), getPrimaryKeyName());
	  }

	  public Page<?> pagedQuery(Page<?> paramPage){
	    return pagedQuery(getEntityClass(), null, paramPage);
	  }

	  public Page<?> pagedQuery(Object paramObject, Page<?> paramPage){
	    return pagedQuery(getEntityClass(), paramObject, paramPage);
	  }

	  public void removeById(Serializable paramSerializable) {
	    removeById(getEntityClass(), paramSerializable);
	  }

	  public void save(Object paramObject){
	    Object localObject;
	    try{
	      localObject = getPrimaryKeyValue(paramObject);
	    }catch (Exception localException){
	      throw new ObjectRetrievalFailureException(this.entityClass, localException);
	    }
	    if (localObject == null)
	      insert(paramObject);
	    else
	      update(paramObject);
	  }

	  public void setPrimaryKeyName(String paramString){
	    this.primaryKeyName = paramString;
	  }

	  public List<T> find(Object paramObject){
	    return find(getEntityClass(), paramObject);
	  }
	
	@SuppressWarnings("rawtypes")
	public static Class getSuperClassGenricType(Class paramClass, int paramInt){
	    Type localType = paramClass.getGenericSuperclass();
	    if (!(localType instanceof ParameterizedType)) {
	      return Object.class;
	    }
	    Type[] arrayOfType = ((ParameterizedType)localType).getActualTypeArguments();
	    if ((paramInt >= arrayOfType.length) || (paramInt < 0)){
	      return Object.class;
	    }
	    if (!(arrayOfType[paramInt] instanceof Class)) {
	      return Object.class;
	    }
	    return (Class)arrayOfType[paramInt];
	  }

}
