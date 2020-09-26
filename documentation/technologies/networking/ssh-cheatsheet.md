# SSH Cheatsheet

## Local Port Forwarding

Command line
```
ssh -L [bind_address]:local_port remote_ip:remote_port
```

Config
```
Host server1
    LocalForward [bind_address]:local_port remote_ip:remote_port
```

## Host jumping

Command line
```
ssh -A server1 ssh -A server2
```

Config
```
# Newer version
Host server2
    ProxyCommand ssh server1 -W %h:%p

# Older version
Host server2
    ProxyCommand ssh server1 nc -q0 %h %p
```

## Agent Forwarding

Command line
```
ssh -A server.com
```

Config
```
Host server
    AgentForward yes
```

## Remote Port Forwarding

Command line
```
ssh -R [bind_address]:local_port remote_ip:remote_port
```

Config
```
```

## Dynamic Port Forwarding


## Reusing Connections