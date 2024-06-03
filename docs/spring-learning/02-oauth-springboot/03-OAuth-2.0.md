# OAuth 2.0

## What is OAuth 2.0?

- Open Authorization 2.0
- An open authorization framework , which enables applications to access user data and perform some operations on behalf of a user.
- OAuth is also considered as authorization delegation framework.

## Roles in OAuth

- **Resource owner (User):** Is an user who owns a resource.
- **Client:** An application which tries to access resource on user behalf.
- **Resource Server:** A server which provides user resource details. Resource owner uses a client to fetch resources from resource server. Eg: Microservices.
- **Authorization server:** It provides access tokens to client to access resource, on successful authorization with provided user credentials. eg: AWS cognito, Microsoft Identity platform, Okta, Keycloak, Spring Authorization server.

## OAuth 2 Client Types

A client is an application which will connect to authorization server and get access token to connect with Resource server, for fetching resources. To connect to resource server client needs a `client_id` to identify each client and a `client_secret` to authorize client.

Not all clients will be able to store `client_secret` safe. Hence the clients are classified as 2 types:
- **Confidential client:** Clients which can store `client_secret` safely. Eg: Java application running on a server.
- **Public Client:** Clients which cannot save `client_secret` safely. Eg: a single page application build with angular or react.

## OAuth access tokens

There are 2 types of access tokens:
- **Identifier:** Which is an alpha numeric string, which will be a unique key from a database where other details about user and its access can be referred.
- **Self-Contain Authorization Information:** This access token is an base64 encoded string which contains 3 sections headers, payload and signature. Payload section contains information regarding the scopes and claims.

## OpenID Connect (OIDC)

- This is an extra layer over authorization server which provides user related information as a token for authorized calls to Authorization server.
- An authorization server encapsulated with an identity layer is called as identity provider.
- Identity token will have general information by default like username, user full name.
	- In addition it can contain:
		- Standard Claims
		- Scopes
		- Address Claims

## OAuth Grant Type and Authorization Flow

### Grant Type

 A grant type is a way by which client can get access tokens.

- Authorization code
- Client Credentials
- PKCE Enhanced Authorization Code.
- Device Code

| Type of Application           | Grant Type used                                      |
| ----------------------------- | ---------------------------------------------------- |
| Server Side Web app           | Authorization code                                   |
| Server side script with no UI | Client Credentials                                   |
| Java Script Single Page App   | PKCE Enhanced Authorization code                     |
| Mobile / Native App           | Authorization Code, PKCE Enhanced Authorization code |
| Device                        | Device Code                                          |

**Refresh Access token :**
This is a special grant type, which is used to exchange a refresh token for an access token 

#### Authorization Code Flow

1)  Initially user will make a get call to auth url

```shell
curl --location 'http://{KEYCLOAK_SERVER_URL}/realms/{NAME_OF_REALM}/protocol/openid-connect/auth?client_id={CREATED_CLIENT}&response_type=code&scope=openid%20profile&redirect_uri=http%3A%2F%2Flocalhost%3A8085%2Fusers&state=sampleatate'
```

- Auth URL query parameters explained
	- `client_id` is the unique identifier created in your realm.
	- `response_type` should be constant value `code`
	- `scope` will differ based on the required information
		- In the above example as OpenID and profile information is required both are mentioned.
	- `redirect_uri` The url to which generated code should be sent
	- `state` is CSRF token which should be a unique value for each session.

2) A login page will be displayed, provide valid credentials and click submit.
3) It will redirect to the `redirect_uri` with a `code` as shown below

```shell
curl http://localhost:8085/users?state=sampleatate&session_state=5c518138-f4b6-4670-9d82-1181162a7d4f&iss=http%3A%2F%2Flocalhost%3A8443%2Frealms%2Falpha-dev&code=GENERATED_CODE
```
- The query parameter `code` can be used to get an access token
4) `code` can now be used to make a call to get `access_token`. A post call need to be made to the token url as shown below

```shell
curl --location 'http://localhost:8443/realms/alpha-dev/protocol/openid-connect/token' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'grant_type=authorization_code' \
--data-urlencode 'client_id=developers' \
--data-urlencode 'client_secret={CLIENT_SECRET_GENERATED}' \
--data-urlencode 'code={CODE_GENERATED}' \
--data-urlencode 'redirect_uri=http://localhost:8085/users' \
--data-urlencode 'scope=openid profile email'
```

A response with access_token will be provided
```json
{
    "access_token": "GENERATED_ACCESS_TOKEN",
    "expires_in": 300,
    "refresh_expires_in": 1785,
    "refresh_token": "GENERATED_REFRESH_TOKEN",
    "token_type": "Bearer",
    "id_token": "ID_TOKEN",
    "not-before-policy": 0,
    "session_state": "5c518138-f4b6-4670-9d82-1181162a7d4f",
    "scope": "openid profile email"
}
```