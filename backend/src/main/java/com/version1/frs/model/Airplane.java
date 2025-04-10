package com.version1.frs.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "airplane")
public class Airplane {
		@Id
		@Column(name = "id")
		private Long id;
		@Column(name = "name")
		private String name;
		@Column(name = "model")
		private String model;
		@Column(name = "manufacturer")
		private String manufacturer;
		@Column(name = "capacity")
		private int capacity;

		public Airplane() {
		}

		public Airplane(Long id, String name, String model, String manufacturer, int capacity) {
			this.id = id;
			this.name = name;
			this.model = model;
			this.manufacturer = manufacturer;
			this.capacity = capacity;
		}


		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getModel() {
			return model;
		}

		public void setModel(String model) {
			this.model = model;
		}

		public String getManufacturer() {
			return manufacturer;
		}

		public void setManufacturer(String manufacturer) {
			this.manufacturer = manufacturer;
		}

		public int getCapacity() {
			return capacity;
		}

		public void setCapacity(int capacity) {
			this.capacity = capacity;
		}

	}



