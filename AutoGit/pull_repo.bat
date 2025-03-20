@echo off
cd /d "D:\p2pChatApp\EncriptedDataProgram\p2pChatApp"

:: Confirm before executing
set /p confirm=Are you sure you want to execute git pull? (Y/N): 
if /I "%confirm%" NEQ "Y" (
    echo Operation canceled. >> git_log.txt
    echo Operation canceled.
    exit /b
)

echo [%DATE% %TIME%] Running git pull... >> git_log.txt
git pull origin main >> git_log.txt 2>&1
echo [%DATE% %TIME%] Git pull completed. >> git_log.txt
echo Log updated in git_log.txt
