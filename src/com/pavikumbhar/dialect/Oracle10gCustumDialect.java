package com.pavikumbhar.dialect;

import java.sql.Types;

import org.hibernate.dialect.Oracle10gDialect;

public class Oracle10gCustumDialect extends Oracle10gDialect {

	public Oracle10gCustumDialect() {
		super();
		registerColumnType(Types.STRUCT, "PORTCHARACTERSTIC");
		registerColumnType(Types.ARRAY, "PORTCHARACTERSTIC_TABLE");
	}

}
