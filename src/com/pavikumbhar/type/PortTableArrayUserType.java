package com.pavikumbhar.type;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Struct;

import oracle.jdbc.driver.OraclePreparedStatement;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;

import org.hibernate.engine.spi.SharedSessionContractImplementor;

import com.pavikumbhar.dto.PortCharacterstic;
import com.pavikumbhar.type.util.AbstractUserType;


public class PortTableArrayUserType extends AbstractUserType<PortCharacterstic[]> {

	 protected static final int  SQLTYPE = java.sql.Types.ARRAY;

	public PortTableArrayUserType() {
		super(PortCharacterstic[].class);
	}

	 @Override
	    public int[] sqlTypes() {
	        return new int[] { SQLTYPE };
	    }
	@Override
	public PortCharacterstic[] get(ResultSet rs, String[] names,
			SharedSessionContractImplementor session, Object owner)
			throws SQLException {
		
		   Array array = rs.getArray(names[0]);
	        if (array == null) {
	            return null;
	        }
	        Object[] structArray = (Object[]) array.getArray();
	        PortCharacterstic[] portCharactersticArray = new PortCharacterstic[structArray.length];
	       
	        
	        for (int i = 0; i < structArray.length; i++) {
	            Struct struct=(Struct) structArray[i];
	        	PortCharacterstic portCharacterstic =new PortCharacterstic();
	        	portCharacterstic.setPortName((String)struct.getAttributes()[0]);
				portCharacterstic.setPortValue((String)struct.getAttributes()[1]);
				portCharactersticArray[i]=portCharacterstic;
			}
	        
	        
	        //  return ArrayUtils.toPrimitive(javaArray);
	       
	        return portCharactersticArray;
		
	}

	@Override
	public void set(PreparedStatement st, PortCharacterstic[] value, int index,
			SharedSessionContractImplementor session) throws SQLException {
		 Connection connection = st.getConnection();
	        OraclePreparedStatement st1=(OraclePreparedStatement) st;
	       if (value == null) {
	            st1.setNull( index, sqlTypes()[0] );
	        } else {
	        	//PortCharacterstic[] castObject = (PortCharacterstic[]) value;
	                	
	        	 StructDescriptor structDescriptor = StructDescriptor.createDescriptor("PORTCHARACTERSTIC", connection); 
	        	 
	        	 Object[] portCharactersticStructArray = new Object[value.length];
	        	 
	        	 for (int i = 0; i < value.length; i++) {
	        		 PortCharacterstic portCharacterstic=value[i];
	        		 STRUCT m =new STRUCT(structDescriptor, connection, new Object[] {  portCharacterstic.getPortName() , portCharacterstic.getPortValue()});
	        		 portCharactersticStructArray[i] = m;
				}
	        	 
	        	 ArrayDescriptor portCharactersticTableDesc = ArrayDescriptor.createDescriptor("PORTCHARACTERSTIC_TABLE", connection);
	        	 
	        	 ARRAY portCharStructArray = new ARRAY(portCharactersticTableDesc, connection, portCharactersticStructArray);
	         	            
	            st1.setARRAY(1, portCharStructArray);
		
	}
	       }


}


	
