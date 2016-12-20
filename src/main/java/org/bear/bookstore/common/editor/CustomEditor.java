package org.bear.bookstore.common.editor;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang3.StringUtils;
import org.bear.bookstore.common.util.BookStoreConstatns;
import org.bear.bookstore.domain.Custom;

/**
 * PropertyEditor使用实例，将属性,属性转换成对象
 * @author q
 *
 */
public class CustomEditor extends PropertyEditorSupport {
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Object source = null;
		if(StringUtils.isNotBlank(text)){
			String[] properties = StringUtils.split(text, BookStoreConstatns.COMMA);
			if(properties.length > 0){
				Custom custom = new Custom();
				custom.setAddress(properties[0]);
				custom.setCusName(properties[1]);
				
				source = custom;
			}
		}
		setValue(source);
	}
}
