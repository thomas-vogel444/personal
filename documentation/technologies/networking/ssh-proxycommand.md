# SSH Proxy Command

How to SSH onto a server via a jump server.

## Passing through a gateway
```
ssh -J jumphost.com server.com
```

## How to pass through a gateway using stdio forwarding

```
ssh -o ProxyCommand="ssh -W %h:%p jumphost.com" server.com
```

or if the user is different on the 2 servers
```
ssh -l tom \
    -o 'ProxyCommand ssh -l jerry %h nc some.server.com 22'
    -o 'HostKeyAlias some.server.com' \
    some.jumphost.com
```

SSH config:
```
Host webserver
        Hostname server.com
        ProxyCommand ssh jumphost.com -W %h:%p
 
Host mysftpserver
        HostName server.com
        HostKeyAlias server.com
        ProxyCommand ssh jumphost.com -W %h:%p 
```