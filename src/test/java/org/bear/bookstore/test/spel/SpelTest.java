package org.bear.bookstore.test.spel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.SpelCompilerMode;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import com.alibaba.fastjson.JSON;

public class SpelTest {
	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		ExpressionParser parser = new SpelExpressionParser();
		Expression exp = parser.parseExpression("'Hello World'");
		String message = (String) exp.getValue();
		System.out.println(message);
		
		//调用对象方法
		exp = parser.parseExpression("'Hello World'.concat('!')");
		message = (String) exp.getValue();
		System.out.println(message);
		
		//获取对象的属性
		exp = parser.parseExpression("'Hello World1111'.bytes");
		byte[] bytes = (byte[]) exp.getValue();
		System.out.println(new String(bytes));
		
		exp = parser.parseExpression("'Hello World'.bytes.length");
		int length = (Integer) exp.getValue();
		System.out.println("obj bytes length " + length);
		
		
		exp = parser.parseExpression("new String('hello worldxxxx').toUpperCase()");
		message = exp.getValue(String.class);
		
		System.out.println(message);
		
		
		
		
		GregorianCalendar c = new GregorianCalendar();
		c.set(1856, 7, 9);
		// The constructor arguments are name, birthday, and nationality.
		Inventor tesla = new Inventor("Nikola Tesla", c.getTime(), "Serbian");
		exp = parser.parseExpression("name");
		//指定根对象获取值
		String name = (String) exp.getValue(tesla);
		System.out.println(name);
		
		exp = parser.parseExpression("name");
		//通过上下文来获取name的值
		EvaluationContext context = new StandardEvaluationContext(tesla);
		name = (String) exp.getValue(context);
		
		System.out.println(name);
		
		//表达式
		exp = parser.parseExpression("name == 'Nikola Tesla'");
		boolean result = exp.getValue(context, Boolean.class); 
		System.out.println(result);
		
		
		
		class Simple {
		    public List<Boolean> booleanList = new ArrayList<Boolean>();
		}

		Simple simple = new Simple();

		simple.booleanList.add(true);

		StandardEvaluationContext simpleContext = new StandardEvaluationContext(simple);

		// false is passed in here as a string. SpEL and the conversion service will
		// correctly recognize that it needs to be a Boolean and convert it
		/**
		 * StandardEvaluationContext包含StandardTypeConverter的实例
		 * 而StandardTypeConverter使用的是DefaultConversionService的实例
		 * 对于泛型类型的转换默认机制
		 */
		parser.parseExpression("booleanList[0]").setValue(simpleContext, "false");

		// b will be false
		Boolean b = simple.booleanList.get(0);
		
		System.out.println(b);
		
		
		
		
		
		
		
		
		
		
		
		class Demo {
		    public List<String> list;
		}

		// Turn on:
		// - auto null reference initialization
		// - auto collection growing
		SpelParserConfiguration config = new SpelParserConfiguration(true,true);
		parser = new SpelExpressionParser(config);
		Expression expression = parser.parseExpression("list[3]");
		Demo demo = new Demo();
		Object o = expression.getValue(demo);
		// demo.list will now be a real collection of 4 entries
		// Each entry is a new empty String
		System.out.println(o.equals(""));
		
		
		
		/**
		 * 一下四种不支持IMMEDIATE
		 * 	expressions involving assignment
		 *	expressions relying on the conversion service
		 *	expressions using custom resolvers or accessors
		 *	expressions using selection or projection
		 */
		config = new SpelParserConfiguration(SpelCompilerMode.IMMEDIATE,SpelTest.class.getClassLoader());
		
		parser = new SpelExpressionParser(config);
		
		expression = parser.parseExpression("list[3]");
		o = expression.getValue(demo);
		
		System.out.println(o.equals(""));
		
		
		
		/**
		 * 关系运算
		 * 数学运算
		 * 赋值
		 * 构造函数
		 * 定义变量
		 */
		
		// evaluates to false
		boolean falseValue = parser.parseExpression(
		        "'xyz' instanceof T(int)").getValue(Boolean.class);
		
		System.out.println(falseValue);

		// evaluates to true
		boolean trueValue = parser.parseExpression(
		        "'5.00' matches '^-?\\d+(\\.\\d{2})?$'").getValue(Boolean.class);

		System.out.println(trueValue);
		//evaluates to false
		falseValue = parser.parseExpression(
		        "'5.0067' matches '^-?\\d+(\\.\\d{2})?$'").getValue(Boolean.class);
		System.out.println(falseValue);
		
		
		
		
		
		Class dateClass = parser.parseExpression("T(java.util.Date)").getValue(Class.class);

		Class stringClass = parser.parseExpression("T(String)").getValue(Class.class);

		boolean trueValue1 = parser.parseExpression(
		        "T(java.math.RoundingMode).CEILING < T(java.math.RoundingMode).FLOOR")
		        .getValue(Boolean.class);
		
		
		
		Inventor einstein = parser.parseExpression(
		        "new org.bear.bookstore.test.spel.Inventor('Albert Einstein',null,'German')")
		        .getValue(Inventor.class);
		
		
		
		
		tesla = new Inventor("Nikola Tesla", null, "Serbian");
		context = new StandardEvaluationContext(tesla);
		context.setVariable("newName", "Mike Tesla");

		parser.parseExpression("Name = #newName").getValue(context);

		System.out.println(tesla.getName()); // "Mike Tesla"
		
		
		
		
		// create an array of integers
		List<Integer> primes = new ArrayList<Integer>();
		primes.addAll(Arrays.asList(2,3,5,7,11,13,17));

		// create parser and set variable 'primes' as the array of integers
		parser = new SpelExpressionParser();
		context = new StandardEvaluationContext();
		context.setVariable("primes",primes);

		// all prime numbers > 10 from the list (using selection ?{...})
		// evaluates to [11, 13, 17]
		List<Integer> primesGreaterThanTen = (List<Integer>) parser.parseExpression(
		        "#primes.?[#this>10]").getValue(context);
		System.out.println(JSON.toJSON(primesGreaterThanTen));
		
		
		
		
		parser = new SpelExpressionParser();
		StandardEvaluationContext  context1 = new StandardEvaluationContext();

		context1.registerFunction("reverseString",
		    StringUtils.class.getDeclaredMethod("reverse", new Class[] { String.class }));

		String helloWorldReversed = parser.parseExpression(
		    "#reverseString('hello')").getValue(context1, String.class);
		
		System.out.println(helloWorldReversed);
		
		
		
		
		
		parser = new SpelExpressionParser();

		context = new StandardEvaluationContext(tesla);

		name = parser.parseExpression("Name?:'Elvis Presley'").getValue(context, String.class);

		System.out.println(name); // Mike Tesla

		tesla.setName(null);

		name = parser.parseExpression("Name?:'Elvis Presley'").getValue(context, String.class);

		System.out.println(name); // Elvis Presley
	}
}
