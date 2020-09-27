provider "google" {
  project = var.project_name
  region = var.region
}

module "networking" {
  source = "../modules/gcp/networking"

  network_name = var.network_name
  public_cidr = var.public_subnet_cidr
  private_cidr = var.private_subnet_cidr
  internal_cidr = var.internal_subnet_cidr
}

module "kubernetes_cluster" {
  depends_on = [module.networking]
  source = "../modules/gcp/kubernetes_cluster"

  region = var.region
  network = var.network_name
  subnet = module.networking.private_subnet_name
  master_cidr = var.k8s_master_cidr
  pod_cidr = var.k8s_pod_cidr
  service_cidr = var.k8s_service_cidr
  network_tags = []
}