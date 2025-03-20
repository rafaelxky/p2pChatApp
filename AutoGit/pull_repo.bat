@echo off
cd /d "D:\p2pChatApp\EncriptedDataProgram\p2pChatApp"

:: Prompt for branch name
set /p branchName=Enter the branch you want to pull from: 

:: Confirm before executing
set /p confirm=Are you sure you want to execute git pull from %branchName%? (Y/N): 
if /I "%confirm%" NEQ "Y" (
    echo Operation canceled. >> git_log.txt
    echo Operation canceled.
    exit /b
)

echo [%DATE% %TIME%] Running git pull from %branchName%... >> git_log.txt
git pull origin %branchName% >> git_log.txt 2>&1
echo [%DATE% %TIME%] Git pull from %branchName% completed. >> git_log.txt
echo Log updated in git_log.txt
