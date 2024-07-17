@echo off
REM Set the working directory to the script's location
cd /d %~dp0

REM Find all Java files and store the list in filelist.txt
dir /s /b *.java > filelist.txt

REM Create bin directory if it doesn't exist
if not exist bin mkdir bin

REM Compile all Java files listed in filelist.txt
javac -d bin -cp "libs/*" @filelist.txt -encoding UTF-8
if %errorlevel% neq 0 (
    echo Compilation failed.
    exit /b %errorlevel%
)

REM Clear the console screen
cls

REM Run the Main class
java -cp "bin;libs/*" Main
if %errorlevel% neq 0 (
    echo Execution failed.
    exit /b %errorlevel%
)

REM Clean up
del /f /q filelist.txt

@REM echo Done.
@REM pause