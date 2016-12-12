package org.bear.bookstore.test.xml;

import org.springframework.beans.factory.parsing.BeanDefinitionParsingException;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.beans.factory.parsing.ProblemReporter;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyProblenReporter implements ProblemReporter {

	@Override
	public void fatal(Problem problem) {
		log.error(JSON.toJSONString(problem));
		throw new BeanDefinitionParsingException(problem);
	}

	@Override
	public void error(Problem problem) {
		log.error(JSON.toJSONString(problem));
		throw new BeanDefinitionParsingException(problem);
	}

	@Override
	public void warning(Problem problem) {
		log.error(JSON.toJSONString(problem));
		throw new BeanDefinitionParsingException(problem);
	}

}
