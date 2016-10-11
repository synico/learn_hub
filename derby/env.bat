@echo off

SET JAVA_HOME=D:\java\7.0

SET ANT_HOME=E:\ant\1.9.7

SET MAVEN_HOME=E:\maven\3.2.3

SET DERBY_HOME=E:\derby\10.3.3.0

SET PATH=%PATH%;%JAVA_HOME%\bin;%ANT_HOME%\bin;%MAVEN_HOME%\bin;%DERBY_HOME%\bin;


REM set classpath
SET CLASSPATH=%DERBY_HOME%\lib\derby.jar;%DERBY_HOME%\lib\derbytools.jar;

REM derby net url: 'jdbc:derby://localhost:1527/D:\IBM\WCDE_ENT70\db\mall_network;user=root;password=APP;create=true';