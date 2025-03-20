@echo off
cd /d "D:\p2pChatApp\EncriptedDataProgram\p2pChatApp"

:: Prompt for commit message
set /p commitMessage=Enter commit message: 

:: Prompt for branch name
set /p branchName=Enter branch name: 

:: Confirm before executing Git commands
set /p confirm=Are you sure you want to commit and push? (Y/N): 
if /I "%confirm%" NEQ "Y" (
    echo Operation canceled.
    pause
    exit /b
)

:: Run Git commands and log output
echo [%DATE% %TIME%] Running git add, commit, and push... >> git_log.txt
git add . >> git_log.txt 2>&1
git commit -m "%commitMessage%" >> git_log.txt 2>&1
git push origin %branchName% >> git_log.txt 2>&1
echo [%DATE% %TIME%] Git push completed. >> git_log.txt

:: Check if log file is empty, delete if it is
for %%A in (git_log.txt) do if %%~zA==0 (
    echo No output generated. Deleting empty log file.
    del git_log.txt
) else (
    echo Log updated in git_log.txt
)

pause
