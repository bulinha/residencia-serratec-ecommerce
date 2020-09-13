package br.org.serratec.backend.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="foto")
@Data
public class Foto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="nome", nullable = false, length=200)
	private String nome;
	
	
	@Column(name="mimetype", nullable = false, length=200)
	private String mimetype;
	
	@Lob
	@Column(name="data", nullable = false)
	private byte[] data;

}
