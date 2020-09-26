# SSH Config

Enabling X11 forwarding and agent forwarding
```
ForwardAgent yes
ForwardX11 yes
```

Port forwarding

Configuring Public Key Authentication
```
IdentityFile ~/.ssh/id_rsa
```

Certificate based authentication
```
CertificateFile
```

## Config Options
```
Host                -> restrict the next options to a host
HostName            -> real hostname to log into
DynamicForward
ForwardAgent
IdentityFile
LocalCommand
LocalForward [bing_address:]port host:port
ProxyCommand
RemoteForward [bing_address:]port host:port
```