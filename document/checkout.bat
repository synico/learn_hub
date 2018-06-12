@REM 
SET GIT_HOME=C:\Users\212706300\opt\git\2.17

SET PATH=%GIT_HOME%\bin;

@REM checkout jsf_hub
start /wait git clone https://github.com/synico/jsf_hub.git

@REM checkout notes
start /wait git clone https://github.com/synico/notes.git

@REM
start /wait git clone https://github.com/synico/spring_hub.git