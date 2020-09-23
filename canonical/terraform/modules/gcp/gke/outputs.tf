output "master_internal_ip" {
  value = google_container_cluster.kubernetes_cluster.private_cluster_config.0.private_endpoint
}