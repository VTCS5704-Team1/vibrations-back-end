# vibrations-back-end
Project for CS5707 - Software Engineering (Fall 2023)

## Instructions for Grader:
* The frontend UI here: https://github.com/VTCS5704-Team1/vibrations-front-end uses this backend for functionality
* If testing backend WITHOUT frontend, use the Postman (https://www.postman.com/)
* The user controllers specify endpoint functionality
* Right now, there is a bug with AWS Elastic Beanstalk where redeployment fails. Do not run the deployment script
* All endpoints require a Bearer Token except:
    * DOMAIN/api/images/upload
    * DOMAIN/api/users/signup
    * DOMAIN/api/users/register
    * DOMAIN/api/users/login
    * DOMAIN/hello --> Test GET endpoint
    * DOMAIN/hello/friend --> Test POST endpoint
* Can either access it via the Cloud deployment (TBD) or by running it locally (localhost:5000)

## Steps for Registering:
* POST to the DOMAIN/api/users/register
  * Will need to pass a JSON request body containing the following information
    * email: String
    * password: String
    * birthdate: String (yyyy-mm-dd)
    * firstName: String
    * lastName: String
    * phoneNumber: String
  * Make Sure that the JSON is formatted as:
    * "label": "value" (unless value is a number)
    * EX: "email": "example@example.com"
* Your account will be stored in our AWS Cognito

## Steps for Logging In:
* Access DOMAIN/api/users/login
  * Will need to a pass a JSON request body containing the email and password
  * You will receive an access token in the JSON response
    * When using Postman, save this access token and pass it as a Bearer Token to the other endpoints

## Steps for Creating a User Profile
* POST to the endpoint DOMAIN/api/users/registerUser
* Include following points in the JSON:
  * firstName: String
  * lastName: String
  * email: String
  * bio: String
  * gender: String
  * topArtists: String[]
  * topSongs: String[]
  * phoneNumber: Long
  * maxDistance: double
  * latitude: double
  * longitude: double
* Now that you've created a profile, you need to upload a PFP at a separate endpoint:
  * Send a POST request to DOMAIN/api/images/upload
  * Provide following POST body (Form-data, not JSON) 
    * pfp: File 
    * email: YOUR EMAIL
  * If you do not upload a PFP, you will not be able to retrieve/use your profile
* Note: for the values that are shared with users/register, in the frontend, these values are directly carried over from that endpoint
* When testing backend using Postman, you should match these values
* You now have a profile in the database
* You can see the data you submitted by calling a GET request to the following endpoint: DOMAIN/api/users/getUser?email={yourEmail}
  * Do not forget to include your email and bearer token

## Instructions for Matching
* Call a GET request to the following endpoint:
  * DOMAIN/api/users/match?email={email}
  * Again: Do not forget your email and bearer token
  * Note: Since the matchmaking algorithm uses a Python algorithm, depending on your choices, you may not receive any matches
    * Use this email: davidfc@vt.edu to see matches if your profile doesn't receive any
    * Matchmaking algorithm source code is here: https://github.com/VTCS5704-Team1/vibrations-matchmaking

## Unit Tests Instructions:
* Run command: 'mvn test'
* Runs test Suite that tests most of the User endpoints
  * Does not test the getUser and upload image endpoints
  * The way we upload pfps to the database and S3 is very novel, and it is difficult to test it in the MockMVC framework 
  * getUser requires the test user to have a PFP, which can't be uploaded
* All other endpoints are tested in the test suite


## Deployment Plans:
* This already deployed here: TBD
* Need to include more restrictive Security configurations
  * API should be made inaccessible to all outside traffic (only accessible to the frontend)
* The current deployment script works, but it will say that it fails. It requires some tweaking in the cloud. Please do not run it.

### Spring-Boot Application
* Project: Maven
* Language: Java (JDK 17)
* Spring-Boot Version: 3.1.5
* Packaging: Jar

### Project Metadata:
* Group: com.vibrations
* Artifact: vibrations-api
* Name: vibrations-api
* Description: API for Vibrations backend project
* Package Name: com.vibrations.vibrations-api



