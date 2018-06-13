@ECHO OFF
SET GIT_HOME=C:\Users\212706300\opt\git\2.17
SET PATH=%GIT_HOME%\bin;
SET CURRENT_PATH=%~dp0

CD %CURRENT_PATH%

FOR /D %%s in (*) do (
    ECHO %%s
    start /b /wait git -C %CURRENT_PATH%%%s status -s
)
