package com.pavikumbhar.type;

import java.io.Serializable;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Struct;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

import com.pavikumbhar.dto.PortCharacterstic;

import oracle.jdbc.driver.OraclePreparedStatement;
import oracle.jdbc.driver.OracleStatement;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;

public class ArrayUserType implements UserType {
    protected static final int  SQLTYPE = java.sql.Types.ARRAY;

 

    @Override
    public Object assemble(final Serializable cached, final Object owner) throws HibernateException {
        return cached;
    }

    @Override
    public Object deepCopy(final Object o) throws HibernateException {
        return o == null ? null : ((PortCharacterstic[]) o).clone();
    }

    @Override
    public Serializable disassemble(final Object o) throws HibernateException {
        return (Serializable) o;
    }

    @Override
    public boolean equals(final Object x, final Object y) throws HibernateException {
        return x == null ? y == null : x.equals(y);
    }

    @Override
    public int hashCode(final Object o) throws HibernateException {
        return o == null ? 0 : o.hashCode();
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Object replace(final Object original, final Object target, final Object owner) throws HibernateException {
        return original;
    }

    @Override
    public Class<PortCharacterstic[]> returnedClass() {
        return PortCharacterstic[].class;
    }

    @Override
    public int[] sqlTypes() {
        return new int[] { SQLTYPE };
    }

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner)
			throws HibernateException, SQLException {
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
	public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session)
			throws HibernateException, SQLException {
        Connection connection = st.getConnection();
     
        

        if (value == null) {
        	st.setNull( index, sqlTypes()[0] );
        } else {
        	PortCharacterstic[] castObject = (PortCharacterstic[]) value;
            
         /*
        	final PortCharacterstic portCharacterstic = (PortCharacterstic) value;
			final Object[] values = new Object[] { portCharacterstic.getPortName() , portCharacterstic.getPortValue() };
		
			final STRUCT struct = new STRUCT(StructDescriptor.createDescriptor(	"PORTCHARACTERSTIC",connection), connection, values);
        */    //Integer[] integers = ArrayUtils.toObject(castObject);
          /*  Array array = connection.createArrayOf("PORTCHARACTERSTIC_TABLE", castObject);*/
        	
        /*	 int iSize = castObject.length; 
             Object[] arrObj =null; 
             Object[][] recObj =null; 
      
             StructDescriptor structDescriptor = StructDescriptor.createDescriptor("PORTCHARACTERSTIC", connection); 
             ArrayDescriptor arrayDescriptor = ArrayDescriptor.createDescriptor("PORTCHARACTERSTIC_TABLE", connection); 

             arrObj = new Object[iSize]; 
             recObj = new Object[iSize][2]; 
             //Structuring obj and arrays
             for (int j = 0; j < iSize ;j++){ 
            	 PortCharacterstic ob= castObject[j];
                       recObj[j][0]=ob.getPortName();
                       recObj[j][1]=ob.getPortValue();
                     
      
                       arrObj[j] = new STRUCT(structDescriptor,connection, recObj[j]);
             }
             ARRAY vals = new ARRAY(arrayDescriptor, connection, arrObj);*/   
        	
        	 StructDescriptor structDescriptor = StructDescriptor.createDescriptor("PORTCHARACTERSTIC", connection); 
        	 
        	 Object[] portCharactersticStructArray = new Object[castObject.length];
        	 
        	 for (int i = 0; i < castObject.length; i++) {
        		 PortCharacterstic portCharacterstic=castObject[i];
        		 STRUCT m =new STRUCT(structDescriptor, connection, new Object[] {  portCharacterstic.getPortName() , portCharacterstic.getPortValue()});
        		 portCharactersticStructArray[i] = m;
			}
        	 
        	 ArrayDescriptor portCharactersticTableDesc = ArrayDescriptor.createDescriptor("PORTCHARACTERSTIC_TABLE", connection);
        	 
        	 ARRAY portCharStructArray = new ARRAY(portCharactersticTableDesc, connection, portCharactersticStructArray);
             st.setArray(index, portCharStructArray);
            
           // st1.setARRAY(1, portCharStructArray);
            
            
        }
		
	}
}
