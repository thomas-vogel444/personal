# SSL

## Key stores and Trust stores

- Truststore
    - used to store certificates from trusted CAs used to verify certificates presented by a Server in a SSL connection
- Keystore
    - used the store private keys and own identity certificates to present to other parties.

If implementing SSL on the server side
- you need to store your server certificates in a Keystore.
- this certificate will be presented to the client every time there is connection initiated.
- if you require the client to authenticate itself, then you also need a Truststore.

In Java, you specify the Keystore and Truststore by psecifying the following properties
- `-javax.net.ssl.keyStore`
- `-javax.net.ssl.trustStore`
The truststore is stored inside the file `JAVA_HOME/JRE/Security/cacerts`

Use the `keytool` command to view the certificates. 

PKCS12 is used to bundle private keys with X.509 certificates. This is a pre-requisite to adding client certificate to a keystore.

## Commands

### Importing a private key and certificate into a keystore
- Use openssl to generate PKCS12 database with the private key and certificate
```bash
openssl pkcs12 -export -in <my-server.cert> -inkey <my-server.key> -out <mykeystore.pkcs12> -name <my-alias>
```

-  Importing certificates into a Keystore
```bash
keytool -import -alias <my-cert-alias> 
```

### Import a CA certificate into a Truststore
- Importing CA certificates into a Truststore
```bash
keytool -import -alias <ca-alias> -trustcacerts -file <ca.pem> -keystore <my-truststore>
```
