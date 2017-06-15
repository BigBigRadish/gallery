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
	 * ������SQL��ѯȫ��
	 * @author gexiaopeng 2007-9-6
	 * @param sqlQueryString sql��ѯ���
	 * @param parameters ��ѯ����
	 * @return list
	 */
	
	public List findList(String sql, Object[] parameters){
		return jdbcTemplate.query(sql,parameters,new ColumnMapRowMapper());
	}
	/**
	 * ������SQL��ѯȫ��
	 * @author gexiaopeng 2007-9-6
	 * @param sqlQueryString sql��ѯ���
	 * @param parameters ��ѯ����
	 * @return list
	 */
	public List findList(String sql, Object[] parameters,RowMapper rowMapper){
		return jdbcTemplate.query(sql,parameters,rowMapper);
	}
	/**
	 * SQL��ѯȫ��(��������)
	 * @author gexiaopeng 2007-10-8
	 * @param sqlQueryString sql��ѯ���
	 * @return  list
	 */
	public List findList(String sql ){
		return jdbcTemplate.query(sql,new ColumnMapRowMapper());
	}
	/**
	 * SQL��ѯȫ��(��������)
	 * @author gexiaopeng 2007-10-8
	 * @param sqlQueryString sql��ѯ���
	 * @return  list
	 */
	public List findList(String sql ,RowMapper rowMapper){
		return jdbcTemplate.query(sql,rowMapper);
		
	}
	/**
	 * ִ��sql(������)����(insert delete update)
	 * @author gexiaopeng 2007-9-6
	 * @param srtSql sql���
	 * @param parameters ��������
	 * @return int
	 */
	public int executeSQL(String sql, Object[] parameters){
		return jdbcTemplate.update(sql,parameters);
	}
	/**
	 * ִ��sql(��������)����(insert delete update)
	 * @author gexiaopeng 2007-10-9
	 * @param strSql sql���
	 * @return int
	 */
	public int executeSQL(String sql){
		return jdbcTemplate.update(sql);
	}
	/**
	 * �õ�һ��map����(������)
	 * @author gexiaopeng 2007-9-12
	 * @param strSql sql���
	 * @param parameters  ��������
	 * @return map  û�з���null
	 */
	public Map getMap(String sql, Object[] parameters){
		List ls=findList(sql,parameters);
		if(ls!=null && ls.size()==1){
			return (Map)ls.iterator().next();
		}
		return null;
		
	}
	/**
	 * �õ�һ��map����(��������)
	 * @author gexiaopeng 2007-9-12
	 * @param strSql sql���
	 * @return map  û�з���null
	 */
	public Map getMap(String sql){
		return getMap(sql,null);
	}
	/**
	 * ���ô洢���̵ȵĻ�������
	 * @param callStr 	String  ����õ���䣬�磺call p1(?,?) ���� ?=call p2(?,?);
	 *                  ע�⣺sql server �б���Ҫ�� "{}" ��:{call p1(?,?)} ����{?=call p2(?,?)}
	 * @param ins		Object[] �����󶨶�Ӧ����Σ�����ޱ�������null
	 * @param outCount	int ���ز�������
		 * @param isReturnValue   boolean �Ƿ��з���ֵ  ��ʽ�� {?=call p2(?,?)}; 
	 * @param isReturnResultSet boolean �Ƿ񷵻ؼ�¼�� (����sql server ,oracle����ʹ��)  
	 * @param  RowMapper 
	 * @return ���ض�Ӧ�Ĳ���ֵMap("out1",value1),...,Map("outn",valuen);���ؼ�¼Map("rs");����ֵMap("rv");out��¼�� Map("outList1")...
	 */
	public Map callProcedure(String callStr, Object[] ins, int outCount,boolean isReturnValue,boolean isSqlReturnResultSet,RowMapper rm){
		
		List inName = new ArrayList();
		Map inValue = new HashMap();
		SqlParameter sp = null;
		SqlOutParameter sop = null;
		Map retMap = null;
		//sql server ���ؼ�¼
		if(isSqlReturnResultSet){
			if(rm==null){
				rm=new ColumnMapRowMapper();
			}
			SqlReturnResultSet ssop = new SqlReturnResultSet("rs",rm);
			inName.add(ssop);
		}
		//return ֵ
		if(isReturnValue){
			sop = new SqlOutParameter("rv", Types.VARCHAR);
			inName.add(sop);
		}
		
		// ���
		if (ins !=null && ins.length > 0) {
			for (int i = 0; i < ins.length; i++) {
				sp = new SqlParameter("In" + i, Types.VARCHAR);
				inName.add(sp);
				inValue.put("In" + i, ins[i]);
			}
		}
		
		// ����
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
	 * ���ô洢���̵ȵĻ�������
	 * @param callStr 	String  ����õ���䣬�磺call p1(?,?) ���� ?=call p2(?,?);
	 *                  ע�⣺sql server �б���Ҫ�� "{}" ��:{call p1(?,?)} ����{?=call p2(?,?)}
	 * @param ins		Object[] �����󶨶�Ӧ����Σ�����ޱ�������null
	 * @param outCount	int ���ز�������
	 * @param isReturnValue   boolean �Ƿ��з���ֵ  ��ʽ�� {?=call p2(?,?)}; 
	 * @param isReturnResultSet boolean �Ƿ񷵻ؼ�¼�� (����sql server ,oracle����ʹ��)  
	 * @return ���ض�Ӧ�Ĳ���ֵMap("out1",value1),...,Map("outn",valuen);���ؼ�¼Map("rs");����ֵMap("rv");out��¼�� Map("outList1")...
	 */
	public Map callProcedure(String callStr, Object[] ins, int outCount,boolean isReturnValue,boolean isSqlReturnResultSet){
		return callProcedure(callStr, ins, outCount,isReturnValue, isSqlReturnResultSet,null);
	}
	/**
	 * ���ô洢����,û�з���ֵ
	 * @author radish  Nov 12, 2016 4:58:05 PM 
	 * @param callStr  ��:{call p1(?,?)}
	 * @param ins  Object[] �����󶨶�Ӧ����Σ�����ޱ�������null
	 */
	public  void callProcedure(String callStr, Object[] ins){
		   callProcedure(callStr, ins,0,false,false);
	}
	/**
	 * ���ô洢���̷��ؽ��ֵ
	 * @author radish  Nov 12, 2016 5:10:15 PM 
	 * @param callStr   ��:{call p1(?,?)}
	 * @param ins   Object[] �����󶨶�Ӧ����Σ�����ޱ�������null
	 * @return map("rs")= java.sql.ResultSet 
	 */
	public   Map callWithResultSet(String callStr, Object[] ins){
	   return   callProcedure(callStr, ins,0,false,true,null);
	}
	/**
	 * ���ô洢���̷��ؽ��ֵ
	 * @author gxp  Nov 12, 2009 5:10:15 PM 
	 * @param callStr   ��:{call p1(?,?)}
	 * @param ins   Object[] �����󶨶�Ӧ����Σ�����ޱ�������null
	 * @param RowMapper rm
	 * @return map("rs")= java.sql.ResultSet 
	 */
	public   Map callWithResultSet(String callStr, Object[] ins,RowMapper rm){
		 return  callProcedure(callStr, ins,0,false,true,rm);
	}
	/**
	 *  ���ô洢���̷��ؽ�����ͷ���ֵ
	 * @author gxp  Nov 12, 2009 5:10:15 PM 
	 * @param callStr   ��:{?=call p1(?,?)}
	 * @param ins   Object[] �����󶨶�Ӧ����Σ�����ޱ�������null
	 * @param RowMapper rm
	 * @return map("rs")= java.sql.ResultSet 
	 */
	public   Map callWithResultSetAndReturn(String callStr, Object[] ins,RowMapper rm){
		 return  callProcedure(callStr, ins,0,true,true,rm);
	}
	/**
	 * ���ô洢���̷���return����ֵ
	 * @author gxp  2017-04-14 ����05:38:19 
	 * @param callStr
	 * @param ins
	 * @return
	 */
	public   String callWithReturn(String callStr, Object[] ins){
		Map map=callProcedure(callStr, ins,0,true,false,null);
		return (String) map.get("rv");
	}
	/**
	 * �����¼��ͬʱ�õ������ӵļ�ֵ (����sql server)
	 * @author gexiaopeng 2007-10-15
	 * @param sql sql���
	 * @param parameters  ��������
	 * @return long  ���ӵļ�ֵ
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
	* @Description:��ȡ��¼��(��������)
	* @param strSql   
	* @return int 
	* @time 2017-04-14 ����06:45:36
	* @author radish
	*/ 
	public int findCount(String strSql) {
		return jdbcTemplate.queryForInt(strSql);
	}
	/**
	* @Title: findCount
	* @Description:��ȡ��¼��(������)
	* @param strSql
	* @param params   
	* @return int 
	* @time 2017-04-14 ����06:44:59
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
