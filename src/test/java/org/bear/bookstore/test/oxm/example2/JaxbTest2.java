package org.bear.bookstore.test.oxm.example2;

import org.bear.bookstore.test.oxm.util.JaxbUtil;
import org.junit.Test;  
  
/** 
 * @author      zhuc 
 * @create      2013-3-29 下午2:52:00 
 */  
public class JaxbTest2 {  
  
    @Test  
    public void showMarshaller() {  
  
        Student student = new Student();  
        student.setId(12);  
        student.setName("test");  
  
        Role role = new Role();  
        role.setDesc("管理");  
        role.setName("班长");  
  
        student.setRole(role);  
  
        String str = JaxbUtil.convertToXml(student);  
        System.out.println(str);  
    }  
  
    @Test  
    public void showUnMarshaller() {  
        String str = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"+  
            "<student id=\"12\">"+  
            "    <name>test</name>"+  
             "   <role>"+  
              "      <name>班长</name>"+  
               "     <desc>管理</desc>"+  
                "</role>"+  
            "</student>";  
        Student student = JaxbUtil.converyToJavaBean(str, Student.class);  
        System.out.println(student);  
    }  
      
}