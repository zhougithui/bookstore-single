<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
        <title>add custom</title>
    </head>
    <body>
        <form method="post" action="<@spring.url '/api/cus/saveCus/${flag}'/>" >
	        <@spring.bind "cus.cusName"/>
		            姓名：<input type="text" name="${spring.status.expression}" value="${spring.status.value?default("")}"/>
		            <#list spring.status.errorMessages as error> <b>${error}</b> <br> </#list>
		            <br/>
            <@spring.bind "cus.phone"/>
		            电话：<input type="text" name="${spring.status.expression}" value="${spring.status.value?default("")}"/>
		            <#list spring.status.errorMessages as error> <b>${error}</b> <br> </#list>
		            <br/>
		        <input type="submit" name="Submit"/><br/>
        </form>
    </body>
</html>