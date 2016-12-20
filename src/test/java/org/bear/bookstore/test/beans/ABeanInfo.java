package org.bear.bookstore.test.beans;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

public class ABeanInfo extends SimpleBeanInfo{
	
	@Override  
    public PropertyDescriptor[] getPropertyDescriptors() {  
        try {  
            //1. 将a绑定到ChartBean的a属性中      
            PropertyDescriptor titlePositionDescriptor = new PropertyDescriptor("a", B.class);  
            titlePositionDescriptor.setPropertyEditorClass(AEditor.class);  
            return new PropertyDescriptor[]{titlePositionDescriptor};  
        } catch (IntrospectionException e) {  
            e.printStackTrace();  
            return null;  
        }  
    }  
}
