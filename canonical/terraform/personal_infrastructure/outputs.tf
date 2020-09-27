output "public_subnet_name" {
  value = module.networking.public_subnet_name
}

output "bastion" {
  value = {
    "internal_ip": module.bastion_host.internal_ip,
    "external_ip": module.bastion_host.external_ip
  }
}

output "kubectl" {
  value = {
    "internal_ip": module.kubectl_instance.internal_ip,
    "external_ip": module.kubectl_instance.external_ip
  }
}

output "master_plane_endpoint" {
  value = module.kubernetes_cluster.master_internal_ip
}