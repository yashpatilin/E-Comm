package com.youpro.EComm_backend.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	@Id
	private int id;
	private String name;
	private String description;
	private  String brand;
	private double price;
	private String category;
	private Date releaseDate;
	private int quantity;

	private String imageName;
	private String imageType;
	private byte[] imageDate;
}
