resource "google_container_cluster" "kubernetes_cluster" {
  name = "personal"

  location = var.region // For master nodes
  network = var.network
  subnetwork = var.subnet

  //  Networking
  //  networking_mode = "VPC_NATIVE"

  master_authorized_networks_config {}

  private_cluster_config {
    enable_private_endpoint = true
    enable_private_nodes = true
    master_ipv4_cidr_block = var.master_cidr
  }

  ip_allocation_policy {
    cluster_ipv4_cidr_block = var.pod_cidr
    services_ipv4_cidr_block = var.service_cidr
  }

  remove_default_node_pool = true
  initial_node_count = 1
}

resource "google_container_node_pool" "toms_node_pool" {
  name = "toms-node-pool"
  cluster = google_container_cluster.kubernetes_cluster.name
  location = var.region

  node_count = 1

  node_config {
    machine_type = "e2-medium"
    preemptible = true
    tags = var.network_tags
  }
}