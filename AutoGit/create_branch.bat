@echo off
cd /d "D:\p2pChatApp\EncriptedDataProgram\p2pChatApp"

:: Prompt for branch name
set /p branchName=Enter the new branch name: 

:: Confirm before creating the branch
set /p confirm=Are you sure you want to create branch "%branchName%"? (Y/N): 
if /I "%confirm%" NEQ "Y" (
    echo Operation canceled.
    timeout /t 2 >nul
    exit /b
)

:: Create and switch to the new branch
git checkout -b %branchName% > git_branch_log.txt 2>&1
echo [%DATE% %TIME%] Branch "%branchName%" created and switched to. >> git_branch_log.txt

:: Exit after completion
echo Exiting in 3 seconds...
timeout /t 3 >nul
exit