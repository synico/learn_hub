@echo off

if "%~1" == "" (
    SET JDK_VERSION=8.0
) else (
    SET JDK_VERSION=%~1
)

SET JAVA_HOME=D:\java\%JDK_VERSION%

SET SCALA_HOME=D:\scala\2.11

SET SBT_HOME=D:\scala\sbt\1.0.11

SET MAVEN_HOME=D:\maven\3.3.9

SET GRADLE_HOME=D:\gradle\3.5

SET DERBY_HOME=D:\derby\10.3.3.0

SET ANT_HOME=E:\apache-ant-1.9.7
SET IVY_HOME=E:\apache-ivy-2.4.0

SET GIT_HOME=D:\Git

SET PATH=%JAVA_HOME%\bin;%MAVEN_HOME%\bin;%GRADLE_HOME%\bin;%DERBY_HOME%\bin;%ANT_HOME%\bin;%SCALA_HOME%\bin;%SBT_HOME%\bin;%GIT_HOME%\cmd;

REM set classpath
SET CLASSPATH=%DERBY_HOME%\lib\derby.jar;%DERBY_HOME%\lib\derbytools.jar;

REM derby net url: 'jdbc:derby://localhost:1527/D:\IBM\WCDE_ENT70\db\mall_network;user=root;password=APP;create=true';