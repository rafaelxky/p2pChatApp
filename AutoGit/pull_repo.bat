:: pull_repo.bat
@echo off
setlocal enabledelayedexpansion

:: Read repository path and log file path from path.txt
set count=0
for /f "tokens=*" %%A in (path.txt) do (
    set /a count+=1
    if !count! equ 1 set "repoPath=%%A"
    if !count! equ 2 set "logPath=%%A"
)

echo Repository path: "%repoPath%"
echo Log file path: "%logPath%"
cd /d "%repoPath%"

:: Display available branches
echo Available branches:
git branch -r

:: Prompt for branch name
set /p branchName=Enter the branch you want to pull from: 

:: Confirm before executing
set /p confirm=Are you sure you want to execute git pull from %branchName%? (Y/N): 
if /I "%confirm%" NEQ "Y" (
    echo Operation canceled. >> "%logPath%"
    echo Operation canceled.
    exit /b
)

:: Run Git pull and log output
echo [%DATE% %TIME%] Running git pull from %branchName%... >> "%logPath%"
git pull origin %branchName% >> "%logPath%" 2>&1
echo [%DATE% %TIME%] Git pull from %branchName% completed. >> "%logPath%"
echo Log updated in "%logPath%"

exit
