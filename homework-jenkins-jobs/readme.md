## Jenkins jobs to build banking homework applications
### How to install jobs
1. Install and configure Jenkins.
2. Copy contents of the following directory to the `${JENKINS_HOME}/jobs/` directory.

### Jobs list
- **jmp7-banking-app-checkstyle** - triggered by Git repository update, performs checkstyle check on the project.
- **jmp7-banking-app-test** - triggered by _jmp7-banking-app-checkstyle_, runs tests on banking app.
- **jmp7-banking-app-build-war** - triggered by _jmp7-banking-app-test_ builds WAR executable.