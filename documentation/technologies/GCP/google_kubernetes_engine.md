# Google Kubernetes Engine

Basics
- Master location

Networking
- Public or private cluster?
    - Private:
        - Master IP range as /28 CIDR range
- Specify
    - Pod IP range as /14 CIDR range
    - Service IP range 
- Enable
    - Intra-node visibility
    - NodeLocal DNS Cache
    - HTTP Load Balancing
    - Master authorized Networks
    - Network policy

Automation
- Vertical pod autoscaling
- Node auto-provisioning

Node pool
- Node configuration
    - Image
    - Machine type
    - Boot disk
    - Enable preemptible nodes
    - Networking
        - Network tags

Security

Metadata

Features
