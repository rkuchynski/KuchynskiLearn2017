## Jenkins jobs to build banking homework applications
### How to install jobs
1. Install and configure Jenkins.
2. Copy contents of the following directory to the `${JENKINS_HOME}/jobs/` directory.

### Jobs list
- **jmp7-banking-app-checkstyle** - triggered by Git repository update, performs checkstyle check on the project.
- **jmp7-banking-app-test** - triggered by _jmp7-banking-app-checkstyle_, runs tests on banking app.
- **jmp7-banking-app-build-war** - triggered by _jmp7-banking-app-test_ builds WAR executable and deploys it to Tomcat.

### Requirements
For successful deployment to Tomcat it must be running at deployment time and should have the following configuration:
- Tomcat should run on 8081 port (otherwise edit edit Tomcat port in **jmp7-banking-app-build-war** job configuration)
- The following role and user should be enabled on tomcat:
	- Role **manager-script**;
	- User **deployer** (password *deployer*) with role **manager-script**.