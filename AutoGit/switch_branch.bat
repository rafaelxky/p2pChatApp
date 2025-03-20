@echo off
cd /d "D:\p2pChatApp\EncriptedDataProgram\p2pChatApp"

:: List all branches
echo Available branches:
git branch -a

:: Prompt for branch name
set /p branchName=Enter the branch you want to switch to: 

:: Confirm before switching
set /p confirm=Are you sure you want to switch to "%branchName%"? (Y/N): 
if /I "%confirm%" NEQ "Y" (
    echo Operation canceled.
    timeout /t 2 >nul
    exit /b
)

:: Switch to the selected branch
git checkout %branchName% > git_branch_switch_log.txt 2>&1
echo [%DATE% %TIME%] Switched to branch "%branchName%". >> git_branch_switch_log.txt

:: Exit after completion
echo Exiting in 3 seconds...
timeout /t 3 >nul
exit
