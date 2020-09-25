# Using OAuth 2.0 for Server to Server Applications

From https://developers.google.com/identity/protocols/oauth2/service-account#java

First create a service account

Second the application prepares to make authorised API calls by using the service account's credentials to request an access token from the OAuth 2.0 auth server

Finally the application uses the access token to call the Google APIs

## Preparing to make an authorized API call

```java
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.services.sqladmin.SQLAdminScopes;

GoogleCredential credential = 
    GoogleCredential.fromStream(new FileInputStream("MyProject-1234.json")).createScoped(Collections.singleton(SQLAdminScopes.SQLSERVICE_ADMIN));
```

## Calling Google APIs

Use the `GoogleCredential` object to call Google APIs

```java
SQLAdmin sqladmin =
    new SQLAdmin.Builder(httpTransport, JSON_FACTORY, credential).build();

SQLAdmin.Instances.List instances =
    sqladmin.instances().list("exciting-example-123").execute();
```