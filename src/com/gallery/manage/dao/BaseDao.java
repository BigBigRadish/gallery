package com.gallery.manage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.CallableStatementCreatorFactory;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.SqlReturnResultSet;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;




@SuppressWarnings({"rawtypes","unchecked","deprecation"})
@Repository
public class BaseDao{
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 带参数SQL查询全部
	 * @author gexiaopeng 2007-9-6
	 * @param sqlQueryString sql查询语句
	 * @param parameters 查询参数
	 * @return list
	 */
	
	public List findList(String sql, Object[] parameters){
		return jdbcTemplate.query(sql,parameters,new ColumnMapRowMapper());
	}
	/**
	 * 带参数SQL查询全部
	 * @author gexiaopeng 2007-9-6
	 * @param sqlQueryString sql查询语句
	 * @param parameters 查询参数
	 * @return list
	 */
	public List findList(String sql, Object[] parameters,RowMapper rowMapper){
		return jdbcTemplate.query(sql,parameters,rowMapper);
	}
	/**
	 * SQL查询全部(不带参数)
	 * @author gexiaopeng 2007-10-8
	 * @param sqlQueryString sql查询语句
	 * @return  list
	 */
	public List findList(String sql ){
		return jdbcTemplate.query(sql,new ColumnMapRowMapper());
	}
	/**
	 * SQL查询全部(不带参数)
	 * @author gexiaopeng 2007-10-8
	 * @param sqlQueryString sql查询语句
	 * @return  list
	 */
	public List findList(String sql ,RowMapper rowMapper){
		return jdbcTemplate.query(sql,rowMapper);
		
	}
	/**
	 * 执行sql(带参数)命令(insert delete update)
	 * @author gexiaopeng 2007-9-6
	 * @param srtSql sql语句
	 * @param parameters 参数集合
	 * @return int
	 */
	public int executeSQL(String sql, Object[] parameters){
		return jdbcTemplate.update(sql,parameters);
	}
	/**
	 * 执行sql(不带参数)命令(insert delete update)
	 * @author gexiaopeng 2007-10-9
	 * @param strSql sql语句
	 * @return int
	 */
	public int executeSQL(String sql){
		return jdbcTemplate.update(sql);
	}
	/**
	 * 得到一个map对象(带参数)
	 * @author gexiaopeng 2007-9-12
	 * @param strSql sql语句
	 * @param parameters  参数集合
	 * @return map  没有返回null
	 */
	public Map getMap(String sql, Object[] parameters){
		List ls=findList(sql,parameters);
		if(ls!=null && ls.size()==1){
			return (Map)ls.iterator().next();
		}
		return null;
		
	}
	/**
	 * 得到一个map对象(不带参数)
	 * @author gexiaopeng 2007-9-12
	 * @param strSql sql语句
	 * @return map  没有返回null
	 */
	public Map getMap(String sql){
		return getMap(sql,null);
	}
	/**
	 * 调用存储过程等的基础方法
	 * @param callStr 	String  需调用的语句，如：call p1(?,?) 或者 ?=call p2(?,?);
	 *                  注意：sql server 中必须要用 "{}" 如:{call p1(?,?)} 或者{?=call p2(?,?)}
	 * @param ins		Object[] 变量绑定对应的入参，如果无变量绑定则传null
	 * @param outCount	int 返回参数个数
		 * @param isReturnValue   boolean 是否有返回值  格式： {?=call p2(?,?)}; 
	 * @param isReturnResultSet boolean 是否返回记录集 (用于sql server ,oracle不能使用)  
	 * @param  RowMapper 
	 * @return 返回对应的参数值Map("out1",value1),...,Map("outn",valuen);返回记录Map("rs");返回值Map("rv");out记录集 Map("outList1")...
	 */
	public Map callProcedure(String callStr, Object[] ins, int outCount,boolean isReturnValue,boolean isSqlReturnResultSet,RowMapper rm){
		
		List inName = new ArrayList();
		Map inValue = new HashMap();
		SqlParameter sp = null;
		SqlOutParameter sop = null;
		Map retMap = null;
		//sql server 返回记录
		if(isSqlReturnResultSet){
			if(rm==null){
				rm=new ColumnMapRowMapper();
			}
			SqlReturnResultSet ssop = new SqlReturnResultSet("rs",rm);
			inName.add(ssop);
		}
		//return 值
		if(isReturnValue){
			sop = new SqlOutParameter("rv", Types.VARCHAR);
			inName.add(sop);
		}
		
		// 入参
		if (ins !=null && ins.length > 0) {
			for (int i = 0; i < ins.length; i++) {
				sp = new SqlParameter("In" + i, Types.VARCHAR);
				inName.add(sp);
				inValue.put("In" + i, ins[i]);
			}
		}
		
		// 出参
		for (int i = 1; i <= outCount; i++) {
			sop = new SqlOutParameter("out" + i, Types.VARCHAR);
			inName.add(sop);
		}
		
	
		CallableStatementCreatorFactory fac = new CallableStatementCreatorFactory(
			callStr, inName);
		
		CallableStatementCreator csc = fac.newCallableStatementCreator(inValue);
		
		retMap = jdbcTemplate.call(csc, inName);
		
		return retMap;
		
	
	}

	/**
	 * 调用存储过程等的基础方法
	 * @param callStr 	String  需调用的语句，如：call p1(?,?) 或者 ?=call p2(?,?);
	 *                  注意：sql server 中必须要用 "{}" 如:{call p1(?,?)} 或者{?=call p2(?,?)}
	 * @param ins		Object[] 变量绑定对应的入参，如果无变量绑定则传null
	 * @param outCount	int 返回参数个数
	 * @param isReturnValue   boolean 是否有返回值  格式： {?=call p2(?,?)}; 
	 * @param isReturnResultSet boolean 是否返回记录集 (用于sql server ,oracle不能使用)  
	 * @return 返回对应的参数值Map("out1",value1),...,Map("outn",valuen);返回记录Map("rs");返回值Map("rv");out记录集 Map("outList1")...
	 */
	public Map callProcedure(String callStr, Object[] ins, int outCount,boolean isReturnValue,boolean isSqlReturnResultSet){
		return callProcedure(callStr, ins, outCount,isReturnValue, isSqlReturnResultSet,null);
	}
	/**
	 * 调用存储过程,没有返回值
	 * @author radish  Nov 12, 2016 4:58:05 PM 
	 * @param callStr  如:{call p1(?,?)}
	 * @param ins  Object[] 变量绑定对应的入参，如果无变量绑定则传null
	 */
	public  void callProcedure(String callStr, Object[] ins){
		   callProcedure(callStr, ins,0,false,false);
	}
	/**
	 * 调用存储过程返回结果值
	 * @author radish  Nov 12, 2016 5:10:15 PM 
	 * @param callStr   如:{call p1(?,?)}
	 * @param ins   Object[] 变量绑定对应的入参，如果无变量绑定则传null
	 * @return map("rs")= java.sql.ResultSet 
	 */
	public   Map callWithResultSet(String callStr, Object[] ins){
	   return   callProcedure(callStr, ins,0,false,true,null);
	}
	/**
	 * 调用存储过程返回结果值
	 * @author gxp  Nov 12, 2009 5:10:15 PM 
	 * @param callStr   如:{call p1(?,?)}
	 * @param ins   Object[] 变量绑定对应的入参，如果无变量绑定则传null
	 * @param RowMapper rm
	 * @return map("rs")= java.sql.ResultSet 
	 */
	public   Map callWithResultSet(String callStr, Object[] ins,RowMapper rm){
		 return  callProcedure(callStr, ins,0,false,true,rm);
	}
	/**
	 *  调用存储过程返回结果集和返回值
	 * @author gxp  Nov 12, 2009 5:10:15 PM 
	 * @param callStr   如:{?=call p1(?,?)}
	 * @param ins   Object[] 变量绑定对应的入参，如果无变量绑定则传null
	 * @param RowMapper rm
	 * @return map("rs")= java.sql.ResultSet 
	 */
	public   Map callWithResultSetAndReturn(String callStr, Object[] ins,RowMapper rm){
		 return  callProcedure(callStr, ins,0,true,true,rm);
	}
	/**
	 * 调用存储过程返回return返回值
	 * @author gxp  2017-04-14 下午05:38:19 
	 * @param callStr
	 * @param ins
	 * @return
	 */
	public   String callWithReturn(String callStr, Object[] ins){
		Map map=callProcedure(callStr, ins,0,true,false,null);
		return (String) map.get("rv");
	}
	/**
	 * 插入记录，同时得到自增加的键值 (用于sql server)
	 * @author gexiaopeng 2007-10-15
	 * @param sql sql语句
	 * @param parameters  参数集合
	 * @return long  增加的键值
	 */
	public long insertIntoSqlServer(final String sql, final Object parameters[]) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				if (parameters != null) {
					int len = parameters.length;
					for (int i = 0; i < len; i++) {
						ps.setObject(i + 1, parameters[i]);
					}
				}
				return ps;
			}

		}, keyHolder);
		return keyHolder.getKey().longValue();
	}
	/**
	* @Title: findCount
	* @Description:获取记录数(不带参数)
	* @param strSql   
	* @return int 
	* @time 2017-04-14 下午06:45:36
	* @author radish
	*/ 
	public int findCount(String strSql) {
		return jdbcTemplate.queryForInt(strSql);
	}
	/**
	* @Title: findCount
	* @Description:获取记录数(带参数)
	* @param strSql
	* @param params   
	* @return int 
	* @time 2017-04-14 下午06:44:59
	* @author radish
	*/ 
	public int findCount(String strSql, Object[] params) {
		return jdbcTemplate.queryForInt(strSql, params);
	}
	
	public Object execute(String callString, CallableStatementCallback action) {
		// TODO Auto-generated method stub
		return jdbcTemplate.execute(callString, action);
	}
}
