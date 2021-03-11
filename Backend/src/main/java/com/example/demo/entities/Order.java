package com.example.demo.entities;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Table(name = "orders")
@Data
public class Order implements Serializable{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    private Integer orderId;
	
	@Basic
    @Column(name = "order_code",nullable=false,length = 50)
    private String orderCode;
	
	@Column(name = "order_date", updatable = false, nullable = false)
	@Temporal(TemporalType.DATE)
	private Calendar registDate;

	@ManyToOne
    @JoinColumn(name = "client_id", nullable = false, updatable = false)
    private Client client;
	
	@ManyToOne
    @JoinColumn(name = "product_id", nullable = false, updatable = false)
    private Product product;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public Calendar getRegistDate() {
		return registDate;
	}

	public void setRegistDate(Calendar registDate) {
		this.registDate = registDate;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
}
