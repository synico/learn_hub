@ECHO OFF
SET GIT_HOME=C:\Users\212706300\opt\git\2.17
SET CURRENT_PATH=%~dp0

CD %CURRENT_PATH%

ECHO 1st param: [%1] ******** 2nd param: [%2] ******** 3rd param: [%3]

FOR /D %%s in (*) do (
    ECHO repo: %%s
    start /b /wait %GIT_HOME%\bin\git.exe -C %CURRENT_PATH%%%s %1 %2 %3
    ECHO ----------------------------------------------------------
)
