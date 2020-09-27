output "bastion_external_ip" {
  value = module.networking.bastion_external_ip
}

output "k8s_master_plane_endpoint" {
  value = module.kubernetes_cluster.master_internal_ip
}

output "kubectl_internal_ip" {
  value = module.kubernetes_cluster.kubectl_internal_ip
}

