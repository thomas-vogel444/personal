resource "google_compute_network" "vpc" {
  name = var.network_name
  auto_create_subnetworks = false
}

// ***************************************************************
// Subnets
resource "google_compute_subnetwork" "public_subnet" {
  name = "${var.network_name}-public-subnet"
  network = google_compute_network.vpc.name
  ip_cidr_range = var.public_cidr
}

resource "google_compute_subnetwork" "private_subnet" {
  name = "${var.network_name}-private-subnet"
  network = google_compute_network.vpc.name
  ip_cidr_range = var.private_cidr
}

resource "google_compute_subnetwork" "internal_subnet" {
  name = "${var.network_name}-internal-subnet"
  network = google_compute_network.vpc.name
  ip_cidr_range = var.internal_cidr
}

// ***************************************************************
// External IPs
resource "google_compute_address" "bastion_external_ip" {
  name = "bastion-external-ip"
}


// ***************************************************************
// NAT Gateway
resource "google_compute_router" "router" {
  name    = "tom-router"
  network = google_compute_network.vpc.name
}

resource "google_compute_router_nat" "nat" {
  name                               = "my-router-nat"
  router                             = google_compute_router.router.name
//  region                             = google_compute_router.router.region
  nat_ip_allocate_option             = "AUTO_ONLY"

  source_subnetwork_ip_ranges_to_nat = "LIST_OF_SUBNETWORKS"
  subnetwork {
    name                    = google_compute_subnetwork.private_subnet.name
    source_ip_ranges_to_nat = ["ALL_IP_RANGES"]
  }
}

// ***************************************************************
// Firewall rules
resource "google_compute_firewall" "allow_ssh_ingress" {
  name = "allow-ssh-ingress"
  network = google_compute_network.vpc.name

  allow {
    protocol = "tcp"
    ports = ["22"]
  }

  direction = "INGRESS"
  source_ranges = ["0.0.0.0/0"]
  source_tags = ["ssh-ingress"]
}

resource "google_compute_firewall" "allow_ssh_egress" {
  name = "allow-ssh-egress"
  network = google_compute_network.vpc.name

  allow {
    protocol = "tcp"
    ports = ["22"]
  }

  direction = "EGRESS"
  target_tags = ["ssh-egress"]
}