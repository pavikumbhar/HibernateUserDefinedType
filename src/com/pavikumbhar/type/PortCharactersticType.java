package com.pavikumbhar.type;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Struct;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;


import com.pavikumbhar.dto.PortCharacterstic;

import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;

public class PortCharactersticType implements UserType {
	private static final int SQL_TYPE = Types.STRUCT;
	private static final String OBJECT_TYPE = "PORTCHARACTERSTIC";
	/**
	 * Returns the object from the 2 level cache
	 */
	
	public int[] sqlTypes() {
		return new int[] {SQL_TYPE};
	} 
	
	
	public Object assemble(Serializable cached, Object owner)
			throws HibernateException {
		return cached;
	}

	/**
	 * Used to create Snapshots of the object
	 */
	public Object deepCopy(Object value) throws HibernateException {
		
		if (value == null) {

			return null;

		}
		final PortCharacterstic recievedParam = (PortCharacterstic) value;
		final PortCharacterstic portCharacterstic = new PortCharacterstic(recievedParam);
		
		return portCharacterstic;
	}

	/**
	 * method called when Hibernate puts the data in a second level cache. The
	 * data is stored in a serializable form
	 */
	public Serializable disassemble(final Object value)
			throws HibernateException {
		return (Serializable) value;
	}

	public boolean equals(final Object o1, final Object o2)
			throws HibernateException {
		boolean isEqual = false;
		if (o1 == o2) {
			isEqual = true;
		}
		if (null == o1 || null == o2) {
			isEqual = false;
		} else {
			isEqual = o1.equals(o2);
		}
		return isEqual;
		// for this to work correctly the equals()
		// method must be implemented correctly by Cust_addressData class
	}

	public int hashCode(final Object arg0) throws HibernateException {
		return arg0.hashCode();
	}

	public boolean isMutable() {
		return true;
	}




	public Object replace(final Object original, final Object target,
			final Object owner) throws HibernateException {
		return this.deepCopy(original);
	}

	@SuppressWarnings("rawtypes")
	public Class returnedClass() {
		return PortCharacterstic.class;
	}


	@Override
	public Object nullSafeGet(ResultSet resultSet, String[] names, SharedSessionContractImplementor sharedSessionContractImplementor, Object arg3)
			throws HibernateException, SQLException {
		// owner here is class from where the call to retrieve data was made.
				// In this case the Test class

				final PortCharacterstic portCharacterstic = new PortCharacterstic();
				
				final Struct struct = (Struct) resultSet.getObject(names[0]);

				if (resultSet.wasNull()) {

					return null;

				}
				portCharacterstic.setPortName((String)struct.getAttributes()[0]);
				portCharacterstic.setPortValue((String)struct.getAttributes()[1]);
				
				return portCharacterstic;
	}


	@Override
	public void nullSafeSet(PreparedStatement statement, Object value, int index, SharedSessionContractImplementor arg3)
			throws HibernateException, SQLException {
		if (value == null) {
			statement.setNull(index, SQL_TYPE, OBJECT_TYPE);
		} 
		else 
		{
			final PortCharacterstic portCharacterstic = (PortCharacterstic) value;
			final Object[] values = new Object[] { portCharacterstic.getPortName() , portCharacterstic.getPortValue() };
			final Connection connection = statement.getConnection();
			final STRUCT struct = new STRUCT(StructDescriptor.createDescriptor(	OBJECT_TYPE,connection), connection, values);
			statement.setObject(index, struct, SQL_TYPE);
		}

		
	}

	

}
