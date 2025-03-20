:: push_repo.bat
::@echo off

:: Read repository path from path.txt
set /p repoPath=<path.txt
echo Repository path: "%repoPath%"
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
    echo Operation canceled.
    exit /b
)

:: Run Git commands and log output
echo [%DATE% %TIME%] Running git add, commit, and push... >> git_log.txt
git add . >> git_log.txt 2>&1
echo [%DATE% %TIME%] Committing with message: "%commitMessage%" >> git_log.txt
git commit -m "%commitMessage%" >> git_log.txt 2>&1
echo [%DATE% %TIME%] Pushing to branch: "%branchName%" >> git_log.txt
git push origin %branchName% >> git_log.txt 2>&1
echo [%DATE% %TIME%] Git push completed. >> git_log.txt

:: Check if log file is empty, delete if it is
for %%A in (git_log.txt) do if %%~zA==0 (
    echo No output generated. Deleting empty log file.
    del git_log.txt
) else (
    echo Log updated in git_log.txt
)

exit
