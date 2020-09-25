# Authenticating as a service account

## Finding credentials automaticallu

Case: running your application inside GCP. e.g. GCE, GKE, GAE, etc...

- Use the Google Cloud Client Libraries to interact with Google services
- they use Application Default Credentials (ADC) to retrieve the service account credentials automatically
- if `GOOGLE_APPLICATION_CREDENTIALS` is set, ADC uses the service account file that it points to.
- if not, ADC uses the default service account provided
- if neither, an error occurs

```java
static void authImplicit() {
  Storage storage = StorageOptions.getDefaultInstance().getService();
  Page<Bucket> buckets = storage.list();
}
```

## Passing credentials manually

Case: running your application outside GCP

- Create a service account and some service account keys which you will download as a json file
- Provide authentication credentials to your application code by setting the environment variable `GOOGLE_APPLICATION_CREDENTIALS`
- Run `export GOOGLE_APPLICATION_CREDENTIALS="[PATH_TO_CREDENTIALS_FILE]"`
- Code is the same as above