## Banking homework application.
### Run application
1. Build project with gradle:
    **gradlew clean build**
2. Run banking-app module:
    **gradlew banking-app:bootRun**
3. Open application readme page in any browser at url:
    **{your_hostname}:8080/**
4. Run requests using any REST tool.

### Browse embedded database
1. By default, embedded DB is located at `d:\temp\banking-app-database`.
2. Install SQuirreL SQL browser: [download link](http://squirrel-sql.sourceforge.net/).
3. Download derby embedded JDBC driver: [maven central link](http://central.maven.org/maven2/org/apache/derby/derby/10.13.1.1/derby-10.13.1.1.jar).
4. Configure SQuirreL tool according to the [following manual](http://squirrel-sql.sourceforge.net/paper/GettingStartedusingtheSQuirreLSQLClient.pdf), configure JDBC connection to Derby. Connection options are the following:
    * URL: `jdbc:derby:d:/temp/banking-app-database;create=true`
    * user: `ba_user`
    * password `ba_pass`