package com.ky.model;

import java.util.HashSet;
import java.util.Set;

public class Node {

	/**
	 * 自身关系 一对多映射
	 */
	private long id;
	private String name;
	
	private Node parentNode;//父节点
	
	private Set<Node> childNodes=new HashSet<Node>();//子节点

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Node getParentNode() {
		return parentNode;
	}

	public void setParentNode(Node parentNode) {
		this.parentNode = parentNode;
	}

	public Set<Node> getChildNodes() {
		return childNodes;
	}

	public void setChildNodes(Set<Node> childNodes) {
		this.childNodes = childNodes;
	}
	
	
	
}
