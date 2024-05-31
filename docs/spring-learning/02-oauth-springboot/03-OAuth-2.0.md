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