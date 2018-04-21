package com.pavikumbhar.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class PortCharacterstic {
	
	private String portName;
	
	private String portValue;
	
	public PortCharacterstic() {
	}

	public PortCharacterstic(PortCharacterstic other) {
		this.setPortName(other.getPortName());
		this.setPortValue(other.getPortValue());
		

	}

}
