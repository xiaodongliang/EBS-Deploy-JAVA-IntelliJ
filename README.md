# EBS-Deploy-JAVA-IntelliJ

[![IntelliJ Ultimate](https://img.shields.io/badge/IntelliJ%20Ultimate-2016.3.7-green.svg)](https://www.jetbrains.com/idea/)
[![JAVA SDK](https://img.shields.io/badge/JAVA%20SDK-1.8-yellow.svg)](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
[![Forge JAVA SDK](https://img.shields.io/badge/Forge%20JAVA%20SDK-1.0.1-orange.svg)](https://github.com/Autodesk-Forge/forge-api-java-client)
[![TOMCAT](https://img.shields.io/badge/tomcat-8.5.27-red.svg)](https://tomcat.apache.org/download-80.cgi)
[![License](http://img.shields.io/:license-mit-blue.svg)](http://opensource.org/licenses/MIT)

## Setup
1. For using this sample, you need an Autodesk developer credentials. Visit the [Forge Developer Portal](https://developer.autodesk.com), sign up for an account, then [create an app](https://developer.autodesk.com/myapps/create). Finally make a copy of client id and client secret. 
2. Please use other ways to translate the source model to the format for Forge Viewer in advance. Get the model base64 urn.
3. Download and build Forge library by Forge JAVA SDK. Copy all * .jar in the target>>lib folder to [libs](libs) folder of this sample. For convenience, these .jar are already included in the project. However please syncronize with Forge JAVA SDK if it has new commits.
4. Open the project of IntelliJ. Add any  libraries if they are missing.

## Local Test

1. Set enviroment variables. 

Mac OSX/Linux (Terminal). Note: to let IntelliJ refresh with the updated enviroment variables, perform 

   open -a "IntelliJ IDEA" to start IntelliJ

   
    export FORGE_CLIENT_ID=<<YOUR CLIENT ID FROM FORGE DEVELOPER PORTAL>>
    export FORGE_CLIENT_SECRET=<<YOUR FORGE CLIENT SECRET>>
    export FORGE_TEST_URN=<<YOUR FORGE TEST URN (with urn:)>> 
 
Windows (command line)

    set FORGE_CLIENT_ID=<<YOUR CLIENT ID FROM FORGE DEVELOPER PORTAL>>
    set FORGE_CLIENT_SECRET=<<YOUR FORGE CLIENT SECRET>>
    set FORGE_TEST_URN=<<YOUR FORGE TEST URN (with urn:)>> 

2. In IntelliJ, configure the runtime configuration. In this case, the project has been configured with Tomcar Server.

3. Run the project, it will open up a browser page with the model loaded in the page. http://localhost:7890

## Deploy by AWS

1. After running the project in the step above, make an zip of out>>artifacts>>ebs-deploy-java_war_exploded.
2. click the button below, and follow the steps in the [video]() to fill in the neccesary parameters of BeanStalk enviroment.

[![Deploy](https://s3.amazonaws.com/deploytomh/button-deploy-aws-mh.png)](https://console.aws.amazon.com/elasticbeanstalk/?region=us-west-2#/newApplication?applicationName=EBS-Deploy-JAVA-IntelliJ&solutionStackName=Tomcat) 

3. To deploy this project to AWS, be sure to set your environment variables in the console of BeanStalk.
- `FORGE_CLIENT_ID`
- `FORGE_CLIENT_SECRET`
- `FORGE_TEST_URN`

Note: in EBS Tomcat, the enviroment vaiables are stored on Tomcat, so use System.getProperty(), instead of System.getenv().

## License

This sample is licensed under the terms of the [MIT License](http://opensource.org/licenses/MIT). 
Please see the [LICENSE](LICENSE) file for full details.


## Written by 

[Xiaodong Liang](https://twitter.com/coldwood) <br />
Developer Advocate <br />
Autodesk Forge Partner Development

