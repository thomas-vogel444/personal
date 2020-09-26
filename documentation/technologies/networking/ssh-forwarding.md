# SSH Forwarding

From https://www.ssh.com/ssh/tunneling/example

From https://www.booleanworld.com/guide-ssh-port-forwarding-tunnelling/

Options:
- `-L`: local forwarding
- `-R`: remote forwarding
- `-N`: disable the shell

## Local Forwarding

Forwards a port from the client machine to the server machine.

- The SSH client listens for connections on a configured port 
- When it receives a connection, it tunnels the connection to an ssh server. 
- The server connects to a configurated destination port, possibly on a differnt machine than the SSH server

Uses:
- Tunneling sessions and file transfers through jump servers
- Connecting to a service on an internal network from the outside
- Connecting to a remote file share over the internet

Via the command line: configured using the -L option
```bash
ssh -L 80:intra.example.com:80 gw.example.com
```
    -> opens a connection to the `gw.example.com` jump server
    -> forwards any connection to port 80 on the local machine to port 80 on intra.example.com

Use a bind address to restrict access to local processes.
```bash
ssh -L 127.0.0.1:80:intra.example.com:80 gw.example.com
```

Via the configuration file: 
- LocalForward option 

## Remote Forwarding

Forwards traffic coming to a port on the server to your local computer.

Via the command line:
```bash
ssh -R 8080:localhost:80 public.example.com
```
    -> Binds port 8080 on public.example.com to your local machine on port 80

Via the configuration file:
- Allow: `GatewayPorts yes` (default)
- Deny: `GatewayPorts no`

## Server-Side Configuration

- `AllowTcpForwarding` must be enabled one the server to allow port forwarding. 
    - By default it is allowed.
    - values:
        - `yes`
        - `all`
        - `no`
        - `local`
        - `remote`
- `AllowStreamLocalForwarding`
    - used to forward Unix domain sockets
    - default is `yes`
    - same values as `AllowTcpForwarding`
- `GatewayPorts`
    - values:
        - `yes`
        - `no`
        - `clientspecified`

## Dynamic port forwarding

e.g. 
```bash
ssh -D 4000 user@example.com
```
    -> creates a SOCKS proxy at port 4000 on your local computer
    -> any traffic sent to this port is sent to its destination through the SSH server