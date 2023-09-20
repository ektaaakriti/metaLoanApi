package snippet;

public class Snippet {
	<!DOCTYPE log4j:configuration SYSTEM "log4j.dtd">
	<log4j:configuration debug="true"
		xmlns:log4j='http://jakarta.apache.org/log4j/'>
	
		<appender name="console"
			class="org.apache.log4j.ConsoleAppender">
			<param name="Target" value="System.out" />
			<layout class="org.apache.log4j.PatternLayout">
				<param name="ConversionPattern"
					value="%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n" />
			</layout>
		</appender>
		<appender name="fileAppender"
			class="org.apache.log4j.RollingFileAppender">
			<param name="File" value="c:\\kafka\\demoApplication.log" />
			<layout class="org.apache.log4j.PatternLayout">
				<param name="ConversionPattern"
					value="%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n" />
			</layout>
		</appender>
		<root>
			<priority value="info,debug" />
			<appender-ref ref="fileAppender" />
			<appender-ref ref="console" />
		</root>
	
	</log4j:configuration>
}

