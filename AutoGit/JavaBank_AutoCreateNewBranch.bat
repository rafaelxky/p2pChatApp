@echo off
cd /d "D:\p2pChatApp\EncriptedDataProgram\p2pChatApp"

:: Display available branches
echo Available branches:
git branch -r

:: Prompt for branch name to checkout from my-javabank
set /p branchName=Enter the branch you want to push to from my-javabank: 

:: Prompt for new branch name
set /p newBranch=Enter the name for your new branch: 

:: Show the commands as if they were typed by the user
echo.
echo You have entered the following commands:
echo git checkout %branchName%
echo git add .
echo git commit -m "final commit"
echo git push my-javabank %branchName%
echo git checkout master
echo git pull cfa-javabank master
echo git checkout -b %newBranch%
echo git add .
echo git commit -m "initial commit"
echo git push my-javabank %newBranch%
echo.

:: Confirm before executing all commands
set /p confirm=Are you sure you want to proceed with these actions? (Y/N): 
if /I "%confirm%" NEQ "Y" (
    echo Operation canceled. >> git_javabank_log.txt
    echo Operation canceled.
    exit /b
)

echo [%DATE% %TIME%] Checking out branch "%branchName%"... >> git_javabank_log.txt
echo Checking out branch "%branchName%"...
git checkout %branchName% >> git_javabank_log.txt

echo [%DATE% %TIME%] Staging all changes... >> git_javabank_log.txt
echo Staging all changes...
git add . >> git_javabank_log.txt

echo [%DATE% %TIME%] Committing changes with message "final commit"... >> git_javabank_log.txt
echo Committing changes with message "final commit"...
git commit -m "final commit" >> git_javabank_log.txt

echo [%DATE% %TIME%] Pushing branch "%branchName%" to my-javabank... >> git_javabank_log.txt
echo Pushing branch "%branchName%" to my-javabank...
git push my-javabank %branchName% >> git_javabank_log.txt

echo [%DATE% %TIME%] Switching to master branch... >> git_javabank_log.txt
echo Switching to master branch...
git checkout master >> git_javabank_log.txt

echo [%DATE% %TIME%] Pulling latest changes from cfa-javabank master... >> git_javabank_log.txt
echo Pulling latest changes from cfa-javabank master...
git pull cfa-javabank master >> git_javabank_log.txt

echo [%DATE% %TIME%] Creating and switching to new branch "%newBranch%"... >> git_javabank_log.txt
echo Creating and switching to new branch "%newBranch%"...
git checkout -b %newBranch% >> git_javabank_log.txt

echo [%DATE% %TIME%] Staging all changes... >> git_javabank_log.txt
echo Staging all changes...
git add . >> git_javabank_log.txt

echo [%DATE% %TIME%] Committing changes with message "initial commit"... >> git_javabank_log.txt
echo Committing changes with message "initial commit"...
git commit -m "initial commit" >> git_javabank_log.txt

echo [%DATE% %TIME%] Pushing new branch "%newBranch%" to my-javabank... >> git_javabank_log.txt
echo Pushing new branch "%newBranch%" to my-javabank...
git push my-javabank %newBranch% >> git_javabank_log.txt

echo [%DATE% %TIME%] Process completed successfully. Exiting... >> git_javabank_log.txt
echo Process completed successfully. Exiting...
exit
