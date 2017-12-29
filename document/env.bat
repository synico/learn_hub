@echo off

if "%~1" == "" (
    SET JDK_VERSION=8.0
) else (
    SET JDK_VERSION=%~1
)

SET JAVA_HOME=D:\java\%JDK_VERSION%

SET SCALA_HOME=D:\scala\2.11.8

SET MAVEN_HOME=D:\maven\3.2.3

SET ANT_HOME=D:\ant\1.9.7
SET IVY_HOME=D:\ant\apache-ivy-2.4.0

SET DERBY_HOME=D:\derby\10.3.3.0

SET PATH=%JAVA_HOME%\bin;%SCALA_HOME%\bin;%MAVEN_HOME%\bin;%DERBY_HOME%\bin;%ANT_HOME%\bin;

REM set classpath
SET CLASSPATH=%DERBY_HOME%\lib\derby.jar;%DERBY_HOME%\lib\derbytools.jar;%IVY_HOME%\ivy-2.4.0.jar;

REM DERBY NETWORK URL: 'jdbc:derby://localhost:1527/D:\IBM\WCDE_ENT70\db\mall_network;user=APP;password=APP;create=true'