project_name = "tom-infrastructure"
region = "europe-west2"
network_name = "toms-network"

public_subnet_cidr = "10.1.0.0/16"
private_subnet_cidr = "10.2.0.0/16"
internal_subnet_cidr = "10.3.0.0/16"

k8s_master_cidr = "10.4.0.240/28"
k8s_service_cidr = "10.5.0.0/16"
k8s_pod_cidr = "10.8.0.0/14"