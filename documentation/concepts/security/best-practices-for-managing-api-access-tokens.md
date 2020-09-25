# Best practices for managing access tokens

From https://dzone.com/articles/security-best-practices-for-managing-api-access-to

2 choices:
- pass a static piece of information together with eth API call
- obtain that piece of information dynamically prior to invoking the API

API keys:
- Pros:
    - easy for the developer
- Cons:
    - less secure
    - no information about the user
    - no granularity of authorisation
    - no expiration unless revoked by the API provider

OAuth:
- Pros:
    - the client application is known
    - scopes can be defined to limit access to certain operations
    - Tokens have a limited lifetime

## Token management recommendations

- Beware of OAuth App credentials leaks
- Don't hardcode tokens in applications
- Treat tokens as you would passwords
- OAuth is not an authentication protocol: use OpenID Connect instead
- Beware of what you store in JWTs and who has access to them
- Validate JWTs Thoroughly
- Don't store tokens in local storage, use secure cookies
- always transport tokens via HTTPS and in the request body
- Use the dedicated browser view to request consent