# vibrations-back-end
Project for CS5707 - Software Engineering (Fall 2023)

## 12/14/23
* The AWS Cloud Account has been shut down.
* This means that the following SaaS services have been lost:
  * Image Hosting (S3)
  * Authentication/Logging in (Cognito)
  * User information/storage (Cognito/RDS SQL Database)
* Thank you!

## Instructions for Grader:
* The frontend UI here: https://github.com/VTCS5704-Team1/vibrations-front-end uses this backend for functionality
* If testing backend WITHOUT frontend, use Postman (https://www.postman.com/)
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

## Acceptance Testing:
* At the moment, the backend can handle the following use cases:
  * Creating an account -- Shown above with the Instructions for Matching
    * At the moment, there is no MFA: email validation nor phone number validation
      * Phone validation required Toll-Free Number registration in AWS, which would fall out of scope
      * Email validation: we just ran out of time
    * There is no account activation/deactivation.
      * Though, there is the ability to delete an account
    * The User does not need a Spotify account. They can either connect their account or just use the SpotifyAPI to select music
  * Generating Matches -- Also shown above
    * Only differences from the Use Case are some of the UI decisions and the fact that the matchmaking algorithm does not incorporate Genre
    * Two reasons why genre was removed from algorithm:
      * SpotifyAPI does not have any endpoints relating to genres
      * Genres can be very specific, which can potentially affect matches.
  * Third use case: Contacting the matched users is entirely unimplemented
    * There are messaging endpoints implemented in the backend and the database
    * However: there is no way connect users to make those endpoints accessible.
    * We ran out of time.


## Deployment Plans:
* This is already deployed here: (http://dev-vibrations-api-final-env.eba-wpisspwu.us-east-2.elasticbeanstalk.com/hello)
* Need to include more restrictive Security configurations
  * API should be made inaccessible to all outside traffic (only accessible to the frontend)
* The current deployment script works, but it will say that it fails. It requires some extra steps for deployment in AWS

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



