/**
 * Copyright 2011-2012 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.dbist.metadata;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.common.util.ValueUtils;

import org.dbist.exception.DbistRuntimeException;

/**
 * @author Steve M. Jung
 * @since 2011. 7. 10 (version 0.0.1)
 */
public class Table {
	private String domain;
	private String name;
	private Class<?> clazz;
	private List<String> pkColumnNameList;
	private String[] pkFieldNames;
	private List<String> titleColumnNameList;
	private List<String> listedColumnNameList;
	private List<Column> columnList = new ArrayList<Column>();
	private String insertSql;
	private String updateSql;
	private String deleteSql;

	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Class<?> getClazz() {
		return clazz;
	}
	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}
	public List<String> getPkColumnNameList() {
		return pkColumnNameList;
	}
	public void setPkColumnNameList(List<String> pkColumnName) {
		this.pkColumnNameList = pkColumnName;
	}
	public boolean isPkColmnName(String name) {
		return getPkColumnNameList().contains(name);
	}
	public String[] getPkFieldNames() {
		populate();
		return pkFieldNames;
	}
	public boolean isPkFieldName(String name) {
		String columnName = toColumnName(name);
		return columnName != null && isPkColmnName(columnName);
	}
	public List<String> getTitleColumnNameList() {
		populate();
		return titleColumnNameList;
	}
	public List<String> getListedColumnNameList() {
		populate();
		return listedColumnNameList;
	}
	private void populate() {
		if (this.titleColumnNameList != null)
			return;
		synchronized (this) {
			if (this.titleColumnNameList != null)
				return;
			List<String> titleColumnName = new ArrayList<String>(1);
			listedColumnNameList = new ArrayList<String>(1);
			String titleCandidate = null;
			for (Column column : this.columnList) {
				if (column.isTitle()) {
					titleColumnName.add(column.getName());
				} else if (column.isListed()) {
					listedColumnNameList.add(column.getName());
				} else if (!column.isPrimaryKey() && titleCandidate == null) {
					titleCandidate = column.getName();
				}
			}
			if (titleColumnName.isEmpty() && titleCandidate != null)
				titleColumnName.add(titleCandidate);
			List<String> pkFieldNameList = new ArrayList<String>();
			for (String columnName : pkColumnNameList)
				pkFieldNameList.add(toFieldName(columnName));
			pkFieldNames = pkFieldNameList.toArray(new String[pkFieldNameList.size()]);
			this.titleColumnNameList = titleColumnName;
		}
	}
	public List<Column> getColumnList() {
		return columnList;
	}
	public Column addColumn(Column column) {
		this.columnList.add(column);
		return column;
	}
	private Map<String, Column> columnMap;
	public Column getColumn(String name) {
		ValueUtils.assertNotNull("name", name);
		if (this.columnMap == null) {
			synchronized (this) {
				if (this.columnMap == null) {
					Map<String, Column> columnMap = new HashMap<String, Column>(this.columnList.size());
					for (Column column : this.columnList)
						columnMap.put(column.getName(), column);
					this.columnMap = columnMap;
				}
			}
		}
		return columnMap.get(name.toLowerCase());
	}
	public Column getColumnByFieldName(String fieldName) {
		String columnName = toColumnName(fieldName);
		return columnName == null ? null : getColumn(columnName);
	}
	public Field getField(String name) {
		Column column = getColumnByFieldName(name);
		return column == null ? null : column.getField();
	}
	public Field getFieldByColumnName(String columnName) {
		Column column = getColumn(columnName);
		return column == null ? null : column.getField();
	}
	private Map<String, String> fieldNameColumNameMap;
	public String toColumnName(String fieldName) {
		if (this.fieldNameColumNameMap == null) {
			synchronized (this) {
				if (this.fieldNameColumNameMap == null) {
					Map<String, String> fieldNameColumnNameMap = new HashMap<String, String>(this.columnList.size());
					for (Column column : this.columnList)
						fieldNameColumnNameMap.put(column.getField().getName(), column.getName());
					this.fieldNameColumNameMap = fieldNameColumnNameMap;
				}
			}
		}
		return fieldNameColumNameMap.get(fieldName);
	}
	public String toFieldName(String columnName) {
		Column column = getColumn(columnName);
		return column == null ? null : column.getField().getName();
	}

	public String getInsertSql() {
		if (insertSql != null)
			return insertSql;
		synchronized (this) {
			if (insertSql != null)
				return insertSql;
			StringBuffer buf = new StringBuffer("insert into ").append(getDomain()).append(".").append(getName()).append("(");
			int i = 0;
			for (Column column : getColumnList())
				buf.append(i++ == 0 ? "" : ", ").append(column.getName());
			buf.append(") values(");
			i = 0;
			for (Column column : getColumnList())
				buf.append(i++ == 0 ? ":" : ", :").append(column.getField().getName());
			buf.append(")");
			insertSql = buf.toString();
		}
		return insertSql;
	}

	public String getUpdateSql() {
		return _getUpdateSql();
	}
	public String getUpdateSql(String... fieldNames) {
		return _getUpdateSql(fieldNames);
	}
	private String _getUpdateSql(String... fieldNames) {
		// Update all fields
		if (ValueUtils.isEmpty(fieldNames)) {
			if (updateSql != null)
				return updateSql;
			synchronized (this) {
				if (updateSql != null)
					return updateSql;
				StringBuffer buf = new StringBuffer("update ").append(getDomain()).append(".").append(getName()).append(" set ");
				StringBuffer whereBuf = new StringBuffer(" where ");
				int i = 0;
				int j = 0;
				for (Column column : getColumnList()) {
					if (column.isPrimaryKey()) {
						whereBuf.append(j++ == 0 ? "" : ", ").append(column.getName()).append(" = ").append(":").append(column.getField().getName());
						continue;
					}
					buf.append(i++ == 0 ? "" : ", ").append(column.getName()).append(" = :").append(column.getField().getName());
				}
				updateSql = buf.append(whereBuf).toString();
			}
			return updateSql;
		}

		// Update some fields
		StringBuffer buf = new StringBuffer("update ").append(getDomain()).append(".").append(getName()).append(" set ");
		StringBuffer whereBuf = new StringBuffer(" where ");
		int i = 0;
		int j = 0;
		for (String fieldName : fieldNames) {
			Column column = getColumnByFieldName(fieldName);
			if (column.isPrimaryKey())
				throw new DbistRuntimeException("Update primary key is not supported. " + getDomain() + "." + getName() + getPkColumnNameList());
			buf.append(i++ == 0 ? "" : ", ").append(toColumnName(fieldName)).append(" = :").append(fieldName);
		}
		for (String columnName : getPkColumnNameList())
			whereBuf.append(j++ == 0 ? "" : ", ").append(columnName).append(" = ").append(":").append(toFieldName(columnName));
		return buf.append(whereBuf).toString();
	}

	public String getDeleteSql() {
		if (deleteSql != null)
			return deleteSql;
		synchronized (this) {
			if (deleteSql != null)
				return deleteSql;
			StringBuffer buf = new StringBuffer("delete from ").append(getDomain()).append(".").append(getName());
			int i = 0;
			for (String columnName : getPkColumnNameList())
				buf.append(i++ == 0 ? " where " : " and ").append(columnName).append(" = :").append(getColumn(columnName).getField().getName());
			deleteSql = buf.toString();
		}
		return deleteSql;
	}
}