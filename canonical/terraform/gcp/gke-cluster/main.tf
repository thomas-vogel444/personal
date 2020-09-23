provider "google" {
  project = var.project_name
  region = var.region
}

module "networking" {
  source = "../../modules/gcp/networking"

  network_name = var.network_name
  public_cidr = var.public_subnet_cidr
  private_cidr = var.private_subnet_cidr
  internal_cidr = var.internal_subnet_cidr
}

module "bastion_host" {
  depends_on = [module.networking]
  source = "../../modules/gcp/instances"

  instance_name = "bastion"
  machine_type = "e2-micro"
  instance_zone = "europe-west2-b"
  subnet_name = module.networking.public_subnet_name
  public_ip = module.networking.bastion_external_ip

  network_tags = ["ssh-ingress", "ssh-egress"]
}

module "kubectl_service_account" {
  source = "../../modules/gcp/iam"
  service_account_name = "kubectl"
  role = "roles/container.developer"
}

module "kubectl_instance" {
  depends_on = [module.networking, module.kubectl_service_account]
  source = "../../modules/gcp/instances"

  instance_name = "kubectl"
  machine_type = "e2-micro"
  instance_zone = "europe-west2-b"
  subnet_name = module.networking.private_subnet_name
  network_tags = ["ssh-ingress"]
  service_account_email = module.kubectl_service_account.email

  startup_script = "sudo apt-get update; sudo apt-get install kubectl"
}

module "kubernetes_cluster" {
  depends_on = [module.networking]
  source = "../../modules/gcp/gke"

  region = var.region
  network = var.network_name
  subnet = module.networking.private_subnet_name
  master_cidr = var.k8s_master_cidr
  pod_cidr = var.k8s_pod_cidr
  service_cidr = var.k8s_service_cidr
  network_tags = []
}