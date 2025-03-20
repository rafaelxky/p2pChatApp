:: push_repo.bat
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

:: Prompt for commit message
set /p commitMessage=Enter commit message: 

:: Prompt for branch name
set /p branchName=Enter branch name: 

:: Confirm before executing Git commands
set /p confirm=Are you sure you want to commit and push? (Y/N): 
if /I "%confirm%" NEQ "Y" (
    echo Operation canceled. >> "%logPath%"
    echo Operation canceled.
    exit /b
)

:: Run Git commands and log output
echo [%DATE% %TIME%] Running git add, commit, and push... >> "%logPath%"
git add . >> "%logPath%" 2>&1
echo [%DATE% %TIME%] Committing with message: "%commitMessage%" >> "%logPath%"
git commit -m "%commitMessage%" >> "%logPath%" 2>&1
echo [%DATE% %TIME%] Pushing to branch: "%branchName%" >> "%logPath%"
git push origin %branchName% >> "%logPath%" 2>&1
echo [%DATE% %TIME%] Git push completed. >> "%logPath%"

:: Check if log file is empty, delete if it is
for %%A in ("%logPath%") do if %%~zA==0 (
    echo No output generated. Deleting empty log file.
    del "%logPath%"
) else (
    echo Log updated in "%logPath%"
)

exit
