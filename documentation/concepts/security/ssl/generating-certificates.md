# Generating Certificates

From https://www.makethenmakeinstall.com/2014/05/ssl-client-authentication-step-by-step/

## Generate a self-signed CA certificate. 

It acts as the trusted authority for both server and client certs.

```bash
openssl req -newkey rsa:4096 -keyform PEM -keyout ca.key -x509 -days 3650 -outform PEM -out ca.cer
```

## Generate your server SSL key and certificate

Generate a server key
```bash
openssl genrsa -out server.key 4096
```

Generate a CSR
```bash
openssl req -new -key server.key -out server.req -sha256
```

Sign the CSR to generate a certificate
```bash 
openssl x509 -req -in server.req -CA ca.cer -CAkey ca.key -set_serial 100 -extensions server -days 1460 -outform PEM -out server.cer -sha256
```

Clean up the CSR
```bash
rm server.req
```

## Install the server certificate in Apache

Copy the CA cert to a permanent place.
```bash
cp ca.cer /etc/ssl/certs/
```

Copy the server cert and private key to a permanent place
```bash
cp server.cer /etc/ssl/certs/server.crt
cp server.key /etc/ssl/private/server.key
```

Change the Apache configuration file to use the certificate and private key

## Generate a client SSL certificate

Generate a private key for the SSL client
```bash
openssl genrsa -out client.key 4096
```

Generate a CSR for the client
```bash 
openssl req -new -key client.key -out client.req -sha256
```

Sign the client certificate with the ca key.
```bash 
openssl x509 -req -in client.req -CA ca.cer -CAkey ca.key -set_serial 101 -extensions client -days 365 -outform PEM -out client.cer
```

Remove the CSR
```bash
rm client.req
```