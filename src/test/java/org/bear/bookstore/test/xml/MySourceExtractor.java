package org.bear.bookstore.test.xml;

import org.springframework.beans.factory.parsing.SourceExtractor;
import org.springframework.core.io.Resource;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MySourceExtractor implements SourceExtractor {

	@Override
	public Object extractSource(Object sourceCandidate, Resource definingResource) {
		log.error(JSON.toJSONString(sourceCandidate));
		log.error(JSON.toJSONString(definingResource));
		return null;
	}

}
