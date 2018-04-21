package com.pavikumbhar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.Type;

import com.pavikumbhar.dto.PortCharacterstic;

import lombok.Data;

@Entity
@Data
public class PortTable {
	
	@Id
	@GeneratedValue
	public int id;
	
	/*
	@Type(type = "com.pavikumbhar.type.PortCharactersticType")
	public PortCharacterstic portCharacterstic;
	*/
	
	 @Type(type ="com.pavikumbhar.type.PortTableArrayUserType")
	 @Column(name = "portChar",columnDefinition="portCharTable")
     private PortCharacterstic[] portChar;

}
