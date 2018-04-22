package com.wangguang.model.sys;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the sys_param database table.
 * 
 */
@Entity
@Table(name="sys_param")
public class Param implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;

	/**
	 * 键
	 */
	@Column(unique = true, name="`key`")
	private String key;

	/**
	 * 配置描述
	 */
	@Column(name="param_desc")
	private String paramDesc;

	/**
	 * 值
	 */
	private String value;

	/**
	 * 参数类型
	 */
	private String type;

	private Integer agentId;


	public Integer getAgentId() {
		return agentId;
	}

	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}

	public Param() {
    }

	public Param(String key, String value, Integer agentId, String paramDesc) {
		this.key = key;
		this.value = value;
		this.agentId = agentId;
		this.paramDesc = paramDesc;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getParamDesc() {
		return this.paramDesc;
	}

	public void setParamDesc(String paramDesc) {
		this.paramDesc = paramDesc;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}